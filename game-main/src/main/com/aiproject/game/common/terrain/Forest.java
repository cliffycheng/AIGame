package com.aiproject.game.common.terrain;

/**
 * Created by cliffycheng on 12/29/2017.
 *
 * Forest terrain that gives a slight defense and dodge bonus, but has one move
 * penalty
 */
public class Forest extends AbstractTerrain
{
    public Forest()
    {
        super ("FOREST", 1, 10, 1);
    }
}
