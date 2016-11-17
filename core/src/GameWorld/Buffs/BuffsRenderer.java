/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Buffs;

import GameWorld.Main.MainWorld;
import GameWorld.Renderer;
import GameWorld.AbstractWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


/**
 *
 * @author qw
 */
public class BuffsRenderer extends Renderer {

    BuffsWorld world;
    
    public BuffsRenderer(BuffsWorld world) {
        super();
        this.world = world;
        Gdx.app.log("BuffsRenderer", "create");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.getUI().draw();
    }  
}
