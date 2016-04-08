/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects.Buffs;

import Enums.BuffType;
import Helper.MyTimer;

/**
 *
 * @author Pablo
 */
public abstract class Buff {

    private MyTimer timer;
    private boolean isActive;
    private BuffType type;
    private int level;

    public Buff(MyTimer time, BuffType type, int level) {
        timer = time;
        isActive = true;
        this.type = type;
        this.level = level;
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
    
    public int getLevel(){
        return level;
    }
    
    public void increaseLevel(){
        level++;
    }
    
    public MyTimer getTimer(){
        return timer;
    }

    public String getSaveData(){
        return level+","+timer.getDateFinish().getTime();
    }
}
