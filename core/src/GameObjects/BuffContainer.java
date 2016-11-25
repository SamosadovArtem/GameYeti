/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Enums.TutorialType;
import GameObjects.Buffs.Buff;
import Helper.AssetLoader;
import Helper.FontLoader;
import Helper.Statistic;
import Helper.TimeConverter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.game.screen.MainScreen;
import com.mygdx.game.tutorial.TutorialHandler;

/**
 *
 * @author qw
 */
public class BuffContainer extends Actor {

    private float xPos, yPos;
    private float width, height;

    private Picture background, icon, timePicture;
    private Label info, countdown;
    private Button upgrade, extend;

    private Group group;

    private Buff buff;
    private TimeConverter timer;

    public BuffContainer(Buff b, float xPos, float yPos, float width, float height, Stage stage) {
        group = new Group();
        buff = b;
        timer = buff.getTimer().getTimeLeft();
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        initObjects();
        stage.addActor(group);
    }

    public void updateTime() {
        timer.removeTime(1);
    }

    private void initObjects() {
        Texture picture = AssetLoader.textureBtnNormal;

        background = new Picture(picture);
        background.setSize(width, height);
        background.setPosition(xPos, yPos - height);
        group.addActor(background);

        icon = buff.getIcon();
        icon.setSize(width / 4, height);
        icon.setPosition(xPos, yPos - height);
        group.addActor(icon);

        timePicture = new Picture(AssetLoader.textureBuffsTime);
        timePicture.setSize(width / 6, height / 4);
        timePicture.setPosition(xPos + width * 5 / 6, yPos - height / 4);
        group.addActor(timePicture);

        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = FontLoader.font;
        labelS.fontColor = Color.WHITE;
        info = new Label("", labelS);
        info.setAlignment(Align.center);
        info.setFontScale(0.5f);
        info.setSize(width / 2, height / 4);
        info.setPosition(xPos + width * 7 / 24, yPos - height * 6 / 9);
        info.setText(buff.getInfo());
        group.addActor(info);

        countdown = new Label("", labelS);
        countdown.setAlignment(Align.center);
        countdown.setFontScale(0.75f);
        countdown.setSize(width / 2, height / 4);
        countdown.setPosition(xPos + width / 5, yPos - height / 4);
        countdown.setText(timer.getTime());
        group.addActor(countdown);

        TextureRegion normal =AssetLoader.buffsArrow;
        TextureRegion pressed = AssetLoader.buffsArrow;

        if(TutorialHandler.getType() == TutorialType.BUFF){
            normal = AssetLoader.tutorialBuff;
            pressed = AssetLoader.tutorialBuff;
        }

        upgrade = new Button("Upgrade", normal, pressed, "", FontLoader.font) {
            public void action() {
                if (buff.checkUpgrade(Statistic.getCoins())) {
                    buff.upgrade();
                    timer = buff.getTimer().getTimeLeft();
                    updateInfo();
                    if(TutorialHandler.getType() == TutorialType.BUFF){
                        TutorialHandler.increaseTutorialLvl();
                    }
                }
            }
        };
        upgrade.setSize(width / 6, height / 4);
        upgrade.setPosition(xPos + width * 5 / 6, yPos - height);
        group.addActor(upgrade);

        extend = new Button("Extend", AssetLoader.buffsClock, AssetLoader.buffsClock, "", FontLoader.font) {
            public void action() {
                if(TutorialHandler.getType() != TutorialType.BUFF) {
                    if (buff.checkUpdate(Statistic.getCoins())) {
                        Gdx.app.log("UPD", "");
                        buff.update();
                        timer = buff.getTimer().getTimeLeft();
                    }
                }
            }
        };
        extend.setSize(width / 6, height / 4);
        extend.setPosition(xPos + width * 5 / 6, yPos - height + upgrade.getHeight());
        group.addActor(extend);
    }

    private void updateInfo() {
      //  info.setText("Info: " + buff.getLevel());
    }

    public void update() {
        countdown.setText(timer.getTime());
        timer.removeTime(1);
    }

    public float getY() {
        return yPos;
    }

    public float getHeight() {
        return this.height;
    }
}
