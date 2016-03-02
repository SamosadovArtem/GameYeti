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
public class BuffsScreen extends AbstractScreen {

    public BuffsScreen(GameLibGDX game) {
        super(game);
        Gdx.app.log("BuffsScreen", "main screen created");
    }

    @Override
    void loadResources() {
        Gdx.app.log("BuffsScreen", "loadResources");
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void initScene() {
        Gdx.app.log("BuffsScreen", "initScene");
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}