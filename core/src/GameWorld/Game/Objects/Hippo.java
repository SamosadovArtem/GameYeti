package GameWorld.Game.Objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import Helper.Constants;
import Helper.WorldUtils;

/**
 * Created by User on 23.11.2016.
 */

public class Hippo extends GameActor {

    private TextureRegion groundTexture;
    private float widthBody;
    private float heightBody;

    private float widthHead;
    private float heightHead;

    public Body foliage;

    public Hippo(World world, float x, float y, float widthHead, float heightHead, float widthBody, float heightBody, TextureRegion groundTexture) {
        this.body = WorldUtils.createHippo(world, x, y,
                widthHead, heightHead,
                widthBody, heightBody);
        this.widthHead = widthHead;
        this.heightHead = heightHead;

        this.widthBody = widthBody;
        this.heightBody = heightBody;

        this.groundTexture = groundTexture;
        foliage = WorldUtils.Foliage;
    }
    
    public Hippo(Body body, float x, float y, float widthHead, float heightHead, float widthBody, float heightBody, TextureRegion groundTexture) {

        this.body = body;
        
        this.widthHead = widthHead;
        this.heightHead = heightHead;

        this.widthBody = widthBody;
        this.heightBody = heightBody;

        this.groundTexture = groundTexture;
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
            batch.draw(groundTexture, getX()-widthHead/2, getY()-heightHead/2, widthHead, heightHead);
            batch.draw(groundTexture, getX()-widthHead/2, getY()-heightBody-heightHead/2, widthBody, heightBody);
        }
    }
}
