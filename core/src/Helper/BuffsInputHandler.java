/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import GameWorld.Buffs.BuffsWorld;
import GameWorld.Maps.MapsWorld;
import com.badlogic.gdx.InputProcessor;
import java.util.ArrayList;

/**
 *
 * @author Pablo
 */
public class BuffsInputHandler implements InputProcessor {

    BuffsWorld world;
    private int _oldY;
    boolean isTouched;
    int temp;

    public BuffsInputHandler(BuffsWorld world) {
        this.world = world;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        world.getUI().getStage().touchDown(screenX, screenY, pointer, button);
        world.getUI().getGuiStage().touchDown(screenX, screenY, pointer, button);
        isTouched = true;
        _oldY = screenY;
        temp = _oldY;
//        if(!world.getBuyMapWindow().getCheck()){
//        world.getBuyMapWindow().checkClick(screenX, screenY);
//        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        world.getUI().getStage().touchUp(screenX, screenY, pointer, button);
        world.getUI().getGuiStage().touchUp(screenX, screenY, pointer, button);
        isTouched = false; 
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
                temp = _oldY;
        if ((isTouched)&&(_oldY > screenY)){

            _oldY = screenY;
            if(world.getUI().getStage().getCamera().position.y > world.getMinY()){
                world.getUI().getStage().getCamera().position.y-=temp-screenY;
                System.out.println("down");
            }
        }

        if ((isTouched)&&(_oldY<screenY)){
            _oldY = screenY;
            if(world.getUI().getStage().getCamera().position.y < world.getMaxY()){
            world.getUI().getStage().getCamera().position.y-=temp-screenY;
            
            }
        }
        System.out.println("Камера" + world.getUI().getStage().getCamera().position.y);
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
