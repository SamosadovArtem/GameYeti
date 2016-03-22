/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

/**
 *
 * @author Pablo
 */
public class JumpCountController {

    private int countOfJump;

    public JumpCountController(int countOfJump) {
        this.countOfJump = countOfJump;
    }

    public void jump() {
        countOfJump--;
    }

    public int getCountOfJump() {
        return countOfJump;
    }

    public boolean checkJump() {
        if (countOfJump > 0) {
            return true;
        } else {
            return false;
        }
    }
}
