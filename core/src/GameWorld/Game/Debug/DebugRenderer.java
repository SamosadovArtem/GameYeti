/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Debug;

import GameWorld.Main.MainWorld;
import GameWorld.Renderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author Pablo
 */
public class DebugRenderer extends Renderer {

    private ShapeRenderer shapeRenderer;
    private DebugWorld world;
    private Stage stage;

    public DebugRenderer(DebugWorld world, Stage stage) {
        super();
        this.world = world;
        this.stage = stage;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
    }
}
