/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects.PrizeField;

import GameObjects.Interface;
import GameObjects.Picture;
import Helper.AssetLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.ArrayList;

/**
 *
 * @author Pablo
 */
public class Ticket {

    private ArrayList<PrizeField> prizeFieldList = new ArrayList<PrizeField>();
    private Picture background;

    public Ticket(Interface ui) {
        background = new Picture(AssetLoader.textureBtnNormal);
        background.setPosition(ui.getWidth() * 1 / 10, 0);
        background.setSize(ui.getWidth() * 8 / 10, ui.getHeight());

        prizeFieldList.add(new PrizeField(ui.getWidth() * 2 / 10, ui.getHeight() / 5));
        prizeFieldList.add(new PrizeField(ui.getWidth() * 5 / 10, ui.getHeight() / 5));
        prizeFieldList.add(new PrizeField(ui.getWidth() * 8 / 10, ui.getHeight() / 5));
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
}
