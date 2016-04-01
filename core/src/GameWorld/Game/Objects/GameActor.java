/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Pablo
 */
public class GameActor extends Actor{
    
    protected Body body;
    protected boolean mapActor = false;
    
    public GameActor(Body body){
        this.body = body;
    }
    
    public GameActor(){
        
    }
    
    public Body getBody(){
        return body;
    }
    
    public void setBody(Body body){
        this.body = body;
    }
    
    public boolean delete(){
        if(body == null){
            this.remove();
            return false;
        }
        return true;
    }
    
  /*  public boolean isVisible(OrthographicCamera camera) {		

    float upX = camera.position.x + camera;
    float upY = camera.position.y + (+ Game.HEIGHT / 2 + Game.OFFSET) * camera.zoom;

    float downX = camera.position.x + (+ Game.WIDTH  / 2 + Game.OFFSET) * camera.zoom;
    float downY = camera.position.y + (- Game.HEIGHT / 2 - Game.OFFSET) * camera.zoom;	

    if (upX <= x && upY >= y &&	downX >= x && downY <= y) {

	return true;
    }
    return false;
}*/
}
