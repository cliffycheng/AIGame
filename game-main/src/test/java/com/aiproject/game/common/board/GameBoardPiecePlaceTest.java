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
public class GameBoardPiecePlaceTest
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
     * GameBoard.placePiece tests
     */

    @Test
    public void placeTest() throws BoardStateException
    {
        Swordmaster s = new Swordmaster();
        board.placePiece(s, 0, 0);
        assertEquals("Pieces don't match at location 0 0", s, board.getPiece(0, 0));

        Swordmaster s2 = new Swordmaster();
        board.placePiece(s2, 1, 1);
        assertEquals("Pieces don't match at location 1 1", s2, board.getPiece(1, 1));

        Swordmaster s3 = new Swordmaster();
        board.placePiece(s3, 2, 2);
        assertEquals("Pieces don't match at location 2 2", s3, board.getPiece(2, 2));

    }

    @Test
    public void placePieceOverPiece() throws BoardStateException
    {

        Swordmaster s2 = new Swordmaster();
        board.placePiece(s2, 1, 1);

        exception.expect(BoardStateException.class);
        Swordmaster s3 = new Swordmaster();
        board.placePiece(s3, 1, 1);
    }

    @Test
    public void placePieceOutOfBoundsNegativeX() throws BoardStateException
    {
        Swordmaster s = new Swordmaster();

        exception.expect(BoardStateException.class);
        board.placePiece(s, -1, 0);
    }

    @Test
    public void placePieceOutOfBoundsNegativeY() throws BoardStateException
    {
        Swordmaster s = new Swordmaster();

        exception.expect(BoardStateException.class);
        board.placePiece(s, 0, -1);
    }

    @Test
    public void placePieceOutOfBoundsNegativeXAndY() throws BoardStateException
    {
        Swordmaster s = new Swordmaster();

        exception.expect(BoardStateException.class);
        board.placePiece(s, -1, -1);
    }

    @Test
    public void placePieceOutOfBoundsOverX() throws BoardStateException
    {
        Swordmaster s = new Swordmaster();

        exception.expect(BoardStateException.class);
        board.placePiece(s, 3, 0);
    }

    @Test
    public void placePieceOutOfBoundsOverY() throws BoardStateException
    {
        Swordmaster s = new Swordmaster();

        exception.expect(BoardStateException.class);
        board.placePiece(s, 0, 3);
    }

    @Test
    public void placePieceOutOfBoundsOverXAndY() throws BoardStateException
    {
        Swordmaster s = new Swordmaster();

        exception.expect(BoardStateException.class);
        board.placePiece(s, 3, 3);
    }
}
