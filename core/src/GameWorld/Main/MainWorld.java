/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Main;

import GameWorld.World;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author qw
 */
public class MainWorld extends World {

    public MainWorld(Stage stage){
        Gdx.app.log("MainWorld ", "create");
    }
    
   private Rectangle rect = new Rectangle(0, 0, 17, 12);

    public void update(float delta) {
        Gdx.app.log("MainWorld ", ""+1/delta);
        rect.x++;
        if (rect.x > 400) {
            rect.x = 0;
        }        
    }

    public Rectangle getRect() {
        return rect;
    }
    
}
