/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Buffs;

import GameObjects.Button;
import GameObjects.Interface;
import GameWorld.AbstractWorld;
import Helper.AssetLoader;
import Helper.FontLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author qw
 */
public class BuffsWorld extends AbstractWorld {

    private Button jumpCount,jumpPower,gravity;
    private Label jumpCountLabel,jumpPowerLabel,gravityLabel;
    
    public BuffsWorld(Interface ui, GameLibGDX g) {
        super(ui, g);
        setUpWorld();
    }

    private void setUpWorld(){
                TextureRegion normalState = AssetLoader.btn;
        TextureRegion pressedState = AssetLoader.btnPress;

        jumpCountButton(normalState,pressedState);
        ui.addBack(game);
    }
    
    @Override
    public void update(float delta) {
    }

    private void jumpCountButton(TextureRegion normalState,TextureRegion pressedState){
         jumpCount = new Button("JumpCount", normalState, pressedState, "JC", FontLoader.font) {
            public void action() {
            }
        };
        jumpCount.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        jumpCount.setPosition(ui.getStage().getWidth() / 5,
                ui.getStage().getHeight()* 2/ 3);
        
        ui.getGuiStage().addActor(jumpCount);
    }
    
    private void jumpCountLabel(){
          Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        jumpCountLabel = new Label("", labelS);
        jumpCountLabel.setAlignment(Align.center);
        jumpCountLabel.setFontScale(2);
        jumpCountLabel.setSize(ui.getStage().getWidth() * 0.4f, ui.getStage().getHeight() / 5);
        
        ui.getGuiStage().addActor(jumpCountLabel);
    }
}
