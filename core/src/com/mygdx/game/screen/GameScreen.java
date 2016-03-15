/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

import GameWorld.Game.GameRenderer;
import GameWorld.Game.GameWorld;
import Helper.InputHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author qw
 */
public class GameScreen extends AbstractScreen {

    private GameWorld world;
    private GameRenderer renderer;

    public GameScreen(GameLibGDX game) {
        super(game);
    }

    @Override
    protected void initScene() {
        Gdx.app.log("GameScreen", "initScene");
        this.world = new GameWorld(stage, game);
        this.renderer = new GameRenderer(world, stage); 
        Gdx.input.setInputProcessor(new InputHandler(world.getPinguin()));
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render(delta);        
    }
    

    @Override
    public void show() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
