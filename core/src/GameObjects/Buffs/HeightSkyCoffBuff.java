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
 * @author qw
 */
public class HeightSkyCoffBuff extends Buff {

    private float count;
    private float coff = 0.1f;

    public HeightSkyCoffBuff(MyTimer time, int level, int lvlMax, float value, int cost) {
        super(time, BuffType.HEIGHT_SKY_COFF, level);
        count = value;
        this.levelMax = lvlMax;
        this.cost = cost;
        super.icon = new Picture(AssetLoader.heightSkyCoffBuff);
    }

    public float getCount() {
        return count + getLevel() * coff;
    }

    public void upgrade() {
        if (level != levelMax) {
            updateTimer(1 * 24 * 60 * 60);
            increaseLevel();
            BuffsInfo.saveHeightSkyCoffBuff(this);
        }
    }

    public void update() {
        if (level != 0) {
            updateTimer(1 * 24 * 60 * 60);
            BuffsInfo.saveHeightSkyCoffBuff(this);
        }
    }

    public int getCoast(int lvl) {
        int c = 1;
        for (int i = 0; i < lvl; i++) {
            c += 2;
        }
        return c * cost;
    }

    public boolean checkUpdate(int allCoins) {
        if (allCoins >= getCoast(level) && level != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUpgrade(int allCoins) {
        if (allCoins >= getCoast(level + 1) && level < levelMax) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getInfo() {
        return "Increase height of the sky \nwhere you can fly.";
    }
}
