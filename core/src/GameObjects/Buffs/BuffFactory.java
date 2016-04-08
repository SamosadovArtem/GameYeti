package GameObjects.Buffs;

import Enums.BuffType;
import Helper.MyTimer;

/**
 * Created by broff on 07.04.2016.
 */
public class BuffFactory {

    ///////////////////BUFFS VALUES////////////////////////
    private static float GRAV_VALUE = -10.0f;
    private static int JUMP_COUNT_VALUE = 3;
    private static int JUMP_POWER_VALUE = 5;
    ///////////////////BUFFS MAX LVL///////////////////////
    private static int GRAV_LVL_MAX = 5;
    private static int JUMP_COUNT_LVL_MAX = 3;
    private static int JUMP_POWER_LVL_MAX = 5;
    ///////////////////BUFFS COST///////////////////////////
    private static int GRAV_COST = 300;
    private static int JUMP_COUNT_COST = 500;
    private static int JUMP_POWER_COST = 300;
    
    public static GravityBuff getGravityBuff(MyTimer time, int level){
        return new GravityBuff(time, level, GRAV_LVL_MAX, GRAV_VALUE, GRAV_COST);
    }

    public static JumpCountBuff getJumpCountBuff(MyTimer time, int level){
        return new JumpCountBuff(time, level, JUMP_COUNT_VALUE);
    }

    public static JumpPowerBuff getJumpPowerBuff(MyTimer time, int level){
        return new JumpPowerBuff(time, level, JUMP_POWER_VALUE);
    }
}
