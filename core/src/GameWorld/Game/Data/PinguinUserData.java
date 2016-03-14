/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Data;

import Enums.UserDataType;
import Helper.Constants;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Pablo
 */
public class PinguinUserData extends UserData{
    
    private Vector2 jumpingLinearImpulse;

    public PinguinUserData() {
        super();
        jumpingLinearImpulse = Constants.RUNNER_JUMPING_LINEAR_IMPULSE;
        userDataType = UserDataType.PINGUIN;
    }
    
    public Vector2 getJumpingLinearImpulse(){
        return jumpingLinearImpulse;
    }
    
    public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse){
        this.jumpingLinearImpulse = jumpingLinearImpulse;
    }
}
