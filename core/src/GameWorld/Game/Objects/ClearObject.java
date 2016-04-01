/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import Helper.WorldUtils;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author qw
 */
public class ClearObject extends GameActor{
    
    private TextureRegion groundTexture;
    private float width;
    private float height;
    private float x;
    private float y;    
        
    public ClearObject(float x, float y, float width, float height, TextureRegion groundTexture){
        this.body = null;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.groundTexture = groundTexture;
    }
    
    @Override
    public float getX(){       
        return x;        
    }
    
    @Override
    public float getY(){
        return y;
    }
    
    @Override
    public float getWidth(){
        return width;
    }
    
    @Override
    public float getHeight(){
        return height;
    }
    
    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }
    
    public void draw (Batch batch, float parentAlpha) {   
        batch.draw(groundTexture, getX() - getWidth()/2, getY() - getHeight() / 2, getWidth(), getHeight());   
        
    }
}