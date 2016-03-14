/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import GameWorld.Game.Objects.GameActor;
import GameWorld.Game.Data.PinguinUserData;
import GameWorld.Game.Data.UserData;
import com.badlogic.gdx.physics.box2d.Body;

/**
 *
 * @author Pablo
 */
public class Pinguin extends GameActor {
    
    private boolean jumping;

    public Pinguin(Body body) {
        super(body);
    }

    @Override
    public PinguinUserData getUserData() {
        return (PinguinUserData) userData;
    }

    public void jump() {

        if (!jumping) {
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
            jumping = true;
        }

    }

    public void landed() {
        jumping = false;
    }
}
