package com.aiproject.game.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cliff on 7/9/2016.
 */
public enum WeaponType {
    SWORD,
    AXE,
    LANCE,
    ANIMA_MAGIC,
    DARK_MAGIC,
    LIGHT_MAGIC,
    BOW;

    public static Map<WeaponType, WeaponType> weaknesses = new HashMap<>();

    public static WeaponType getWeakness(WeaponType weapon)
    {
        if (weapon == SWORD)
        {
            return LANCE;
        }
        else if (weapon == LANCE)
        {
            return AXE;
        }
        else if (weapon == AXE)
        {
            return SWORD;
        }
        else if (weapon == DARK_MAGIC)
        {
            return LIGHT_MAGIC;
        }
        else if (weapon == LIGHT_MAGIC)
        {
            return ANIMA_MAGIC;
        }
        else if (weapon == ANIMA_MAGIC)
        {
            return DARK_MAGIC;
        }
        else
        {
            return null;
        }

    }
}
