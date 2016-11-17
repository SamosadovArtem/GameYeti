/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import GameWorld.Game.GameWorld;
import Helper.Constants;
import Helper.WorldUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import static java.lang.Thread.sleep;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo
 */
public class Antelope extends GameActor {

    private TextureRegion antelopeTexture;
    private float width;
    private float height;
    //  private boolean isUsed;
    private Body downBody;

    private AntelopeThread antelopeThread;
    private Thread aThread;

    private GameWorld gameWorld;

    public Antelope(TextureRegion antelopeTexture, float x, float y, float width, float height, World world,
            GameWorld gameWorld) {
        downBody = WorldUtils.createAntelopeBody(world, x, y + height * 3 / 4, width, height * 3 / 4);
        body = WorldUtils.createAntelopeBack(world, x, y + height + height * 3 / 4, width, height / 4);

        WorldUtils.createAntelope(world, body, downBody);
        this.width = width;
        this.height = height;
        this.antelopeTexture = antelopeTexture;
        //    this.isUsed = false;
        body.getFixtureList().get(0).setUserData(this);
        downBody.getFixtureList().get(0).setUserData("ANTELOPE");
        this.gameWorld = gameWorld;
    }

    public Antelope(Body body, TextureRegion antelopeTexture, float x, float y, float width, float height) {
        this.body = body;
        this.width = width;
        this.height = height;
        this.antelopeTexture = antelopeTexture;
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

    public void setDownBody(Body body) {
        this.downBody = body;
    }

    public Body getDownBody() {
        return downBody;
    }

    public void draw(Batch batch, float parentAlpha) {
        if (delete() && checkDraw()) {
            batch.draw(antelopeTexture, getX() - width / 2, getY(), getWidth(), getHeight() / 4);
            batch.draw(antelopeTexture, getX() - width / 2, getY() - getHeight(), getWidth(), getHeight());
        }
    }

    public void ride(Pinguin pinguin) {
        if (!pinguin.getIsRide()) {
            pinguin.setIsRide(true);
            antelopeThread = new AntelopeThread(pinguin, this);
            aThread = new Thread(antelopeThread);
            aThread.start();
        }
    }
}

class AntelopeThread implements Runnable {

    private boolean check;
    private Pinguin pinguin;
    private Antelope antelope;

    public AntelopeThread(Pinguin pinguin, Antelope antelope) {
        check = false;
        this.pinguin = pinguin;
        this.antelope = antelope;
    }

    @Override
    public void run() {
        try {
            //pinguin.setIsRide(true);
            pinguin.hide();
            pinguin.getBody().setGravityScale(0f);
            antelope.getDownBody().getFixtureList().get(0).setRestitution(0f);

            antelope.getBody().setLinearVelocity(new Vector2(200f, 0));
            antelope.getDownBody().setLinearVelocity(new Vector2(200f, 0));
            for (int i = 0; i < 5; i++) {
                pinguin.getBody().setLinearVelocity(new Vector2(200f + antelope.getBody().getPosition().x - pinguin.getBody().getPosition().x,
                        antelope.getBody().getPosition().y + antelope.getHeight() / 8 - pinguin.getBody().getPosition().y));
                sleep(1000);
            }
            check = true;
            //     pinguin.getAntelope().setIsUsed(true);
            pinguin.getBody().setLinearVelocity(new Vector2(200f, 200f));

            //   antelope.getBody().setActive(false);
            pinguin.getBody().setGravityScale(Constants.RUNNER_GRAVITY_SCALE);

            pinguin.show();
            sleep(300);
            pinguin.setIsRide(false);

            antelope.getBody().setLinearVelocity(new Vector2(-200f, 0f));
            antelope.getDownBody().setLinearVelocity(new Vector2(-200f, 0f));
            sleep(5300);

            antelope.getBody().setLinearVelocity(new Vector2(0f, 0f));
            antelope.getDownBody().setLinearVelocity(new Vector2(0f, 0f));

            antelope.getDownBody().getFixtureList().get(0).setRestitution(1f);

        } catch (InterruptedException ex) {
            Logger.getLogger(AntelopeThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean getCheck() {
        return check;
    }
}
