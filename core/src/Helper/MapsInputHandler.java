/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import GameObjects.Map;
import GameWorld.Maps.MapsWorld;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class MapsInputHandler implements InputProcessor{

    MapsWorld world;
    boolean isTouched;
    private int _oldX;
    
    private float min=100000, max=0;
    
    public MapsInputHandler(ArrayList<Float> list, MapsWorld world){
        this.world = world;
        for(float f : list){
            if(min > f){
                min = f;
            }
            if(max < f){
                max = f;
            }
        }
    }
    
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        isTouched = true;
        world.isNotTouched = false;    
        _oldX = screenX;
        world.stage.touchDown(screenX, screenY, pointer, button);
        
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        isTouched = false; 
        world.calculateBtnPos();
        world.isNotTouched = true;        
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) { 
        int temp = _oldX;
        if ((isTouched)&&(_oldX > screenX)){

            _oldX = screenX;
            if(world.stage.getCamera().position.x < max){
                world.stage.getCamera().position.x+=temp-screenX;
            }
            System.out.println(screenX);
        }

        if ((isTouched)&&(_oldX<screenX)){
            _oldX = screenX;
            if(world.stage.getCamera().position.x > min){
            world.stage.getCamera().position.x+=temp-screenX;
            }
            System.out.println(screenX);
        }
        return true; 
    }
    
    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return true;
    }
        
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return true;
    }
    
}
