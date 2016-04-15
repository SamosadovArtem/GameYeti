/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Helper.AssetLoader;
import Helper.DailyGiftHandler;
import Helper.FontLoader;
import Helper.Statistic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.screen.GameScreen;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class DailyGiftWindow extends AbstractWindow {

    public DailyGiftWindow(Stage stage) {
        super(stage);
        width = stage.getWidth() * 2 / 3;
        height = stage.getHeight() * 2 / 5;
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
        textLabel.setText("Gift: ");
        textLabel.setPosition(xPos + width / 2 - textLabel.getWidth() / 2,
                yPos + height - textLabel.getHeight());
        group.addActor(textLabel);
    }

    @Override
    protected void initButtons(GameLibGDX game) {
        Button applyButton = new Button("Restart", normalState, pressedState, "RESTART", FontLoader.font) {
            public void action() {
                deleteWindow();
                setCheck(true);
                Statistic.addCoins(DailyGiftHandler.gift.getCoins());
                DailyGiftHandler.refresh();
                DailyGiftHandler.dispose();
            }
        };
        applyButton.setSize(width / 5, height / 6);
        applyButton.setPosition(xPos + width / 2 - applyButton.getWidth() / 2, yPos);
        group.addActor(applyButton);
        initPictures();
    }

    private void initPictures() {
        List<Picture> array = DailyGiftHandler.getArrayOfPictures();
        Picture firstDay = array.get(0);
        Picture secondDay = array.get(1);
        Picture thirdDay = array.get(2);
        Picture fourthDay = array.get(3);
        Picture fifthDay = array.get(4);

        firstDay.setPosition(xPos + width / 7, yPos + height / 2);
        firstDay.setSize(width / 10, height / 10);
        secondDay.setPosition(xPos + width * 2 / 7, yPos + height / 2);
        secondDay.setSize(width / 10, height / 10);
        thirdDay.setPosition(xPos + width * 3 / 7, yPos + height / 2);
        thirdDay.setSize(width / 10, height / 10);
        fourthDay.setPosition(xPos + width * 4 / 7, yPos + height / 2);
        fourthDay.setSize(width / 10, height / 10);
        fifthDay.setPosition(xPos + width * 5 / 7, yPos + height / 2);
        fifthDay.setSize(width / 10, height / 10);

        group.addActor(firstDay);
        group.addActor(secondDay);
        group.addActor(thirdDay);
        group.addActor(fourthDay);
        group.addActor(fifthDay);
    }

}
