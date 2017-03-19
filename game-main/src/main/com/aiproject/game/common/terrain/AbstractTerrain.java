package com.aiproject.game.common.terrain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by cliffycheng on 3/9/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractTerrain
{
    String name;
    int defBonus;
    int dodgeBonus;
    int movePenalty;
}
