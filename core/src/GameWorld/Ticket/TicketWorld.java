/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Ticket;

import GameObjects.Interface;
import GameObjects.PrizeField.PrizeField;
import GameObjects.PrizeField.Ticket;
import GameWorld.AbstractWorld;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author Pablo
 */
public class TicketWorld extends AbstractWorld {
    
    private Ticket ticket;
    
    public TicketWorld(Interface ui, GameLibGDX g) {
        super(ui, g);
        initTicket();
        ui.addBack(game);
    }
    
    private void initTicket() {
        ticket = new Ticket(ui);
        ticket.addToStage(ui.getStage());
    }
    
    @Override
    public void update(float delta) {
        ui.updateFps(1 / delta);
    }
    
    public Ticket getTicket() {
        return ticket;
    }
}
