package com.aiproject.game.common.board;

import com.aiproject.game.common.board.exceptions.BoardStateException;
import com.aiproject.game.common.board.exceptions.PieceDoesNotExistException;
import com.aiproject.game.common.pieces.Piece;
import com.aiproject.game.common.terrain.AbstractTerrain;
import com.aiproject.game.common.terrain.Plain;

import java.util.*;


/**
 * Created by Cliff on 7/10/2016.
 */
public class GameBoard
{
    private int xLimit;
    private int yLimit;

    private PieceMap currentPieces;
    private Map<String, Coordinate> pieceToLocationCache;

    private AbstractTerrain[][] terrain;


    public GameBoard(int x, int y)
    {
        xLimit = x;
        yLimit = y;

        currentPieces = new PieceMap(x, y);
        pieceToLocationCache = new HashMap<String, Coordinate>();
        terrain = new AbstractTerrain[x][y];

        // fill terrain with default Plain terrain
        for (int i = 0; i < x; i++)
        {
            for (int g = 0; g < y; g++)
            {
                terrain[i][g] = new Plain();
            }
        }
    }

    /***
     * PIECE METHODS
     */

    /**
     * Place a given piece into board. Adds piece to the piece-to-location cache to do reverse-lookup
     * from piece name to location.
     *
     * Throws BoardStateException if attempting to place piece outside of
     * board or placing piece on top of another.
     *
     * @param piece
     * @param x
     * @param y
     * @throws BoardStateException if attempting to place piece outside of board or placing piece
     *          on top of another.
     */
    public void placePiece(Piece piece, int x, int y) throws BoardStateException
    {
        if (pieceToLocationCache.containsKey(piece.getName()))
        {
            throw new BoardStateException("Piece with name " + piece.getName() + "already exists");
        }

        if (getPiece(x,y) != null)
        {
            throw new BoardStateException("Piece already exists at coordinates " + x + " " + y);
        }

        currentPieces.put(piece, x, y);
        pieceToLocationCache.put(piece.getName(), new Coordinate(x,y));
    }

    /**
     * Returns the piece at the given (x,y) coordinates. Returns null
     * if no piece exists. Returns null if given bad coordinates
     * @param x
     * @param y
     * @return the piece at position (x,y), null if position is empty or
     *         position given is not valid
     */
    public Piece getPiece(int x, int y)
    {
        return currentPieces.get(x, y);
    }

    /**
     * Returns the piece with the name given. Returns null if no piece
     * with that name exists.
     * @param name name of piece to get information for
     * @return the piece with the given name, or null if piece does not exist
     */
    public Piece getPiece(String name)
    {
        if (pieceToLocationCache.containsKey(name))
        {
            return currentPieces.get(pieceToLocationCache.get(name));
        }

        return null;
    }

    /**
     * Attempts to move the piece at coordinates (x1, y1) to position (x2, y2).
     *
     * Will fail if :
     * 1 ) Either set of coordinates is invalid
     * 2 ) There's no piece at coordinate (x1, y1)
     * 3 ) There is a piece at coordinate (x2, y2)
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @throws BoardStateException
     */
    public void movePiece(int x1, int y1, int x2, int y2) throws BoardStateException
    {
        if (currentPieces.get(x1,y1) != null)
        {
            if (currentPieces.get(x2, y2) == null)
            {
                currentPieces.movePiece(x1, y1, x2, y2);
                updatePieceLocation(currentPieces.get(x2, y2).getName(), x2, y2);
            }
            else throw new BoardStateException("Tried to move a piece to coordinates " + x2 + " " + y2 + "that is occupied");
        }
        else throw new BoardStateException("Tried to move a piece at coordinates " + x1 + " " + y1 + "that does not exist");
    }

    public Set<Coordinate> getPossibleMoves(int x, int y) throws BoardStateException
    {
        if (currentPieces.get(x, y) != null)
        {
            Set<Coordinate> possibleMoves = new HashSet<>();
            Coordinate coordinate = new Coordinate(x, y);
            Piece piece = currentPieces.get(coordinate);

            int moves = piece.getMove();
            getPossibleMovesHelper(possibleMoves, moves, coordinate);

            return possibleMoves;
        }
        else
        {
            throw new PieceDoesNotExistException("No piece at coordinate (" + x + ", " + y + ")");
        }
    }

    public Set<Coordinate> getPossibleMoves(String pieceName) throws BoardStateException
    {

        if (pieceToLocationCache.containsKey(pieceName))
        {
            Set<Coordinate> possibleMoves = new HashSet<>();
            Coordinate coordinate = pieceToLocationCache.get(pieceName);
            Piece piece = currentPieces.get(coordinate);

            int moves = piece.getMove();
            getPossibleMovesHelper(possibleMoves, moves, coordinate);

            return possibleMoves;
        }
        else
        {
            throw new PieceDoesNotExistException("No piece exists with name " + pieceName);
        }
    }

    private void getPossibleMovesHelper(Set<Coordinate> possibleMoves,
                                        int numMoves,
                                        Coordinate currentCoor)
    {
        possibleMoves.add(currentCoor);
        if (numMoves <= 0 )
        {
            return;
        }
        else
        {
            attemptMove(possibleMoves, numMoves - 1, currentCoor.up());
            attemptMove(possibleMoves, numMoves - 1, currentCoor.down());
            attemptMove(possibleMoves, numMoves - 1, currentCoor.left());
            attemptMove(possibleMoves, numMoves - 1, currentCoor.right());
        }
    }

    private void attemptMove(Set<Coordinate> possibleMoves,
                             int numMoves,
                             Coordinate futureCoor)
    {
        // If we know this coordinate has been visited before, then don't bother
        if (possibleMoves.contains(futureCoor))
        {
            return;
        }
        else
        {
            // Check terrain
            AbstractTerrain t = getTerrain(futureCoor.getX(), futureCoor.getY());
            // Does terrain have movement penalty?
            int terrainPenalty = t.getMovePenalty();

            if (numMoves - terrainPenalty >= 0)
            {
                possibleMoves.add(futureCoor);
                getPossibleMovesHelper(possibleMoves, numMoves - terrainPenalty, futureCoor);
            }
        }
    }

    /**
     * Attempts to move the piece with the given name to coordinate (x,y)
     * Does validation to make sure the given piece is actually able to move to
     * the location, based on terrain, piece's move stat, etc
     *
     * If piece moves successfully, returns true.
     *
     * If piece with this name does not exist, throws PieceDoesNotExistException.
     * If piece is not able to move to that location, returns false.
     *
     * @param name Name of piece to move
     * @param x
     * @param y
     * @return true if move was successful, false if piece was not able to move to given
     *         coordinates
     */
    public boolean movePieceWithValidation(String name, int x, int y) throws BoardStateException
    {
        if (pieceToLocationCache.containsKey(name))
        {
            return true;
        }
        else
        {
            throw new PieceDoesNotExistException("No piece exists with name " + name);
        }
    }

    public void removePiece(int x, int y) throws BoardStateException
    {
        if(currentPieces.get(x, y) != null)
        {
            Piece piece = currentPieces.get(x, y);
            pieceToLocationCache.remove(piece.getName());
            currentPieces.remove(x,y);
        }
    }

    private void updatePieceLocation(String name, int x, int y)
    {
        if (pieceToLocationCache.get(name) != null)
        {
            Coordinate coor = pieceToLocationCache.get(name);
            coor.setX(x);
            coor.setY(y);
        }
    }

    /***
     * BOARD UTILITIES
     */
    public boolean isValidCoordinate(Coordinate coor)
    {
        return coor.getX() >= 0 && coor.getX() < xLimit && coor.getY() >= 0 && coor.getY() < yLimit;
    }

    /***
     * TERRAIN METHODS
     */
    public AbstractTerrain getTerrain(int x, int y)
    {
        if (x < 0 || x >= terrain.length || y < 0 || y >= terrain[0].length)
        {
            return null;
        }

        return terrain[x][y];
    }

}
