/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Pablo
 */
public class GameActor extends Actor{
    
    protected Body body;
    
    public GameActor(Body body){
        this.body = body;
    }
    
    public GameActor(){
        
    }
    
    public Body getBody(){
        return body;
    }
    
    public boolean delete(){
        if(body == null){
            this.remove();
            return false;
        }
        return true;
    }
    
}
