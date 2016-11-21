/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Maps;

import GameObjects.AbstractWindow;
import GameObjects.Button;
import GameObjects.Interface;
import GameObjects.Map;
import GameWorld.AbstractWorld;
import GameWorld.Game.Objects.BuyMapWindow;
import Helper.AssetLoader;
import Helper.FontLoader;
import Helper.Statistic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.screen.GameScreen;
import java.util.ArrayList;

/**
 *
 * @author qw
 */
public class MapsWorld extends AbstractWorld {
    


    public ArrayList<Map> mapsList = new ArrayList<Map>();

    ArrayList<Float> mapLocations = new ArrayList<Float>();

    public boolean isNotTouched = false;

    public Float nearestButton;

    TextureRegion firstButtonNormalState = AssetLoader.btn;
    TextureRegion firstButtonUnboughtState = AssetLoader.btn;
    TextureRegion firstButtonnPressedState = AssetLoader.btnPress;
    TextureRegion dialogWindowNormalState = AssetLoader.btn;

    Button confirmDialogButton;
    Button cancelDialogButton;
    Button backDialogButton;

    Map mapToBuy;

    Boolean isTouchUnboughtMap = false;
    Boolean isTouchCancel = false;
    private Boolean isDialog = false;
    
    BuyMapWindow buyMapWindow;
    
    Button rightButton;
    Button leftButton;

    
    public MapsWorld(Interface ui, GameLibGDX g) {
        super(ui, g);

        buyMapWindow = new BuyMapWindow(ui.getGuiStage(),this);
        mapsList = LoadMaps();
        LoadLeftAndRightButtons();
        SetVisible();

        createUI(mapsList);

    }

    @Override
    public void update(float delta) {

//        System.out.println("Камера - "+ui.getStage().getCamera().position.x);
//        System.out.println("Первая мапа - "+mapsList.get(1).getX());
            
         //System.out.println(GetActiveMapNumber());
        
        
        ui.updateFps(1 / delta);
        
        moveCamera();
        SetVisible();
        }

    private void createUI(ArrayList<Map> allMap) {
        for (int i = 0; i < allMap.size(); i++) {
            ui.getStage().addActor(allMap.get(i));
        }
        ui.addBack(game);
    }

    public boolean isDialog() {
        return isDialog;
    }
    public void setIsDialog(boolean flag){
        System.out.println("I'm set to "+flag);
        isDialog = flag;
    }
   

    private ArrayList<Map> LoadMaps() {

        ArrayList<Map> allMap = CreateMaps();
        String[] mapsArray = Statistic.getOpenMaps();
        for (int i = 0; i < mapsArray.length; i++) {
            try {
                allMap.get(Integer.valueOf(mapsArray[i])).BuyMap();
            } catch (Exception e){

            }
        }
        return allMap;
    }

    private ArrayList<Map> CreateMaps() {
        ArrayList<Map> allMap = MapsLoader.GetMaps(this);

        mapLocations = MapsLoader.GetMapsLocations();
        return allMap;
    }

    public ArrayList<Float> GetButtonPositions() {
        return mapLocations;
    }

    private void moveCamera() {
        if (isNotTouched) {
            
            float speed = 0;
            //if (nearestButton != getCameraX()) {
            if (Math.abs((double) (nearestButton - getCameraX())) > 10) {//TODO: Тут можно относительный размер
                speed = (nearestButton - getCameraX()) / 10.0f;
                setCameraX(getCameraX() + speed);//TODO: Возможно баг пофикшен, но это неточно
                //isDialog = true;
            } else {
                isNotTouched = false;
                //isDialog = false;
            }
            //setCameraX(getCameraX() + speed);
        }
    }

    public void calculateBtnPos() {
        float camPosX = ui.getStage().getCamera().position.x;
        float min = 100000;
        for (float f : mapLocations) {
            if (Math.abs(camPosX - f) < min) {
                min = Math.abs(camPosX - f);
                nearestButton = f;
            }
        }
    }

    protected void setCameraX(float x) {
        ui.getStage().getCamera().position.x = x;
        ui.getStage().getCamera().update();
    }

    protected float getCameraX() {
        return ui.getStage().getCamera().position.x;
    }

    public GameLibGDX getGame() {
        System.out.println("getGame");
        return this.game;
    }
    public void setIsCancel(boolean flag){
        this.isTouchCancel = flag;
    }
     
    public void touchConfirm(){
        System.out.println("touchConfirm");
        
                        try {
                    Statistic.removeCoins(mapToBuy.GetPrice());
                    mapToBuy.BuyMap();
                    Statistic.OpenMap(mapToBuy.getName());
                    isTouchCancel = true;
                    buyMapWindow.deleteWindow();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
    }
    public BuyMapWindow getBuyMapWindow(){
        return this.buyMapWindow;
    }
    
    private void LoadLeftAndRightButtons(){
        leftButton = new Button("Left", AssetLoader.leftSlideTexture, AssetLoader.leftSlidePressedTexture, "", FontLoader.font) {
            public void action() {
                
                if(!isDialog){
                
                if (GetActiveMapNumber()!=0){
//                setNearestLength((float) (ui.getStage().getCamera().position.x
//                        - (mapLocations.get(GetActiveMapNumber()-1))));
                    setNearestLength((float) (ui.getStage().getCamera().position.x-
                            mapsList.get(0).getWidth()*2));
                }
                
                
                SetVisible();
                
                }
            }
        };
        leftButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        leftButton.setPosition((float) (leftButton.getWidth()/4),ui.getGuiStage().getHeight()/2
        - leftButton.getHeight()/2);

        ui.getGuiStage().addActor(leftButton);
        
        rightButton = new Button("Right", AssetLoader.rightSlideTexture, AssetLoader.rightSlidePressedTexture, "", FontLoader.font) {
            public void action() {
                
                if(!isDialog){
                
                if (GetActiveMapNumber()!=mapsList.size()-1){
//                setNearestLength((float) (ui.getStage().getCamera().position.x
//                        - (mapLocations.get(GetActiveMapNumber()+1))));

                    setNearestLength((float) (ui.getStage().getCamera().position.x+
                            mapsList.get(0).getWidth()*2));

                }
                
                SetVisible();
                
                }
            }
        };
        rightButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        rightButton.setPosition((float) (ui.getGuiStage().getWidth()-rightButton.getWidth()*1.25),ui.getGuiStage().getHeight()/2
        - rightButton.getHeight()/2);

        ui.getGuiStage().addActor(rightButton);
    }
    
    public void SetVisible(){
        
        if (GetActiveMapNumber()==0){
            leftButton.setVisible(false);
            rightButton.setVisible(true);
        }else{
            if (GetActiveMapNumber()==mapsList.size()-1){
                rightButton.setVisible(false);
                leftButton.setVisible(true);
            } else {
                rightButton.setVisible(true);
                leftButton.setVisible(true);
            }
        }
        
    }
    
    private int GetActiveMapNumber(){

        
        for (int i = 0;i<mapsList.size();i++){
            
            //System.out.println((ui.getStage().getCamera().position.x-mapsList.get(i).getX()));
            
            if (Math.abs((ui.getStage().getCamera().position.x-mapsList.get(i).getX()))<=
                    mapsList.get(0).getWidth()/2
                    +
                    mapsList.get(i).getWidth()/4){
                
                
                
                return i;
            }
        }
        return -1;
    }
    
    public void setNearestLength(float length){
        this.nearestButton = length;
    }   

}
