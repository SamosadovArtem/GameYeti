/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import GameWorld.Game.Objects.GameActor;
import GameWorld.Game.Data.PinguinUserData;
import GameWorld.Game.Data.UserData;
import Helper.Constants;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Pablo
 */
public class testObj extends Actor {

    private TextureRegion pinguinTexture;

    public testObj(TextureRegion pinguinTexture, int x, int y) {        
        this.pinguinTexture = pinguinTexture;
        setWidth(20);
        setHeight(40);
        setX(x);
        setY(y);
    }    
    
    public void draw (Batch batch, float parentAlpha) {
    
      batch.draw(pinguinTexture, getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
   
   }
}
