/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Ticket;

import GameObjects.PrizeField.ProtectiveLayerParticle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 *
 * @author Pablo
 */
public class TicketListener extends InputListener {

    private ProtectiveLayerParticle particle;

    public TicketListener(ProtectiveLayerParticle particle) {
        super();
        this.particle = particle;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Gdx.app.log("ewqe", "ewqewqewq");
        particle.action();
        return true;
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

    }

    @Override
    public void touchDragged(InputEvent event, float x, float y, int pointer) {
        particle.action();
    }
}
