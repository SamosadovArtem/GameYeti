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
public class JumpCountBuff extends Buff{

    private int count = 3;

    public JumpCountBuff(MyTimer time, int level, int value) {
        super(time, BuffType.JUMPCOUNT, level);
        count = value;
    }

    public int getCount(){
        return count;
    }
    
}
