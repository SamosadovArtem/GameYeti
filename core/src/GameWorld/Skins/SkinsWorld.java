package GameWorld.Skins;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.screen.BuySkinsScreen;

import GameObjects.Button;
import GameObjects.Interface;
import GameObjects.Scroll;
import GameWorld.AbstractWorld;
import GameWorld.Skins.Elements.SkinList;
import GameWorld.Skins.Elements.SkinsContainer;
import GameWorld.Skins.Elements.SkinsStatistic;
import Helper.AssetLoader;

/**
 * Created by broff on 15.04.2016.
 */
public class SkinsWorld extends AbstractWorld {

    private SkinsContainer box;
    private int activeSkin;
    private Button buyButton;
    private Label label;
    public boolean scrollArea = false;
    private float inertion = 0;

    public SkinsWorld(Interface ui, GameLibGDX g) {
        super(ui, g);
        ui.addBack(game);
        initAllSkins();
        initBuyButton();
        activeSkin = SkinsStatistic.getActiveSkin();
    }

    @Override
    public void update(float delta) {
        updateButton();

        if(inertion < -5 || inertion > 5){
            box.moveX(inertion);
            if(inertion < 0 ) {
                inertion+=0.5f;
            } else {
                inertion-=0.5f;
            }
            if(inertion > -5 && inertion < 5){
                inertion = 0;
            }
        } else {
            moveCamera();
        }
    }

    private void initAllSkins(){
        SkinList skinList = new SkinList();
        box = new SkinsContainer(skinList.getSkins(), ui.getStage().getCamera().position.x, ui.getStage(), this);
    }

    public SkinsContainer getSkins(){
        return box;
    }

    private void initBuyButton(){
        buyButton = new Button("BuyButton", AssetLoader.btn, AssetLoader.btnPress, "", new BitmapFont())
        {
            public void action() {
                box.getSkins().get(activeSkin).click();
                updateBtnText();
            }
        };
        buyButton.setSize(ui.getStage().getWidth() * 0.4f / 3, ui.getStage().getHeight() / 6);
        buyButton.setPosition(ui.getStage().getWidth() / 2 - buyButton.getWidth() / 2,
                ui.getStage().getHeight() / 4 - buyButton.getHeight() / 2);


        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        label = new Label(box.getSkinName(), labelS);
        label.setPosition(ui.getStage().getWidth() / 2 - buyButton.getWidth() / 2,
                buyButton.getY() - buyButton.getHeight());

        updateBtnText();
        label.setText(box.getSkinName());

        ui.getGuiStage().addActor(label);
        ui.getGuiStage().addActor(buyButton);
    }

    private void updateButton(){
        if(activeSkin != box.getActiveButton()){           
            updateBtnText();
        }
    }
    
    private void updateBtnText(){
        label.setText(box.getSkinName());
        activeSkin = box.getActiveButton();
        if(box.getSkins().get(activeSkin).getBuyStatus()){
            if(SkinsStatistic.getActiveSkin() == box.getSkins().get(activeSkin).getIndex()){
               buyButton.setText("Active"); 
            } else {
                buyButton.setText("Activate"); 
            }
        } else {
            buyButton.setText(""+box.getSkinCoast());
        }
    }

    private void moveCamera() {
        if(!scrollArea) {
            float speed = (getCameraX() - box.getSkins().get(activeSkin).getXCenter()) / 30.0f;
            box.moveX(speed);
        }
    }

    protected float getCameraX() {
        return ui.getStage().getCamera().position.x;
    }

    public void setInertion(float in){
        inertion = in;
    }

}