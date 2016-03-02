/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

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
        this.game.setScreen(new MapsScreen(game));
        
    }

    @Override
    void loadResources() {
        Gdx.app.log("MainScreen", "loadResources");
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void initScene() {
        Gdx.app.log("MainScreen", "initScene");
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
