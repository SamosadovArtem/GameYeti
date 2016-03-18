/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import GameWorld.Game.Objects.GameActor;
import Helper.Constants;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;

/**
 *
 * @author Pablo
 */
public class Ground extends GameActor{
    
    private TextureRegion groundTexture;
    
    public Ground(Body body, TextureRegion groundTexture){
        super(body);
        this.groundTexture = groundTexture;
    }
    
    @Override
    public float getX(){
        return body.getPosition().x;
    }
    
    @Override
    public float getY(){
        return body.getPosition().y;
    }
    
    @Override
    public float getWidth(){
        return Constants.GROUND_WIDTH;
    }
    
    @Override
    public float getHeight(){
        return Constants.GROUND_HEIGHT;
    }
    
    public void draw (Batch batch, float parentAlpha) {    
      batch.draw(groundTexture, getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());   
    }
}
