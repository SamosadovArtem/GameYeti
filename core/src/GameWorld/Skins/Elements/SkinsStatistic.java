package GameWorld.Skins.Elements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by broff on 15.04.2016.
 */
public class SkinsStatistic {

    private static Preferences prefs;

    public static void load(){
        prefs = Gdx.app.getPreferences("YetiGameSkins");

        if (!prefs.contains("activeSkin")) {
            prefs.putInteger("activeSkin", 0);
            prefs.flush();
        }

        if (!prefs.contains("skins")) {
            prefs.putString("skins", "0");
            prefs.flush();
        }
    }

    public static int getActiveSkin(){
        return prefs.getInteger("activeSkin");
    }

    public static void setActiveSkin(int num){
        prefs.putInteger("activeSkin", num);
        prefs.flush();
    }

    public static List<Integer> getSkins(){
        List<Integer> allBoughtSkins = new LinkedList<Integer>();
        String str = prefs.getString("skins");
        String[] s = str.split("[,]");

        for(int i = 0; i < s.length; i++){
            try{
                allBoughtSkins.add(Integer.valueOf(s[i]));
            }
            catch (Exception e){
                Gdx.app.log("SKINS", "ParseError num:" + i);
            }
        }

        return allBoughtSkins;
    }

    public static void addSkin(int num){
        String str = prefs.getString("skins");
        str+=","+num;
        prefs.putString("skins", str);
        prefs.flush();
    }
}