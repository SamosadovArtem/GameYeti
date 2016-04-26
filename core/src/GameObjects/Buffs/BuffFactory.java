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
    private static float FRICTION_VALUE = 0.8f;
    private static float HEIGHT_SKY_COFF_VALUE = 1.1f;
    private static int COINS_VALUE = 1;
    private static float POWER_COFF_VALUE = 3.0f;
    private static float DIRECTION_COFF_VALUE = 0.04f;
    ///////////////////BUFFS MAX LVL///////////////////////
    private static int GRAV_LVL_MAX = 5;
    private static int JUMP_COUNT_LVL_MAX = 3;
    private static int JUMP_POWER_LVL_MAX = 5;
    private static int FRICTION_LVL_MAX = 6;
    private static int HEIGHT_SKY_COFF_LVL_MAX = 3;
    private static int COINS_LVL_MAX = 3;
    private static int POWER_COFF_LVL_MAX = 3;
    private static int DIRECTION_COFF_LVL_MAX = 3;
    ///////////////////BUFFS COST///////////////////////////
    private static int GRAV_COST = 1;
    private static int JUMP_COUNT_COST = 1;
    private static int JUMP_POWER_COST = 1;
    private static int FRICTION_COST = 1;
    private static int HEIGHT_SKY_COFF_COST = 1;
    private static int COINS_COST = 1;
    private static int POWER_COFF_COST = 1;
    private static int DIRECTION_COFF_COST = 1;
    
    public static GravityBuff getGravityBuff(MyTimer time, int level){
        return new GravityBuff(time, level, GRAV_LVL_MAX, GRAV_VALUE, GRAV_COST);
    }

    public static JumpCountBuff getJumpCountBuff(MyTimer time, int level){
        return new JumpCountBuff(time, level, JUMP_COUNT_LVL_MAX, JUMP_COUNT_VALUE, JUMP_COUNT_COST);
    }

    public static JumpPowerBuff getJumpPowerBuff(MyTimer time, int level){
        return new JumpPowerBuff(time, level, JUMP_POWER_LVL_MAX, JUMP_POWER_VALUE, JUMP_POWER_COST);
    }
    
    public static FrictionBuff getFrictionBuff(MyTimer time, int level){
        return new FrictionBuff(time, level, FRICTION_LVL_MAX, FRICTION_VALUE, FRICTION_COST);
    }
    
    public static HeightSkyCoffBuff getHeightSkyCoffBuff(MyTimer time, int level){
        return new HeightSkyCoffBuff(time, level, HEIGHT_SKY_COFF_LVL_MAX, HEIGHT_SKY_COFF_VALUE, HEIGHT_SKY_COFF_COST);
    }
    
    public static CoinsBuff getCoinsBuff(MyTimer time, int level){
        return new CoinsBuff(time, level, COINS_LVL_MAX, COINS_VALUE, COINS_COST);
    }
    
    public static DirectionCoffBuff getDirectionCoffBuff(MyTimer time, int level){
        return new DirectionCoffBuff(time, level, DIRECTION_COFF_LVL_MAX, DIRECTION_COFF_VALUE, DIRECTION_COFF_COST);
    }
    
    public static PowerCoffBuff getPowerCoffBuff(MyTimer time, int level){
        return new PowerCoffBuff(time, level, POWER_COFF_LVL_MAX, POWER_COFF_VALUE, POWER_COFF_COST);
    }
}
