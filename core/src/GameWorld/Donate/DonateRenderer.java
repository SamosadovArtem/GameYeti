/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Donate;

/**
 * @author AS
 */

import Enums.TutorialType;
import GameObjects.Interface;
import GameWorld.Main.MainWorld;
import GameWorld.Renderer;
import GameWorld.Donate.*;
import Helper.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.tutorial.TutorialHandler;

public class DonateRenderer extends Renderer {
    DonateWorld world;
    ShapeRenderer shapeRenderer;
    private Interface ui;

    public DonateRenderer(DonateWorld world, Interface ui) {
        super();
        this.world = world;
        this.ui = ui;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    @Override
    public void render() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        ui.draw();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        // Говорим shapeRenderer начинать отрисовывать формы
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(255, 0, 0, 0.5f);

        if (TutorialHandler.getType() == TutorialType.PURCHASES) {
            shapeRenderer.rect(0, 0,
                    Constants.APP_WIDTH / 4f, Constants.APP_HEIGHT);
            shapeRenderer.rect(Constants.APP_WIDTH / 4f, 0,
                    Constants.APP_WIDTH / 4f, Constants.APP_HEIGHT * 5f / 8f);
            shapeRenderer.rect(Constants.APP_WIDTH / 4f, Constants.APP_HEIGHT * 7f / 8f,
                    Constants.APP_WIDTH / 4f, Constants.APP_HEIGHT / 8f);
            shapeRenderer.rect(Constants.APP_WIDTH / 2f, 0,
                    Constants.APP_WIDTH / 2f, Constants.APP_HEIGHT);
        }
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }
}
