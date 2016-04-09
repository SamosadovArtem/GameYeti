/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Settings;

import GameObjects.Button;
import GameObjects.Interface;
import GameObjects.Scroll;
import GameWorld.AbstractWorld;
import Helper.AssetLoader;
import Helper.FontLoader;
import Helper.Statistic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.screen.GameScreen;

/**
 *
 * @author Pablo
 */
public class SettingsWorld extends AbstractWorld {

    private Texture backTexture = AssetLoader.textureBtnNormal, valueTexture = AssetLoader.textureBtnPress;
    private TextureRegion normalState = AssetLoader.btn, pressedState = AssetLoader.btnPress;
    
    private Label soundText, musicText;
    private Scroll soundScroll, musicScroll;
    private Button disableADS, disableRemind;

    public SettingsWorld(Interface ui, GameLibGDX g) {
        super(ui, g);
        initUI();
        ui.addBack(game);
    }

    @Override
    public void update(float delta) {
        //
    }

    private void initUI() {
        initLabelSound();
        initScrollSound();
        initLabelMusic();
        initScrollMusic();
        initDisableADSButton();
        initDisableRemindButton();
    }

    private void initLabelSound() {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        soundText = new Label("", labelS);
        soundText.setAlignment(Align.center);
        soundText.setFontScale(1);
        soundText.setSize(ui.getStage().getWidth() * 0.4f, ui.getStage().getHeight() / 5);
        soundText.setPosition(0, ui.getHeight() * 4 / 5);
        soundText.setText("Sound: ");
        ui.getGuiStage().addActor(soundText);
    }

    private void initScrollSound() {
        float value = ((float)Statistic.getSoundLevel())/100;
        soundScroll = new Scroll("Sound", backTexture, backTexture,value);
        soundScroll.setSize(ui.getWidth() / 5, ui.getHeight() / 10);
        soundScroll.setPosition(ui.getWidth() * 3 / 5, ui.getHeight() * 4 / 5);
        ui.getGuiStage().addActor(soundScroll);
    }

    private void initLabelMusic() {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        musicText = new Label("", labelS);
        musicText.setAlignment(Align.center);
        musicText.setFontScale(1);
        musicText.setSize(ui.getStage().getWidth() * 0.4f, ui.getStage().getHeight() / 5);
        musicText.setPosition(0, ui.getHeight() * 3 / 5);
        musicText.setText("Music: ");
        ui.getGuiStage().addActor(musicText);
    }

    private void initScrollMusic() {
        float value = ((float)Statistic.getMusicLevel())/100;
        musicScroll = new Scroll("Music", backTexture, backTexture,value);
        musicScroll.setSize(ui.getWidth() / 5, ui.getHeight() / 10);
        musicScroll.setPosition(ui.getWidth() * 3 / 5, ui.getHeight() * 3 / 5);
        ui.getGuiStage().addActor(musicScroll);
    }
    
    private void initDisableADSButton(){
        disableADS = new Button("Top", normalState, pressedState, "PLEASE", FontLoader.font) {
            public void action() {
                
            soundScroll.getValue();
            
            
            
            System.out.println( (int)(soundScroll.getValue()*100));
            }
        };
        disableADS.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        disableADS.setPosition(0, ui.getHeight() / 5);

        ui.getGuiStage().addActor(disableADS);
    }
    
    private void initDisableRemindButton(){
        disableRemind = new Button("Top", normalState, pressedState, "DO'IT", FontLoader.font) {
            public void action() {
                

            }
        };
        disableRemind.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        disableRemind.setPosition(ui.getWidth() * 3 / 5, ui.getHeight() / 5);

        ui.getGuiStage().addActor(disableRemind);
    }
}
