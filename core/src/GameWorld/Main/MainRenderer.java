/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Main;

import Enums.TutorialType;
import GameObjects.Interface;
import GameWorld.Renderer;
import Helper.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.tutorial.Tutorial;
import com.mygdx.game.tutorial.TutorialHandler;

/**
 * @author qw
 */
public class MainRenderer extends Renderer {


    private ShapeRenderer shapeRenderer;
    MainWorld world;
    private Interface ui;

    public MainRenderer(MainWorld world, Interface ui) {
        super();
        this.world = world;
        this.ui = ui;
        Gdx.app.log("MainRenderer", "create");
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        /*
         * 2. Мы отрисовываем однотонный квадрат
         */
        ui.draw();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        // Говорим shapeRenderer начинать отрисовывать формы
        shapeRenderer.begin(ShapeType.Filled);

        shapeRenderer.setColor(0, 0, 0, 0.5f);

        if (TutorialHandler.getType() == TutorialType.PLAY) {
            //ui.getStage().getWidth() * 0.4f, ui.getStage().getHeight() / 5
            //ui.getStage().getWidth()*0.3f,
            //   ui.getStage().getHeight()/2
            shapeRenderer.rect(0, 0,
                    Constants.APP_WIDTH * 0.3f, Constants.APP_HEIGHT);
            shapeRenderer.rect(Constants.APP_WIDTH * 0.3f, 0,
                    Constants.APP_WIDTH * 0.4f, Constants.APP_HEIGHT / 2);
            shapeRenderer.rect(Constants.APP_WIDTH * 0.3f, Constants.APP_HEIGHT * 7 / 10,
                    Constants.APP_WIDTH * 0.4f, Constants.APP_HEIGHT / 2);
            shapeRenderer.rect(Constants.APP_WIDTH * 0.7f, 0,
                    Constants.APP_WIDTH * 0.3f, Constants.APP_HEIGHT);
        } else if (TutorialHandler.getType() == TutorialType.TICKET) {
            shapeRenderer.rect(0, 0,
                    Constants.APP_WIDTH * 0.7f / 3f, Constants.APP_HEIGHT);
            shapeRenderer.rect(Constants.APP_WIDTH * 0.7f / 3f, 0,
                    Constants.APP_WIDTH * 0.4f / 3f, Constants.APP_HEIGHT * 3 / 10);
            shapeRenderer.rect(Constants.APP_WIDTH * 1.1f / 3f, 0,
                    Constants.APP_WIDTH * 1.9f / 3f, Constants.APP_HEIGHT);
            shapeRenderer.rect(Constants.APP_WIDTH * 0.7f / 3f, Constants.APP_HEIGHT * 28 / 60,
                    Constants.APP_WIDTH * 0.4f / 3f, Constants.APP_HEIGHT * 32 / 60);
        } else if (TutorialHandler.getType() == TutorialType.BUFF) {
            shapeRenderer.rect(0, 0,
                    Constants.APP_WIDTH * 0.7f / 3f, Constants.APP_HEIGHT);
            shapeRenderer.rect(Constants.APP_WIDTH * 0.7f / 3f, 0,
                    Constants.APP_WIDTH * 0.4f / 3f, Constants.APP_HEIGHT / 10);
            shapeRenderer.rect(Constants.APP_WIDTH * 1.1f / 3f, 0,
                    Constants.APP_WIDTH * 1.9f / 3f, Constants.APP_HEIGHT);
            shapeRenderer.rect(Constants.APP_WIDTH * 0.7f / 3f, Constants.APP_HEIGHT * 4 / 15,
                    Constants.APP_WIDTH * 0.4f / 3f, Constants.APP_HEIGHT * 11 / 15);
        } else if (TutorialHandler.getType() == TutorialType.PURCHASES) {
            shapeRenderer.rect(0, 0,
                    Constants.APP_WIDTH * 2.6f / 6f, Constants.APP_HEIGHT);
            shapeRenderer.rect(Constants.APP_WIDTH * 1.3f / 3f, 0,
                    Constants.APP_WIDTH * 0.4f / 3f, Constants.APP_HEIGHT / 10);
            shapeRenderer.rect(Constants.APP_WIDTH * 1.7f / 3f, 0,
                    Constants.APP_WIDTH * 1.3f / 3f, Constants.APP_HEIGHT);
            shapeRenderer.rect(Constants.APP_WIDTH * 1.3f / 3f, Constants.APP_HEIGHT * 4 / 15,
                    Constants.APP_WIDTH * 0.4f / 3f, Constants.APP_HEIGHT * 11 / 15);
        } else if (TutorialHandler.getType() == TutorialType.SKIN){
            shapeRenderer.rect(0, 0,
                    Constants.APP_WIDTH * 1.9f / 3f, Constants.APP_HEIGHT);
            shapeRenderer.rect(Constants.APP_WIDTH * 1.9f / 3f, 0,
                    Constants.APP_WIDTH * 0.4f / 3f, Constants.APP_HEIGHT / 10);
            shapeRenderer.rect(Constants.APP_WIDTH * 2.3f / 3f, 0,
                    Constants.APP_WIDTH * 0.7f / 3f, Constants.APP_HEIGHT);
            shapeRenderer.rect(Constants.APP_WIDTH * 1.9f / 3f, Constants.APP_HEIGHT * 4 / 15,
                    Constants.APP_WIDTH * 0.4f / 3f, Constants.APP_HEIGHT * 11 / 15);
        }
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);

    }
}
