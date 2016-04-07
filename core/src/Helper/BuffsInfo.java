package Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.LinkedList;
import java.util.List;

import GameObjects.Buffs.Buff;
import GameObjects.Buffs.BuffFactory;
import GameObjects.Buffs.GravityBuff;
import GameObjects.Buffs.JumpCountBuff;
import GameObjects.Buffs.JumpPowerBuff;

/**
 * Created by broff on 07.04.2016.
 */
public class BuffsInfo {
    private static Preferences prefs;

    private static String gravTag = "gravityBuff";
    private static String jumpCountTag = "jumpCountBuff";
    private static String jumpPowerTag = "jumpPowerBuff";

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
    }

    public static List<Buff> getBuffs(){
        List<Buff> buffs = new LinkedList<Buff>();
        buffs.add(getGravityBuff());
        buffs.add(getJumpCountBuff());
        buffs.add(getJumpPowerBuff());
       return buffs;
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

    public static void saveJumpPowerBuf(Buff jp){
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
