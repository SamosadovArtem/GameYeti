/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 *
 * @author qw
 */
public class Statistic {

    private static Preferences prefs;
    private String bouhgtMaps;

    public static void load() {
        prefs = Gdx.app.getPreferences("YetiGame");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
            prefs.flush();
        }

        if (!prefs.contains("coins")) {
            prefs.putInteger("coins", 0);
            prefs.flush();
        }

        if (!prefs.contains("openMaps")) {
            prefs.putString("openMaps", "0");
            prefs.flush();
        }

        if (!prefs.contains("allGames")) {
            prefs.putInteger("allGames", 0);
            prefs.flush();
        }

        if (!prefs.contains("allJumps")) {
            prefs.putInteger("allJumps", 0);
            prefs.flush();
        }

        if (!prefs.contains("allLength")) {
            prefs.putInteger("allLength", 0);
            prefs.flush();
        }
        if (!prefs.contains("sounds")) {
            prefs.putInteger("sounds", 100);
        }
        if (!prefs.contains("music")) {
            prefs.putInteger("music", 100);
        }
        if (!prefs.contains("tickets")) {
            prefs.putInteger("tickets", 0);
        }

        CloseMaps();
    }

    public static int getTickets() {
        return prefs.getInteger("tickets");
    }

    public static void addTicket() {
        prefs.putInteger("tickets", getTickets() + 1);
        prefs.flush();
    }

    public static void removeTicket() {
        if (getTickets() > 0) {
            prefs.putInteger("tickets", getTickets() - 1);
            prefs.flush();
        }
    }

    public static int getGames() {
        return prefs.getInteger("allGames");
    }

    public static void playGame() {
        prefs.putInteger("allGames", getGames() + 1);
        prefs.flush();
    }

    public static int getJumps() {
        return prefs.getInteger("allJumps");
    }

    public static void jump() {
        prefs.putInteger("allJumps", getJumps() + 1);
        prefs.flush();
    }

    public static int getLength() {
        return prefs.getInteger("allLength");
    }

    public static void setLength(int l) {
        prefs.putInteger("allLength", getLength() + l);
        prefs.flush();
    }

    public static int getCoins() {
        return prefs.getInteger("coins");
    }

    public static String[] getOpenMaps() {
        String mapsString = prefs.getString("openMaps");
        String[] mapsArray = mapsString.split(",");
        return mapsArray;
    }

    public static void removeCoins(int pay) {
        int newCoinsVal = getCoins() - pay;
        if (newCoinsVal > 0) {
            prefs.putInteger("coins", newCoinsVal);
            prefs.flush();
        } else {
            throw new IllegalArgumentException("You have no " + pay + " coins");
        }
    }

    public static void CloseMaps() {
        prefs.putString("openMaps", "0");
        prefs.flush();
    }

    public static void OpenMap(String mapNumber) {
        String oldMaps = prefs.getString("openMaps");
        prefs.putString("openMaps", oldMaps + "," + mapNumber);
        prefs.flush();
    }

    public static void addCoins(int add) {
        int newCoinsVal = getCoins() + add;
        if (add > 0) {
            prefs.putInteger("coins", newCoinsVal);
            prefs.flush();
        } else {
            throw new IllegalArgumentException("Start Position must be > 0");
        }
    }

    public static void setHighScore(int val) {
        if (getHighScore() < val) {
            prefs.putInteger("highScore", val);
            prefs.flush();
        } else {
            //throw new IllegalArgumentException("HighScore value must be more");
        }
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }

    public static void dispose() {
        prefs.clear();
    }

    public static int getSoundLevel() {
        return prefs.getInteger("sounds");
    }

    public static void setSoundsLevel(int lvl) {
        prefs.putInteger("sounds", lvl);
        prefs.flush();
    }

    public static int getMusicLevel() {
        return prefs.getInteger("music");
    }

    public static void setMusicLevel(int lvl) {
        prefs.putInteger("music", lvl);
        prefs.flush();
    }
}
