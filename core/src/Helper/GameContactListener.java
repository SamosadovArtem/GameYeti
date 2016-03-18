/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import GameWorld.Game.Objects.Giraff;
import GameWorld.Game.Objects.Pinguin;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.WorldManifold;

/**
 *
 * @author Pablo
 */
public class GameContactListener implements ContactListener {

    private Pinguin pinguin;

    public GameContactListener(Pinguin pinguin) {
        this.pinguin = pinguin;
    }

    @Override
    public void beginContact(Contact cntct) {
    }

    @Override
    public void endContact(Contact cntct) {
        
    }

    @Override
    public void preSolve(Contact contact, Manifold mnfld) {
        WorldManifold manifold = contact.getWorldManifold();
        for (int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
            if(contact.getFixtureA().getUserData() != null && contact.getFixtureB().getUserData() != null ){
                contactGiraffe(contact);
                if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("SNAKE")) {
                    if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("PINGUIN")) {
                        contact.setEnabled(false);                    
                        snakeJump();
                    }
                }
                if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("SNAKE")) {
                    if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("PINGUIN")) {
                        contact.setEnabled(false);

                        snakeJump();
                    }
                }
            }
        }        
    }
    
     private boolean activeGiraff = false;
    
    private void giraffeHit(Body giraff){
        if(giraff.isActive()){
            //giraff.setActive(false);
            //giraff.
        }
    }
    
    private void contactGiraffe(Contact contact){      
        
        if(contact.getFixtureB().getUserData() instanceof Giraff || 
                contact.getFixtureA().getUserData() instanceof Giraff){
            Gdx.app.log("PINGUIN", "GIRAF");
        }
        
        if (contact.getFixtureB().getUserData().equals("PINGUIN") &&
                contact.getFixtureA().getUserData().equals("GIRAFFEHEAD")) {                    
            Gdx.app.log("PINGUIN", "GIRAFFEHEAD");
            giraffeHit(contact.getFixtureA().getBody());
        }

        if (contact.getFixtureA().getUserData().equals("PINGUIN") &&
                contact.getFixtureB().getUserData().equals("GIRAFFEHEAD")) {
            Gdx.app.log("PINGUIN", "GIRAFFEHEAD");
            giraffeHit(contact.getFixtureB().getBody());
        }
                
    }
    
    private void snakeJump(){
        if(pinguin.getBody().getLinearVelocity().y <= 50f){
            pinguin.getBody().setLinearVelocity(new Vector2(pinguin.getBody().getLinearVelocity().x,100f));
        } else if(pinguin.getBody().getLinearVelocity().y <= 200f) {
            pinguin.getBody().setLinearVelocity(
                        new Vector2(pinguin.getBody().getLinearVelocity().x, 
                        pinguin.getBody().getLinearVelocity().y *1.2f));
        } else {
            pinguin.getBody().setLinearVelocity(
                        new Vector2(pinguin.getBody().getLinearVelocity().x, 
                        200f));
        }

        if(pinguin.getBody().getLinearVelocity().x >= 0){
            if(pinguin.getBody().getLinearVelocity().x<=50){
                pinguin.getBody().setLinearVelocity(new Vector2(50f, 
                            pinguin.getBody().getLinearVelocity().y));
            } 
        } else{
            if(pinguin.getBody().getLinearVelocity().x >=-50){
                pinguin.getBody().setLinearVelocity(new Vector2(-50f, 
                            pinguin.getBody().getLinearVelocity().y));
            } 
        }
    }

    @Override
    public void postSolve(Contact cntct, ContactImpulse ci) {
    }

}
