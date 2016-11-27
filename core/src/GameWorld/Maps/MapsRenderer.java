/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Maps;

import Enums.TutorialType;
import GameObjects.Interface;
import GameWorld.Renderer;
import Helper.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.tutorial.TutorialHandler;

/**
 * @author qw
 */
public class MapsRenderer extends Renderer {

    private ShapeRenderer shapeRenderer;
    MapsWorld world;
    private Interface ui;

    public MapsRenderer(MapsWorld world, Interface ui) {
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

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        // Говорим shapeRenderer начинать отрисовывать формы
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(0, 0, 0, 0.5f);

        if (TutorialHandler.getType() == TutorialType.PLAY) {
            shapeRenderer.rect(0, 0,
                    Constants.APP_WIDTH * 0.3f, Constants.APP_HEIGHT);
            shapeRenderer.rect(Constants.APP_WIDTH * 0.3f, 0,
                    Constants.APP_WIDTH * 0.4f, Constants.APP_HEIGHT * 2 / 5);
            shapeRenderer.rect(Constants.APP_WIDTH * 0.3f, Constants.APP_HEIGHT * 3 / 5,
                    Constants.APP_WIDTH * 0.4f, Constants.APP_HEIGHT * 2 / 5);
            shapeRenderer.rect(Constants.APP_WIDTH * 0.7f, 0,
                    Constants.APP_WIDTH * 0.3f, Constants.APP_HEIGHT);
        }
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

}