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
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author qw
 */
public class Tree extends GameActor {

    private TextureRegion groundTexture;
    private float widthStem;
    private float heightStem;

    private float widthFoliage;
    private float heightFoliage;

    public Body foliage;

    public Tree(World world, float x, float y, float widthStem, float heightStem, float widthFoliage, float heightFoliage, TextureRegion groundTexture) {
        this.body = WorldUtils.createThree(world, x, y,
                widthStem, heightStem,
                widthFoliage, heightFoliage);
        this.widthStem = widthStem;
        this.heightStem = heightStem;

        this.widthFoliage = widthFoliage;
        this.heightFoliage = heightFoliage;

        this.groundTexture = groundTexture;
        foliage = WorldUtils.Foliage;
    }

    public Tree(Body body, float x, float y, float widthStem, float heightStem, float widthFoliage, float heightFoliage, TextureRegion groundTexture) {
        this.body = body;
        this.widthStem = widthStem;
        this.heightStem = heightStem;

        this.widthFoliage = widthFoliage;
        this.heightFoliage = heightFoliage;

        this.groundTexture = groundTexture;
        foliage = WorldUtils.Foliage;
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

    public void draw(Batch batch, float parentAlpha) {
        if (delete() && checkDraw()) {
            batch.draw(groundTexture, getX() - widthStem / 2, getY() - heightStem / 2, widthStem, heightStem);
            batch.draw(groundTexture, getX() - widthFoliage / 2, getY() - heightStem / 2 + heightStem, widthFoliage, heightFoliage);
        }
    }
}
