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
import com.mygdx.game.GameLibGDX;
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
    Boolean isDialog = false;
    
    BuyMapWindow buyMapWindow;


    
    public MapsWorld(Interface ui, GameLibGDX g) {
        super(ui, g);

        buyMapWindow = new BuyMapWindow(ui.getGuiStage(),this);
        mapsList = LoadMaps(); 

        createUI(mapsList);

    }

    @Override
    public void update(float delta) {

        ui.updateFps(1 / delta);
        moveCamera();

//        if (isTouchUnboughtMap) {
//            
//            isDialog = true;
//           //buyMapWindow.createWindow(game);
//           isTouchUnboughtMap = false;
//        }
//
//            if (isTouchCancel) { 
//                //buyMapWindow.unScroll();
//                isTouchCancel = false;
//                isDialog = false;
                
                /*
я хачу умирець
        :(
        ни нада(
        я скрою вены(
        не скрывай(
        скрою((

*/
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
            if (nearestButton != getCameraX()) {
                speed = (nearestButton - getCameraX()) / 10.0f;
            } else {
                isNotTouched = false;
            }
            setCameraX(getCameraX() + speed);
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
        return this.game;
    }
    public void setIsCancel(boolean flag){
        this.isTouchCancel = flag;
    }
    
   
    
    public void touchConfirm(){
                        try {
                    Statistic.payCoins(mapToBuy.GetPrice());
                    mapToBuy.BuyMap();
                    Statistic.OpenMap(mapToBuy.getName());
                    isTouchCancel = true;
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
    }
    public BuyMapWindow getBuyMapWindow(){
        return this.buyMapWindow;
    }
    

}
