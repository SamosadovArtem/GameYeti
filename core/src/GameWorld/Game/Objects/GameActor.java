/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Pablo
 */
public class GameActor extends Actor {

    protected Body body;
    protected boolean mapActor = false;

    public GameActor(Body body) {
        this.body = body;
    }

    public GameActor() {

    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public boolean delete() {
        if (body.getFixtureList().size==0) {
            Gdx.app.log("pidor","ti");
            this.remove();
            System.out.println("pidor");
            return false;
        }
        
        return true;
    }

    protected boolean checkDraw() {
        Camera camera = this.getStage().getCamera();

        float upX = camera.position.x + camera.viewportWidth / 1.5f;
        float downX = camera.position.x - camera.viewportWidth / 1.5f;

        if (body.getPosition().x > upX
                || body.getPosition().x < downX) {
            return false;
        }
        return true;
    }
}
