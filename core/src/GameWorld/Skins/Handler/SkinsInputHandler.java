package GameWorld.Skins.Handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.GameLibGDX;

import java.util.ArrayList;
import java.util.Date;

import GameWorld.Maps.MapsWorld;
import GameWorld.Skins.SkinsWorld;

/**
 * Created by broff on 15.04.2016.
 */
public class SkinsInputHandler implements InputProcessor {

    private GameLibGDX game;
    SkinsWorld world;
    private int _oldX;
    private int temp;
    private boolean isTouched;
    private float min = 100000, max = 0;
    private Date touchT;
    private float touchX, upX;

    public SkinsInputHandler(SkinsWorld world, GameLibGDX game) {
        this.game = game;
        this.world = world;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        isTouched = true;
        world.scrollArea = true;
        touchT = new Date();
        touchX = screenX;
        _oldX = screenX;
        world.getUI().getStage().touchDown(screenX, screenY, pointer, button);
        world.getUI().getGuiStage().touchDown(screenX, screenY, pointer, button);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        isTouched = false;
        world.scrollArea = false;

        float in = (screenX - touchX) / ((new Date().getTime() - touchT.getTime()) / 10l);
        Gdx.app.log("x", "" + in);
        world.setInertion(in);
        world.getUI().getStage().touchUp(screenX, screenY, pointer, button);
        world.getUI().getGuiStage().touchUp(screenX, screenY, pointer, button);
        return true;
    }


    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        //world.getUI().getStage().getCamera().position.x+=temp-screenX;

        temp = _oldX;
        if ((isTouched)) {

            _oldX = screenX;
            world.getSkins().moveX(-1 * (temp - screenX));
        }
        return true;
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (keyCode == Input.Keys.BACK) {
            game.getScreen().backPress();
        }
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
