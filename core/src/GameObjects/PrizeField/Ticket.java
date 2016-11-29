/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects.PrizeField;

import GameObjects.Interface;
import GameObjects.Picture;
import Helper.AssetLoader;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.ArrayList;

/**
 *
 * @author Pablo
 */
public class Ticket {

    private ArrayList<PrizeField> prizeFieldList = new ArrayList<PrizeField>();
    private Picture background;
    private boolean isWin;

    public Ticket(Interface ui) {
        background = new Picture(AssetLoader.textureBtnNormal);
        background.setPosition(0, 0);
        background.setSize(ui.getWidth(), ui.getHeight());

        Gdx.app.log(String.valueOf(ui.getWidth() * 2 / 10), String.valueOf(ui.getHeight() / 5));

        prizeFieldList.add(new PrizeField(ui.getWidth() * 2 / 10, ui.getHeight() / 5));
        prizeFieldList.add(new PrizeField(ui.getWidth() * 5 / 10, ui.getHeight() / 5));
        prizeFieldList.add(new PrizeField(ui.getWidth() * 8 / 10, ui.getHeight() / 5));
        checkIsWin();
    }

    private void checkIsWin() {
        isWin = true;
        for (PrizeField p : prizeFieldList) {
            if (!p.getIsWin()) {
                isWin = false;
            }
        }
    }

    public ArrayList<PrizeField> getPrizeFieldList() {
        return prizeFieldList;
    }

    public void addToStage(Stage stage) {
        stage.addActor(background);
        for (PrizeField p : prizeFieldList) {
            p.addToStage(stage);
        }
    }

    public boolean getIsWin() {
        return isWin;
    }

    public boolean getIsActive() {
        for (PrizeField p : prizeFieldList) {
            if (!p.getIsActive()) {
                return false;
            }
        }
        return true;
    }
}
