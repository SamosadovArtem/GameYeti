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
    
    public static void load(){
        prefs = Gdx.app.getPreferences("YetiGame");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
        
        if (!prefs.contains("coins")) {
            prefs.putInteger("coins", 0);
        }
    }
    
    public static int getCoins(){
        return prefs.getInteger("coins");
    }
    
    public static void payCoins(int pay){
        int newCoinsVal = getCoins() - pay;
        if(newCoinsVal > 0){
            prefs.putInteger("coins", newCoinsVal);
            prefs.flush();
        } else {
            throw new IllegalArgumentException("You have no " + pay + " coins");
        }
    }
    
    public static void addCoins(int add){
        int newCoinsVal = getCoins() + add;
        if(add > 0){
            prefs.putInteger("coins", newCoinsVal);
            prefs.flush();
        } else {
            throw new IllegalArgumentException("Start Position must be > 0");
        }
    }
    
    
    public static void setHighScore(int val) {
        if(getHighScore() < val){
            prefs.putInteger("highScore", val);
            prefs.flush();
        } else {
            //throw new IllegalArgumentException("HighScore value must be more");
        }
    }

    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }
    
    public static void dispose(){
        prefs.clear();
    }
}
