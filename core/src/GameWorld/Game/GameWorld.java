/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game;

import GameObjects.Button;
import GameObjects.Interface;
import GameWorld.AbstractWorld;
import GameWorld.Game.Generator.Generator;
import GameWorld.Game.Objects.EndGameWindow;
import GameWorld.Game.Objects.GameActor;
import GameWorld.Game.Objects.Ground;
import GameWorld.Game.Objects.Pinguin;
import GameWorld.Game.Objects.Tablet;
import Helper.AssetLoader;
import Helper.Constants;
import Helper.FontLoader;
import Helper.GameContactListener;
import Helper.JumpCountController;
import Helper.WorldUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.screen.DebugScreen;

/**
 *
 * @author qw
 */
public class GameWorld extends AbstractWorld {
    
    public World world;
    
    private GameMap map = new GameMap();
    private Ground ground, ground2;
    private Pinguin pinguin;
    private JumpCountController jumpCountController;
    private Label jumpCountText;
    
    private EndGameWindow endGameWindow;
    
    private Button debugButton;
    
    private final float TIME_STEP = 1 / 400f;
    private float accumulator = 0f;
    private float maxX = 0;
    private int objectsGenerateNum = 30;
    
    public GameWorld(Interface ui, GameLibGDX g) {
        super(ui, g);
        setUpWorld();
        Gdx.app.log("GameWorld", "create");
    }
    
    private void setUpWorld() {
        world = WorldUtils.createWorld();
        jumpCountController = new JumpCountController(3);
        endGameWindow = new EndGameWindow(ui.getGuiStage());
        setUpGround();
        addTablets(ground.getX(), ground2.getX() + ground2.getWidth());
        setUpRunner();
        addDebugButton(AssetLoader.btn, AssetLoader.btnPress);
        world.setContactListener(new GameContactListener(this, pinguin));
        initJumpCount();
        createObjects((int) maxX, objectsGenerateNum);
        ui.addBack(game);        
    }
    
    private void createObjects(int startPos, int count) {
        Generator g = new Generator(world, (int) Constants.GROUND_Y, startPos, count);
        //map.addUnits(g.getList(), (int) Constants.GROUND_Y, world);
        for (GameActor t : g.getObj()) {
            //map.getStage().addActor(t);        
            ui.getStage().addActor(t);            
            if (t.getBody().getPosition().x > maxX) {
                maxX = t.getBody().getPosition().x;
            }
            Gdx.app.log("GameWorld", "genegate x=" + maxX);
        }
        for (GameActor t : g.getMapObj()) {            
            map.getStage().addActor(t);            
        }
    }
    
    public GameMap getMap() {
        return map;
    }
    
    private void drawJumpCount() {
        jumpCountText.setText("Count of jumps: " + jumpCountController.getCountOfJump());
    }
    
    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world, 0f), AssetLoader.btn);
        ground2 = new Ground(WorldUtils.createGround(world, Constants.GROUND_WIDTH), AssetLoader.btn);
        ui.getStage().addActor(ground);
        ui.getStage().addActor(ground2);
    }
    
    private void addGround() {
        float gr1 = ground.getBody().getPosition().x;
        float gr2 = ground2.getBody().getPosition().x;
        float pinguinX = pinguin.getBody().getPosition().x;
        float dlt = Constants.GROUND_WIDTH / 2 - Constants.APP_WIDTH * 1.5f;
        
        if (gr1 >= gr2) {
            if (pinguinX >= gr1 + dlt) {
                world.destroyBody(ground2.getBody());
                ground2 = new Ground(WorldUtils.createGround(world, Constants.GROUND_WIDTH
                        + ground.getBody().getPosition().x), AssetLoader.btn);
                ui.getStage().addActor(ground2);
                addTablets(ground.getX() + ground.getWidth(), ground2.getX() + ground2.getWidth());
                
            } else if (gr2 - dlt >= pinguinX) {
                world.destroyBody(ground.getBody());
                ground = new Ground(WorldUtils.createGround(world, ground2.getBody().getPosition().x
                        - Constants.GROUND_WIDTH), AssetLoader.btn);
                ui.getStage().addActor(ground);
                addTablets(ground2.getX() + ground2.getWidth(), ground.getX() + ground.getWidth());
            }
        } else if (pinguinX >= gr2 + dlt) {
            world.destroyBody(ground.getBody());
            ground = new Ground(WorldUtils.createGround(world, Constants.GROUND_WIDTH
                    + ground2.getBody().getPosition().x), AssetLoader.btn);
            ui.getStage().addActor(ground);
            addTablets(ground2.getX() + ground2.getWidth(), ground.getX() + ground.getWidth());
        } else if (gr1 >= pinguinX - dlt) {
            world.destroyBody(ground2.getBody());
            ground2 = new Ground(WorldUtils.createGround(world, ground.getBody().getPosition().x
                    - Constants.GROUND_WIDTH), AssetLoader.btn);
            ui.getStage().addActor(ground2);
            addTablets(ground.getX() + ground.getWidth(), ground2.getX() + ground2.getWidth());
        }
    }
    
    private void addTablets(float a, float b) {
        float c = b - a;
        int i = (int) (a / 1000);
        int d = (int) (c / 1000);
        d += i;
        if (d >= 1) {
            for (; i <= d; i++) {
                addTablet(i * 1000);
            }
        }
    }
    
    private void setUpRunner() {
        pinguin = new Pinguin(WorldUtils.createPinguin(world), AssetLoader.btn);
        ui.getStage().addActor(pinguin);
    }
    
    @Override
    public void update(float delta) {        
        
        ui.updateFps(1 / delta);
        
        accumulator += delta;
        
        addGround();
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        
        for (Body bod : bodies) {
            if (pinguin.getBody().getPosition().x - bod.getPosition().x >= Constants.APP_WIDTH * 5
                    && bod != null) {
                bod.setUserData("DELETE");
                bod.getFixtureList().get(0).setUserData("DELETE");
            }
        }
        
        for (Body bod : bodies) {
            if (bod.getFixtureList().get(0).getUserData() != null
                    && bod.getFixtureList().get(0).getUserData().equals("DELETE")) {
                if (world.isLocked()) {
                    world.destroyBody(bod);
                }
            }
        }
        
        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 1);
            accumulator -= TIME_STEP;
        }
        //moveUI();
        initHit();
        setAngularPinguin();
        checkHeight();
        unlimitedGame();
        drawJumpCount();
        map.focusCameraX(pinguin);
        //   debugButton.setPosition(stage.getCamera().position.x + stage.getWidth() / 3, stage.getCamera().position.y + stage.getHeight() / 3);
    }
    
    private void unlimitedGame() {
        if (Math.abs(maxX - pinguin.getBody().getPosition().x) <= Constants.APP_WIDTH * 1) {
            createObjects((int) maxX, objectsGenerateNum);
        }
    }
    
    private void checkHeight() {
        if (this.getPlayerY() >= Constants.APP_HEIGHT * 1.1) {
            pinguin.getBody().setLinearVelocity(new Vector2(10, 0));
        }
    }
    
    private void setAngularPinguin() {
        float angle = pinguin.getBody().getLinearVelocity().angle();
        if (pinguin.getBody().getLinearVelocity().x <= 0.5) {
            angle = 0;
        }
        pinguin.setAngle(angle);
        
    }
    
    private void initHit() {
        boolean check = !pinguin.moved() && !endGameWindow.getIsVisible();
        if (!jumpCountController.checkJump() && check) {
            endGameWindow.initHighscore((int) pinguin.getX());
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
    
    private void initJumpCount() {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        jumpCountText = new Label("", labelS);
        jumpCountText.setAlignment(Align.center);
        jumpCountText.setFontScale(1);
        jumpCountText.setSize(ui.getStage().getWidth() * 0.4f, ui.getStage().getHeight() / 5);
        jumpCountText.setPosition(0, 250);
        ui.getGuiStage().addActor(jumpCountText);
    }
    
    private void addDebugButton(TextureRegion normalState, TextureRegion pressedState) {
        debugButton = new Button("Top", normalState, pressedState, "TOP", FontLoader.font) {
            public void action() {
                Gdx.app.log("check", "check");
                
                ui.getStage().getActors().clear();
                game.setScreen(new DebugScreen(game));
                
            }
        };
        debugButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        debugButton.setPosition(ui.getStage().getCamera().position.x + ui.getStage().getWidth() / 3,
                ui.getStage().getCamera().position.y + ui.getStage().getHeight() / 3);
        
        ui.getGuiStage().addActor(debugButton);
    }
    
    public void addTablet(float x) {
        Tablet tablet = new Tablet("1", AssetLoader.textureBtnNormal, x);
        tablet.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        tablet.setPosition(x, Constants.GROUND_HEIGHT + Constants.GROUND_Y - tablet.getHeight() / 2);
        ui.getStage().addActor(tablet);
    }
    
    public JumpCountController getJumpCountController() {
        return jumpCountController;
    }
    
    public EndGameWindow getEndGameWindow() {
        return endGameWindow;
    }
}
