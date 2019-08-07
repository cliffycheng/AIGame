package com.aiproject.game.common.board.exceptions;

/**
 * Created by cliffycheng on 3/18/2017.
 *
 * Exception when requested piece does not exist.
 */
public class PieceDoesNotExistException extends BoardStateException
{
    public PieceDoesNotExistException()
    {
        super();
    }

    public PieceDoesNotExistException(String message)
    {
        super(message);
    }
}
