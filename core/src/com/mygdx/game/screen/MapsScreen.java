/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

import GameWorld.Main.MainRenderer;
import GameWorld.Main.MainWorld;
import GameWorld.Maps.MapsRenderer;
import GameWorld.Maps.MapsWorld;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author qw
 */
public class MapsScreen extends AbstractScreen {

    public MapsWorld world;
    public MapsRenderer render;
    
    public MapsScreen(GameLibGDX game) {
        super(game);
        Gdx.app.log("MapScreen", "main screen created");
    }

    @Override
    protected void initScene() {
        Gdx.app.log("MainScreen", "initScene");
        this.world = new MapsWorld();
        this.render = new MapsRenderer(world); 
    }
    
    @Override
    public void render(float delta) {
        world.update(delta);
        render.render();
    }
}
