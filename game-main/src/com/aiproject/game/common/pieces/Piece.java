package com.aiproject.game.common.pieces;

import com.aiproject.game.common.enums.WeaponType;
import lombok.Data;

/**
 * Base class of pieces in game
 */
@Data
public abstract class Piece
{
    //stats
    private int health;
    private int defense;
    private int attack;
    private int speed;
    private int move;
    private int skill;

    //weapon used
    private WeaponType weaponType;

}
