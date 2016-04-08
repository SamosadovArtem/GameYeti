/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import GameObjects.Buffs.Buff;
import Helper.TimeConverter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author qw
 */
public class BuffContainer extends Actor {
    
    private Buff buff;
    private TimeConverter time;
    
    public BuffContainer(Buff b){
        buff = b;
        time = buff.getTimer().getTimeLeft();
    }
    
    public void updateTime(){
        time.removeTime(1);
    }
}
