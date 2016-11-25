/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Ticket;

import Enums.GiftType;
import Enums.TutorialType;
import GameObjects.Button;
import GameObjects.Interface;
import GameObjects.PrizeField.PrizeField;
import GameObjects.PrizeField.Ticket;
import GameWorld.AbstractWorld;
import GameWorld.Ticket.Gift.GiftHandler;
import Helper.AssetLoader;
import Helper.FontLoader;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.screen.GiftScreen;
import com.mygdx.game.screen.SettingsScreen;
import com.mygdx.game.tutorial.TutorialHandler;

/**
 * @author Pablo
 */
public class TicketWorld extends AbstractWorld {

    private Ticket ticket;
    private Button resultButton;
    private boolean isActive = false;

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
        checkWin();
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void checkWin() {
        if (ticket.getIsActive() && ticket.getIsWin() && !isActive) {
            isActive = true;
            addButton();
        }
    }

    private void addButton() {
        resultButton = new Button("Result", AssetLoader.btn, AssetLoader.btnPress, "RESULT", FontLoader.font) {
            public void action() {
                if (TutorialHandler.getType() == TutorialType.TICKET) {
                    GiftScreen.initType(GiftType.COIN);
                } else {
                    GiftScreen.initType(GiftHandler.getGiftType());
                }
                game.setScreen(new GiftScreen(game));
            }
        };
        resultButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        resultButton.setPosition(ui.getStage().getWidth() / 2, ui.getStage().getHeight() / 2);
        ui.getStage().addActor(resultButton);
    }
}
