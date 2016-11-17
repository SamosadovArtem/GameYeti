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
import Helper.BuffsInputHandler;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLibGDX;

/**
 * @author qw
 */
public class BuffsScreen extends AbstractScreen {

    public BuffsWorld world;
    public BuffsRenderer renderer;

    public BuffsScreen(GameLibGDX game) {
        super(game);
    }

    @Override
    protected void initScene() {
        this.world = new BuffsWorld(ui, game);
        this.renderer = new BuffsRenderer(world);
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new BuffsInputHandler(world, game));
    }

    @Override
    public void hide() {
        this.world.getThread().interrupt();
    }

    @Override
    public void backPress() {
        game.setScreen(new MainScreen(game));
    }
}
