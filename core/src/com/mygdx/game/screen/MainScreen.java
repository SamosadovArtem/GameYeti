/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

import GameWorld.Main.MainRenderer;
import GameWorld.Main.MainWorld;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author qw
 */
public class MainScreen extends AbstractScreen {

    public MainWorld world;
    public MainRenderer render;

    public MainScreen(GameLibGDX game) {
        super(game);
        Gdx.app.log("MainScreen", "main screen created");
    }

    @Override
    protected void initScene() {
        Gdx.app.log("MainScreen", "initScene");
        this.world = 
                new MainWorld(ui, game);
        this.render = new MainRenderer(world, ui);
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        render.render();
    }

    @Override
    public void backPress(){
        Gdx.app.exit();
    }

}
