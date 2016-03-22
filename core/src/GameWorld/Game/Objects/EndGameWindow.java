/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import GameObjects.Button;
import GameObjects.AbstractWindow;
import Helper.FontLoader;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.screen.DebugScreen;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.MainScreen;

/**
 *
 * @author Pablo
 */
public class EndGameWindow extends AbstractWindow {

    public EndGameWindow(Stage stage) {
        super(stage);
        width = stage.getWidth() / 2;
        height = stage.getHeight() * 4 / 5;
    }

    @Override
    protected void initText() {
        Label textLabel;
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        textLabel = new Label("", labelS);
        textLabel.setAlignment(Align.center);
        textLabel.setFontScale(1);
        textLabel.setSize(width / 3, height / 6);
        textLabel.setText("GAME OVER");
        textLabel.setPosition(xPos, yPos + height / 5);
        stage.addActor(textLabel);
    }

    @Override
    protected void initButtons(GameLibGDX game) {
        Button restartButton = new Button("Restart", normalState, pressedState, "RESTART", FontLoader.font) {
            public void action() {
                Gdx.app.log("check", "check");

                game.setScreen(new GameScreen(game));
            }
        };
        restartButton.setSize(width / 5, height / 6);
        restartButton.setPosition(xPos - width * 1 / 3, yPos);
        stage.addActor(restartButton);

        Button backButton = new Button("Back", normalState, pressedState, "BACK", FontLoader.font) {
            public void action() {
                Gdx.app.log("check", "check");

                game.setScreen(new MainScreen(game));
            }
        };
        backButton.setSize(width / 5, height / 6);
        backButton.setPosition(xPos + width * 1 / 3, yPos);
        stage.addActor(backButton);
    }
}
