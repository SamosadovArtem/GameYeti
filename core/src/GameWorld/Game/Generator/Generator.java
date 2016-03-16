/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Generator;

import GameWorld.Game.Objects.testObj;
import Helper.AssetLoader;
import LocationGenerator.Barrier;
import LocationGenerator.Location;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qw
 */
public class Generator {
    private List<testObj> objects = new ArrayList<testObj>();
    
    public Generator(int y, int startPosition, int barriersCount){
        List<Barrier> barr = Location.GetBarrierList(startPosition, barriersCount, true);
        
        for(Barrier b : barr){
            objects.add(new testObj(AssetLoader.btn, b.GetLocation(), y));
        }
    }
    
    public List<testObj> getObj(){
        return objects;
    }
}
