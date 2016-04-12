/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import GameObjects.DailyGift;
import GameObjects.Picture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class DailyGiftHandler {

    private static Preferences prefs;

    private static String tag = "gift";

    public static void load() {
        prefs = Gdx.app.getPreferences("YetiGameGift");

        if (!prefs.contains(tag)) {
            prefs.putString(tag, "0,0,0");
            prefs.flush();
        }
    }
    
    public static void saveJumpCountBuff(DailyGift dg) {
        prefs.putString(tag, dg.getSaveData());
        prefs.flush();
    }

    public static DailyGift getDailyGift() {
        int level = getLevel();
        long minTime = getMinTime();
        long maxTime = getMaxTime();
        return new DailyGift(new MyTimer(minTime), new MyTimer(maxTime), level);
    }

    public static void checkDailyGift() {

    }

    public static List<Picture> getArrayOfPictures() {
        List<Picture> array = new ArrayList<Picture>();
        array.add(new Picture(AssetLoader.textureBtnNormal));
        array.add(new Picture(AssetLoader.textureBtnNormal));
        array.add(new Picture(AssetLoader.textureBtnNormal));
        array.add(new Picture(AssetLoader.textureBtnNormal));
        array.add(new Picture(AssetLoader.textureBtnNormal));
        return array;
    }

    private static int getLevel() {
        String str = new String(prefs.getString(tag));
        String[] strArr = str.split(",");
        return Integer.valueOf(strArr[0]);
    }

    private static long getMinTime() {
        String str = new String(prefs.getString(tag));
        String[] strArr = str.split(",");
        return Integer.valueOf(strArr[1]);
    }

    private static long getMaxTime() {
        String str = new String(prefs.getString(tag));
        String[] strArr = str.split(",");
        return Integer.valueOf(strArr[2]);
    }
}
