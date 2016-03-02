/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Main;

import GameWorld.World;
import com.badlogic.gdx.Gdx;

/**
 *
 * @author qw
 */
public class MainWorld extends World {

    public MainWorld(){
        Gdx.app.log("MainWorld ", "create");
    }
    
    @Override
    public void update(float delta) {
    }
    
}
