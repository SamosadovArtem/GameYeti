/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects.PrizeField;

import java.util.Random;

/**
 *
 * @author Pablo
 */
public class TicketHandler {

    public static boolean getIsWin() {
        Random r = new Random();
        if (r.nextInt(100) <= 80) {
            return true;
        }
        return false;
    }
}
