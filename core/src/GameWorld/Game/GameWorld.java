/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game;

import GameWorld.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author qw
 */
public class GameWorld extends World {

    public GameWorld(Stage stage, GameLibGDX g){        
        super(stage, g);
        Gdx.app.log("GameWorld", "create");
    }
    
    @Override
    public void update(float delta) {
    }
    
}