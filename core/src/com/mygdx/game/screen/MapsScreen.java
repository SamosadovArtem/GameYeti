/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author qw
 */
public class MapsScreen extends AbstractScreen {

    public MapsScreen(GameLibGDX game) {
        super(game);
    Gdx.app.log("MapScreen", "main screen created");
    }

    @Override
    void loadResources() {
        Gdx.app.log("MapScreen", "loadResources");
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void initScene() {
        Gdx.app.log("MapScreen", "initScene");
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
