package com.aiproject.game.common.weapons;

import com.aiproject.game.common.enums.WeaponType;
import lombok.Data;


/**
 * Created by Cliff on 7/10/2016.
 */
@Data
public abstract class Weapon
{
    private WeaponType weaponType;

    private int attackValue;

    // number of squares away this weapon can attack.
    // Made an array because some weapons can attack multiple ranges
    private int[] range;


}
