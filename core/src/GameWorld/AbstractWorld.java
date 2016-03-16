/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld;

import Helper.Statistic;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author qw
 */
public abstract class AbstractWorld {    
    
    public Stage stage;
    protected GameLibGDX game;
    public AbstractWorld(Stage s, GameLibGDX g){
        this.stage = s;
        this.game = g;
    }
    
    public abstract void update(float delta);
    
    private Label coinsText;
    
    protected void initCoins(){
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        coinsText = new Label("", labelS);
        coinsText.setAlignment(Align.center);
        coinsText.setSize(stage.getWidth() * 0.2f, stage.getHeight() * 0.1f);         
        updateCoins();
        stage.addActor(coinsText);
    }
    
    protected void updateCoins(){
        coinsText.setText("You coins "+Statistic.getCoins());
        coinsText.setPosition(stage.getWidth() - coinsText.getWidth(), 
                     stage.getHeight() - coinsText.getHeight());       
    }
}
