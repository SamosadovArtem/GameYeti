/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Main;

import Enums.TutorialType;
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
import com.mygdx.game.tutorial.TutorialHandler;

/**
 * @author qw
 */
public class MainWorld extends AbstractWorld {

    //////////////////////////
    Button mapsScreenButton, settingsButton, buffsButton, topButton, statisticsButton, ticketButton;
    Label highscoreText;

    public MainWorld(Interface ui, GameLibGDX g) {
        super(ui, g);

        SoundsLoader.LoadBasicSounds();
        SoundsLoader.PlayBackSound();

        TutorialHandler.initTutorial();

        createUI();

        if (TutorialHandler.getType() == TutorialType.DONE) {
            handleDailyGift();
        }
    }

    private void handleDailyGift() {
        DailyGiftHandler.setDailyGift();
        DailyGiftWindow dailyGiftWindow = new DailyGiftWindow(ui.getGuiStage());
        if (DailyGiftHandler.gift.checkIsAvailable()) {
            dailyGiftWindow.instantShow(game);
        }
    }


    public void update(float delta) {
    }

    private void createUI() {

        playButton(AssetLoader.playTexture, AssetLoader.playPressedTexture);
        settingsButton(AssetLoader.settingsTexture, AssetLoader.settingsPressedTexture);
        ticketButton(AssetLoader.ticketTexture, AssetLoader.ticketPressedTexture);
        statisticButton(AssetLoader.statisticTexture, AssetLoader.statisticPressedTexture);
        buffsButton(AssetLoader.buffTexture, AssetLoader.buffPressedTexture);
        coinsButton(AssetLoader.coinsTexture, AssetLoader.coinsPressedTexture);
        skinsButton(AssetLoader.skinsTexture, AssetLoader.skinsPressedTexture);
        initHighscore();
    }

    private void playButton(TextureRegion normalState, TextureRegion pressedState) {
        TextureRegion normal = normalState;
        TextureRegion pressed = pressedState;

        if (TutorialHandler.getType() == TutorialType.PLAY) {
            normal = AssetLoader.tutorialPlay;
            pressed = AssetLoader.tutorialPlay;
        }
        mapsScreenButton = new Button("Play", normal, pressed, "", FontLoader.font) {
            public void action() {
                if (TutorialHandler.getType() == TutorialType.DONE ||
                        TutorialHandler.getType() == TutorialType.PLAY) {
                    game.setScreen(new MapsScreen(game));
                }
            }
        };
        mapsScreenButton.setSize(ui.getStage().getWidth() * 0.4f, ui.getStage().getHeight() / 5);
        mapsScreenButton.setPosition(ui.getStage().getWidth() * 0.3f,
                (ui.getStage().getHeight() / 2));

        ui.getGuiStage().addActor(mapsScreenButton);
    }

    private void settingsButton(TextureRegion normalState, TextureRegion pressedState) {
        settingsButton = new Button("Settings", normalState, pressedState, "", FontLoader.font) {
            public void action() {
                if (TutorialHandler.getType() == TutorialType.DONE) {
                    game.setScreen(new SettingsScreen(game));
                }
            }
        };
        settingsButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        settingsButton.setPosition(ui.getStage().getWidth() / 2 - settingsButton.getWidth() / 2,
                mapsScreenButton.getY() - mapsScreenButton.getHeight());

        ui.getGuiStage().addActor(settingsButton);
    }

    private void ticketButton(TextureRegion normalState, TextureRegion pressedState) {

        TextureRegion normal = normalState;
        TextureRegion pressed = pressedState;

        if (TutorialHandler.getType() == TutorialType.TICKET) {
            normal = AssetLoader.tutorialTicket;
            pressed = AssetLoader.tutorialTicket;
        }

        ticketButton = new Button("Ticket", normal, pressed, String.valueOf(Statistic.getTickets()), FontLoader.font) {
            public void action() {
                if (TutorialHandler.getType() == TutorialType.DONE ||
                        TutorialHandler.getType() == TutorialType.TICKET) {
                    if (Statistic.getTickets() > 0) {
                        Statistic.removeTicket();
                        game.setScreen(new TicketScreen(game));
                    } else {
                        new TicketBuyWindow(getStage(), this).instantShow(game);
                    }
                }
            }
        };
        ticketButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        ticketButton.setPosition(settingsButton.getX() - ui.getStage().getWidth() * 0.6f / 3,
                mapsScreenButton.getY() - mapsScreenButton.getHeight());

        ui.getGuiStage().addActor(ticketButton);
    }

    private void statisticButton(TextureRegion normalState, TextureRegion pressedState) {
        statisticsButton = new Button("Stats", normalState, pressedState, "", FontLoader.font) {
            public void action() {
                if (TutorialHandler.getType() == TutorialType.DONE) {
                    game.setScreen(new StatisticScreen(game));
                }
            }
        };
        statisticsButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        statisticsButton.setPosition(settingsButton.getX() + ui.getStage().getWidth() * 0.6f / 3,
                mapsScreenButton.getY() - mapsScreenButton.getHeight());
        ui.getGuiStage().addActor(statisticsButton);
    }

    private void buffsButton(TextureRegion normalState, TextureRegion pressedState) {

        TextureRegion normal = normalState;
        TextureRegion pressed = pressedState;

        if (TutorialHandler.getType() == TutorialType.BUFF) {
            normal = AssetLoader.tutorialBuff;
            pressed = AssetLoader.tutorialBuff;
        }

        buffsButton = new Button("Buff", normal, pressed, "", FontLoader.font) {
            public void action() {
                if (TutorialHandler.getType() == TutorialType.DONE ||
                        TutorialHandler.getType() == TutorialType.BUFF) {
                    game.setScreen(new BuffsScreen(game));
                }
            }
        };
        buffsButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        buffsButton.setPosition(settingsButton.getX() - ui.getStage().getWidth() * 0.6f / 3,
                mapsScreenButton.getY() - mapsScreenButton.getHeight() * 2);

        ui.getGuiStage().addActor(buffsButton);
    }

    private void coinsButton(TextureRegion normalState, TextureRegion pressedState) {
        TextureRegion normal = normalState;
        TextureRegion pressed = pressedState;

        if (TutorialHandler.getType() == TutorialType.PURCHASES) {
            normal = AssetLoader.tutorialPurchase;
            pressed = AssetLoader.tutorialPurchase;
        }

        topButton = new Button("Coins", normal, pressed, "", FontLoader.font) {
            public void action() {
                if (TutorialHandler.getType() == TutorialType.DONE ||
                        TutorialHandler.getType() == TutorialType.PURCHASES) {
                    game.setScreen(new DonateScreen(game));
                }
            }
        };
        topButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        topButton.setPosition(ui.getStage().getWidth() / 2 - settingsButton.getWidth() / 2,
                mapsScreenButton.getY() - mapsScreenButton.getHeight() * 2);

        ui.getGuiStage().addActor(topButton);
    }

    private void skinsButton(TextureRegion normalState, TextureRegion pressedState) {
        TextureRegion normal = normalState;
        TextureRegion pressed = pressedState;

        if (TutorialHandler.getType() == TutorialType.SKIN) {
            normal = AssetLoader.tutorialPurchase;
            pressed = AssetLoader.tutorialPurchase;
        }
        topButton = new Button("Skins", normal, pressed, "", FontLoader.font) {
            public void action() {
                if (TutorialHandler.getType() == TutorialType.DONE ||
                        TutorialHandler.getType() == TutorialType.SKIN) {
                    if (TutorialHandler.getType() == TutorialType.SKIN) {
                        TutorialHandler.increaseTutorialLvl();
                    }
                    game.setScreen(new BuySkinsScreen(game));
                }
            }
        };
        topButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        topButton.setPosition(settingsButton.getX() + ui.getStage().getWidth() * 0.6f / 3,
                mapsScreenButton.getY() - mapsScreenButton.getHeight() * 2);

        ui.getGuiStage().addActor(topButton);
    }

    private void initHighscore() {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = FontLoader.font;
        labelS.fontColor = Color.WHITE;
        highscoreText = new Label("", labelS);
        highscoreText.setAlignment(Align.center);
        highscoreText.setFontScale(1.1f);
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
