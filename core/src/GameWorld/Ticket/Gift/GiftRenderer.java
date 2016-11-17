/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Ticket.Gift;

import GameWorld.Renderer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 *
 * @author Pablo
 */
public class GiftRenderer extends Renderer {

    private GiftWorld world;

    public GiftRenderer(GiftWorld world) {
        super();
        this.world = world;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.getUI().draw();
    }

}
