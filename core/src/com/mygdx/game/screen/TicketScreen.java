/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

import GameWorld.Ticket.TicketRenderer;
import GameWorld.Ticket.TicketWorld;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author Pablo
 */
public class TicketScreen extends AbstractScreen {

    private TicketWorld world;
    private TicketRenderer renderer;

    public TicketScreen(GameLibGDX game) {
        super(game);
    }

    @Override
    protected void initScene() {
        this.world = new TicketWorld(ui, game);
        this.renderer = new TicketRenderer(world);
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render();
    }

}
