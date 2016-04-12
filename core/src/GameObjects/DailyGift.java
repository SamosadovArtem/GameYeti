/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Helper.MyTimer;
import com.badlogic.gdx.utils.Array;
import java.util.List;

/**
 *
 * @author Pablo
 */
public class DailyGift {

    private MyTimer timerMin;
    private MyTimer timerMax;
    private int level;
    private static int[] award = {1, 3, 5, 10, 25};

    public DailyGift(MyTimer timerMin, MyTimer timerMax, int level) {
        this.timerMin = timerMin;
        this.timerMax = timerMax;
        this.level = level;
    }

    public String getSaveData() {
        return level + "," + timerMin.getDateFinish().getTime()
                + "," + timerMax.getDateFinish().getTime();
    }
}
