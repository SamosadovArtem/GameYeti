/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import Helper.Constants;
import Helper.WorldUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import static java.lang.Thread.sleep;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public float widthHead;
    public float heightHead;
    private boolean isActive = true;
    
    public Body head;
    
    
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
        head = WorldUtils.GiraffeHead;
        WorldUtils.GiraffeHead.getFixtureList().get(0).setUserData(this);
    }
    
    @Override
    public float getX(){
        return body.getPosition().x;
    }
    
    @Override
    public float getY(){
        return body.getPosition().y;
    }
    
    public void draw (Batch batch, float parentAlpha) {    
        batch.draw(groundTexture, getX() - widthBody/2, getY() - heightBody / 2 - 40, 10, 40);   
        batch.draw(groundTexture, getX() + widthBody/2 - 10, getY() - heightBody / 2 - 40, 10, 40);    
        batch.draw(groundTexture, getX() - widthBody/2, getY() - heightBody / 2, widthBody, heightBody);   
        batch.draw(groundTexture, getX() - widthBody/2, getY() + heightBody / 2 , widthNeck, heightNeck);   
        batch.draw(groundTexture, getX() - widthBody/2 + widthNeck - widthHead, getY() + heightBody / 2 + heightNeck, widthHead, heightHead);   
    }
    
    public void throwPinguin(Pinguin p){
        if(isActive()){
            setActive(false);
            GiraffThrow gt = new GiraffThrow(p, this);
            Thread t = new Thread(gt);
            t.start();   
        }
    }
    
    public boolean isActive(){
        return isActive;
    }
    
    public void setActive(boolean a){
        isActive = a;
    }
}


class GiraffThrow implements Runnable {
    Pinguin pinguin;
    Giraff giraff;
    
    float velX;
    
    public GiraffThrow(Pinguin p, Giraff g){
        this.pinguin = p;        
        this.giraff = g;
        velX = pinguin.getBody().getLinearVelocity().x;
    }
    
    @Override()
    public void run(){
        try {
            
            Gdx.app.log("","iawdawdnlkwa");
            pinguin.hide();
            pinguin.getBody().setLinearVelocity(0, 0);
            pinguin.getBody().setGravityScale(0);
            for(int i = 0; i<=20;i++){
                pinguin.getBody().setLinearVelocity(giraff.head.getPosition().x - pinguin.getBody().getPosition().x - giraff.widthHead,
                        giraff.head.getPosition().y - pinguin.getBody().getPosition().y);
                sleep(100);
            }
            throwPinguin();
            pinguin.show();
            sleep(1000);
            giraff.setActive(true);            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(GiraffThrow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void throwPinguin(){        
        float x=0;
        if(velX >= 0){
            if(velX>=50 && velX <=200){
                x = velX * 1.3f;
            } else {
                x = 200;
            }
        } else{
            x = 200;
        }
        pinguin.getBody().setGravityScale(Constants.RUNNER_GRAVITY_SCALE);
        
        pinguin.getBody().setLinearVelocity(x, 150);
    }
}
