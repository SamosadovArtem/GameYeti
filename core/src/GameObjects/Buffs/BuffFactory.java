package GameObjects.Buffs;

import Enums.BuffType;
import Helper.MyTimer;

/**
 * Created by broff on 07.04.2016.
 */
public class BuffFactory {

    private static float GRAV_VALUE = -10.0f;
    private static int JUMP_COUNT_VALUE = 3;
    private static int JUMP_POWER_VALUE = 5;

    public static GravityBuff getGravityBuff(MyTimer time, int level){
        return new GravityBuff(time, level, GRAV_VALUE);
    }

    public static JumpCountBuff getJumpCountBuff(MyTimer time, int level){
        return new JumpCountBuff(time, level, JUMP_COUNT_VALUE);
    }

    public static JumpPowerBuff getJumpPowerBuff(MyTimer time, int level){
        return new JumpPowerBuff(time, level, JUMP_POWER_VALUE);
    }
}
