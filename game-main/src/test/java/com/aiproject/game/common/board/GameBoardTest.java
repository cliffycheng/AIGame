package com.aiproject.game.common.board;

import com.aiproject.game.common.board.exceptions.BoardStateException;
import com.aiproject.game.common.pieces.Swordmaster;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by cliffycheng on 2/4/2017.
 */
public class GameBoardTest
{
    GameBoard board;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Before
    public void initialize()
    {
        board = new GameBoard(3,3);
    }

    /**
     * GameBoard constructor tests
     */

    @Test
    public void createBadBoardXZero()
    {
        exception.expect(RuntimeException.class);
        board = new GameBoard(0, 1);
    }

    @Test
    public void createBadBoardXNegative()
    {
        exception.expect(RuntimeException.class);
        board = new GameBoard(-1, 1);
    }

    @Test
    public void createBadBoardYZero()
    {
        exception.expect(RuntimeException.class);
        board = new GameBoard(1, 0);
    }
    @Test
    public void createBadBoardYNegative()
    {
        exception.expect(RuntimeException.class);
        board = new GameBoard(1, -1);
    }
}
