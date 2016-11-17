package com.mygdx.game.screen;

import com.mygdx.game.GameLibGDX;

import GameWorld.BuyCoins.BuyCoinsRenderer;
import GameWorld.BuyCoins.BuyCoinsWorld;

/**
 * Created by User on 17.11.2016.
 */

public class BuyCoinsScreen extends AbstractScreen {

    public BuyCoinsWorld world;
    public BuyCoinsRenderer render;

    public BuyCoinsScreen(GameLibGDX game) {
        super(game);
    }

    @Override
    protected void initScene() {
        this.world = new BuyCoinsWorld(ui, game);
        this.render = new BuyCoinsRenderer(world);
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        render.render();
    }

    @Override
    public void backPress() {
        game.setScreen(new MainScreen(game));
    }
}