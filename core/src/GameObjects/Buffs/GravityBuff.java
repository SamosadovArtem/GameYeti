/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects.Buffs;

import Enums.BuffType;
import GameObjects.Picture;
import Helper.AssetLoader;
import Helper.BuffsInfo;
import Helper.MyTimer;

/**
 *
 * @author Pablo
 */
public class GravityBuff extends Buff {

    private float gravityVal = 0;
    private float coff = 1.0f;

    public GravityBuff(MyTimer time, int level, int lvlMax, float value, int cost) {
        super(time, BuffType.GRAVITY, level);
        this.gravityVal = value;
        this.levelMax = lvlMax;
        this.cost = cost;
        super.icon = new Picture(AssetLoader.gravityBuff);
    }

    public float getGravity() {
        return gravityVal + coff * getLevel();
    }

    public void upgrade() {
        if (level != levelMax) {
            updateTimer(1 * 24 * 60 * 60);
            increaseLevel();
            BuffsInfo.saveGravityBuff(this);
        }
    }

    public void update() {
        if (level != 0) {
            updateTimer(1 * 24 * 60 * 60);
            BuffsInfo.saveGravityBuff(this);
        }
    }

    public int getCost(int lvl) {
        int c = 1;
        for (int i = 0; i < lvl; i++) {
            c += 2;
        }
        return c * cost;
    }

    public boolean checkUpdate(int allCoins) {
        if (allCoins >= getCost(level) && level != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUpgrade(int allCoins) {
        if (allCoins >= getCost(level + 1) && level < levelMax) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getInfo() {
        return "Decrease \nthe gravity.";
    }
}
