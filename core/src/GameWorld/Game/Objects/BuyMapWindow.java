/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import GameObjects.AbstractWindow;
import GameObjects.Button;
import GameWorld.Maps.MapsWorld;
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
import com.mygdx.game.screen.MainScreen;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;
import static java.lang.Thread.sleep;

/**
 *
 * @author Admin
 */
public class BuyMapWindow extends AbstractWindow {
    
    private static BuyMapWindow instance;

    private Thread mapThread;
    
    private int mapCost = 0;
    private MapsWorld mapsWorld;

    public BuyMapWindow(Stage stage, MapsWorld mapsWorld) {
        super(stage);
        width = stage.getWidth() / 2;
        height = stage.getHeight() * 3 / 5;
        this.mapsWorld = mapsWorld;
    }

    

//        public static BuyMapWindow GetInstance(Stage stage, MapsWorld mapsWorld){
//        if (instance==null){
//            instance = new BuyMapWindow(stage, mapsWorld);
//        }
//        return instance;
//    }

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
        textLabel.setText("Buy map");
        textLabel.setPosition(xPos + width / 2 - textLabel.getWidth() / 2,
                yPos + height - textLabel.getHeight());
        group.addActor(textLabel);

        Label costLabel;
        costLabel = new Label("", labelS);
        costLabel.setAlignment(Align.center);
        costLabel.setFontScale(1);
        costLabel.setSize(width / 3, height / 6);
        costLabel.setText("" + mapCost);
        costLabel.setPosition(xPos + width / 2 - costLabel.getWidth() / 2,
                yPos + height * 2 / 3);
        group.addActor(costLabel);
    }

    @Override
    protected void initButtons(final GameLibGDX game) {
        Button buyButton = new Button("Buy", normalState, pressedState, "BUY", FontLoader.font) {
            public void action() {
                Gdx.app.log("check", "CONFIRM");
                
                mapsWorld.touchConfirm();
                //game.setScreen(new GameScreen(game));
            }
        };
        buyButton.setSize(width / 5, height / 6);
        buyButton.setPosition(xPos, yPos);
        group.addActor(buyButton);

        Button cancelButton = new Button("Cancel", normalState, pressedState, "CANCEL", FontLoader.font) {
            public void action() {
                Gdx.app.log("check", "CANCEL");

                mapsWorld.setIsCancel(true);
                
                mapsWorld.getBuyMapWindow().setCheck(true);
                
                mapsWorld.getBuyMapWindow().deleteWindow();
                
                
                
            }
        };
        cancelButton.setSize(width / 5, height / 6);
        cancelButton.setPosition(xPos + width - cancelButton.getWidth(), yPos);
        group.addActor(cancelButton);
    }
    
//        public void unScroll() {
//
//            this.deleteWindow();
//        
//        
//    }
    
        

    
}