/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import GameObjects.Button;
import GameObjects.AbstractWindow;
import Helper.FontLoader;
import Helper.SoundsLoader;
import Helper.Statistic;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
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

    private int highscore = 0;

    public EndGameWindow(Stage stage) {
        super(stage);
        width = stage.getWidth() / 2;
        height = stage.getHeight() * 3 / 5;
    }

    public void initHighscore(int score) {
        highscore = score;
        Statistic.setHighScore(score);
        Statistic.setLength(score);
    }

    @Override
    protected void initText() {
        Label textLabel;
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = FontLoader.font;
        labelS.fontColor = Color.WHITE;
        textLabel = new Label("", labelS);
        textLabel.setAlignment(Align.center);
        textLabel.setFontScale(0.5f);
        textLabel.setSize(width / 3, height / 6);
        textLabel.setText("GAME OVER");
        textLabel.setPosition(xPos + width / 2 - textLabel.getWidth() / 2,
                yPos + height - textLabel.getHeight());
        group.addActor(textLabel);

        Label scoreLabel;
        scoreLabel = new Label("", labelS);
        scoreLabel.setAlignment(Align.center);
        scoreLabel.setFontScale(1);
        scoreLabel.setSize(width / 3, height / 6);
        scoreLabel.setText("" + highscore);
        scoreLabel.setPosition(xPos + width / 2 - scoreLabel.getWidth() / 2,
                yPos + height * 2 / 3);
        group.addActor(scoreLabel);
    }

    @Override
    protected void initButtons(final GameLibGDX game) {
        Button restartButton = new Button("Restart", normalState, pressedState, "RESTART", FontLoader.font) {
            public void action() {
                Gdx.app.log("check", "check");

                game.setScreen(new GameScreen(game));
            }
        };
        restartButton.setSize(width / 5, height / 6);
        restartButton.setPosition(xPos, yPos);
        group.addActor(restartButton);

        Button backButton = new Button("Back", normalState, pressedState, "BACK", FontLoader.font) {
            public void action() {
                Gdx.app.log("check", "check");

                SoundsLoader.DisposedGameSounds();

                game.setScreen(new MainScreen(game));
            }
        };
        backButton.setSize(width / 5, height / 6);
        backButton.setPosition(xPos + width - backButton.getWidth(), yPos);
        group.addActor(backButton);
    }
}
