/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game;

import GameObjects.Interface;
import GameObjects.Picture;
import GameWorld.Buffs.BuffsWorld;
import GameWorld.Renderer;
import GameWorld.AbstractWorld;
import GameWorld.Main.MainWorld;
import Helper.AssetLoader;
import Helper.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.CpuSpriteBatch;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    private GameMap map;
    private GameWorld world;
    private Interface ui;
    private SpriteBatch batch;
    private Texture background;
    private int sourceX = 0;

    public GameRenderer(GameWorld world, Interface ui, GameMap map) {
        super();
        background = AssetLoader.gameBackground;
        background.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
        batch = new SpriteBatch();
        Picture pc = new Picture(background);
        this.world = world;
        this.ui = ui;
        this.map = map;
        
        renderer = new Box2DDebugRenderer();
        renderer.setDrawVelocities(true);
        ui.getStage().getCamera().position.x = ui.getStage().getWidth() * 0.3f;
        Gdx.app.log("GameRenderer", "create");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //renderer.render(world.world, cam.combined);
        batch.begin();
        batch.draw(background,0,(int)(Constants.GROUND_HEIGHT / 2 + Constants.GROUND_Y),
                Gdx.graphics.getWidth(), Gdx.graphics.getHeight() - (int)(Constants.GROUND_HEIGHT / 2 + Constants.GROUND_Y)
                ,sourceX, 0, 1200, 676, true, false);
        
        batch.end();
        sourceX =-(int) world.getPlayerX();
        map.draw();
        ui.draw();        
        
    }
    
}
