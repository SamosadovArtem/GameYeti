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
import Helper.Statistic;
import com.badlogic.gdx.Game;
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
    TextureRegion dialogWindowNormalState = AssetLoader.btn;

    Button confirmDialogButton;
    Button cancelDialogButton;
    Button backDialogButton;

    Map mapToBuy;

    Boolean isTouchUnboughtMap = false;
    Boolean isTouchCancel = false;
    Boolean isDialog = false;

    public MapsWorld(Stage stage, GameLibGDX g) {
        super(stage, g);
        Gdx.app.log("MapsWorld", "create");

        mapsList = LoadMaps(); //Там должны загружаться карты, но пока будут создаваться
        //drawMaps(maplsList);
        createUI(mapsList);
        CreateDialogWindow();
    }

    @Override
    public void update(float delta) {
            
        
                moveCamera();
           
            
        confirmDialogButton.setX((float) (stage.getCamera().position.x - 
                confirmDialogButton.getWidth() * 1.5));

        cancelDialogButton.setX((float) (stage.getCamera().position.x + 
                confirmDialogButton.getWidth() * 1.5));
        
        
        backDialogButton.setX ((float) (confirmDialogButton.getX()- 
                confirmDialogButton.getWidth()*1.5));

        if (isTouchUnboughtMap) {
            System.out.println(mapsList.get(1).IsMapBought());
            isDialog = true;

            if (confirmDialogButton.getY() >= stage.getHeight() / 2) {
                isTouchUnboughtMap = false;
                //isDialog = false;
            }
            if (isTouchUnboughtMap) { // ИСПРАВЬ ПОТОМ ЭТО, ПОЖАЛУЙСТА
                MoveDialogWindow(true);
            }
        }
            if (isTouchCancel) {

            if (confirmDialogButton.getY() <=confirmDialogButton.getHeight()*(-5) ) {
                isTouchCancel = false;
                isDialog = false;
            }
            if (isTouchCancel) { // ИСПРАВЬ ПОТОМ ЭТО, ПОЖАЛУЙСТА
                MoveDialogWindow(false);
            
           }
        }

    }

    private void createUI(ArrayList<Map> allMap) {
        for (int i = 0; i < allMap.size(); i++) {
            stage.addActor(allMap.get(i));
        }
    }
    public boolean isDialog(){
        return isDialog;
    }

    private void MoveDialogWindow(Boolean isUP) {
        if (isUP){
        confirmDialogButton.setY(confirmDialogButton.getY() + 15);

        cancelDialogButton.setY(cancelDialogButton.getY() + 15);
        
        backDialogButton.setY(backDialogButton.getY() + 15);
        }
        else{
        confirmDialogButton.setY(confirmDialogButton.getY() - 15 );

        cancelDialogButton.setY(cancelDialogButton.getY() - 15);
        
        backDialogButton.setY(backDialogButton.getY() - 15);
            
        }
            
    }

    private void CreateDialogWindow() {

        
        this.confirmDialogButton = new Button("Confirm", firstButtonNormalState,
                firstButtonNormalState, "cnf", FontLoader.font) {
            public void action() {

                try {
                    Statistic.payCoins(mapToBuy.GetPrice());
                    mapToBuy.BuyMap();
                    Statistic.OpenMap(mapToBuy.getName());
                    isTouchCancel = true;
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }

                System.out.println(mapToBuy.getName());
                System.out.println(mapToBuy.IsMapBought());
            }
        };
        confirmDialogButton.setSize(stage.getWidth() * 0.1f, stage.getHeight() / 10);
        confirmDialogButton.setPosition(stage.getCamera().position.x - 
                confirmDialogButton.getWidth(), confirmDialogButton.getHeight()*(-5));

        this.cancelDialogButton = new Button("Cancel", firstButtonNormalState,
                firstButtonNormalState, "cnl", FontLoader.font) {
            @Override
            public void action() {
                isTouchCancel = true;
            }
        };
        cancelDialogButton.setSize(stage.getWidth() * 0.1f, stage.getHeight() / 10);
        cancelDialogButton.setPosition(stage.getCamera().position.x + 
                confirmDialogButton.getWidth(), confirmDialogButton.getHeight()*(-5));

        this.backDialogButton = new Button("Back", firstButtonNormalState, 
                firstButtonNormalState, "Back", FontLoader.font);
        backDialogButton.setSize(stage.getWidth() * 0.7f, stage.getHeight() / 2);
        backDialogButton.setPosition(confirmDialogButton.getX()- 
                confirmDialogButton.getWidth()*2, confirmDialogButton.getHeight()*-1 
                        + confirmDialogButton.getHeight()*(-5));
        
        stage.addActor(backDialogButton);
        stage.addActor(confirmDialogButton);
        stage.addActor(cancelDialogButton);
        
    }

    private ArrayList<Map> LoadMaps() {
        String openMaps = Statistic.getOpenMaps();
        ArrayList<Map> allMap = CreateMaps();
        String[] mapsArray = openMaps.split(",");
        for (int i = 0; i < mapsArray.length; i++) {
            allMap.get(Integer.parseInt(mapsArray[i])).BuyMap();
            int a = 7;
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
        float camPosX = stage.getCamera().position.x;
        float min = 100000;
        for (float f : mapLocations) {
            if (Math.abs(camPosX - f) < min) {
                min = Math.abs(camPosX - f);
                nearestButton = f;
            }
        }
        Gdx.app.log("MapsWorld", "create" + nearestButton);
    }

    protected void setCameraX(float x) {
        stage.getCamera().position.x = x;
        stage.getCamera().update();
    }

    protected float getCameraX() {
        return stage.getCamera().position.x;
    }
    
    public GameLibGDX GetGame(){
        return this.game;
    }

}
