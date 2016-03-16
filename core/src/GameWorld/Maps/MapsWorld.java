/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Maps;

import GameObjects.Button;
import GameWorld.AbstractWorld;
import Helper.AssetLoader;
import Helper.FontLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.mygdx.game.GameLibGDX;
import java.util.ArrayList;

/**
 *
 * @author qw
 */
public class MapsWorld extends AbstractWorld {

    ArrayList<Button> mapsList = new ArrayList<Button>();
    
    ArrayList<Float> buttonLocations = new ArrayList<>();
        
    public boolean isNotTouched = false;
    
    public Float nearestButton;
    
    TextureRegion firstButtonNormalState = AssetLoader.btn;
    TextureRegion firstButtonnPressedState = AssetLoader.btnPress;
    
    public MapsWorld(Stage stage, GameLibGDX g){        
        super(stage, g);
        Gdx.app.log("MapsWorld", "create");
        createUI();
    }
    
    @Override
    public void update(float delta) {
        moveCamera();
    }
    
    private void createUI(){
        LoadButtons();       
    }
    
    private ArrayList<Button> LoadButtons(){
        ArrayList< Button> allButtons = new ArrayList<>();
        
        Button tempButton = new Button("firstMap", firstButtonNormalState, 
                firstButtonnPressedState, "MAPP1", FontLoader.font);
         tempButton.setSize(stage.getWidth() * 0.4f, stage.getHeight() / 5);
         tempButton.setPosition((stage.getWidth() - tempButton.getWidth())/2,
                (stage.getHeight() - tempButton.getHeight())/2);
        
        allButtons.add(tempButton);
        buttonLocations.add(tempButton.getX()+tempButton.getWidth()/2);
        stage.addActor(tempButton);
        
        Button tempButton2 = new Button("secondMap", firstButtonNormalState, 
        firstButtonnPressedState, "MAPP2", FontLoader.font);
        tempButton2.setSize(stage.getWidth() * 0.4f, stage.getHeight() / 5);
        tempButton2.setPosition((stage.getWidth() - tempButton.getWidth())/2+tempButton2.getWidth()*2,
        (stage.getHeight() - tempButton.getHeight())/2);
        
        allButtons.add(tempButton2);
        buttonLocations.add(tempButton2.getX()+tempButton2.getWidth()/2);
        stage.addActor(tempButton2);
        
        return allButtons;
    }
    
    public ArrayList<Float> GetButtonPositions(){
        return buttonLocations;
    }
    
    private void moveCamera(){
        if(isNotTouched){
            float speed = 0;
            if(nearestButton != getCameraX()){
                speed = (nearestButton - getCameraX()) / 10.0f;
            } else {
                isNotTouched = false;
            }
            setCameraX(getCameraX() + speed);
        }
    }    
    
    public void calculateBtnPos(){
        float camPosX = stage.getCamera().position.x;
        float min = 100000;
        for(float f: buttonLocations){
            if(Math.abs(camPosX - f) < min){
                min = Math.abs(camPosX - f);
                nearestButton = f;
            }
        }
        Gdx.app.log("MapsWorld", "create" + nearestButton);
    }
    
    protected void setCameraX(float x){
        stage.getCamera().position.x = x;
        stage.getCamera().update();
    }
    
    protected float getCameraX(){
       return  stage.getCamera().position.x;
    }
    
}