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
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author qw
 */
public class GameRenderer extends Renderer {

    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;
    
    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;
    private GameWorld world;
    private Stage stage;

    public GameRenderer(GameWorld world, Stage stage) {
        super();
        this.world = world;
        this.stage = stage;
        
        setupCamera();        
        renderer = new Box2DDebugRenderer();
        Gdx.app.log("GameRenderer", "create");
    }

    private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();  
    }
        
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0,0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //camera.position.set(world.getPlayerX(), camera.viewportHeight / 2, 0f);
        
        renderer.render(world.world, camera.combined);
    }
    
    @Override
    public void render(){}
}
