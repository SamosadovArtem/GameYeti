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
import java.util.Date;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class DailyGiftHandler {

    private static Preferences prefs;

    private static String tag = "gift";

    public static DailyGift gift;

    public static void load() {
        prefs = Gdx.app.getPreferences("YetiGame");

        if (!prefs.contains(tag)) {
            prefs.putString(tag, "0,0,0");
            prefs.flush();
        }
    }

    public static void saveGift() {
        prefs.putString(tag, gift.getSaveData());
        prefs.flush();
    }

    public static void setDailyGift() {
        gift = getDailyGift();
    }

    private static DailyGift getDailyGift() {
        int level = getLevel();
        long minTime = getMinTime();
        long maxTime = getMaxTime();
        Date now = new Date();
        if (level <= 0 || (minTime - now.getTime() <= 0 && maxTime - now.getTime() <= 0)) {
            return new DailyGift(new MyTimer(new Date(), 0), new MyTimer(new Date(), 24 * 60 * 60), 1);
        }
        return new DailyGift(new MyTimer(minTime), new MyTimer(maxTime), level);
    }

    public static void refresh() {
        gift.increaseLevel();
        saveGift();
    }

    public static List<Picture> getArrayOfPictures() {
        List<Picture> array = new ArrayList<Picture>();
        for (int i = 0; i < 5; i++) {
            if (i < gift.getLevel()) {
                array.add(new Picture(AssetLoader.dailyGiftFull));
            } else {
                array.add(new Picture(AssetLoader.dailyGiftEmpty));
            }
        }
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
        return Long.valueOf(strArr[1]);
    }

    private static long getMaxTime() {
        String str = new String(prefs.getString(tag));
        String[] strArr = str.split(",");
        return Long.valueOf(strArr[2]);
    }

    public static void dispose() {
        gift = new DailyGift();
    }
}
