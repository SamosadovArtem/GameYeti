/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import GameWorld.Game.Objects.GameActor;
import GameWorld.Game.Data.PinguinUserData;
import GameWorld.Game.Data.UserData;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 *
 * @author Pablo
 */
public class Pinguin extends GameActor {

    private int power;
    private float directionX;
    private float directionY;
    private boolean isIncrease;
    private boolean isPower;
    private boolean isDir;

    public Pinguin(Body body) {
        super(body);
        power = 0;
        directionX = 0f;
        directionY = 1f;
        isIncrease = true;
        isPower = false;
        isDir = true;
    }

    @Override
    public PinguinUserData getUserData() {
        return (PinguinUserData) userData;
    }
    
    public void setPower(int p){
        power = p;
    }
    
    public void defaultPos(){
        directionX = 0f;
        directionY = 1f;
    }
    
    public void jump(){
        body.applyLinearImpulse(new Vector2(directionX * power, directionY * power), body.getWorldCenter(), true);
    }
    
    public int getPower() {
        return power;
    }

    public float getDirectionX() {
        return directionX;
    }

    public float getDirectionY() {
        return directionY;
    }

    public void changePower() {
        if (!isPower) {
            if (isIncrease) {
                power++;
                if (power >= 100) {
                    isIncrease = false;
                }
            } else {
                power--;
                if (power <= 1) {
                    isIncrease = true;
                }
            }
        }
    }

    public void changeDirection() {
        if (!isDir) {
            if (isIncrease) {
                directionX = directionX + 0.01f;
                directionY = directionY - 0.01f;
                if (directionX >= 1.00f) {
                    isIncrease = false;
                }
            } else {
                directionX = directionX - 0.01f;
                directionY = directionY + 0.01f;
                if (directionX <= 0.00f) {
                    isIncrease = true;
                }
            }
        }
    }

    public boolean moved() {        
        if(body.getLinearVelocity().x == 0.0f && body.getLinearVelocity().y == 0.0f){            
            return false;
        } else {
            return true;
        }
    }
    
    public boolean getIsPower() {
        return isPower;
    }

    public void setIsPower(boolean isPower) {
        this.isPower = isPower;
    }

    public boolean getIsDir() {
        return isDir;
    }

    public void setIsDir(boolean isDir) {
        this.isDir = isDir;
    }
}
