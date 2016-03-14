/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game;

import GameWorld.AbstractWorld;
import GameWorld.Game.Objects.Ground;
import GameWorld.Game.Objects.Pinguin;
import Helper.BodyUtils;
import Helper.Constants;
import Helper.WorldUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author qw
 */
public class GameWorld extends AbstractWorld implements ContactListener  {
   
    public World world;
    private Ground ground;
    private Pinguin pinguin;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;
    
     public GameWorld(Stage stage, GameLibGDX g){        
        super(stage, g);
        setUpWorld();
        Gdx.app.log("GameWorld", "create");
    }
    
    private void setUpWorld() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpGround();
        setUpRunner();
        pinguin.jump();
    }

    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world));
        stage.addActor(ground);
    }

    private void setUpRunner() {
        pinguin = new Pinguin(WorldUtils.createPinguin(world));
        stage.addActor(pinguin);
    }  

    @Override
    public void update(float delta)  {
        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }
        
        if(pinguin.getBody().getPosition().x >= Constants.RUNNER_X + 15){
            pinguin.getBody().setLinearVelocity(new Vector2(-20.0f, 0));
        } else if (pinguin.getBody().getPosition().x <= Constants.RUNNER_X){
            pinguin.getBody().setLinearVelocity(new Vector2(20.0f, 20.0f));
        }
        Gdx.app.log("GameWorld", "pingPosx" + pinguin.getBody().getPosition().x);
        Gdx.app.log("GameWorld", "pingPosy" + pinguin.getBody().getPosition().y);
    }

    @Override
    public void beginContact(Contact contact) {

        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyUtils.bodyIsPinguin(a) && BodyUtils.bodyIsGround(b))
                || (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsPinguin(b))) {
            pinguin.landed();
        }
    }
    
    public float getPlayerX(){
        return pinguin.getBody().getLinearVelocity().x;
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
    
}