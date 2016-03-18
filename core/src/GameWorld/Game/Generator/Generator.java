/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Generator;

import GameWorld.Game.Objects.GameActor;
import GameWorld.Game.Objects.Giraff;
import GameWorld.Game.Objects.Snake;
import GameWorld.Game.Objects.StopObj;
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
            if(b.GetType() == BarrierTypes.STOP){
                objects.add(new StopObj(world, b.GetLocation(), y,
                        30, 60,
                        AssetLoader.btn));
            } else if(b.GetType() == BarrierTypes.PUSH){
                Random r = new Random();
                if(r.nextInt(2)==1){
                    objects.add(new Snake(AssetLoader.btn, b.GetLocation() ,Constants.GROUND_Y+20f,100f,20f,world));
                } else {
                    objects.add(new Giraff(world, b.GetLocation(), Constants.GROUND_Y + Constants.GROUND_HEIGHT/2 + 40 , 100f
                    , 40f, 10f, 60f
                    , 40f, 20f, AssetLoader.btn));
                }
            }
        }
    }
    
    public List<GameActor> getObj(){
        return objects;
    }
}
