package com.aiproject.game.common.board;

import com.aiproject.game.common.board.exceptions.BoardStateException;
import com.aiproject.game.common.pieces.Swordmaster;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

/**
 * Created by cliffycheng on 3/1/2017.
 */
public class GameBoardPieceMoveTest
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
     * GameBoard.movePiece tests
     */

    @Test
    public void movePieceTest() throws BoardStateException
    {
        Swordmaster s1 = new Swordmaster("s1");
        Swordmaster s2 = new Swordmaster("s2");
        Swordmaster s3 = new Swordmaster("s3");
        board.placePiece(s1, 0, 0);
        board.placePiece(s2, 0, 1);
        board.placePiece(s3, 2,2);

        board.movePiece(0,0, 1,1);
        assertEquals("s1 should be at location 1 1", s1, board.getPiece(1, 1));
        assertEquals("s1 should no longer be at location 0 0", null, board.getPiece(0, 0));

        board.movePiece(0,1, 0,2);
        assertEquals("s2 should be at location 0 2", s2, board.getPiece(0, 2));
        assertEquals("s2 should no longer be at location 0 1", null, board.getPiece(0, 1));

        board.movePiece(2,2, 2,1);
        assertEquals("s3 should be at location 2 1", s3, board.getPiece(2, 1));
        assertEquals("s3 should no longer be at location 2 2", null, board.getPiece(2, 2));

    }

    @Test
    public void movePieceToAnotherPieceOriginalLocation() throws BoardStateException
    {
        Swordmaster s1 = new Swordmaster();
        Swordmaster s2 = new Swordmaster();
        board.placePiece(s1, 0, 0);
        board.placePiece(s2, 0, 1);

        board.movePiece(0,0, 1,1);
        assertEquals("s1 should be at location 1 1", s1, board.getPiece(1, 1));
        assertEquals("s1 should no longer be at location 0 0", null, board.getPiece(0, 0));

        board.movePiece(0,1, 0,0);
        assertEquals("s2 should be at location 0 0", s2, board.getPiece(0, 0));
        assertEquals("s2 should no longer be at location 0 1", null, board.getPiece(0, 1));

        //piece S1 should still exist
        assertEquals("s1 should still be at location 1 1", s1, board.getPiece(1, 1));
        assertEquals("s1 should no longer be at location 1 1", s2, board.getPiece(0, 0));
    }

    @Test
    public void movePieceOverPiece() throws BoardStateException
    {
        Swordmaster s1 = new Swordmaster();
        Swordmaster s2 = new Swordmaster();
        board.placePiece(s1, 0, 0);
        board.placePiece(s2, 0, 1);

        exception.expect(BoardStateException.class);
        board.movePiece(0,0,0,1);
    }

    @Test
    public void movePieceInPlace() throws BoardStateException
    {
        Swordmaster s1 = new Swordmaster();
        Swordmaster s2 = new Swordmaster();
        Swordmaster s3 = new Swordmaster();
        board.placePiece(s1, 0, 0);
        board.placePiece(s2, 0, 1);
        board.placePiece(s3, 1,1);
    }

    @Test
    public void moveNonexistentPiece() throws BoardStateException
    {
        exception.expect(BoardStateException.class);
        board.movePiece(0,0,0,0);
    }

    @Test
    public void movePieceOutOfBoundsNegativeX() throws BoardStateException
    {
        Swordmaster s = new Swordmaster();
        board.placePiece(s, 0, 0);

        exception.expect(BoardStateException.class);
        board.movePiece(0,0, -1,0);
    }

    @Test
    public void movePieceOutOfBoundsNegativeY() throws BoardStateException
    {
        Swordmaster s = new Swordmaster();
        board.placePiece(s, 0, 0);

        exception.expect(BoardStateException.class);
        board.movePiece(0,0, 0,-1);
    }

    @Test
    public void movePieceOutOfBoundsNegativeXAndY() throws BoardStateException
    {
        Swordmaster s = new Swordmaster();
        board.placePiece(s, 0, 0);

        exception.expect(BoardStateException.class);
        board.movePiece(0,0, -1,-1);
    }

    @Test
    public void movePieceOutOfBoundsOverX() throws BoardStateException
    {
        Swordmaster s = new Swordmaster();
        board.placePiece(s, 0, 0);

        exception.expect(BoardStateException.class);
        board.movePiece(0,0, 4,0);
    }
    @Test

    public void movePieceOutOfBoundsOverY() throws BoardStateException
    {
        Swordmaster s = new Swordmaster();
        board.placePiece(s, 0, 0);

        exception.expect(BoardStateException.class);
        board.movePiece(0,0, 0,4);
    }

    @Test
    public void movePieceOutOfBoundsOverXAndY() throws BoardStateException
    {
        Swordmaster s = new Swordmaster();
        board.placePiece(s, 0, 0);

        exception.expect(BoardStateException.class);
        board.movePiece(0,0, 4,4);
    }
}
