/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld;

import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 *
 * @author qw
 */
public abstract class Renderer {
 
    
    protected OrthographicCamera cam;
        
    public Renderer(){
        cam = new OrthographicCamera();
        cam.setToOrtho(true, 800, 480);
    }
    
    public abstract void render();
}
