package GameWorld.Skins.Handler;

import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;

import GameWorld.Maps.MapsWorld;
import GameWorld.Skins.SkinsWorld;

/**
 * Created by broff on 15.04.2016.
 */
public class SkinsInputHandler implements InputProcessor {

    SkinsWorld world;

    public SkinsInputHandler(SkinsWorld world){
        this.world = world;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        world.getUI().getStage().touchDown(screenX, screenY, pointer, button);
        world.getUI().getGuiStage().touchDown(screenX, screenY, pointer, button);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        world.getUI().getStage().touchUp(screenX, screenY, pointer, button);
        world.getUI().getGuiStage().touchUp(screenX, screenY, pointer, button);
        return true;
    }

    int temp;
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        world.getUI().getStage().getCamera().position.x+=temp-screenX;
        return false;
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
