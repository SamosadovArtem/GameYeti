/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

import Enums.GiftType;
import GameWorld.Ticket.Gift.GiftRenderer;
import GameWorld.Ticket.Gift.GiftWorld;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author Pablo
 */
public class GiftScreen extends AbstractScreen {

    private GiftWorld world;
    private GiftRenderer renderer;

    private static GiftType type;

    public GiftScreen(GameLibGDX game) {
        super(game);
    }
    
    public static void initType(GiftType type){
        GiftScreen.type = type;
    }

    @Override
    protected void initScene() {
        world = new GiftWorld(ui, game, type);
        renderer = new GiftRenderer(world);
    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render();
    }

    @Override
    public void backPress() {
        game.setScreen(new MainScreen(game));
    }
}
