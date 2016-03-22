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
    
    public Generator(World world, int y, int startPosition, int barriersCount){
        List<Barrier> barr = Location.GetBarrierList(startPosition, barriersCount, true);
        
        for(Barrier b : barr){
            switch(b.GetType()){
                case STOP:
                    objects.add(new StopObj(world, b.GetX(), y,
                        30, 60,
                        AssetLoader.btn));
                    break;
                case SNAKE:
                    objects.add(new Snake(AssetLoader.btn, b.GetX() ,Constants.GROUND_Y+20f,100f,20f,world));
                    break;
                case ANTELOPHE:
                    objects.add(new Antelope(AssetLoader.btn,b.GetX(),Constants.GROUND_Y,100f,30f,world));
                    break;
                case TREE:
                    objects.add(new Tree(world, b.GetX(),Constants.GROUND_Y + Constants.GROUND_HEIGHT/2,
                    10,70,70,10, AssetLoader.btn));
                    break;
                case GIRAFFE:
                    objects.add(new Giraffe(world, b.GetX(), Constants.GROUND_Y + Constants.GROUND_HEIGHT/2 + 40 , 100f
                    , 40f, 10f, 60f
                    , 40f, 20f, AssetLoader.btn));
                    break;
                case COIN:
                    objects.add(new Coin(AssetLoader.btn, b.GetX() , b.GetY() + 40f, 40f, 40f, world));
                    break;
                default:
                    objects.add(new StopObj(world, b.GetX(), y,
                        30, 60,
                        AssetLoader.btn));
                    break;
            }
        }
        
    }
    
    public List<GameActor> getObj(){
        return objects;
    }
}
