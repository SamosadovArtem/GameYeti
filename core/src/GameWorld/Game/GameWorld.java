/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game;

import GameWorld.AbstractWorld;
import GameWorld.Game.Generator.Generator;
import GameWorld.Game.Objects.GameActor;
import GameWorld.Game.Objects.Ground;
import GameWorld.Game.Objects.Pinguin;
import GameWorld.Game.Objects.Snake;
import Helper.AssetLoader;
import Helper.BodyUtils;
import Helper.Constants;
import Helper.GameContactListener;
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
    private Ground ground, ground2;
    private Pinguin pinguin;	
    private Snake snake;
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
        setUpGround();
        setUpRunner();
        world.setContactListener(new GameContactListener(pinguin));
        initDirX();
        initDirY();
        initPower();
        createObjects();		
        snake = new Snake(AssetLoader.btn,300f,Constants.GROUND_Y+20f,100f,20f,world);
        stage.addActor(snake);
    }
    
    private void createObjects(){
        Generator g = new Generator(world, (int)Constants.GROUND_Y ,0, 30);
        for(GameActor t: g.getObj()){
            stage.addActor(t);
            Gdx.app.log("GameWorld", "genegate x="+ t.getX());
        }
    }

    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world, 0f), AssetLoader.btn);
      //  ground.setPosition(100, 100);

        stage.addActor(ground);
        ground2 = new Ground(WorldUtils.createGround(world, Constants.GROUND_WIDTH), AssetLoader.btn);
        stage.addActor(ground2);
    }
	
	
    private void addGround() {
        if (ground.getBody().getPosition().x >= ground2.getBody().getPosition().x) {
            ground2 = new Ground(WorldUtils.createGround(world, Constants.GROUND_WIDTH
                    + ground.getBody().getPosition().x), AssetLoader.btn);
            stage.addActor(ground2);
        } else {
            ground = new Ground(WorldUtils.createGround(world, Constants.GROUND_WIDTH
                    + ground2.getBody().getPosition().x), AssetLoader.btn);
            stage.addActor(ground);
        }
    }

    private void setUpRunner() {
        pinguin = new Pinguin(WorldUtils.createPinguin(world), AssetLoader.btn);
        stage.addActor(pinguin);
    }

    @Override
    public void update(float delta) {
        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 1);
            accumulator -= TIME_STEP;
        }
        moveUI();
        initHit();  
        setAngularPinguin();
        checkHeight();		
		
        if (pinguin.getBody().getPosition().x >= ground.getBody().getPosition().x) {
            addGround();
        }
    }
    
    private void checkHeight(){
        if(this.getPlayerY() >= Constants.APP_HEIGHT * 1.1){
            pinguin.getBody().setLinearVelocity(new Vector2(0, 0));
        }
    }
    
    private void setAngularPinguin(){
        float angle = pinguin.getBody().getLinearVelocity().angle();
        if(pinguin.getBody().getLinearVelocity().x <= 0.2){
            angle = 0;
        }
        pinguin.setRotation(angle);
        
    }
    
    private void moveUI(){
        dirXText.setX(stage.getCamera().position.x - stage.getWidth()/2);
        dirYText.setX(stage.getCamera().position.x - stage.getWidth()/2);
        powText.setX(stage.getCamera().position.x - stage.getWidth()/2);
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
        
        return pinguin.getBody().getPosition().x;
    }
    
    public float getPlayerY() {
        return pinguin.getBody().getPosition().y;
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
        dirXText.setPosition(0, 350);
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
        dirYText.setPosition(0, 400);
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
        powText.setPosition(0, 300);
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
