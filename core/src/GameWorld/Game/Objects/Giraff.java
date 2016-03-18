/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import Helper.WorldUtils;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author qw
 */
public class Giraff extends GameActor{
    
    private TextureRegion groundTexture;
    private float widthBody;
    private float heightBody;
    
    private float widthNeck;
    private float heightNeck;
    
    private float widthHead;
    private float heightHead;
    
    
    public Giraff(World world, float x, float y, float widthBody
        , float heightBody, float widthNeck, float heightNeck
        , float widthHead, float heightHead, TextureRegion groundTexture){
        this.body = WorldUtils.createGiraff(world, x, y, 
                widthBody, heightBody, 
                widthNeck, heightNeck,
                widthHead, heightHead);
        this.widthBody = widthBody;
        this.heightBody = heightBody;
        
        this.widthNeck = widthNeck;
        this.heightNeck = heightNeck;
    
        this.widthHead = widthHead;
        this.heightHead = heightHead;
        this.groundTexture = groundTexture;
        body.getFixtureList().get(0).setUserData(this);
    }
    
    @Override
    public float getX(){
        return body.getPosition().x;
    }
    
    @Override
    public float getY(){
        return body.getPosition().y;
    }
    
   /* @Override
    public float getWidth(){
        return width;
    }
    
    @Override
    public float getHeight(){
        return height;
    }*/
    
    public void draw (Batch batch, float parentAlpha) {    
        batch.draw(groundTexture, getX() - widthBody/2, getY() - heightBody / 2 - 40, 10, 40);   
        batch.draw(groundTexture, getX() + widthBody/2 - 10, getY() - heightBody / 2 - 40, 10, 40);    
        batch.draw(groundTexture, getX() - widthBody/2, getY() - heightBody / 2, widthBody, heightBody);   
        batch.draw(groundTexture, getX() - widthBody/2, getY() + heightBody / 2 , widthNeck, heightNeck);   
        batch.draw(groundTexture, getX() - widthBody/2 + widthNeck - widthHead, getY() + heightBody / 2 + heightNeck, widthHead, heightHead);   
        
        //Body bodyB = getGiraffeBody(world, x, y + heightBody / 2, widthBody , heightBody);
        //Body bodyN = getGiraffeNeck(world, x - widthBody/2 + widthNeck/2 , y + heightNeck / 2 + heightBody, widthNeck , heightNeck);
        //Body bodyH = getGiraffeHead(world, x - widthBody/2 + widthNeck - widthHead/2, y + heightHead / 2 + heightNeck + heightBody, widthHead , heightHead);
    }
}

