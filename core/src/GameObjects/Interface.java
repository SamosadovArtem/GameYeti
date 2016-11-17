/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Helper.AssetLoader;
import Helper.FontLoader;
import Helper.Statistic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.screen.MainScreen;

/**
 *
 * @author Pablo
 */
public class Interface {

    private Stage stage, guiStage;
    private int coin = Statistic.getCoins();
    private Label coinText, fpsText;

    public Interface(Stage stage, Stage guiStage) {
        this.stage = stage;
        this.guiStage = guiStage;
        addCoins();
        addFPS();
    }

    public Stage getStage() {
        return stage;
    }

    public Stage getGuiStage() {
        return guiStage;
    }

    public void dispose() {
        stage.dispose();
        guiStage.dispose();
    }

    public void draw() {
        updateCoins();
        stage.draw();
        guiStage.draw();
    }

    public float getWidth() {
        return stage.getWidth();
    }

    public float getHeight() {
        return stage.getHeight();
    }

    private void addCoins() {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = FontLoader.font;
        labelS.fontColor = Color.WHITE;
        coinText = new Label("", labelS);
        coinText.setAlignment(Align.center);
        coinText.setFontScale(0.5f);
        coinText.setSize(guiStage.getWidth() * 9 / 5, guiStage.getHeight() / 5);
        coinText.setPosition(0, guiStage.getHeight() * 3 / 5);
        coinText.setText("Coins: " + Statistic.getCoins());
        guiStage.addActor(coinText);
    }

    private void updateCoins() {
        if (coin != Statistic.getCoins()) {
            coin = Statistic.getCoins();
            coinText.setText("Coins: " + coin);
        }
    }

    public void addBack(final GameLibGDX game) {
        Button backButton;
        backButton = new Button("Back", AssetLoader.backTexture, AssetLoader.backPressedTexture, "", FontLoader.font) {
            public void action() {
                game.setScreen(new MainScreen(game));
            }
        };
        backButton.setSize(getStage().getWidth() * 0.4f / 3, getStage().getHeight() / 6);
        backButton.setPosition(0,
                getStage().getHeight() * 5 / 6);

        getGuiStage().addActor(backButton);
    }

    private void addFPS() {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.RED;
        fpsText = new Label("", labelS);
        fpsText.setAlignment(Align.center);
        fpsText.setFontScale(1);
        fpsText.setSize(guiStage.getWidth() * 9 / 5, guiStage.getHeight() / 5);
        fpsText.setPosition(0, guiStage.getHeight() * 2 / 5);
        fpsText.setText("FPS: " + 0);
        guiStage.addActor(fpsText);
    }

    public void updateFps(float value) {
        fpsText.setText("FPS: " + (int) value);
    }
}
