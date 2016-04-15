package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLibGDX;

import GameWorld.Skins.Handler.SkinsInputHandler;
import GameWorld.Skins.SkinsRenderer;
import GameWorld.Skins.SkinsWorld;
import Helper.MapsInputHandler;

/**
 * Created by broff on 15.04.2016.
 */
public class BuySkinsScreen extends AbstractScreen {

    public SkinsWorld world;
    public SkinsRenderer render;

    public BuySkinsScreen(GameLibGDX game) {
        super(game);
        SkinsInputHandler sih = new SkinsInputHandler(world);

        Gdx.input.setInputProcessor(sih);
        Gdx.app.log("BuySkinsScreen", "Skins screen created");
    }

    @Override
    protected void initScene() {
        Gdx.app.log("BuySkinsScreen", "initScene");
        this.world = new SkinsWorld(ui, game);
        this.render = new SkinsRenderer(ui, world);
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        render.render();
    }

    @Override
    public void show() {
    }
}