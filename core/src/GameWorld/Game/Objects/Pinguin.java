/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import GameWorld.Game.Objects.GameActor;
import Helper.AssetLoader;
import Helper.Constants;
import Helper.Statistic;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

/**
 *
 * @author Pablo
 */
public class Pinguin extends GameActor {

    private int power;
    private int powerCof = 5;
    private float directionX;
    private float directionY;
    private float angular = 0;
    private boolean isIncrease;
    private boolean isPower;
    private boolean isDir;
    private boolean visible = true;
    private boolean isRide;
    private TextureRegion pinguinTexture;

    public Pinguin(Body body, TextureRegion pinguinTexture, int powerCof) {
        super(body);
        this.pinguinTexture = pinguinTexture;
        this.powerCof = powerCof;
        power = 0;
        directionX = 0f;
        directionY = 1f;
        isIncrease = true;
        isPower = false;
        isDir = true;
    }
    
    public void setAngle(float f){
        
        this.setRotation(f);
            //this.getBody().setTransform(this.getBody().getPosition(), 
            //(float)Math.toRadians(angular));
    }
    
    public void show(){
        visible = true;
    }

    public void hide(){
        visible = false;
    }
    public boolean isVisible(){
        return visible;
    }
    
	public boolean getIsRide(){
        return isRide;
    }
    
    public void setIsRide(boolean isRide){
        this.isRide = isRide;
    }
    
    @Override
    public float getX(){
        return body.getPosition().x - getWidth()/2;
    }
    
    @Override
    public float getY(){
        return body.getPosition().y;
    }
    
    @Override
    public float getWidth(){
        return Constants.RUNNER_WIDTH;
    }
    
    @Override
    public float getHeight(){
        return Constants.RUNNER_HEIGHT;
    }
    
    public void draw (Batch batch, float parentAlpha) {
        if(delete()){
            if(visible){
                batch.draw(pinguinTexture, 
                        getX() , getY() - getHeight() / 2,
                        getWidth() / 2 , getHeight() / 2, 
                        getWidth(), getHeight(),
                        1,1,
                        getRotation());   
                if (!moved()) {                    
                    drawPower(batch);
                    if (!getIsDir()) {
                        drawDirection(batch);
                    }
                }
            }        
        }
    }
    
    private void drawPower(Batch batch){
        batch.draw(AssetLoader.textureBtnNormal, getX() - getHeight() , getY() - getHeight() / 2, 
                getHeight(), getWidth() * (getPower() / 100.0f), 
            0 , (int)(AssetLoader.textureBtnNormal.getHeight() * ( 1.0f - getPower() / 100.0f)),
            (int)AssetLoader.textureBtnNormal.getWidth(), 
            (int)(AssetLoader.textureBtnNormal.getHeight() * (getPower() / 100.0f)), false, false);
    }
    
    private void drawDirection(Batch batch){
        batch.draw(pinguinTexture, 
                        getX() + getWidth() , getY() - getHeight() / 2,
                        getHeight() / 4 , getHeight() / 4, 
                        getWidth(), getHeight() / 2,
                        1,1,
                        new Vector2(directionX, directionY).angle());
    }
    
    public void setPower(int p){
        power = p;
    }
    
    public void defaultPos(){
        directionX = 0f;
        directionY = 1f;
    }
    
    public void jump(){
        body.applyLinearImpulse(new Vector2(directionX * power * powerCof, directionY * power * powerCof),
                body.getWorldCenter(), true);
        Statistic.jump();
        //body.setTransform(body.getPosition(), (float)Math.toRadians(body.getLinearVelocity().angle()));
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
