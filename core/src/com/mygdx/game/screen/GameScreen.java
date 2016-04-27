/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

import GameWorld.Game.GameRenderer;
import GameWorld.Game.GameWorld;
import Helper.InputHandler;
import Helper.Statistic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author qw
 */
public class GameScreen extends AbstractScreen {

    private GameWorld world;
    private GameRenderer renderer;
    private float cameraCoff = 30.0f;

    public GameScreen(GameLibGDX game) {
        super(game);
    }

    @Override
    protected void initScene() {
        Gdx.app.log("GameScreen", "initScene");
        this.world = new GameWorld(ui, game);
        this.renderer = new GameRenderer(world, ui, world.getMap());
        Gdx.input.setInputProcessor(new InputHandler(world));
        Statistic.playGame();
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        moveCamera();
        //Gdx.app.log("GameWorld", "xPos" + world.getPinguin().getBody().getPosition().x);
        //Gdx.app.log("GameWorld", "xPosCam" + stage.getCamera().position.x);
        renderer.render();
    }

    private void moveCamera() {
        float speed = 0;
        if (world.getPlayerX() != getCameraX() + ui.getStage().getWidth() * 0.3f) {
            speed = (world.getPlayerX() - getCameraX() + ui.getStage().getWidth() * 0.3f) / cameraCoff;
        }
        setCameraX(getCameraX() + speed);
    }

    @Override
    public void show() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        world.hide();
    }

    @Override
    public void pause() {
        world.pause();
    }

    @Override
    public void resume() {
        world.resume();
    }
}
