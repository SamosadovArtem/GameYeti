/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Main;

import GameObjects.Button;
import GameObjects.DailyGiftWindow;
import GameObjects.Interface;
import GameWorld.AbstractWorld;
import GameWorld.Ticket.TicketBuyWindow;
import Helper.AssetLoader;
import Helper.DailyGiftHandler;
import Helper.FontLoader;
import Helper.SoundsLoader;
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
import com.mygdx.game.screen.BuySkinsScreen;
import com.mygdx.game.screen.DonateScreen;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.MapsScreen;
import com.mygdx.game.screen.SettingsScreen;
import com.mygdx.game.screen.StatisticScreen;
import com.mygdx.game.screen.TicketScreen;

/**
 *
 * @author qw
 */
public class MainWorld extends AbstractWorld {

    //////////////////////////
    Button mapsScreenButton, settingsButton, buffsButton, topButton, statisticsButton, ticketButton;
    Label highscoreText;

    public MainWorld(Interface ui, GameLibGDX g) {
        super(ui, g);
        Gdx.app.log("MainWorld ", "create");

        SoundsLoader.LoadBasicSounds();
        SoundsLoader.PlayBackSound();

        createUI();

        handleDailyGift();
    }

    private void handleDailyGift() {
        DailyGiftHandler.setDailyGift();
        Gdx.app.log("fewfq", ": " + DailyGiftHandler.gift.getSaveData());
        DailyGiftWindow dailyGiftWindow = new DailyGiftWindow(ui.getGuiStage());
        if (DailyGiftHandler.gift.checkIsAvailable()) {
            dailyGiftWindow.instantShow(game);
        }
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

        playButton(AssetLoader.playTexture, AssetLoader.playPressedTexture);
        settingsButton(AssetLoader.settingsTexture, AssetLoader.settingsPressedTexture);
        statisticButton(AssetLoader.statisticTexture, AssetLoader.statisticPressedTexture);
        buffsButton(AssetLoader.buffTexture, AssetLoader.buffPressedTexture);
        coinsButton(AssetLoader.coinsTexture, AssetLoader.coinsPressedTexture);
        skinsButton(AssetLoader.skinsTexture, AssetLoader.skinsPressedTexture);
        ticketButton(AssetLoader.ticketTexture, AssetLoader.ticketPressedTexture);
        initHighscore();
    }

    private void checkDailyGift() {

    }

    private void playButton(TextureRegion normalState, TextureRegion pressedState) {
        mapsScreenButton = new Button("Play", normalState, pressedState, "", FontLoader.font) {
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
        settingsButton = new Button("Settings", normalState, pressedState, "", FontLoader.font) {
            public void action() {
                game.setScreen(new SettingsScreen(game));
            }
        };
        settingsButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        settingsButton.setPosition(ui.getStage().getWidth() / 5 - settingsButton.getWidth() / 2,
                mapsScreenButton.getY() - mapsScreenButton.getHeight());

        ui.getGuiStage().addActor(settingsButton);
    }

    private void buffsButton(TextureRegion normalState, TextureRegion pressedState) {
        buffsButton = new Button("Buff", normalState, pressedState, "", FontLoader.font) {
            public void action() {
                game.setScreen(new BuffsScreen(game));
            }
        };
        buffsButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        buffsButton.setPosition(ui.getStage().getWidth() * 3 / 5 - buffsButton.getWidth() / 2,
                mapsScreenButton.getY() - mapsScreenButton.getHeight());

        ui.getGuiStage().addActor(buffsButton);
    }

    private void statisticButton(TextureRegion normalState, TextureRegion pressedState) {
        statisticsButton = new Button("Stats", normalState, pressedState, "", FontLoader.font) {
            public void action() {
                game.setScreen(new StatisticScreen(game));
            }
        };
        statisticsButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        statisticsButton.setPosition(ui.getStage().getWidth() * 2 / 5 - statisticsButton.getWidth() / 2,
                mapsScreenButton.getY() - mapsScreenButton.getHeight());

        ui.getGuiStage().addActor(statisticsButton);
    }

    private void coinsButton(TextureRegion normalState, TextureRegion pressedState) {
        topButton = new Button("Coins", normalState, pressedState, "", FontLoader.font) {
            public void action() {
                game.setScreen(new DonateScreen(game));
            }
        };
        topButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        topButton.setPosition(ui.getStage().getWidth() * 4 / 5 - buffsButton.getWidth() / 2,
                mapsScreenButton.getY() - mapsScreenButton.getHeight());

        ui.getGuiStage().addActor(topButton);
    }

    private void skinsButton(TextureRegion normalState, TextureRegion pressedState) {
        topButton = new Button("Skins", normalState, pressedState, "", FontLoader.font) {
            public void action() {
                game.setScreen(new BuySkinsScreen(game));
            }
        };
        topButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        topButton.setPosition(ui.getStage().getWidth() * 5 / 5 - buffsButton.getWidth() / 2,
                mapsScreenButton.getY() - mapsScreenButton.getHeight());

        ui.getGuiStage().addActor(topButton);
    }

    private void ticketButton(TextureRegion normalState, TextureRegion pressedState) {
        ticketButton = new Button("Ticket", normalState, pressedState, String.valueOf(Statistic.getTickets()), FontLoader.font) {
            public void action() {
                if (Statistic.getTickets() > 0) {
                    Statistic.removeTicket();
                    game.setScreen(new TicketScreen(game));
                } else {
                    new TicketBuyWindow(getStage(), this).instantShow(game);
                }
            }
        };
        ticketButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        ticketButton.setPosition(0,
                mapsScreenButton.getY() - mapsScreenButton.getHeight());

        ui.getGuiStage().addActor(ticketButton);
    }

    private void initHighscore() {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = FontLoader.font;
        labelS.fontColor = Color.WHITE;
        highscoreText = new Label("", labelS);
        highscoreText.setAlignment(Align.center);
        highscoreText.setFontScale(1);
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
