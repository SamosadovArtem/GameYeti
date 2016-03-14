/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Main;

import GameWorld.Renderer;
import GameWorld.AbstractWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author qw
 */
public class MainRenderer extends Renderer {

       
    private ShapeRenderer shapeRenderer;
    MainWorld world;
    Stage stage;
    
    public MainRenderer(MainWorld world, Stage stage) {
        super();
        this.world = world;
        this.stage = stage;
        Gdx.app.log("MainRenderer", "create");
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }

    @Override
    public void render() {
         Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /*
         * 2. Мы отрисовываем однотонный квадрат
         */

        // Говорим shapeRenderer начинать отрисовывать формы
        shapeRenderer.begin(ShapeType.Filled);

        // Выбираем RGB Color 87, 109, 120, не прозрачный
        shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

        // Отрисовываем квадрат из myWorld (Используем ShapeType.Filled)
        shapeRenderer.rect(world.getRect().x, world.getRect().y,
                world.getRect().width, world.getRect().height);

        // говорим shapeRenderer прекратить отрисовку
        // Мы ДОЛЖНЫ каждый раз это делать
        shapeRenderer.end();

        /*
         * 3. Мы отрисовываем рамку для квадрата
         */

        // Говорим shapeRenderer нарисовать рамку следующей формы
        shapeRenderer.begin(ShapeType.Line);

        // Выбираем цвет RGB Color 255, 109, 120, не прозрачный
        shapeRenderer.setColor(255 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);

        // Отрисовываем квадрат из myWorld (Using ShapeType.Line)
        shapeRenderer.rect(world.getRect().x, world.getRect().y,
                world.getRect().width, world.getRect().height);

        shapeRenderer.end();
        stage.draw();
    }
    
}
