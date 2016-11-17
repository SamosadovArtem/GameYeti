/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import Enums.UserDataType;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

/**
 * @author Pablo
 */
public class WorldUtils {

    public static World createWorld(float val) {
        World w = new World(new Vector2(0, val), true);
        return w;
    }

    public static Body createGround(World world, float startPos) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(new Vector2(Constants.GROUND_X + startPos, Constants.GROUND_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.GROUND_WIDTH / 2, Constants.GROUND_HEIGHT / 2);
        body.createFixture(shape, Constants.GROUND_DENSITY);
        body.getFixtureList().get(0).setUserData("GROUND");
        shape.dispose();
        return body;
    }


    public static Body createPinguin(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(Constants.RUNNER_X, Constants.RUNNER_Y));
        Body body = world.createBody(bodyDef);
        body.setGravityScale(Constants.RUNNER_GRAVITY_SCALE);
        body.setFixedRotation(false);
        PolygonShape box = new PolygonShape();
        box.setAsBox(Constants.RUNNER_WIDTH / 2, Constants.RUNNER_HEIGHT / 2,
                body.getLocalCenter(), 0);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = BuffsInfo.getFrictionBuff().getPower();
        fixtureDef.restitution = 0.35f;
        body.createFixture(fixtureDef);
        //body.resetMassData();
        body.getFixtureList().get(0).setUserData("PINGUIN");
        box.dispose();
        return body;
    }

    public static Body createStopObj(World world, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(x, y + height / 2));
        Body body = world.createBody(bodyDef);
        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.5f;
        body.createFixture(fixtureDef);
        body.resetMassData();
        body.getFixtureList().get(0).setUserData("STOPOBJ");
        box.dispose();
        return body;
    }

    public static Body createThree(World world, float x, float y, float widthBody
            , float heightBody, float widthNeck, float heightNeck) {
        Body bodyStem = getStemBody(world, x, y + heightBody / 2, widthBody, heightBody);
        Body bodyFoliage = getFoliageBody(world, x, y + heightNeck / 2 + heightBody,
                widthNeck, heightNeck);
        return bodyStem;
    }

    public static Body Foliage;

    private static Body getFoliageBody(World world, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(x, y));
        Body body = world.createBody(bodyDef);
        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.4f;
        body.createFixture(fixtureDef);
        body.resetMassData();
        body.getFixtureList().get(0).setUserData("FOLIAGEBODY");
        box.dispose();
        Foliage = body;
        return body;
    }

    private static Body getStemBody(World world, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(x, y));
        Body body = world.createBody(bodyDef);
        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.4f;
        body.createFixture(fixtureDef);
        body.resetMassData();
        body.getFixtureList().get(0).setUserData("STEMBODY");
        box.dispose();
        return body;
    }

    public static Body createGiraff(World world, float x, float y, float widthBody
            , float heightBody, float widthNeck, float heightNeck
            , float widthHead, float heightHead) {
        Body bodyB = getGiraffeBody(world, x, y + heightBody / 2, widthBody, heightBody);
        Body bodyN = getGiraffeNeck(world, x - widthBody / 2 + widthNeck / 2, y + heightNeck / 2 + heightBody, widthNeck, heightNeck);
        Body bodyH = getGiraffeHead(world, x - widthBody / 2 + widthNeck - widthHead / 2, y + heightHead / 2 + heightNeck + heightBody, widthHead, heightHead);
        return bodyB;
    }

    public static Body GiraffeBody;

    private static Body getGiraffeBody(World world, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(x, y));
        Body body = world.createBody(bodyDef);
        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;
        body.createFixture(fixtureDef);
        body.resetMassData();
        body.getFixtureList().get(0).setUserData("GIRAFFEBODY");
        box.dispose();
        return body;
    }

    public static Body GiraffeNeck;

    private static Body getGiraffeNeck(World world, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(x, y));
        Body body = world.createBody(bodyDef);
        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;
        body.createFixture(fixtureDef);
        body.resetMassData();
        body.getFixtureList().get(0).setUserData("GIRAFFENECK");
        box.dispose();
        return body;
    }

    public static Body GiraffeHead;

    private static Body getGiraffeHead(World world, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(x, y));
        Body body = world.createBody(bodyDef);
        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;
        body.createFixture(fixtureDef);
        body.resetMassData();
        body.getFixtureList().get(0).setUserData("GIRAFFEHEAD");
        box.dispose();
        GiraffeHead = body;
        return body;
    }

    public static Body createSnake(World world, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(x, y + height));
        //   bodyDef.active = false;
        Body body = world.createBody(bodyDef);
        body.setGravityScale(Constants.RUNNER_GRAVITY_SCALE);
        body.setFixedRotation(false);
        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 1.8f;
        fixtureDef.restitution = 0.35f;
        body.createFixture(fixtureDef);
        body.resetMassData();
        body.getFixtureList().get(0).setUserData("SNAKE");
        box.dispose();
        return body;
    }

    public static Body createCoin(World world, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(x, y + height));
        //   bodyDef.active = false;
        Body body = world.createBody(bodyDef);
        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 1.8f;
        fixtureDef.restitution = 0.35f;
        body.createFixture(fixtureDef);
        body.resetMassData();
        body.getFixtureList().get(0).setUserData("COIN");
        box.dispose();
        return body;
    }

    public static void createAntelope(World world, Body body, Body backBody) {
        RevoluteJointDef jointDef = new RevoluteJointDef();
        jointDef.initialize(backBody, body, backBody.getWorldCenter());
        jointDef.collideConnected = true;
        world.createJoint(jointDef);
    }

    public static Body createAntelopeBack(World world, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(x, y + height));
        //   bodyDef.active = false;
        Body body = world.createBody(bodyDef);
        body.setGravityScale(0f);
        body.setFixedRotation(false);
        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 1.8f;
        fixtureDef.restitution = 0f;
        body.createFixture(fixtureDef);
        body.getFixtureList().get(0).setUserData("AntelopeBack");
        body.resetMassData();
        box.dispose();
        return body;
    }

    public static Body createAntelopeBody(World world, float x, float y, float width, float height) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(new Vector2(x, y + height));
        Body body = world.createBody(bodyDef);
        body.setGravityScale(0f);
        body.setFixedRotation(false);
        PolygonShape box = new PolygonShape();
        box.setAsBox(width / 2, height / 2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = 1.0f;
        fixtureDef.friction = 1.8f;
        fixtureDef.restitution = 0f;
        body.createFixture(fixtureDef);
        body.resetMassData();
        body.getFixtureList().get(0).setUserData("AntelopeBody");
        box.dispose();
        return body;
    }

}
