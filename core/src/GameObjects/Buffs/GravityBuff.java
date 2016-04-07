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

    public GravityBuff(MyTimer time, int level, float value) {
        super(time, BuffType.GRAVITY, level);
        gravityVal = value;
    }
    
}
