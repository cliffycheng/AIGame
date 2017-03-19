package com.aiproject.game.common.board;

import com.aiproject.game.common.terrain.Plain;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cliffycheng on 3/9/2017.
 */
public class GameBoardTerrainTest
{
    int x = 3;
    int y = 3;

    GameBoard board;

    @Before
    public void initialize()
    {
        board = new GameBoard(x , y);
    }

    @Test
    public void VerifyDefaultTerrain()
    {
        for (int i = 0; i < x; i++)
        {
            for (int g = 0; g < y; g++)
            {
                assertTrue("Default board does not have Plain terrain at " + i + "," + g,
                        board.getTerrain(i,g) instanceof Plain);
            }
        }
    }

    @Test
    public void getTerrainOutOfBoundsNegativeX()
    {
        assertNull("Should get null when invalid bounds",
                board.getTerrain(-1, 0));
    }

    @Test
    public void getTerrainOutOfBoundsNegativeY()
    {
        assertNull("Should get null when invalid bounds",
                board.getTerrain(0, -1));
    }

    @Test
    public void getTerrainOutOfBoundsNegativeXAndY()
    {
        assertNull("Should get null when invalid bounds",
                board.getTerrain(-1, -1));
    }

    @Test
    public void getTerrainOutOfBoundsOverX()
    {
        assertNull("Should get null when invalid bounds",
                board.getTerrain(3, 0));

        assertNull("Should get null when invalid bounds",
                board.getTerrain(4, 0));
    }

    @Test
    public void getTerrainOutOfBoundsOverY()
    {
        assertNull("Should get null when invalid bounds",
                board.getTerrain(0, 3));

        assertNull("Should get null when invalid bounds",
                board.getTerrain(0, 4));
    }

    @Test
    public void getTerrainOutOfBoundsOverXAndY()
    {

        assertNull("Should get null when invalid bounds",
                board.getTerrain(3, 3));

        assertNull("Should get null when invalid bounds",
                board.getTerrain(4, 4));
    }
}
