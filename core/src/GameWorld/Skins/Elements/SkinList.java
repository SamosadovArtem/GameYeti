package GameWorld.Skins.Elements;

import java.util.LinkedList;
import java.util.List;

import Helper.AssetLoader;
import Helper.Constants;

/**
 * Created by broff on 15.04.2016.
 */
public class SkinList {

    List<Skin> list = new LinkedList<Skin>();

    public SkinList(){
        list.add(new Skin(0, 100, "name1", AssetLoader.pinguinTexture));
        list.add(new Skin(1, 200, "name2", AssetLoader.pinguinTexture));

    }

    public List<Skin> getSkins(){
        return list;
    }

    public Skin getActiveSkin(){
        int active = SkinsStatistic.getActiveSkin();
        for(Skin s : list){
            if(s.getID() == active){
                return s;
            }
        }
        return new Skin(0, 0, "name1", AssetLoader.pinguinTexture);
    }
}
