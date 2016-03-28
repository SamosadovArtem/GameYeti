/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Maps;

import GameObjects.Interface;
import GameWorld.Renderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author qw
 */
public class MapsRenderer extends Renderer {

    MapsWorld world;
    private Interface ui;
    
    public MapsRenderer(MapsWorld world, Interface ui) {
        super();
        this.world = world;
        this.ui = ui;
        Gdx.app.log("MapsRenderer", "create");
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        ui.draw();
    }
    
}