/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Main;

import GameObjects.Button;
import GameObjects.Interface;
import GameWorld.AbstractWorld;
import Helper.AssetLoader;
import Helper.FontLoader;
import Helper.Statistic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.screen.BuffsScreen;
import com.mygdx.game.screen.DonateScreen;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.MapsScreen;
import com.mygdx.game.screen.SettingsScreen;
import com.mygdx.game.screen.StatisticScreen;

/**
 *
 * @author qw
 */
public class MainWorld extends AbstractWorld {

    //////////////////////////
    Button mapsScreenButton, settingsButton, buffsButton, topButton, statisticsButton;
    Label highscoreText;

    public MainWorld(Interface ui, GameLibGDX g) {
        super(ui, g);
        Gdx.app.log("MainWorld ", "create");
        createUI();
    }

    private Rectangle rect = new Rectangle(0, 0, 17, 12);

    public void update(float delta) {
        rect.x++;
        if (rect.x > 400) {
            rect.x = 0;
        }
    }

    public Rectangle getRect() {
        return rect;
    }

    private void createUI() {

        TextureRegion normalState = AssetLoader.btn;
        TextureRegion pressedState = AssetLoader.btnPress;

        playButton(normalState, pressedState);
        settingsButton(normalState, pressedState);
        statisticButton(normalState, pressedState);
        buffsButton(normalState, pressedState);
        topButton(normalState, pressedState);
        initHighscore();
    }

    private void playButton(TextureRegion normalState, TextureRegion pressedState) {
        mapsScreenButton = new Button("Play", normalState, pressedState, "PLAY", FontLoader.font) {
            public void action() {
                game.setScreen(new MapsScreen(game));
            }
        };
        mapsScreenButton.setSize(ui.getStage().getWidth() * 0.4f, ui.getStage().getHeight() / 5);
        mapsScreenButton.setPosition((ui.getStage().getWidth() - mapsScreenButton.getWidth()) / 2,
                (ui.getStage().getHeight() - mapsScreenButton.getHeight()) / 2);

        ui.getGuiStage().addActor(mapsScreenButton);
    }

    private void settingsButton(TextureRegion normalState, TextureRegion pressedState) {
        settingsButton = new Button("Settings", normalState, pressedState, "SETT", FontLoader.font) {
            public void action() {
                game.setScreen(new SettingsScreen(game));
            }
        };
        settingsButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        settingsButton.setPosition(ui.getStage().getWidth()/5-settingsButton.getWidth()/2,
                mapsScreenButton.getY() - mapsScreenButton.getHeight());

        ui.getGuiStage().addActor(settingsButton);
    }

    private void buffsButton(TextureRegion normalState, TextureRegion pressedState) {
        buffsButton = new Button("Buffs", normalState, pressedState, "BUFF", FontLoader.font) {
            public void action() {
                game.setScreen(new BuffsScreen(game));                
            }
        };
        buffsButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        buffsButton.setPosition(ui.getStage().getWidth()*3/5 - buffsButton.getWidth()/2,
                mapsScreenButton.getY() - mapsScreenButton.getHeight());

        ui.getGuiStage().addActor(buffsButton);
    }
    
    private void statisticButton(TextureRegion normalState, TextureRegion pressedState){
         statisticsButton = new Button("Buffs", normalState, pressedState, "BUFF", FontLoader.font) {
            public void action() {
                game.setScreen(new StatisticScreen(game));                
            }
        };
        statisticsButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        statisticsButton.setPosition(ui.getStage().getWidth()*2/5-statisticsButton.getWidth()/2,
                mapsScreenButton.getY() - mapsScreenButton.getHeight());

        ui.getGuiStage().addActor(statisticsButton);
    }

    private void topButton(TextureRegion normalState, TextureRegion pressedState) {
        topButton = new Button("Top", normalState, pressedState, "TOP", FontLoader.font) {
            public void action() {
                game.setScreen(new DonateScreen(game));                
            }
        };
        topButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        topButton.setPosition(ui.getStage().getWidth()*4/5 - buffsButton.getWidth()/2,
                mapsScreenButton.getY() - mapsScreenButton.getHeight());

        ui.getGuiStage().addActor(topButton);
    }

    private void initHighscore() {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        highscoreText = new Label("", labelS);
        highscoreText.setAlignment(Align.center);
        highscoreText.setFontScale(2);
        highscoreText.setSize(ui.getStage().getWidth() * 0.4f, ui.getStage().getHeight() / 5);
        updateHighscore();
        ui.getGuiStage().addActor(highscoreText);
    }

    private void updateHighscore() {
        highscoreText.setText("You highscore " + Statistic.getHighScore());
        highscoreText.setPosition((ui.getStage().getWidth() - highscoreText.getWidth()) / 2,
                mapsScreenButton.getY() + highscoreText.getHeight());
    }
}
