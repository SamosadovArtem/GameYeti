/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Statistic;

import GameObjects.Interface;
import GameWorld.Renderer;
import GameWorld.Settings.SettingsWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author qw
 */
public class StatisticRenderer extends Renderer {

    StatisticWorld world;
    private Interface ui;

    public StatisticRenderer(StatisticWorld world, Interface ui) {
        super();
        this.world = world;
        this.ui = ui;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ui.draw();
    }

}