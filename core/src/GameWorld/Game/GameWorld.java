/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game;

import GameObjects.Button;
import GameWorld.AbstractWorld;
import GameWorld.Game.Generator.Generator;
import GameWorld.Game.Objects.Antelope;
import GameWorld.Game.Objects.EndGameWindow;
import GameWorld.Game.Objects.GameActor;
import GameWorld.Game.Objects.Giraffe;
import GameWorld.Game.Objects.Ground;
import GameWorld.Game.Objects.Pinguin;
import GameWorld.Game.Objects.Snake;
import GameWorld.Game.Objects.Tree;
import Helper.AssetLoader;
import Helper.Constants;
import Helper.FontLoader;
import Helper.GameContactListener;
import Helper.JumpCountController;
import Helper.Statistic;
import Helper.WorldUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
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
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.screen.DebugScreen;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author qw
 */
public class GameWorld extends AbstractWorld {

    public World world;
    private Ground ground, ground2;
    private Pinguin pinguin;	
    private JumpCountController jumpCountController;
    private Label dirXText;
    private Label dirYText;
    private Label powText;
    private Label jumpCountText;
	
    private EndGameWindow endGameWindow;
	
    private Button debugButton;

    private final float TIME_STEP = 1 / 400f;
    private float accumulator = 0f;
    private float maxX = 0;
    private int objectsGenerateNum = 30;

    public GameWorld(Stage stage, GameLibGDX g) {
        super(stage, g);
        setUpWorld();
        Gdx.app.log("GameWorld", "create");
    }

    private void setUpWorld() {
       world = WorldUtils.createWorld();
        jumpCountController = new JumpCountController(3);
        endGameWindow = new EndGameWindow(stage);

        setUpGround();
        setUpRunner();
        addDebugButton(AssetLoader.btn, AssetLoader.btnPress);     
        world.setContactListener(new GameContactListener(this, pinguin));
        initDirX();
        initDirY();
        initPower();
        initJumpCount();
        createObjects((int) maxX, objectsGenerateNum);        
    }
    
    private void createObjects(int startPos, int count){
        Generator g = new Generator(world, (int)Constants.GROUND_Y ,startPos, count);
        for(GameActor t: g.getObj()){
            stage.addActor(t);
            if(t.getBody().getPosition().x > maxX){
                maxX = t.getBody().getPosition().x;
            }
            Gdx.app.log("GameWorld", "genegate x=" + maxX);
        }
    }

	
    private void drawJumpCount() {
        jumpCountText.setText("Count of jumps: " + jumpCountController.getCountOfJump());
    }
	
    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world, 0f), AssetLoader.btn);        
        ground2 = new Ground(WorldUtils.createGround(world, Constants.GROUND_WIDTH), AssetLoader.btn);
        stage.addActor(ground);
        stage.addActor(ground2);
    }
	
	
    private void addGround() {
        float gr1 = ground.getBody().getPosition().x;
        float gr2 = ground2.getBody().getPosition().x;
        float pinguinX = pinguin.getBody().getPosition().x;
        float dlt = Constants.GROUND_WIDTH / 2 - Constants.APP_WIDTH * 1.5f;
        
        
        if(gr1 >= gr2){
            if(pinguinX >= gr1 + dlt){
                world.destroyBody(ground2.getBody());
                ground2 = new Ground(WorldUtils.createGround(world, Constants.GROUND_WIDTH
                        + ground.getBody().getPosition().x), AssetLoader.btn);
                stage.addActor(ground2);
            } else if(gr2- dlt >= pinguinX){
                world.destroyBody(ground.getBody());
                ground = new Ground(WorldUtils.createGround(world, ground2.getBody().getPosition().x -
                        Constants.GROUND_WIDTH), AssetLoader.btn);
                stage.addActor(ground);
            }
        } else {
            if(pinguinX >= gr2 + dlt){
                world.destroyBody(ground.getBody());
                ground = new Ground(WorldUtils.createGround(world, Constants.GROUND_WIDTH
                        + ground2.getBody().getPosition().x), AssetLoader.btn);
                stage.addActor(ground);
            } else if(gr1 >= pinguinX - dlt){
                world.destroyBody(ground2.getBody());
                ground2 = new Ground(WorldUtils.createGround(world, ground.getBody().getPosition().x -
                        Constants.GROUND_WIDTH), AssetLoader.btn);
                stage.addActor(ground2);
            }
        }
    }

    private void setUpRunner() {
        pinguin = new Pinguin(WorldUtils.createPinguin(world), AssetLoader.btn);
        stage.addActor(pinguin);
    }

    @Override
    public void update(float delta) {
        accumulator += delta;

        addGround();
        Array<Body> bodies = new Array<Body>();       
        world.getBodies(bodies); 
        
        for(Body bod: bodies){ 
            if(pinguin.getBody().getPosition().x - bod.getPosition().x >= Constants.APP_WIDTH * 5){
                bod.setUserData("DELETE");
                bod.getFixtureList().get(0).setUserData("DELETE");
            }
        }
        
        for(Body bod: bodies){ 
            if(bod.getFixtureList().get(0).getUserData() != null
                    && bod.getFixtureList().get(0).getUserData().equals("DELETE")){
                world.destroyBody(bod); 
            }
        }
        
        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 1);
            accumulator -= TIME_STEP;
        }
        moveUI();
        initHit();  
        setAngularPinguin();
        checkHeight();		
        unlimitedGame();            
        drawJumpCount();        
        debugButton.setPosition(stage.getCamera().position.x + stage.getWidth() / 3, stage.getCamera().position.y + stage.getHeight() / 3);
    }
    
    private void unlimitedGame(){
        if(Math.abs(maxX - pinguin.getBody().getPosition().x) <= Constants.APP_WIDTH * 1){
            createObjects((int)maxX, objectsGenerateNum);
        }
    }
    
    private void checkHeight(){
        if(this.getPlayerY() >= Constants.APP_HEIGHT * 1.1){
            pinguin.getBody().setLinearVelocity(new Vector2(10, 0));
        }
    }
    
    private void setAngularPinguin(){
        float angle = pinguin.getBody().getLinearVelocity().angle();
        if(pinguin.getBody().getLinearVelocity().x <= 0.5){
            angle = 0;
        }
        pinguin.setAngle(angle);
        
    }
    
    private void moveUI() {
        dirXText.setX(stage.getCamera().position.x - stage.getWidth() / 2);
        dirYText.setX(stage.getCamera().position.x - stage.getWidth() / 2);
        powText.setX(stage.getCamera().position.x - stage.getWidth() / 2);
        jumpCountText.setX(stage.getCamera().position.x - stage.getWidth() / 2);
    }

    private void initHit() {
        dirXText.setText(String.format("Direction X: %.3f", pinguin.getDirectionX()));
        dirYText.setText(String.format("Direction Y: %.3f", pinguin.getDirectionY()));
        powText.setText("Power: " + pinguin.getPower());
        boolean check = !pinguin.moved() && !endGameWindow.getIsVisible();
        if (!jumpCountController.checkJump() && check) {
            endGameWindow.createWindow(game);
            int i = 0;
        } else if (check) {
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
        dirXText.setFontScale(1);
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
        dirYText.setFontScale(1);
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
        powText.setFontScale(1);
        powText.setSize(stage.getWidth() * 0.4f, stage.getHeight() / 5);
        powText.setPosition(0, 300);
        stage.addActor(powText);
    }

    private void initJumpCount() {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        jumpCountText = new Label("", labelS);
        jumpCountText.setAlignment(Align.center);
        jumpCountText.setFontScale(1);
        jumpCountText.setSize(stage.getWidth() * 0.4f, stage.getHeight() / 5);
        jumpCountText.setPosition(-100, 250);
        stage.addActor(jumpCountText);
    }

    private void addDebugButton(TextureRegion normalState, TextureRegion pressedState) {
        debugButton = new Button("Top", normalState, pressedState, "TOP", FontLoader.font) {
            public void action() {
                Gdx.app.log("check", "check");

                stage.getActors().clear();
                game.setScreen(new DebugScreen(game));

            }
        };
        debugButton.setSize(stage.getWidth() * 0.4f / 3, stage.getHeight() / 6);
        debugButton.setPosition(stage.getCamera().position.x + stage.getWidth() / 3, stage.getCamera().position.y + stage.getHeight() / 3);

        stage.addActor(debugButton);
    }

    public JumpCountController getJumpCountController() {
        return jumpCountController;
    }
}
