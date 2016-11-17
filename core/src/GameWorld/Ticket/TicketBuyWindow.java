/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Ticket;

import GameObjects.AbstractWindow;
import GameObjects.Button;
import Helper.FontLoader;
import Helper.SoundsLoader;
import Helper.Statistic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.MainScreen;

/**
 *
 * @author User
 */
public class TicketBuyWindow extends AbstractWindow {

    private Label textLabel;
    private Button ticketButton;

    public TicketBuyWindow(Stage stage, Button button) {
        super(stage);
        width = stage.getWidth() * 2 / 3;
        height = stage.getHeight() * 2 / 5;
        ticketButton = button;
    }

    @Override
    protected void initText() {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = FontLoader.font;
        labelS.fontColor = Color.WHITE;
        textLabel = new Label("", labelS);
        textLabel.setAlignment(Align.center);
        textLabel.setFontScale(0.5f);
        textLabel.setSize(width / 3, height / 6);
        textLabel.setText("You have no tickets. \nWould you like to purchase?");
        textLabel.setPosition(xPos + width / 2 - textLabel.getWidth() / 2,
                yPos + height/2);
        group.addActor(textLabel);
    }

    @Override
    protected void initButtons(GameLibGDX game) {
        Button restartButton = new Button("Back", normalState, pressedState, "Back", FontLoader.font) {
            public void action() {
                deleteWindow();
            }
        };
        restartButton.setSize(width / 5, height / 6);
        restartButton.setPosition(xPos, yPos);
        group.addActor(restartButton);

        Button backButton = new Button("Purchase", normalState, pressedState, "Purchase", FontLoader.font) {
            public void action() {
                int coinsCount = Statistic.getCoins();
                int price = 50;
                if (coinsCount >= price) {
                    Statistic.removeCoins(price);
                    Statistic.addTicket();
                    ticketButton.setText(String.valueOf(1));
                    deleteWindow();
                } else {
                    textLabel.setText("Sorry, you have not enough coins :(");
                }
            }
        };
        backButton.setSize(width / 5, height / 6);
        backButton.setPosition(xPos + width - backButton.getWidth(), yPos);
        group.addActor(backButton);
    }

}
