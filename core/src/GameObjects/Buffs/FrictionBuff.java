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
public class FrictionBuff extends Buff {

    private float power = 0.8f;
    private float coff = 0.1f;

    public FrictionBuff(MyTimer time, int level, int lvlMax, float value, int cost) {
        super(time, BuffType.FRICTION, level);
        power = value;
        this.levelMax = lvlMax;
        this.cost = cost;
        super.icon = new Picture(AssetLoader.frictionBuff);
    }

    public float getPower() {
        return power - getLevel() * coff;
    }

    public void upgrade() {
        if (level != levelMax) {
            updateTimer(1 * 24 * 60 * 60);
            increaseLevel();
            BuffsInfo.saveFrictionBuff(this);
        }
    }

    public void update() {
        if (level != 0) {
            updateTimer(1 * 24 * 60 * 60);
            BuffsInfo.saveFrictionBuff(this);
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
        return "Decrease friction \nof the pinguin.";
    }
}
