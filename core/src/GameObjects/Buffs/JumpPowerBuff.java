/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects.Buffs;

import Enums.BuffType;

/**
 *
 * @author Pablo
 */
public class JumpPowerBuff extends Buff {

    public JumpPowerBuff(long length, int value) {
        super(length, BuffType.JUMPPOWER, value);
    }

}
