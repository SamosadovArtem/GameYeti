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
    private int value;

    public Buff(long length, BuffType type, int value) {
        timer = new MyTimer(length);
        isActive = true;
        this.type = type;
        this.value = value;
    }

    public void setIsActive() {
        if (!timer.getTimeStatus()) {
            isActive = false;
        }
    }

    public boolean getIsActive() {
        return isActive;
    }

    public int getValue() {
        return value;
    }

    public BuffType getType() {
        return type;
    }
}
