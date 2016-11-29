/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects.Buffs;

import Enums.BuffType;
import GameObjects.Picture;
import Helper.AssetLoader;
import Helper.MyTimer;
import java.util.Date;

/**
 *
 * @author Pablo
 */
public abstract class Buff {

    private MyTimer timer;
    private boolean isActive;
    private BuffType type;
    protected int level = 0;
    protected int levelMax = 0;
    protected int cost;
    protected Picture icon;

    public Buff(MyTimer time, BuffType type, int level) {
        timer = time;

        this.type = type;
        
        icon = new Picture(AssetLoader.textureBtnNormal);
        
        if (timer.getTimeStatus()) {
            this.level = level;
            isActive = true;
        } else {
            this.level = 0;
            isActive = false;
        }
    }
    
    public boolean getIsActive() {
        if (!timer.getTimeStatus()) {
            isActive = false;
        };
        return isActive;
    }

    public BuffType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getLevelMax(){
        return levelMax;
    }

    public void increaseLevel() {
        level++;
    }

    public MyTimer getTimer() {
        return timer;
    }

    public String getSaveData() {
        return level + "," + timer.getDateFinish().getTime();
    }

    public void updateTimer(long time) {
        timer = new MyTimer(new Date(), time);
    }
    
    public Picture getIcon(){
        return icon;
    }

    public abstract void upgrade();

    public abstract void update();

    public abstract int getCost(int level);

    public abstract boolean checkUpdate(int allCoins);

    public abstract boolean checkUpgrade(int allCoins);
    
    public abstract String getInfo();
}
