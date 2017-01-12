package com.aiproject.game.common.pieces;

import com.aiproject.game.common.enums.WeaponType;
import com.aiproject.game.common.weapons.IronSword;
import lombok.Data;

/**
 * Created by Cliff on 7/10/2016.
 */
@Data
public class Swordmaster extends Piece
{
    public Swordmaster()
    {
        setAttack(2);
        setDefense(1);
        setHealth(20);
        setMove(6);
        setSpeed(10);
        setSkill(10);
        getWeaponType().add(WeaponType.SWORD);
        getWeapons().add(new IronSword());
    }
}
