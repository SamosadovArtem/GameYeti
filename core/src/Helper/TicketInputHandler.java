/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import GameObjects.PrizeField.PrizeField;
import GameObjects.PrizeField.ProtectiveLayerParticle;
import GameWorld.Ticket.TicketWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.GameLibGDX;

import java.util.ArrayList;

/**
 * @author Pablo
 */
public class TicketInputHandler implements InputProcessor {

    private GameLibGDX game;
    private TicketWorld world;

    public TicketInputHandler(TicketWorld world, GameLibGDX game) {
        this.world = world;
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.BACK) {
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        world.getUI().getStage().touchDown(screenX, screenY, pointer, button);
        world.getUI().getGuiStage().touchDown(screenX, screenY, pointer, button);
        Gdx.app.log("inputhandler", "touchdown");
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        world.getUI().getStage().touchUp(screenX, screenY, pointer, button);
        world.getUI().getGuiStage().touchUp(screenX, screenY, pointer, button);
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        world.getUI().getStage().touchDragged(screenX, screenY, pointer);
        world.getUI().getGuiStage().touchDragged(screenX, screenY, pointer);
        checkParticles(screenX, screenY);
        return true;
    }

    private void checkParticles(int screenX, int screenY) {
        float xPos = (float) screenX;
        float yPos = world.getUI().getHeight() - (float) screenY;
        ArrayList<PrizeField> array = world.getTicket().getPrizeFieldList();
        for (PrizeField p : array) {
            ArrayList<ProtectiveLayerParticle> array2 = p.getProtectiveLayer();
            for (ProtectiveLayerParticle a : array2) {
                if (xPos + 5 >= a.getX() && xPos - 5 <= (a.getX() + a.getWidth())
                        && yPos + 5 >= a.getY() && yPos - 5 <= (a.getY() + a.getHeight())) {
                    a.action();
                    p.increaseCountOfActiveParticles();
                    // world.checkWin();
                }
            }
        }
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
