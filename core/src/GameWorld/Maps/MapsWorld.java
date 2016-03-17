/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Maps;

import GameObjects.Button;
import GameObjects.Map;
import GameWorld.AbstractWorld;
import Helper.AssetLoader;
import Helper.FontLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.screen.GameScreen;
import java.util.ArrayList;

/**
 *
 * @author qw
 */
public class MapsWorld extends AbstractWorld {

    public ArrayList<Map> mapsList = new ArrayList<Map>();
    
    ArrayList<Float> mapLocations = new ArrayList<>();
        
    public boolean isNotTouched = false;
    
    public Float nearestButton;
    
    TextureRegion firstButtonNormalState = AssetLoader.btn;
    TextureRegion firstButtonUnboughtState = AssetLoader.btn;
    TextureRegion firstButtonnPressedState = AssetLoader.btnPress;
    
    public MapsWorld(Stage stage, GameLibGDX g){        
        super(stage, g);
        Gdx.app.log("MapsWorld", "create");
        mapsList = LoadMaps(); //Там должны загружаться карты, но пока будут создаваться
        //drawMaps(maplsList);
        createUI(mapsList);
    }
    
    @Override
    public void update(float delta) {
        moveCamera();
    }
    
    private void createUI(ArrayList<Map> allMap){
        for(int i = 0; i< allMap.size();i++){
            stage.addActor(allMap.get(i));
        }
    }
    
    private ArrayList<Map> LoadMaps(){
        ArrayList<Map> allMap = new ArrayList<>();
        
        Map tempMap = new Map("firstMap", firstButtonNormalState, 
                firstButtonnPressedState,firstButtonUnboughtState ,"MAPP1", FontLoader.font){
                    public void action(){
                game.setScreen(new GameScreen(game));                
            }
        };   
         tempMap.setSize(stage.getWidth() * 0.4f, stage.getHeight() / 5);
         tempMap.setPosition((stage.getWidth() - tempMap.getWidth())/2,
                (stage.getHeight() - tempMap.getHeight())/2);
        
        allMap.add(tempMap);
        mapLocations.add(tempMap.getX()+tempMap.getWidth()/2);
        //stage.addActor(tempMap);
        
        Map tempMap2 = new Map("secondMap", firstButtonNormalState, 
        firstButtonnPressedState,firstButtonUnboughtState, "MAPP2", FontLoader.font);
        tempMap2.setSize(stage.getWidth() * 0.4f, stage.getHeight() / 5);
        tempMap2.setPosition((stage.getWidth() - tempMap.getWidth())/2+tempMap2.getWidth()*2,
        (stage.getHeight() - tempMap.getHeight())/2);
        
        allMap.add(tempMap2);
        mapLocations.add(tempMap2.getX()+tempMap2.getWidth()/2);
        //stage.addActor(tempMap2);
        
        return allMap;
    }
    
    public ArrayList<Float> GetButtonPositions(){
        return mapLocations;
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
        for(float f: mapLocations){
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