/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author AS
 */
package GameWorld.Donate;
import GameObjects.Button;
import GameWorld.World;
import Helper.AssetLoader;
import Helper.FontLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.GameLibGDX;

public class DonateWorld extends World {
    Button Donate100, Donate500, Donate1000, Donate5000;
    Label label100, label1000, label5000, label500;
    public DonateWorld(Stage s, GameLibGDX g) {
        super(s, g);
        setButtons();
        setLabel(label100,stage.getWidth()/8,6*stage.getHeight()/8,"100");
        setLabel(label1000,stage.getWidth()-4*stage.getWidth()/8,6*stage.getHeight()/8,"1000");
        setLabel(label500,stage.getWidth()/8,2*stage.getHeight()/8,"500");
        setLabel(label5000,stage.getWidth()-4*stage.getWidth()/8,2*stage.getHeight()/8,"5000");
    }

    @Override
    public void update(float delta) {
        
    }
    private void setButtons(){
        TextureRegion normalState = AssetLoader.btn;
        TextureRegion pressedState = AssetLoader.btnPress;
        donate100(normalState,pressedState);
        donate1000(normalState,pressedState);
        donate500(normalState,pressedState);
        donate5000(normalState,pressedState);
    }
    private void setLabel(Label label, float x, float y, String text){
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        label = new Label(text, labelS);
        //label.setSize(stage.getWidth()/10,stage.getHeight()/10);
        //label.setScale(100,100);
        label.setPosition(x, y);
        stage.addActor(label);
    }
    private void donate100(TextureRegion normalState, TextureRegion pressedState){
        Donate100 = new Button("100", normalState, pressedState, "100", FontLoader.font){
            public void action(){
                //here          
            }
        };    
        Donate100.setSize(2*stage.getWidth()/8,2*stage.getHeight()/8);
        Donate100.setPosition(stage.getWidth()/8+stage.getWidth()/8,5*stage.getHeight()/8);      
        
        stage.addActor(Donate100);
    }
    private void donate1000(TextureRegion normalState, TextureRegion pressedState){
        Donate1000 = new Button("1000", normalState, pressedState, "1000", FontLoader.font){
            public void action(){
                //here          
            }
        };    
        Donate1000.setSize(2*stage.getWidth()/8,2*stage.getHeight()/8);
        Donate1000.setPosition(stage.getWidth()-3*stage.getWidth()/8,5*stage.getHeight()/8);         
        
        stage.addActor(Donate1000);
    }        
    private void donate500(TextureRegion normalState, TextureRegion pressedState){
        Donate500 = new Button("500", normalState, pressedState, "500", FontLoader.font){
            public void action(){
                //here          
            }
        };    
        Donate500.setSize(2*stage.getWidth()/8,2*stage.getHeight()/8);
        Donate500.setPosition(stage.getWidth()/8+stage.getWidth()/8,stage.getHeight()/8);      
        
        stage.addActor(Donate500);
    }
    private void donate5000(TextureRegion normalState, TextureRegion pressedState){
        Donate5000 = new Button("5000", normalState, pressedState, "5000", FontLoader.font){
            public void action(){
                //here          
            }
        };    
        Donate5000.setSize(2*stage.getWidth()/8,2*stage.getHeight()/8);
        Donate5000.setPosition(stage.getWidth()-3*stage.getWidth()/8,stage.getHeight()/8);          
        
        stage.addActor(Donate5000);
        }
}