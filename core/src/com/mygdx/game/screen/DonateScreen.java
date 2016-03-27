/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

/**
 *
 * @author AS
 */
import GameWorld.World;
import GameWorld.Buffs.BuffsWorld;
import GameWorld.Main.MainRenderer;
import GameWorld.Main.MainWorld;
import com.badlogic.gdx.Gdx;
import GameWorld.Donate.*;
import com.mygdx.game.GameLibGDX;

public class DonateScreen extends AbstractScreen{
    public DonateWorld world;
    public DonateRenderer renderer;

    public DonateScreen(GameLibGDX game) {
        super(game);
    }
    @Override
    public void render(float delta) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        world.update(delta);
        renderer.render();
    }
    @Override
    public void initScene(){
        Gdx.app.log("DonateScreen","created");
        this.world = new DonateWorld(stage, game);
        renderer = new DonateRenderer(this.world,stage);
    }
}
