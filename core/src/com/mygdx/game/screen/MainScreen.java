/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

import GameWorld.Main.MainRenderer;
import GameWorld.Main.MainWorld;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLibGDX;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author qw
 */
public class MainScreen extends AbstractScreen {

    public MainScreen(GameLibGDX game) {
        super(game);
        Gdx.app.log("MainScreen", "main screen created");
    }

    @Override
    protected void initScene() {  
        Gdx.app.log("MainScreen", "initScene");
        this.world = new MainWorld();
        this.render = new MainRenderer(world);        
    }
    
}
