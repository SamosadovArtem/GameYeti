/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game;

import GameWorld.Buffs.BuffsWorld;
import GameWorld.Renderer;
import GameWorld.AbstractWorld;
import GameWorld.Main.MainWorld;
import Helper.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.Set;

/**
 *
 * @author qw
 */
public class GameRenderer extends Renderer {
        
    private Box2DDebugRenderer renderer;
    private GameWorld world;
    private Stage stage;

    public GameRenderer(GameWorld world, Stage stage) {
        super();
        this.world = world;
        this.stage = stage;
        
        renderer = new Box2DDebugRenderer();
        renderer.setDrawVelocities(true);
        stage.getCamera().position.x = stage.getWidth() * 0.3f;
        Gdx.app.log("GameRenderer", "create");
    }
    
    @Override
    public void render(){
        Gdx.gl.glClearColor(0, 0,0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);       
        //renderer.render(world.world, cam.combined);
        stage.draw();
    }
}
