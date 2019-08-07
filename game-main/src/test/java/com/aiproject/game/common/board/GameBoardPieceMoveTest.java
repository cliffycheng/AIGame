package com.aiproject.game.common.board;

import com.aiproject.game.common.board.exceptions.BoardStateException;
import com.aiproject.game.common.terrain.Forest;
import com.aiproject.game.common.pieces.Swordmaster;
import com.aiproject.game.pieces.MockPiece;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Set;

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

    /**
     *  _ _ _
     *  _ M _
     *  _ _ _
     *
     *  M = mock piece
     *  _ = plains terrain (no penalty)
     *
     *  getPossibleMoves should give (0, 1), (1, 0), (1, 1), (1, 2), (2, 1)
     * @throws BoardStateException
     */
    @Test
    public void getPossibleMovesTest_1Move() throws BoardStateException
    {
        String pieceName = "mock";
        MockPiece mock = new MockPiece();
        mock.setName(pieceName);
        mock.setMove(1);

        board.placePiece(mock, 1, 1);

        Set<Coordinate> possibleMoves = board.getPossibleMoves(pieceName);
        assertEquals(5, possibleMoves.size());

        assertTrue(possibleMoves.contains(new Coordinate(0,1)));
        assertTrue(possibleMoves.contains(new Coordinate(1,0)));
        assertTrue(possibleMoves.contains(new Coordinate(1,0)));
        assertTrue(possibleMoves.contains(new Coordinate(1,1)));
        assertTrue(possibleMoves.contains(new Coordinate(1,2)));
        assertTrue(possibleMoves.contains(new Coordinate(2,1)));

        assertFalse(possibleMoves.contains(new Coordinate(0,0)));
        assertFalse(possibleMoves.contains(new Coordinate(0,2)));
        assertFalse(possibleMoves.contains(new Coordinate(2,0)));
        assertFalse(possibleMoves.contains(new Coordinate(2,2)));

    }

    /**
     *  _ _ _
     *  _ _ _
     *  M _ _
     *
     *  M = mock piece
     *  _ = plains terrain (no penalty)
     *
     *  getPossibleMoves should give (1, 0, (2, 0), (2, 1)
     * @throws BoardStateException
     */
    @Test
    public void getPossibleMovesTest_1Move_Corner() throws BoardStateException
    {
        String pieceName = "mock";
        MockPiece mock = new MockPiece();
        mock.setName(pieceName);
        mock.setMove(1);

        board.placePiece(mock, 2, 0);

        Set<Coordinate> possibleMoves = board.getPossibleMoves(pieceName);
        assertEquals(3, possibleMoves.size());

        assertTrue(possibleMoves.contains(new Coordinate(1,0)));
        assertTrue(possibleMoves.contains(new Coordinate(2,0)));
        assertTrue(possibleMoves.contains(new Coordinate(2,1)));

        assertFalse(possibleMoves.contains(new Coordinate(0,0)));
        assertFalse(possibleMoves.contains(new Coordinate(0,1)));
        assertFalse(possibleMoves.contains(new Coordinate(0,2)));
        assertFalse(possibleMoves.contains(new Coordinate(1,1)));
        assertFalse(possibleMoves.contains(new Coordinate(1,2)));
        assertFalse(possibleMoves.contains(new Coordinate(2,2)));

    }

    /**
     *  _ _ _
     *  _ M _
     *  _ _ _
     *
     *  M = mock piece
     *  _ = plains terrain (no penalty)
     *
     *  Starting piece is on a forest
     *
     *  getPossibleMoves should give (0, 1), (1, 0), (1, 1), (1, 2), (2, 1).
     *  Starting on a forest should not incur a movement penalty
     * @throws BoardStateException
     */
    @Test
    public void getPossibleMovesTest_Forest_Starting() throws BoardStateException
    {
        String pieceName = "mock";
        MockPiece mock = new MockPiece();
        mock.setName(pieceName);
        mock.setMove(1);

        board.setTerrain(new Forest(), 1, 1);
        board.placePiece(mock, 1, 1);

        Set<Coordinate> possibleMoves = board.getPossibleMoves(pieceName);
        assertEquals(5, possibleMoves.size());

        assertTrue(possibleMoves.contains(new Coordinate(0,1)));
        assertTrue(possibleMoves.contains(new Coordinate(1,0)));
        assertTrue(possibleMoves.contains(new Coordinate(1,0)));
        assertTrue(possibleMoves.contains(new Coordinate(1,1)));
        assertTrue(possibleMoves.contains(new Coordinate(1,2)));
        assertTrue(possibleMoves.contains(new Coordinate(2,1)));

        assertFalse(possibleMoves.contains(new Coordinate(0,0)));
        assertFalse(possibleMoves.contains(new Coordinate(0,2)));
        assertFalse(possibleMoves.contains(new Coordinate(2,0)));
        assertFalse(possibleMoves.contains(new Coordinate(2,2)));

    }

    /**
     *  _ _ _
     *  _ M _
     *  _ _ _
     *
     *  M = mock piece
     *  _ = plains terrain (no penalty)
     *
     *  2 move spaces allowed, should not try to add positions off the board
     *  Should not try to report multiple instances of the same space, ie report position (1,1) twice
     *  (one for zero spaces moved and one for 2 spaces moved
     *
     *  getPossibleMoves should allow piece to move on the whole board
     * @throws BoardStateException
     */
    @Test
    public void getPossibleMovesTest_2Moves() throws BoardStateException
    {
        String pieceName = "mock";
        MockPiece mock = new MockPiece();
        mock.setName(pieceName);
        mock.setMove(2);

        board.placePiece(mock, 1, 1);

        Set<Coordinate> possibleMoves = board.getPossibleMoves(pieceName);
        assertEquals(9, possibleMoves.size());

        assertTrue(possibleMoves.contains(new Coordinate(0,1)));
        assertTrue(possibleMoves.contains(new Coordinate(1,0)));
        assertTrue(possibleMoves.contains(new Coordinate(1,0)));
        assertTrue(possibleMoves.contains(new Coordinate(1,1)));
        assertTrue(possibleMoves.contains(new Coordinate(1,2)));
        assertTrue(possibleMoves.contains(new Coordinate(2,1)));

        // The corners of the board that are unreachable in just one move
        assertTrue(possibleMoves.contains(new Coordinate(0,0)));
        assertTrue(possibleMoves.contains(new Coordinate(0,2)));
        assertTrue(possibleMoves.contains(new Coordinate(2,0)));
        assertTrue(possibleMoves.contains(new Coordinate(2,2)));
    }

    /**
     *  _ _ _
     *  _ _ _
     *  M _ _
     *
     *  M = mock piece
     *  _ = plains terrain (no penalty)
     *
     *  2 move spaces allowed, should not try to add positions off the board
     *  Should not try to report multiple instances of the same space, ie report position (1,1) twice
     *  (one for zero spaces moved and one for 2 spaces moved
     *
     * @throws BoardStateException
     */
    @Test
    public void getPossibleMovesTest_2Moves_Corner() throws BoardStateException
    {
        String pieceName = "mock";
        MockPiece mock = new MockPiece();
        mock.setName(pieceName);
        mock.setMove(2);

        board.placePiece(mock, 2, 0);

        Set<Coordinate> possibleMoves = board.getPossibleMoves(pieceName);
        assertEquals(6, possibleMoves.size());

        assertTrue(possibleMoves.contains(new Coordinate(0,0)));
        assertTrue(possibleMoves.contains(new Coordinate(1,0)));
        assertTrue(possibleMoves.contains(new Coordinate(1,1)));
        assertTrue(possibleMoves.contains(new Coordinate(2,0)));
        assertTrue(possibleMoves.contains(new Coordinate(2,1)));
        assertTrue(possibleMoves.contains(new Coordinate(2,2)));

        // Unreachable spaces
        assertFalse(possibleMoves.contains(new Coordinate(0,1)));
        assertFalse(possibleMoves.contains(new Coordinate(0,2)));
        assertFalse(possibleMoves.contains(new Coordinate(1,2)));
    }

    /**
     *  F F F
     *  F M F
     *  F F F
     *
     *  M = mock piece (on Plains terrain, no move penalty)
     *  F = plains terrain (1 move penalty)
     *
     *  1 move allowed. Piece should not be able to move because
     *  move penalty by the forest terrain should prevent any moves.
     *
     * @throws BoardStateException
     */
    @Test
    public void getPossibleMovesTest_Forest_1Move() throws BoardStateException
    {
        String pieceName = "mock";
        MockPiece mock = new MockPiece();
        mock.setName(pieceName);
        mock.setMove(1);

        // Set up terrain
        board.setTerrain(new Forest(), 0, 0);
        board.setTerrain(new Forest(), 0, 1);
        board.setTerrain(new Forest(), 0, 2);
        board.setTerrain(new Forest(), 1, 0);
        board.setTerrain(new Forest(), 1, 1);
        board.setTerrain(new Forest(), 1, 2);
        board.setTerrain(new Forest(), 2, 0);
        board.setTerrain(new Forest(), 2, 1);
        board.setTerrain(new Forest(), 2, 2);

        board.placePiece(mock, 1, 1);

        Set<Coordinate> possibleMoves = board.getPossibleMoves(pieceName);
        assertEquals(1, possibleMoves.size());

        // The only valid move should be to stay in place
        assertTrue(possibleMoves.contains(new Coordinate(1,1)));

        assertFalse(possibleMoves.contains(new Coordinate(0,0)));
        assertFalse(possibleMoves.contains(new Coordinate(0,1)));
        assertFalse(possibleMoves.contains(new Coordinate(0,2)));
        assertFalse(possibleMoves.contains(new Coordinate(1,0)));
        assertFalse(possibleMoves.contains(new Coordinate(1,2)));
        assertFalse(possibleMoves.contains(new Coordinate(2,0)));
        assertFalse(possibleMoves.contains(new Coordinate(2,1)));
        assertFalse(possibleMoves.contains(new Coordinate(2,2)));
    }

    /**
     *  F F F
     *  F M F
     *  F F F
     *
     *  M = mock piece (on Plains terrain, no move penalty)
     *  F = plains terrain (1 move penalty)
     *
     *  2 move spaces allowed. Piece should only be allowed to move onto the forests
     *  directly north, south, east, and west, or to stay on current spot
     *
     * @throws BoardStateException
     */
    @Test
    public void getPossibleMovesTest_Forest_All_2Moves() throws BoardStateException
    {
        String pieceName = "mock";
        MockPiece mock = new MockPiece();
        mock.setName(pieceName);
        mock.setMove(2);

        // Set up terrain
        board.setTerrain(new Forest(), 0, 0);
        board.setTerrain(new Forest(), 0, 1);
        board.setTerrain(new Forest(), 0, 2);
        board.setTerrain(new Forest(), 1, 0);
        board.setTerrain(new Forest(), 1, 1);
        board.setTerrain(new Forest(), 1, 2);
        board.setTerrain(new Forest(), 2, 0);
        board.setTerrain(new Forest(), 2, 1);
        board.setTerrain(new Forest(), 2, 2);

        board.placePiece(mock, 1, 1);

        Set<Coordinate> possibleMoves = board.getPossibleMoves(pieceName);
        assertEquals(5, possibleMoves.size());

        assertTrue(possibleMoves.contains(new Coordinate(0,1)));
        assertTrue(possibleMoves.contains(new Coordinate(1,0)));
        assertTrue(possibleMoves.contains(new Coordinate(1,1)));
        assertTrue(possibleMoves.contains(new Coordinate(1,2)));
        assertTrue(possibleMoves.contains(new Coordinate(2,1)));

        // The corners of the board that are unreachable in just one move
        assertFalse(possibleMoves.contains(new Coordinate(0,0)));
        assertFalse(possibleMoves.contains(new Coordinate(0,2)));
        assertFalse(possibleMoves.contains(new Coordinate(2,0)));
        assertFalse(possibleMoves.contains(new Coordinate(2,2)));
    }

    /**
     *  _ F _
     *  F M F
     *  _ F _
     *
     *  M = mock piece (on Plains terrain, no move penalty)
     *  F = plains terrain (1 move penalty)
     *
     *  2 move spaces allowed. Piece should only be allowed to move onto the forests
     *  directly north, south, east, and west, or to stay on current spot. It should
     *  not be able to move to the corners even though they are empty terrain, because
     *  all moves should have been used to get onto the forests
     *
     * @throws BoardStateException
     */
    @Test
    public void getPossibleMovesTest_Forest_Surround_2Moves() throws BoardStateException
    {
        String pieceName = "mock";
        MockPiece mock = new MockPiece();
        mock.setName(pieceName);
        mock.setMove(2);

        // Set up terrain
        board.setTerrain(new Forest(), 0, 1);
        board.setTerrain(new Forest(), 1, 0);
        board.setTerrain(new Forest(), 1, 1);
        board.setTerrain(new Forest(), 1, 2);
        board.setTerrain(new Forest(), 2, 1);

        board.placePiece(mock, 1, 1);

        Set<Coordinate> possibleMoves = board.getPossibleMoves(pieceName);
        assertEquals(5, possibleMoves.size());

        assertTrue(possibleMoves.contains(new Coordinate(0,1)));
        assertTrue(possibleMoves.contains(new Coordinate(1,0)));
        assertTrue(possibleMoves.contains(new Coordinate(1,1)));
        assertTrue(possibleMoves.contains(new Coordinate(1,2)));
        assertTrue(possibleMoves.contains(new Coordinate(2,1)));

        // The corners of the board that are unreachable in just one move
        assertFalse(possibleMoves.contains(new Coordinate(0,0)));
        assertFalse(possibleMoves.contains(new Coordinate(0,2)));
        assertFalse(possibleMoves.contains(new Coordinate(2,0)));
        assertFalse(possibleMoves.contains(new Coordinate(2,2)));
    }

    /**
     *  _ _ _
     *  _ M _
     *  _ _ _
     *
     *  M = mock piece
     *  _ = plains terrain (no penalty)
     *
     *  3 move spaces allowed, should not try to add positions off the board
     *  Should not try to report multiple instances of the same space, ie report position (1,1) twice
     *  (one for zero spaces moved and one for 2 spaces moved
     *
     *  getPossibleMoves should allow piece to move on the whole board
     * @throws BoardStateException
     */
    @Test
    public void getPossibleMovesTest_3Moves() throws BoardStateException
    {
        String pieceName = "mock";
        MockPiece mock = new MockPiece();
        mock.setName(pieceName);
        mock.setMove(3);

        board.placePiece(mock, 1, 1);

        Set<Coordinate> possibleMoves = board.getPossibleMoves(pieceName);
        assertEquals(9, possibleMoves.size());

        assertTrue(possibleMoves.contains(new Coordinate(0,1)));
        assertTrue(possibleMoves.contains(new Coordinate(1,0)));
        assertTrue(possibleMoves.contains(new Coordinate(1,0)));
        assertTrue(possibleMoves.contains(new Coordinate(1,1)));
        assertTrue(possibleMoves.contains(new Coordinate(1,2)));
        assertTrue(possibleMoves.contains(new Coordinate(2,1)));

        // The corners of the board that are unreachable in just one move
        assertTrue(possibleMoves.contains(new Coordinate(0,0)));
        assertTrue(possibleMoves.contains(new Coordinate(0,2)));
        assertTrue(possibleMoves.contains(new Coordinate(2,0)));
        assertTrue(possibleMoves.contains(new Coordinate(2,2)));
    }

    /**
     *  _ _ _
     *  _ _ _
     *  M _ _
     *
     *  M = mock piece
     *  _ = plains terrain (no penalty)
     *
     *  3 move spaces allowed, from the corner should not be able to reach the top right corner
     *  Should not try to add positions off the board
     *  Should not try to report multiple instances of the same space, ie report position (1,1) twice
     *  (one for zero spaces moved and one for 2 spaces moved
     *
     * @throws BoardStateException
     */
    @Test
    public void getPossibleMovesTest_3Moves_Corner() throws BoardStateException
    {
        String pieceName = "mock";
        MockPiece mock = new MockPiece();
        mock.setName(pieceName);
        mock.setMove(3);

        board.placePiece(mock, 2, 0);

        Set<Coordinate> possibleMoves = board.getPossibleMoves(pieceName);
        //assertEquals(8, possibleMoves.size());

        assertTrue(possibleMoves.contains(new Coordinate(0,0)));
        assertTrue(possibleMoves.contains(new Coordinate(0,1)));
        assertTrue(possibleMoves.contains(new Coordinate(1,0)));
        assertTrue(possibleMoves.contains(new Coordinate(1,1)));
        assertTrue(possibleMoves.contains(new Coordinate(1,2)));
        assertTrue(possibleMoves.contains(new Coordinate(2,0)));
        assertTrue(possibleMoves.contains(new Coordinate(2,1)));
        assertTrue(possibleMoves.contains(new Coordinate(2,2)));

        // Unreachable
        assertTrue(possibleMoves.contains(new Coordinate(0,2)));
    }
}
