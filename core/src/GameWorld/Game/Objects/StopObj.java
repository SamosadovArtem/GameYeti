/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import Helper.Constants;
import Helper.WorldUtils;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author qw
 */
public class StopObj extends GameActor{
    
    private TextureRegion groundTexture;
    private float width;
    private float height;
    
    
    public StopObj(World world, float x, float y, float width, float height, TextureRegion groundTexture){
        this.body = WorldUtils.createStopObj(world, x, y, width, height);
        this. width = width;
        this.height = height;
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
        return width;
    }
    
    @Override
    public float getHeight(){
        return height;
    }
    
    public void draw (Batch batch, float parentAlpha) {    
      batch.draw(groundTexture, getX() - getWidth()/2, getY() - getHeight() / 2, getWidth(), getHeight());   
    }
}
