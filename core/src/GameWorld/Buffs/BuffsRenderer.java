/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Buffs;

import Enums.TutorialType;
import GameWorld.Main.MainWorld;
import GameWorld.Renderer;
import GameWorld.AbstractWorld;
import Helper.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.tutorial.TutorialHandler;


/**
 * @author qw
 */
public class BuffsRenderer extends Renderer {

    private ShapeRenderer shapeRenderer;
    BuffsWorld world;

    public BuffsRenderer(BuffsWorld world) {
        super();
        this.world = world;
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.getUI().draw();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        // Говорим shapeRenderer начинать отрисовывать формы
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(0, 0, 0, 0.5f);

        if (TutorialHandler.getType() == TutorialType.BUFF) {
            shapeRenderer.rect(0, 0,
                    Constants.APP_WIDTH * 0.7f, Constants.APP_HEIGHT);
            shapeRenderer.rect(Constants.APP_WIDTH * 0.7f, 0,
                    Constants.APP_WIDTH * 0.1f, Constants.APP_HEIGHT * 41 / 60);
            shapeRenderer.rect(Constants.APP_WIDTH * 0.7f, Constants.APP_HEIGHT * (41f / 60f + 1f / 16f),
                    Constants.APP_WIDTH * 0.1f, Constants.APP_HEIGHT * (1f - 41f / 60f - 1f / 16f));
            shapeRenderer.rect(Constants.APP_WIDTH * 0.8f, 0,
                    Constants.APP_WIDTH * 0.2f, Constants.APP_HEIGHT);
        }
        shapeRenderer.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }
}
