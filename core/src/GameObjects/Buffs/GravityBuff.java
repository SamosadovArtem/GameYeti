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
public class GravityBuff extends Buff{

    private float gravityVal = 0;
    private float coff = 1.0f;

    public GravityBuff(MyTimer time, int level, int lvlMax, float value, int cost) {
        super(time, BuffType.GRAVITY, level);
        this.gravityVal = value;
        this.levelMax = lvlMax;
        this.cost = cost;
    }

    public float getGravity(){        
        return gravityVal + coff * getLevel();
    }
    
    public void upgrade(){
        if(level != levelMax){
            updateTimer(1*24*60*60);
            increaseLevel();
        }
    }
    
    public void update(){
        if(level != 0){
            updateTimer(1*24*60*60);
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
        if(allCoins >= getCoast(level + 1) && level < levelMax){
            return true;
        } else {
            return false;
        }
    }
    
}
