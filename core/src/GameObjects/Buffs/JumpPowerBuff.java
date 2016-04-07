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
public class JumpPowerBuff extends Buff {

    private float power;

    public JumpPowerBuff(MyTimer time, int level, float value) {
        super(time, BuffType.JUMPPOWER, level);
        power = value;
    }

}
