/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

import GameWorld.Settings.SettingsRenderer;
import GameWorld.Settings.SettingsWorld;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author Pablo
 */
public class SettingsScreen extends AbstractScreen {

    private SettingsWorld world;
    private SettingsRenderer renderer;

    public SettingsScreen(GameLibGDX game) {
        super(game);
    }

    @Override
    protected void initScene() {
        this.world = new SettingsWorld(ui, game);
        this.renderer = new SettingsRenderer(world, ui);
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render();
    }

    @Override
    public void dispose() {
        world.getUI().dispose();
        // game.dispose();
    }

    @Override
    public void backPress(){
        game.setScreen(new MainScreen(game));
    }
}
