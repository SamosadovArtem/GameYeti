    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Generator;

import GameWorld.Game.Objects.Antelope;
import GameWorld.Game.Objects.Coin;
import GameWorld.Game.Objects.GameActor;
import GameWorld.Game.Objects.Giraffe;
import GameWorld.Game.Objects.Snake;
import GameWorld.Game.Objects.StopObj;
import GameWorld.Game.Objects.Tree;
import Helper.AssetLoader;
import Helper.Constants;
import LocationGenerator.Barrier;
import LocationGenerator.BarrierTypes;
import LocationGenerator.Location;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author qw
 */
public class Generator {
    private List<GameActor> objects = new ArrayList<GameActor>();
    private List<GameActor> mapObjects = new ArrayList<GameActor>();
    private List<Barrier> barr;
    
    public Generator(World world, int y, int startPosition, int barriersCount){
        barr = Location.GetBarrierList(startPosition, barriersCount, true);
        
        for(Barrier b : barr){
            switch(b.GetType()){
                case STOP:
                    StopObj s = new StopObj(world, b.GetX(), y, 30, 60,
                        AssetLoader.btn);
                    StopObj ms = new StopObj(s.getBody(), b.GetX(),  - Constants.GROUND_HEIGHT / 2,
                        30, 60,
                        AssetLoader.btn);
                    objects.add(s);
                    mapObjects.add(ms);
                    break;
                case SNAKE:
                    Snake snake = new Snake(AssetLoader.btn, b.GetX() ,Constants.GROUND_Y+20f, 100f, 20f, world);
                    Snake mapSnake = new Snake(snake.getBody(),AssetLoader.btn, b.GetX() ,Constants.GROUND_Y+20f, 100f, 20f);
                    objects.add(snake);
                    mapObjects.add(mapSnake);
                    break;
                case ANTELOPHE:
                    Antelope a = new Antelope(AssetLoader.btn,b.GetX(),Constants.GROUND_Y,100f,30f,world);
                    Antelope ma = new Antelope(a.getBody(), AssetLoader.btn,b.GetX(),Constants.GROUND_Y,100f,30f);
                    objects.add(a);
                    mapObjects.add(ma);
                    break;
                case TREE:
                    Tree t = new Tree(world, b.GetX(),Constants.GROUND_Y + Constants.GROUND_HEIGHT/2,
                    10,70,70,10, AssetLoader.btn);
                    Tree mt = new Tree(t.getBody(), b.GetX(),Constants.GROUND_Y + Constants.GROUND_HEIGHT/2,
                    10,70,70,10, AssetLoader.btn);
                    objects.add(t);
                    mapObjects.add(mt);
                    break;
                case GIRAFFE:
                    Giraffe g = new Giraffe(world, b.GetX(), Constants.GROUND_Y + Constants.GROUND_HEIGHT/2 + 40 , 100f
                    , 40f, 10f, 60f
                    , 40f, 20f, AssetLoader.btn);
                    Giraffe mg = new Giraffe(g.getBody(), b.GetX(), Constants.GROUND_Y + Constants.GROUND_HEIGHT/2 + 40 , 100f
                    , 40f, 10f, 60f
                    , 40f, 20f, AssetLoader.btn);
                    objects.add(g);
                    mapObjects.add(mg);
                    break;
                case COIN:
                    Coin c = new Coin(AssetLoader.btn, b.GetX() , b.GetY() + 40f, 40f, 40f, world);
                    Coin mc = new Coin(c.getBody(), AssetLoader.btn, b.GetX() , b.GetY() + 40f, 40f, 40f);
                    objects.add(c);
                    mapObjects.add(mc);
                    break;
                default:
                    break;
            }
        }
        
    }
    
    public List<GameActor> getObj(){
        return objects;
    }
    
    public List<GameActor> getMapObj(){
        return mapObjects;
    }
    
    public List<Barrier> getList(){
        return barr;
    }
}
