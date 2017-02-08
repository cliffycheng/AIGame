package com.aiproject.game.common.board;

import com.aiproject.game.common.board.exceptions.BoardStateException;
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
    private PieceMap currentPieces;


    public GameBoard(int x, int y)
    {
        currentPieces = new PieceMap(x, y);
    }

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

    public Piece getPiece(int x, int y)
    {
        return currentPieces.get(x, y);
    }

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
}
