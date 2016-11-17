/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game;

import GameObjects.Buffs.Buff;
import GameObjects.Button;
import GameObjects.Interface;
import GameObjects.Picture;
import GameWorld.AbstractWorld;
import GameWorld.Game.Generator.Generator;
import GameWorld.Game.Objects.Antelope;
import GameWorld.Game.Objects.EndGameWindow;
import GameWorld.Game.Objects.GameActor;
import GameWorld.Game.Objects.Giraffe;
import GameWorld.Game.Objects.Ground;
import GameWorld.Game.Objects.Pinguin;
import GameWorld.Game.Objects.Tablet;
import GameWorld.Skins.Elements.SkinList;
import Helper.AssetLoader;
import Helper.BuffsInfo;
import Helper.Constants;
import Helper.FontLoader;
import Helper.GameContactListener;
import Helper.JumpCountController;
import Helper.SoundsLoader;
import Helper.WorldUtils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.screen.DebugScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author qw
 */
public class GameWorld extends AbstractWorld {

    public World world;

    private GameMap map = new GameMap(this);
    private Ground ground, ground2;
    private Pinguin pinguin;
    private JumpCountController jumpCountController;
    private Label jumpCountText;
    private boolean pause;

    private EndGameWindow endGameWindow;

    private Button debugButton, pauseButton;

    private final float TIME_STEP = 1 / 400f;
    private float accumulator = 0f;
    private float maxX = 0;
    private int objectsGenerateNum = 5;
    private float heightCoff = BuffsInfo.getHeightSkyCoffBuff().getCount();

    private float xBuffPosition = (float) (ui.getGuiStage().getWidth() * 0.20);
    private float yBuffPosition = ui.getGuiStage().getHeight() - ui.getGuiStage().getHeight() / 10;

    private Thread worldGenerate;

    public GameWorld(Interface ui, GameLibGDX g) {
        super(ui, g);
        pause = false;
        setUpWorld();

        //newWorldGenerate();
    }

    private void setUpWorld() {

        SoundsLoader.LoadGameSounds();
        world = WorldUtils.createWorld(BuffsInfo.getGravityBuff().getGravity());
        //jumpCountController = new JumpCountController(BuffsInfo.getJumpCountBuff().getCount());
        jumpCountController = new JumpCountController(20);

        endGameWindow = new EndGameWindow(ui.getGuiStage());
        setUpGround();
        addTablets(ground.getX(), ground2.getX() + ground2.getWidth());
        setUpRunner();
        addDebugButton(AssetLoader.btn, AssetLoader.btnPress);
        addPauseButton(AssetLoader.pauseTexture, AssetLoader.pauseTexture);
        world.setContactListener(new GameContactListener(this, pinguin));
        initJumpCount();
        createObjects((int) maxX, objectsGenerateNum);

        ui.addBack(game);

        GetBuffsInfo();

    }

    private void createObjects(int startPos, int count) {
        Generator g = new Generator(world, (int) Constants.GROUND_Y, startPos, count, this);
        //map.addUnits(g.getList(), (int) Constants.GROUND_Y, world);
        for (GameActor t : g.getObj()) {
            //map.getStage().addActor(t);        
            ui.getStage().addActor(t);
            if (t.getBody().getPosition().x > maxX) {
                maxX = t.getBody().getPosition().x;
            }
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
        ground = new Ground(WorldUtils.createGround(world, 0f));
        ground2 = new Ground(WorldUtils.createGround(world, Constants.GROUND_WIDTH));
        ui.getStage().addActor(ground);
        ui.getStage().addActor(ground2);
    }

    private void addGround() {
        final float gr1 = ground.getBody().getPosition().x;
        final float gr2 = ground2.getBody().getPosition().x;
        final float pinguinX = pinguin.getBody().getPosition().x;
        final float dlt = Constants.GROUND_WIDTH / 2 - Constants.APP_WIDTH * 1.5f;

        if (gr1 >= gr2) {
            worldGenerate = new Thread(new Runnable() {
                public void run() {
                    if (pinguinX >= gr1 + dlt) {
                        world.destroyBody(ground2.getBody());
                        ground2 = new Ground(WorldUtils.createGround(world, Constants.GROUND_WIDTH
                                + ground.getBody().getPosition().x));
                        ui.getStage().addActor(ground2);
                        addTablets(ground.getX() + ground.getWidth(), ground2.getX() + ground2.getWidth());

                    } else if (gr2 - dlt >= pinguinX) {
                        world.destroyBody(ground.getBody());
                        ground = new Ground(WorldUtils.createGround(world, ground2.getBody().getPosition().x
                                - Constants.GROUND_WIDTH));
                        ui.getStage().addActor(ground);
                        addTablets(ground2.getX() + ground2.getWidth(), ground.getX() + ground.getWidth());
                    }
                }
            });
            worldGenerate.start();
        } else if (pinguinX >= gr2 + dlt) {
            worldGenerate = new Thread(new Runnable() {
                public void run() {
                    world.destroyBody(ground.getBody());
                    ground = new Ground(WorldUtils.createGround(world, Constants.GROUND_WIDTH
                            + ground2.getBody().getPosition().x));
                    ui.getStage().addActor(ground);
                    addTablets(ground2.getX() + ground2.getWidth(), ground.getX() + ground.getWidth());
                }
            });
            worldGenerate.start();
        } else if (gr1 >= pinguinX - dlt) {
            worldGenerate = new Thread(new Runnable() {
                public void run() {
                    worldGenerate.start();
                    world.destroyBody(ground2.getBody());
                    ground2 = new Ground(WorldUtils.createGround(world, ground.getBody().getPosition().x
                            - Constants.GROUND_WIDTH));
                    ui.getStage().addActor(ground2);
                    addTablets(ground.getX() + ground.getWidth(), ground2.getX() + ground2.getWidth());
                }
            });
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
        pinguin = new Pinguin(WorldUtils.createPinguin(world));
        ui.getStage().addActor(pinguin);
    }

    @Override
    public void update(float delta) {
        ui.updateFps(1 / delta);

        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 1);
            accumulator -= TIME_STEP;
        }
        initHit();
        setAngularPinguin();
        checkHeight();
        drawJumpCount();
        map.focusCameraX(pinguin);

        unlimitedGame();
        addGround();

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body bod : bodies) {
            if (bod.getFixtureList().size != 0 && bod.getFixtureList().get(0).getUserData().equals("DELETE")) {
                world.destroyBody(bod);
            }
            if (pinguin.getBody().getPosition().x - bod.getPosition().x >= Constants.APP_WIDTH * 5
                    && bod.getFixtureList().get(0).getUserData() != "GROUND") {
                bod.getFixtureList().get(0).setUserData(null);

                world.destroyBody(bod);
                //bod.getFixtureList().get(0).setUserData(null);
            }
        }
    }

    private void unlimitedGame() {
        if (Math.abs(maxX - pinguin.getBody().getPosition().x) <= Constants.APP_WIDTH * 4) {
            worldGenerate = new Thread(new Runnable() {
                public void run() {
                    createObjects((int) maxX, 5);
                }
            });
            worldGenerate.start();
        }
    }

    private void checkHeight() {
        if (this.getPlayerY() >= Gdx.app.getGraphics().getHeight() * heightCoff) {
            pinguin.getBody().setLinearVelocity(new Vector2(10, -1));
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
            endGameWindow.showWindow(game);
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
        debugButton = new Button("Top", normalState, pressedState, "", FontLoader.font) {
            public void action() {
                ui.getStage().getActors().clear();
                game.setScreen(new DebugScreen(game));

            }
        };
        debugButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        debugButton.setPosition(ui.getStage().getCamera().position.x,
                ui.getStage().getCamera().position.y + ui.getStage().getHeight() / 3);

        ui.getGuiStage().addActor(debugButton);
    }

    private void addPauseButton(TextureRegion normalState, TextureRegion pressedState) {
        pauseButton = new Button("Top", normalState, pressedState, "", FontLoader.font) {
            public void action() {

                pause = !pause;

            }
        };
        pauseButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        pauseButton.setPosition(ui.getStage().getCamera().position.x + ui.getStage().getWidth() / 3,
                ui.getStage().getCamera().position.y + ui.getStage().getHeight() / 3);

        ui.getGuiStage().addActor(pauseButton);
    }

    public void addTablet(float x) {
        Tablet tablet = new Tablet("1", AssetLoader.textureBtnNormal, x);
        tablet.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        tablet.setPosition(x, Constants.GROUND_HEIGHT + Constants.GROUND_Y - tablet.getHeight() / 2);
        tablet.toBack();
        ui.getStage().addActor(tablet);
    }

    public JumpCountController getJumpCountController() {
        return jumpCountController;
    }

    public EndGameWindow getEndGameWindow() {
        return endGameWindow;
    }

    private void GetBuffsInfo() {
        List<Buff> allBufs = BuffsInfo.getBuffs();

        for (int i = 0; i < allBufs.size(); i++) {

            if (allBufs.get(i).getLevel() > 0) {
                Picture buff = allBufs.get(i).getIcon();
                buff.setSize(ui.getStage().getWidth() * 0.05f, ui.getStage().getHeight() / 15);
                buff.setPosition(xBuffPosition, yBuffPosition);

                xBuffPosition += buff.getWidth() * 1.5;

                ui.getGuiStage().addActor(buff);
            }
            //xBuffPosition = buff
        }
    }
}
