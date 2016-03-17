/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import GameWorld.Game.Data.UserData;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Pablo
 */
public class GameActor extends Actor{
    
    protected Body body;
    protected UserData userData;
    
    public GameActor(Body body){
        this.body = body;
        this.userData = (UserData) body.getUserData();
    }
    
    public GameActor(){
        
    }
    
    public Body getBody(){
        return body;
    }
    
    public UserData getUserData(){
        return null;
    }
}
