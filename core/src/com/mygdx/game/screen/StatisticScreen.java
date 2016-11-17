/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

import GameWorld.Donate.DonateRenderer;
import GameWorld.Donate.DonateWorld;
import GameWorld.Statistic.StatisticRenderer;
import GameWorld.Statistic.StatisticWorld;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author qw
 */
public class StatisticScreen extends AbstractScreen{
    public StatisticWorld world;
    public StatisticRenderer renderer;

    public StatisticScreen(GameLibGDX game) {
        super(game);
    }
    @Override
    public void render(float delta) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        world.update(delta);
        renderer.render();
    }
    @Override
    public void initScene(){
        Gdx.app.log("DonateScreen","created");
        this.world = new StatisticWorld(ui, game);
        renderer = new StatisticRenderer(this.world, ui);
    }
    @Override
    public void dispose() {
        world.getUI().dispose();
    }

    @Override
    public void backPress() {
        game.setScreen(new MainScreen(game));
    }
}
