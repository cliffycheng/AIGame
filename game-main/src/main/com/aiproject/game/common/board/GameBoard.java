package com.aiproject.game.common.board;

import com.aiproject.game.common.board.exceptions.BoardStateException;
import com.aiproject.game.common.pieces.Piece;
import com.aiproject.game.common.terrain.AbstractTerrain;
import com.aiproject.game.common.terrain.Plain;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Cliff on 7/10/2016.
 */
public class GameBoard
{
    private PieceMap currentPieces;
    private AbstractTerrain[][] terrain;


    public GameBoard(int x, int y)
    {
        currentPieces = new PieceMap(x, y);
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
     * Place a given piece into board.
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
        if (getPiece(x,y) == null)
        {
            currentPieces.put(piece, x, y);
        }
        else
        {
            throw new BoardStateException("Piece already exists at coordinates " + x + " " + y);
        }
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
            }
            else throw new BoardStateException("Tried to move a piece to coordinates " + x2 + " " + y2 + "that is occupied");
        }
        else throw new BoardStateException("Tried to move a piece at coordinates " + x1 + " " + y1 + "that does not exist");
    }

    public void removePiece(int x, int y) throws BoardStateException
    {
        if(currentPieces.get(x, y) != null)
        {
            currentPieces.remove(x,y);
        }
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
