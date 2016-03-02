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
public class GameScreen extends AbstractScreen {

    public GameScreen(GameLibGDX game) {
        super(game);
        Gdx.app.log("GameScreen", "main screen created");
    }

    @Override
    void loadResources() {
        Gdx.app.log("GameScreen", "loadResources");
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void initScene() {
        Gdx.app.log("GameScreen", "initScene");
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}