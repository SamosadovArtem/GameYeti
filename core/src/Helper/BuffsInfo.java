package Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.LinkedList;
import java.util.List;

import GameObjects.Buffs.Buff;
import GameObjects.Buffs.BuffFactory;
import GameObjects.Buffs.CoinsBuff;
import GameObjects.Buffs.DirectionCoffBuff;
import GameObjects.Buffs.FrictionBuff;
import GameObjects.Buffs.GravityBuff;
import GameObjects.Buffs.HeightSkyCoffBuff;
import GameObjects.Buffs.JumpCountBuff;
import GameObjects.Buffs.JumpPowerBuff;
import GameObjects.Buffs.PowerCoffBuff;

/**
 * Created by broff on 07.04.2016.
 */
public class BuffsInfo {
    private static Preferences prefs;

    private static String gravTag = "gravityBuff";
    private static String jumpCountTag = "jumpCountBuff";
    private static String jumpPowerTag = "jumpPowerBuff";
    private static String frictionTag = "jumpPowerBuff";
    private static String heightSkyTag = "heightSkyBuff";
    private static String coinsTag = "coinsBuff";
    private static String powerCoffTag = "powerCoffBuff";
    private static String directionCoffTag = "directionCoffBuff";

    public static void load(){
        prefs = Gdx.app.getPreferences("YetiGameBuffs");

        if (!prefs.contains(gravTag)) {
            prefs.putString(gravTag, "0,0");
            prefs.flush();
        }

        if (!prefs.contains(jumpCountTag)) {
            prefs.putString(jumpCountTag, "0,0");
            prefs.flush();
        }

        if (!prefs.contains(jumpPowerTag)) {
            prefs.putString(jumpPowerTag, "0,0");
            prefs.flush();
        }
        
        if (!prefs.contains(frictionTag)) {
            prefs.putString(frictionTag, "0,0");
            prefs.flush();
        }
        
        if (!prefs.contains(heightSkyTag)) {
            prefs.putString(heightSkyTag, "0,0");
            prefs.flush();
        }
        
        if (!prefs.contains(coinsTag)) {
            prefs.putString(coinsTag, "0,0");
            prefs.flush();
        }
        
        if (!prefs.contains(powerCoffTag)) {
            prefs.putString(powerCoffTag, "0,0");
            prefs.flush();
        }
        
        if (!prefs.contains(directionCoffTag)) {
            prefs.putString(directionCoffTag, "0,0");
            prefs.flush();
        }
    }

    public static List<Buff> getBuffs(){
        List<Buff> buffs = new LinkedList<Buff>();
        buffs.add(getGravityBuff());
        buffs.add(getJumpCountBuff());
        buffs.add(getJumpPowerBuff());
        buffs.add(getFrictionBuff());
        buffs.add(getHeightSkyCoffBuff());
        buffs.add(getCoinsBuff());
        buffs.add(getDirectionCoffBuff());
        buffs.add(getPowerCoffBuff());
       return buffs;
    }

    public static DirectionCoffBuff getDirectionCoffBuff(){
        int lvl = getLevel(directionCoffTag);
        long finish = getTime(directionCoffTag);
        return BuffFactory.getDirectionCoffBuff(new MyTimer(finish), lvl);
    }

    public static void saveDirectionCoffBuff(Buff gravity){
        prefs.putString(directionCoffTag, gravity.getSaveData());
        prefs.flush();
    }
    
    public static PowerCoffBuff getPowerCoffBuff(){
        int lvl = getLevel(powerCoffTag);
        long finish = getTime(powerCoffTag);
        return BuffFactory.getPowerCoffBuff(new MyTimer(finish), lvl);
    }

    public static void savePowerCoffBuff(Buff gravity){
        prefs.putString(powerCoffTag, gravity.getSaveData());
        prefs.flush();
    }
    
    public static CoinsBuff getCoinsBuff(){
        int lvl = getLevel(coinsTag);
        long finish = getTime(coinsTag);
        return BuffFactory.getCoinsBuff(new MyTimer(finish), lvl);
    }

    public static void saveCoinsBuff(Buff gravity){
        prefs.putString(coinsTag, gravity.getSaveData());
        prefs.flush();
    }
    
    public static HeightSkyCoffBuff getHeightSkyCoffBuff(){
        int lvl = getLevel(heightSkyTag);
        long finish = getTime(heightSkyTag);
        return BuffFactory.getHeightSkyCoffBuff(new MyTimer(finish), lvl);
    }

    public static void saveHeightSkyCoffBuff(Buff gravity){
        prefs.putString(heightSkyTag, gravity.getSaveData());
        prefs.flush();
    }
    
    public static FrictionBuff getFrictionBuff(){
        int lvl = getLevel(frictionTag);
        long finish = getTime(frictionTag);
        return BuffFactory.getFrictionBuff(new MyTimer(finish), lvl);
    }

    public static void saveFrictionBuff(Buff gravity){
        prefs.putString(frictionTag, gravity.getSaveData());
        prefs.flush();
    }    
    
    public static GravityBuff getGravityBuff(){
        int lvl = getLevel(gravTag);
        long finish = getTime(gravTag);
        return BuffFactory.getGravityBuff(new MyTimer(finish), lvl);
    }

    public static void saveGravityBuff(Buff gravity){
        prefs.putString(gravTag, gravity.getSaveData());
        prefs.flush();
    }

    public static JumpCountBuff getJumpCountBuff(){
        int lvl = getLevel(jumpCountTag);
        long finish = getTime(jumpCountTag);
        return BuffFactory.getJumpCountBuff(new MyTimer(finish), lvl);
    }

    public static void saveJumpCountBuff(Buff jc){
        prefs.putString(jumpCountTag, jc.getSaveData());
        prefs.flush();
    }

    public static JumpPowerBuff getJumpPowerBuff(){
        int lvl = getLevel(jumpPowerTag);
        long finish = getTime(jumpPowerTag);
        return BuffFactory.getJumpPowerBuff(new MyTimer(finish), lvl);
    }

    public static void saveJumpPowerBuff(Buff jp){
        prefs.putString(jumpPowerTag, jp.getSaveData());
        prefs.flush();
    }

    private static int getLevel(String tag){
        String str = new String(prefs.getString(tag));
        String[] strArr = str.split(",");
        return Integer.valueOf(strArr[0]);
    }

    private static long getTime(String tag){
        String str = new String(prefs.getString(tag));
        String[] strArr = str.split(",");
        return Long.valueOf(strArr[1]);
    }
}
