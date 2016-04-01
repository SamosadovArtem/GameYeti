/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import Helper.Constants;
import Helper.Statistic;
import Helper.WorldUtils;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.JointEdge;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author qw
 */
public class Coin extends GameActor {

    private TextureRegion snakeTexture;
    private float width;
    private float height;

    public Coin(TextureRegion snakeTexture, float x, float y, float width, float height, World world) {
        body = WorldUtils.createCoin(world, x, y, width, height);
        this.width = width;
        this.height = height;
        this.snakeTexture = snakeTexture;
        this.body.getFixtureList().get(0).setUserData(this);
    }
    
    public Coin(Body body, TextureRegion snakeTexture, float x, float y, float width, float height) {
        this.body = body;
        this.width = width;
        this.height = height;
        this.snakeTexture = snakeTexture;
        this.mapActor = true;
    }

    @Override
    public float getX() {
        if(body != null){
            return body.getPosition().x;
        } else {
            return 0;
        }
    }

    @Override
    public float getY() {
        if(mapActor){
            return body.getPosition().y - Constants.GROUND_Y - Constants.GROUND_HEIGHT / 2;
        } else if(body != null){
            return body.getPosition().y;
        } else {
            return 0;
        }
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    public void draw(Batch batch, float parentAlpha) {
        if(body.getFixtureList().get(0).getUserData().equals("DELETE")){
            this.remove();
        } else if(delete()){
            batch.draw(snakeTexture, getX()-width/2, getY()-getHeight()/2, getWidth(), getHeight());
        }
    }
    
    public void take(){
        Statistic.addCoins(1);
        this.remove();      
    }
}