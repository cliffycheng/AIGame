package com.aiproject.game.common.pieces;

import com.aiproject.game.common.enums.WeaponType;

import com.aiproject.game.common.weapons.Weapon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class of pieces in game
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Piece
{
    //stats
    private int health;
    private int defense;
    private int attack;
    private int speed;
    private int move;
    private int skill;

    //weapon used
    private List<WeaponType> weaponType = new ArrayList<>();
    private List<Weapon> weapons = new ArrayList<>();

}
