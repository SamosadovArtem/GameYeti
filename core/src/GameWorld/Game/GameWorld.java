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
import Helper.Statistic;
import Helper.WorldUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author qw
 */
public class GameWorld extends AbstractWorld  implements ContactListener {

    public World world;
    private Ground ground;
    private Pinguin pinguin;
    private Label dirXText;
    private Label dirYText;
    private Label powText;

    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    public GameWorld(Stage stage, GameLibGDX g) {
        super(stage, g);
        setUpWorld();
        Gdx.app.log("GameWorld", "create");
    }

    private void setUpWorld() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpGround();
        setUpRunner();
        initDirX();
        initDirY();
        initPower();
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
    public void update(float delta) {
        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }

        if (pinguin.getBody().getPosition().x >= Constants.RUNNER_X + 15) {
            pinguin.getBody().setLinearVelocity(new Vector2(-10.0f, 0));
        }
        else if (pinguin.getBody().getPosition().x <= Constants.RUNNER_X){
            pinguin.getBody().setLinearVelocity(new Vector2(10.0f, 20.0f));
        }
        
        initHit();
        
    }
    
    private void initHit(){
        dirXText.setText(String.format("Direction X: %.3f", pinguin.getDirectionX()));
        dirYText.setText(String.format("Direction Y: %.3f", pinguin.getDirectionY()));
        powText.setText("Power: " + pinguin.getPower());
        
        if(!pinguin.moved()){            
            if (!pinguin.getIsPower()) {
                pinguin.changePower();
            } else if (!pinguin.getIsDir()) {
                pinguin.changeDirection();
            }
        }
    }

    public float getPlayerX() {
        return pinguin.getBody().getLinearVelocity().x;
    }
    
    public float getPlayerY() {
        return pinguin.getBody().getLinearVelocity().y;
    }

    public Pinguin getPinguin() {
        return pinguin;
    }

    private void initDirX() {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        dirXText = new Label("", labelS);
        dirXText.setAlignment(Align.center);
        dirXText.setFontScale(2);
        dirXText.setSize(stage.getWidth() * 0.4f, stage.getHeight() / 5);
        dirXText.setPosition(100, 100);
        stage.addActor(dirXText);
    }

    private void initDirY() {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        dirYText = new Label("", labelS);
        dirYText.setAlignment(Align.center);
        dirYText.setFontScale(2);
        dirYText.setSize(stage.getWidth() * 0.4f, stage.getHeight() / 5);
        dirYText.setPosition(100, 50);
        stage.addActor(dirYText);
    }

    private void initPower() {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        powText = new Label("", labelS);
        powText.setAlignment(Align.center);
        powText.setFontScale(2);
        powText.setSize(stage.getWidth() * 0.4f, stage.getHeight() / 5);
        powText.setPosition(100, 150);
        stage.addActor(powText);
    }

    @Override
    public void beginContact(Contact cntct) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void endContact(Contact cntct) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void preSolve(Contact cntct, Manifold mnfld) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postSolve(Contact cntct, ContactImpulse ci) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
