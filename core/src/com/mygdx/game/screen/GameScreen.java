/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

import GameWorld.Game.GameRenderer;
import GameWorld.Game.GameWorld;
import GameWorld.Main.MainRenderer;
import GameWorld.Main.MainWorld;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author qw
 */
public class GameScreen extends AbstractScreen {

    public GameWorld world;
    public GameRenderer render;
    
    public GameScreen(GameLibGDX game) {
        super(game);
        Gdx.app.log("GameScreen", "main screen created");
    }
    
    @Override
    protected void initScene() {
        Gdx.app.log("GameScreen", "initScene");
        this.world = new GameWorld();
        this.render = new GameRenderer(world); 
    }
    
    @Override
    public void render(float delta) {
        world.update(delta);
        render.render();
    }
}