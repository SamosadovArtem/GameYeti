/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import Helper.Constants;
import Helper.WorldUtils;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author Pablo
 */
public class Snake extends GameActor {

    private TextureRegion snakeTexture;
    private float width;
    private float height;

    public Snake(TextureRegion snakeTexture, float x, float y, float width, float height, World world) {
        body = WorldUtils.createSnake(world, x, y, width, height);
        this.width = width;
        this.height = height;
        this.snakeTexture = snakeTexture;
    }

    public Snake(Body body, TextureRegion snakeTexture, float x, float y, float width, float height) {
        this.body = body;
        this.width = width;
        this.height = height;
        this.snakeTexture = snakeTexture;
        this.mapActor = true;
    }

    @Override
    public float getX() {
        return body.getPosition().x;
    }

    @Override
    public float getY() {
        if (mapActor) {
            return body.getPosition().y - Constants.GROUND_Y - Constants.GROUND_HEIGHT / 2;
        } else {
            return body.getPosition().y;
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
        if (delete() && checkDraw()) {
            batch.draw(snakeTexture, getX() - width / 2, getY() - getHeight() / 2, getWidth(), getHeight());
        }
    }
}
