package com.aiproject.game.common.weapons;

import com.aiproject.game.common.enums.WeaponType;
import lombok.Data;

/**
 * Created by cliffycheng on 1/1/2017.
 */
@Data
public class IronSword extends Weapon{

    public IronSword()
    {
        super(WeaponType.SWORD, 3, new int[]{1});
    }
}
