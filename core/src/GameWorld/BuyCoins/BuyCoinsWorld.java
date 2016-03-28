/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.BuyCoins;

import GameObjects.Interface;
import GameWorld.AbstractWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author qw
 */
public class BuyCoinsWorld extends AbstractWorld {

    public BuyCoinsWorld(Interface ui, GameLibGDX g) {
        super(ui, g);
        Gdx.app.log("BuyCoinsWorld", "create");
    }

    @Override
    public void update(float delta) {
    }

}
