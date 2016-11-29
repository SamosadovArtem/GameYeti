/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects.PrizeField;

import GameWorld.Game.Objects.GameActor;
import GameWorld.Ticket.TicketListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * @author Pablo
 */
public class ProtectiveLayerParticle extends GameActor {

    private Texture sprite;
    private boolean isVisible;
    private int spriteXPos, spriteYPos;

    public ProtectiveLayerParticle(Texture sprite) {
        this.addListener(new TicketListener(this));
        this.sprite = sprite;
        this.isVisible = true;
    }

    public void draw(Batch batch, float parentAlpha) {
        if (isVisible) {
            //       batch.draw(sprite, getX(), getY(), getWidth(), getHeight());
            batch.draw(sprite, getX(), getY(), this.getWidth(), this.getHeight(), spriteXPos, spriteYPos,
                    (int) this.getWidth(), (int) this.getHeight(), true, false);
        }
    }

    public void action() {
        isVisible = false;
        Gdx.app.log("point x: " + getX(), "point y: " + getY());
    }

    public void initSpritePos(int x, int y) {
        this.spriteXPos = x;
        this.spriteYPos = y;
    }
}
