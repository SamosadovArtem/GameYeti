/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld;

import GameObjects.Interface;
import Helper.Statistic;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author qw
 */
public abstract class AbstractWorld {

    public Interface ui;

    protected GameLibGDX game;

    public AbstractWorld(Interface ui, GameLibGDX g) {
        this.ui = ui;
        this.game = g;
    }

    public abstract void update(float delta);
    
    public Interface getUI(){
        return ui;
    }
}
