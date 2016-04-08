/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects.Buffs;

import Enums.BuffType;
import Helper.BuffsInfo;
import Helper.MyTimer;

/**
 *
 * @author Pablo
 */
public class JumpCountBuff extends Buff{

    private int count = 3;

    public JumpCountBuff(MyTimer time, int level, int value) {
        super(time, BuffType.JUMPCOUNT, level);
        count = value;
    }

    public int getCount(){
        return count + getLevel();
    }
    
    public void upgrade(){
        if(level != levelMax){
            updateTimer(1*24*60*60);
            increaseLevel();
            BuffsInfo.saveGravityBuff(this);
        }
    }
    
    public void update(){
        if(level != 0){
            updateTimer(1*24*60*60);
            BuffsInfo.saveGravityBuff(this);
        }
    }
    
    public int getCoast(int lvl){
        int c = 1;
        for(int i = 0; i < lvl;i++){
            c+=i*2;
        }
        return c * cost;
    }
    
    public boolean checkUpdate(int allCoins){
        if(allCoins >= getCoast(level)){
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkUpgrade(int allCoins){
        if(allCoins >= getCoast(level + 1)){
            return true;
        } else {
            return false;
        }
    }
    
}
