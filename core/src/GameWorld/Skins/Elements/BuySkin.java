package GameWorld.Skins.Elements;

import Helper.Statistic;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by broff on 15.04.2016.
 */
public class BuySkin extends Actor {

    private int index;

    private float posX;
    private float posY;
    private float width;
    private float height;
    private int cost;
    private boolean buyStatus;
    private String name = "";
    private TextureRegion skin;


    public BuySkin(int index, TextureRegion pTextureRegion, float x, float y, float width, float height,
                   int cost, boolean buyStatus, String name){
        this.skin = pTextureRegion;
        this.index = index;
        this.posX = x;
        this.posY = y;
        this.width = width;
        this.height = height;
        this.cost = cost;
        this.buyStatus = buyStatus;
        this.name = name;
    }

    private void updatePos(){
        this.setPosition(posX - getWidth() / 2, posY + getHeight() / 2);
    }

    public void setX(float x){
        posX = x;
        updatePos();
    }
    
    public void setXCenter(float x){
        posX = x - getWidth()/2;
        updatePos();
    }

    public void setY(float y){
        posY = y;
        updatePos();
    }

    public float getX(){
        return posX;
    }

    public float getXCenter(){
        return posX + getWidth() / 2;
    }

    public float getY(){
        return posY;
    }

    public void setWidth(float w){
        width = w;
        this.setWidth(w);
        updatePos();
    }

    public void setHeight(float h){
        height = h;
        this.setWidth(h);
        updatePos();
    }

    public void buySkin(){
        buyStatus = false;
    }

    public void setBuyStatus(boolean s){
        buyStatus = s;
    }

    public boolean getBuyStatus(){
        return buyStatus;
    }

    public String getName(){
        return name;
    }

    public int getIndex(){
        return index;
    }

    public int getCost(){
        return cost;
    }

    public void setScale(float s){
        float xC = this.getXCenter();
        this.setSize(width * s, height * s);
        setXCenter(xC);
        updatePos();
    }

    public void draw (Batch batch, float parentAlpha) {
        batch.draw(skin, getX(), getY(), getWidth(), getHeight());
    }
    
    public void click(){
        if(buyStatus){
            SkinsStatistic.setActiveSkin(getIndex());
        } else {
            if(Statistic.getCoins() >= this.cost){
                Statistic.payCoins(cost);
                SkinsStatistic.addSkin(index);
                buyStatus = true;
            }
        }
    }
}
