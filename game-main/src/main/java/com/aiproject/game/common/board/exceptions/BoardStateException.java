package com.aiproject.game.common.board.exceptions;

/**
 * Created by cliffycheng on 2/7/2017.
 *
 * Exception used to represent any issues regarding board state, such as putting in
 * pieces in the wrong place, places pieces can't go, etc
 */
public class BoardStateException extends Exception
{
    public BoardStateException()
    {
        super();
    }

    public BoardStateException(String message)
    {
        super(message);
    }
}
