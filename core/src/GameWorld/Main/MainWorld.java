/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Main;

import GameObjects.Button;
import GameWorld.AbstractWorld;
import Helper.AssetLoader;
import Helper.FontLoader;
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
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.MapsScreen;

/**
 *
 * @author qw
 */
public class MainWorld extends AbstractWorld {

    
    //////////////////////////
    
    Button mapsScreenButton, settingsButton, buffsButton , topButton;
    Label highscoreText;
    
    public MainWorld(Stage stage, GameLibGDX g){        
        super(stage, g);
        Gdx.app.log("MainWorld ", "create");
        createUI();
    }
    
    private Rectangle rect = new Rectangle(0, 0, 17, 12);

    public void update(float delta) {
        rect.x++;
        if (rect.x > 400) {
            rect.x = 0;
        }        
    }

    public Rectangle getRect() {
        return rect;
    }
    
    private void createUI(){

        TextureRegion normalState = AssetLoader.btn;
        TextureRegion pressedState = AssetLoader.btnPress;
        
        playButton(normalState, pressedState);        
        settingsButton(normalState, pressedState);    
        buffsButton(normalState, pressedState);    
        topButton(normalState, pressedState);   
        initHighscore();
        initCoins();
    }
    
    private void playButton(TextureRegion normalState, TextureRegion pressedState){
        mapsScreenButton = new Button("Play", normalState, pressedState, "PLAY", FontLoader.font){
            public void action(){
                game.setScreen(new GameScreen(game));                
            }
        };    
        mapsScreenButton.setSize(stage.getWidth() * 0.4f, stage.getHeight() / 5);
        mapsScreenButton.setPosition((stage.getWidth() - mapsScreenButton.getWidth())/2,
                (stage.getHeight() - mapsScreenButton.getHeight())/2);
                
        stage.addActor(mapsScreenButton);
    }
    
    private void settingsButton(TextureRegion normalState, TextureRegion pressedState){
        settingsButton = new Button("Settings", normalState, pressedState, "SETT", FontLoader.font){
            public void action(){
                game.setScreen(new MapsScreen(game));
                Statistic.setHighScore(30);
                updateHighscore();
            }
        };    
        settingsButton.setSize(stage.getWidth() * 0.4f / 3, stage.getHeight() / 6);
        settingsButton.setPosition((stage.getWidth() - mapsScreenButton.getWidth())/2,
                 mapsScreenButton.getY() - mapsScreenButton.getHeight());        
        
        stage.addActor(settingsButton);
    }
    
    private void buffsButton(TextureRegion normalState, TextureRegion pressedState){
        buffsButton = new Button("Buffs", normalState, pressedState, "BUFF", FontLoader.font){
            public void action(){
                //game.setScreen(new MapsScreen(game));                
            }
        };    
        buffsButton.setSize(stage.getWidth() * 0.4f / 3, stage.getHeight() / 6);
        buffsButton.setPosition(settingsButton.getX() + settingsButton.getWidth(),
                 mapsScreenButton.getY() - mapsScreenButton.getHeight());        
        
        stage.addActor(buffsButton);
    }
    
    private void topButton(TextureRegion normalState, TextureRegion pressedState){
        topButton = new Button("Top", normalState, pressedState, "TOP", FontLoader.font){
            public void action(){
                //game.setScreen(new MapsScreen(game));                
            }
        };    
        topButton.setSize(stage.getWidth() * 0.4f / 3, stage.getHeight() / 6);
        topButton.setPosition(buffsButton.getX() + buffsButton.getWidth(),
                 mapsScreenButton.getY() - mapsScreenButton.getHeight());        
        
        stage.addActor(topButton);
    }
 
    private void initHighscore(){
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        highscoreText = new Label("", labelS);
        highscoreText.setAlignment(Align.center);
        highscoreText.setFontScale(2);
        highscoreText.setSize(stage.getWidth() * 0.4f, stage.getHeight() / 5);         
        updateHighscore();
        stage.addActor(highscoreText);
    }
    
    private void updateHighscore(){
        highscoreText.setText("You highscore "+Statistic.getHighScore());
        highscoreText.setPosition((stage.getWidth() - highscoreText.getWidth())/2, 
                     mapsScreenButton.getY() + highscoreText.getHeight());       
    }
}
