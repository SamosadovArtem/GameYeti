/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld;

import Helper.Constants;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 *
 * @author qw
 */
public abstract class Renderer {
 
    
    public OrthographicCamera cam;
        
    public Renderer(){
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Constants.APP_WIDTH, Constants.APP_HEIGHT);
    }
    
    public abstract void render();
}
