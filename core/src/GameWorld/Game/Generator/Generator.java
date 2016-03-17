/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Generator;

import GameWorld.Game.Objects.GameActor;
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
                objects.add(new Snake(AssetLoader.btn, b.GetLocation() ,Constants.GROUND_Y+20f,100f,20f,world));
            }
        }
    }
    
    public List<GameActor> getObj(){
        return objects;
    }
}
