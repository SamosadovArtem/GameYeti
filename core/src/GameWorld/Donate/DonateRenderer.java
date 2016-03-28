/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Donate;

/**
 *
 * @author AS
 */
import GameObjects.Interface;
import GameWorld.Main.MainWorld;
import GameWorld.Renderer;
import GameWorld.Donate.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class DonateRenderer extends Renderer {
    DonateWorld world;
    ShapeRenderer shapeRenderer;
    private Interface ui;
    public DonateRenderer(DonateWorld world, Interface ui){
        super();
        this.world = world;
        this.ui = ui;
        Gdx.app.log("DonateRenderer", "create");
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);
    }
    @Override
    public void render() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(87 / 255.0f, 109 / 255.0f, 120 / 255.0f, 1);
        //here
        ui.draw();
        shapeRenderer.end();
    }
}
