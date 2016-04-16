package GameWorld.Skins.Elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import GameWorld.Game.Objects.GameActor;

/**
 * Created by broff on 15.04.2016.
 */
public class Skin{
    private int id;
    private String name;
    private int cost;
    private TextureRegion texture;

    public Skin(int id, int cost, String name, TextureRegion t){
        this.id = id;
        this.cost = cost;
        this.name = name;
        this.texture = t;
    }

    public int getID(){
        return id;
    }

    public int getCost(){
        return cost;
    }

    public String getName(){
        return name;
    }

    public TextureRegion getTexture(){
        return texture;
    }
}