/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects.PrizeField;

import GameObjects.Picture;
import Helper.AssetLoader;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import java.util.ArrayList;

/**
 *
 * @author Pablo
 */
public class PrizeField {

    private Picture prize;
    private ArrayList<ProtectiveLayerParticle> protectiveLayer = new ArrayList<ProtectiveLayerParticle>();
    private Group group = new Group();
    private final int xMultiplier = 5;
    private final int yMultiplier = 5;
    private float xPos, yPos;

    public PrizeField(float xPos, float yPos) {
        prize = new Picture(AssetLoader.pinguin);
        this.xPos = xPos;
        this.yPos = yPos;
        prize.setPosition(xPos, yPos);
        prize.setSize(100, 100);
        group.addActor(prize);
        initProtectiveLayer();
    }

    private void initProtectiveLayer() {
        for (int i = 0; i < 400; i++) {
            protectiveLayer.add(new ProtectiveLayerParticle(AssetLoader.protectiveLayerTexture));
        }
        setPosition();
        for (ProtectiveLayerParticle p : protectiveLayer) {
            p.setSize(xMultiplier, yMultiplier);
            group.addActor(p);
        }
    }

    private void setPosition() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                protectiveLayer.get(i * 20 + j).setPosition(j * xMultiplier + xPos, i * yMultiplier + yPos);
                protectiveLayer.get(protectiveLayer.size() - 1 - (i * 20 + j)).initSpritePos(j * xMultiplier, i * yMultiplier);
            }
        }
    }

    public void addToStage(Stage stage) {
        stage.addActor(group);
    }

    public ArrayList<ProtectiveLayerParticle> getProtectiveLayer() {
        return protectiveLayer;
    }
}
