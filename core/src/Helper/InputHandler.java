/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import GameWorld.Game.GameWorld;
import GameWorld.Game.Objects.Pinguin;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

/**
 *
 * @author Pablo
 */
public class InputHandler implements InputProcessor {
    
    
    private Pinguin pinguin;	
    private GameWorld world;
    
    public InputHandler(GameWorld world) {
        this.pinguin = world.getPinguin();
        this.world = world;
    }

    
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }
    
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }
    
    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        world.getUI().getStage().touchDown(screenX, screenY, pointer, button);
        world.getUI().getGuiStage().touchDown(screenX, screenY, pointer, button);

        if (!pinguin.moved() && world.getJumpCountController().checkJump()) {
            if (!pinguin.getIsPower()) {
                pinguin.setIsPower(true);
                pinguin.setIsDir(false);
            } else if (!pinguin.getIsDir()) {
                pinguin.jump();
                world.getJumpCountController().jump();
                pinguin.setIsPower(false);
                pinguin.setIsDir(true);
                pinguin.setPower(0);
                pinguin.defaultPos();
            }
        }
        return true;
    }
    
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
