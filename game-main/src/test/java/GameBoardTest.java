import com.aiproject.game.common.board.GameBoard;
import com.aiproject.game.common.pieces.Swordmaster;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cliffycheng on 2/4/2017.
 */
public class GameBoardTest
{
    GameBoard board;

    @Before
    public void initialize()
    {
        board = new GameBoard(3,3);
    }

    @Test
    public void placeTest()
    {
        Swordmaster s = new Swordmaster();
        board.placePiece(s, 0, 0);
        assertEquals("Pieces don't match at location 0 0", s, board.getPieceAtLoc(0, 0));

        Swordmaster s2 = new Swordmaster();
        board.placePiece(s2, 1, 1);
        assertEquals("Pieces don't match at location 1 1", s2, board.getPieceAtLoc(0, 0));
    }
}
