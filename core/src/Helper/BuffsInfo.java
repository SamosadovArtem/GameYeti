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
    private static String coinsTag = "coinsBuff";
    private static String directionCoffTag = "directionCoffBuff";
    private static String frictionTag = "frictionBuff";
    private static String heightSkyCoffTag = "heightSkyCoffBuff";
    private static String powerCoffTag = "powerCoffBuff";

    public static void load() {
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

        if (!prefs.contains(coinsTag)) {
            prefs.putString(coinsTag, "0,0");
            prefs.flush();
        }

        if (!prefs.contains(directionCoffTag)) {
            prefs.putString(directionCoffTag, "0,0");
            prefs.flush();
        }

        if (!prefs.contains(frictionTag)) {
            prefs.putString(frictionTag, "0,0");
            prefs.flush();
        }

        if (!prefs.contains(heightSkyCoffTag)) {
            prefs.putString(heightSkyCoffTag, "0,0");
            prefs.flush();
        }

        if (!prefs.contains(powerCoffTag)) {
            prefs.putString(powerCoffTag, "0,0");
            prefs.flush();
        }
    }

    public static List<Buff> getBuffs() {
        List<Buff> buffs = new LinkedList<Buff>();
        buffs.add(getGravityBuff());
        buffs.add(getJumpCountBuff());
        buffs.add(getJumpPowerBuff());
        buffs.add(getCoinsBuff());
        buffs.add(getDirectionCoffBuff());
        buffs.add(getFrictionBuff());
        buffs.add(getHeightSkyCoffBuff());
        buffs.add(getPowerCoffBuff());
        return buffs;
    }

    public static GravityBuff getGravityBuff() {
        int lvl = getLevel(gravTag);
        long finish = getTime(gravTag);
        return BuffFactory.getGravityBuff(new MyTimer(finish), lvl);
    }

    public static void saveGravityBuff(Buff gravity) {
        prefs.putString(gravTag, gravity.getSaveData());
        prefs.flush();
    }

    public static JumpCountBuff getJumpCountBuff() {
        int lvl = getLevel(jumpCountTag);
        long finish = getTime(jumpCountTag);
        return BuffFactory.getJumpCountBuff(new MyTimer(finish), lvl);
    }

    public static void saveJumpCountBuff(Buff jc) {
        prefs.putString(jumpCountTag, jc.getSaveData());
        prefs.flush();
    }

    public static JumpPowerBuff getJumpPowerBuff() {
        int lvl = getLevel(jumpPowerTag);
        long finish = getTime(jumpPowerTag);
        return BuffFactory.getJumpPowerBuff(new MyTimer(finish), lvl);
    }

    public static void saveJumpPowerBuff(Buff jp) {
        prefs.putString(jumpPowerTag, jp.getSaveData());
        prefs.flush();
    }

    public static DirectionCoffBuff getDirectionCoffBuff() {
        int lvl = getLevel(directionCoffTag);
        long finish = getTime(directionCoffTag);
        return BuffFactory.getDirectionCoffBuff(new MyTimer(finish), lvl);
    }

    public static void saveDirectionCoffBuff(Buff jc) {
        prefs.putString(directionCoffTag, jc.getSaveData());
        prefs.flush();
    }

    public static FrictionBuff getFrictionBuff() {
        int lvl = getLevel(frictionTag);
        long finish = getTime(frictionTag);
        return BuffFactory.getFrictionBuff(new MyTimer(finish), lvl);
    }

    public static void saveFrictionBuff(Buff jc) {
        prefs.putString(frictionTag, jc.getSaveData());
        prefs.flush();
    }

    public static HeightSkyCoffBuff getHeightSkyCoffBuff() {
        int lvl = getLevel(heightSkyCoffTag);
        long finish = getTime(heightSkyCoffTag);
        return BuffFactory.getHeightSkyCoffBuff(new MyTimer(finish), lvl);
    }

    public static void saveHeightSkyCoffBuff(Buff jc) {
        prefs.putString(heightSkyCoffTag, jc.getSaveData());
        prefs.flush();
    }

    public static PowerCoffBuff getPowerCoffBuff() {
        int lvl = getLevel(powerCoffTag);
        long finish = getTime(powerCoffTag);
        return BuffFactory.getPowerCoffBuff(new MyTimer(finish), lvl);
    }

    public static void savePowerCoffBuff(Buff jc) {
        prefs.putString(powerCoffTag, jc.getSaveData());
        prefs.flush();
    }

    public static CoinsBuff getCoinsBuff() {
        int lvl = getLevel(coinsTag);
        long finish = getTime(coinsTag);
        return BuffFactory.getCoinsBuff(new MyTimer(finish), lvl);
    }

    public static void saveCoinsBuff(Buff jc) {
        prefs.putString(coinsTag, jc.getSaveData());
        prefs.flush();
    }

    private static int getLevel(String tag) {
        String str = prefs.getString(tag);
        String[] strArr = str.split(",");
        return Integer.valueOf(strArr[0]);
    }

    private static long getTime(String tag) {
        String str = prefs.getString(tag);
        String[] strArr = str.split(",");
        return Long.valueOf(strArr[1]);
    }
}
