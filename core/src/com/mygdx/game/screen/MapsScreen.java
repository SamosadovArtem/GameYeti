/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

import Enums.TutorialType;
import GameWorld.Main.MainRenderer;
import GameWorld.Main.MainWorld;
import GameWorld.Maps.MapsRenderer;
import GameWorld.Maps.MapsWorld;
import Helper.MapsInputHandler;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.tutorial.TutorialHandler;

/**
 * @author qw
 */
public class MapsScreen extends AbstractScreen {

    public MapsWorld world;
    public MapsRenderer render;

    public MapsScreen(GameLibGDX game) {
        super(game);
        MapsInputHandler mph = new MapsInputHandler(world.GetButtonPositions(), world, game);
        Gdx.input.setInputProcessor(mph);
    }

    @Override
    protected void initScene() {
        this.world = new MapsWorld(ui, game);
        this.render = new MapsRenderer(world, ui);
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        render.render();
        ui.getStage().getCamera().update();
        getStage().getCamera().update();
    }

    @Override
    public void show() {
    }

    @Override
    public void backPress() {
        if (TutorialHandler.getType() != TutorialType.PLAY) {
            game.setScreen(new MainScreen(game));
        }
    }
}
