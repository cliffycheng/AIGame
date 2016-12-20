package com.aiproject.game.common.board;

import com.aiproject.game.common.pieces.Piece;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Cliff on 7/10/2016.
 */
public class GameBoard
{
    private Piece[][] pieceBoard;

    private Map<Location, Piece> currentPieces;


    public GameBoard(int x, int y)
    {
        pieceBoard = new Piece[x][y];
        currentPieces = new HashMap<>();
    }

    public void placePiece(Piece piece, int x, int y)
    {
        if (pieceBoard[x][y] != null)
        {
            pieceBoard[x][y] = piece;
            currentPieces.put(new Location(x,y), piece);
        }
        else
        {
            throw new RuntimeException("Piece already exists at coordinates " + x + " " + y);
        }
    }

    public void movePiece(int x1, int y1, int x2, int y2)
    {
        if (pieceBoard[x1][y1] != null)
        {
            if (pieceBoard[x2][y2] == null)
            {
                pieceBoard[x2][y2] = pieceBoard[x1][y1];
                pieceBoard[x1][y1] = null;
            }
            else throw new RuntimeException("Tried to move a piece to coordinates " + x2 + " " + y2 + "that is occupied");
        }
        else throw new RuntimeException("Tried to move a piece at coordinates " + x1 + " " + y1 + "that does not exist");
    }

    public void removePiece(int x, int y)
    {
        if (pieceBoard[x][y] != null)
        {
            pieceBoard[x][y] = null;
        }
        else
        {
            throw new RuntimeException("Piece does not exist at coordinates " + x + " " + y);
        }
    }


    @AllArgsConstructor
    @Data
    public class Location
    {
        int x;
        int y;
    }
}
