/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

import GameWorld.Buffs.BuffsRenderer;
import GameWorld.Buffs.BuffsWorld;
import GameWorld.Main.MainRenderer;
import GameWorld.Main.MainWorld;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author qw
 */
public class BuffsScreen extends AbstractScreen {

    public BuffsWorld world;
    public BuffsRenderer render;
    
    public BuffsScreen(GameLibGDX game) {
        super(game);
        Gdx.app.log("BuffsScreen", "main screen created");
    }

    @Override
    protected void initScene() {
        Gdx.app.log("BuffsScreen", "initScene");
        this.world = new BuffsWorld();
        this.render = new BuffsRenderer(world);
    }    
    
    @Override
    public void render(float delta) {
        world.update(delta);
        render.render();
    }
}