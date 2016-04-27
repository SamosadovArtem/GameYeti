/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

import GameWorld.Game.Debug.DebugRenderer;
import GameWorld.Game.Debug.DebugWorld;
import GameWorld.Game.GameWorld;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author Pablo
 */
public class DebugScreen extends AbstractScreen {

    private DebugWorld world;
    private DebugRenderer renderer;

    public DebugScreen(GameLibGDX game) {
        super(game);
    }

    @Override
    protected void initScene() {
        this.world = new DebugWorld(ui, game);
        this.renderer = new DebugRenderer(world, ui);
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
