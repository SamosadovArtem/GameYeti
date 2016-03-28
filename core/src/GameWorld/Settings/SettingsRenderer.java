/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Settings;

import GameObjects.Interface;
import GameWorld.Main.MainWorld;
import GameWorld.Renderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 *
 * @author Pablo
 */
public class SettingsRenderer extends Renderer {

    private ShapeRenderer shapeRenderer;
    SettingsWorld world;
    private Interface ui;

    public SettingsRenderer(SettingsWorld world, Interface ui) {
        super();
        this.world = world;
        this.ui = ui;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ui.draw();
    }

}
