/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import Enums.UserDataType;
import GameWorld.Game.Data.GroundUserData;
import GameWorld.Game.Data.PinguinUserData;
import GameWorld.Game.Data.UserData;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 *
 * @author Pablo
 */
public class WorldUtils {

    public static World createWorld() {
        World w = new World(Constants.WORLD_GRAVITY, true);
        return w;
    }

    public static Body createGround(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.GROUND_WIDTH / 2, Constants.GROUND_HEIGHT / 2);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        body.setUserData(new GroundUserData());
        shape.dispose();
        return body;
    }

    public static Body createPinguin(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y));
        Body body = world.createBody(bodyDef);
        body.setGravityScale(Constants.RUNNER_GRAVITY_SCALE);
        body.setFixedRotation(true);
        CircleShape circle = new CircleShape();
        circle.setRadius(Constants.RUNNER_WIDTH);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.35f;
        body.createFixture(fixtureDef);
        body.resetMassData();
        body.setUserData(new PinguinUserData());
        circle.dispose();
        return body;
    }

}
