/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import GameWorld.Game.GameWorld;
import GameWorld.Game.Objects.Antelope;
import GameWorld.Game.Objects.Coin;
import GameWorld.Game.Objects.Giraffe;
import GameWorld.Game.Objects.Pinguin;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.WorldManifold;

/**
 *
 * @author Pablo
 */
public class GameContactListener implements ContactListener {

    private Pinguin pinguin;
    GameWorld w;

    public GameContactListener(GameWorld w, Pinguin pinguin) {
        this.pinguin = pinguin;
        this.w = w;
    }

    @Override
    public void beginContact(Contact contact) {
        coinsContact(contact);
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold mnfld) {
        WorldManifold manifold = contact.getWorldManifold();
        for (int j = 0; j < manifold.getNumberOfContactPoints(); j++) {
            if (contact.getFixtureA().getUserData() != null && contact.getFixtureB().getUserData() != null) {
                if ((checkFixtureA(contact, "PINGUIN") || checkFixtureB(contact, "PINGUIN")) && pinguin.getIsRide()) {
                    contact.setEnabled(false);
                }
                antelopeContact(contact);
                contactGiraffe(contact);
                snakeContact(contact);
                coinsPreContact(contact);
                deleteContact(contact);
            }
        }
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse ci) {

    }

    private void deleteContact(Contact contact) {
        if (contact.getFixtureA().getUserData().equals("DELETE") || contact.getFixtureB().getUserData().equals("DELETE")) {
            contact.setEnabled(false);
        }
    }

    private void coinsContact(Contact contact) {
        if (contact.getFixtureA().getUserData() != null && contact.getFixtureB().getUserData() != null) {
            Coin g;
            if (contact.getFixtureB().getUserData() instanceof Coin
                    && contact.getFixtureA().getUserData().equals("PINGUIN")) {
                g = (Coin) contact.getFixtureB().getUserData();
                g.getBody().setUserData("DELETE");
                g.getBody().getFixtureList().get(0).setUserData("DELETE");
                g.take();

            } else if (contact.getFixtureA().getUserData() instanceof Coin
                    && contact.getFixtureB().getUserData().equals("PINGUIN")) {
                g = (Coin) contact.getFixtureA().getUserData();
                g.getBody().setUserData("DELETE");
                g.getBody().getFixtureList().get(0).setUserData("DELETE");
                g.take();
            }
        }
    }

    private void coinsPreContact(Contact contact) {
        if (contact.getFixtureA().getUserData() != null && contact.getFixtureB().getUserData() != null) {
            Coin g;
            if (contact.getFixtureB().getUserData() instanceof Coin
                    && contact.getFixtureA().getUserData().equals("PINGUIN")) {
                contact.setEnabled(false);

            } else if (contact.getFixtureA().getUserData() instanceof Coin
                    && contact.getFixtureB().getUserData().equals("PINGUIN")) {
                contact.setEnabled(false);
            }
        }
    }

    private void antelopeContact(Contact contact) {
        antelopeBackContact(contact);
        antelopeBodyContact(contact);
    }

    private void antelopeBackContact(Contact contact) {
        if (contact.getFixtureA().getUserData() instanceof Antelope) {
            contact.setEnabled(false);
            if (checkFixtureB(contact, "PINGUIN")) {
                //contact.setEnabled(true);
                Antelope ant = (Antelope) contact.getFixtureA().getUserData();
                ant.ride(pinguin);
            }
        }
        if (contact.getFixtureB().getUserData() instanceof Antelope) {
            contact.setEnabled(false);
            if (checkFixtureA(contact, "PINGUIN")) {
                // contact.setEnabled(true);
                //  antelopeRide((Antelope) contact.getFixtureB().getUserData());
                Antelope ant = (Antelope) contact.getFixtureB().getUserData();
                ant.ride(pinguin);
            }
        }
    }

    private boolean checkFixtureA(Contact contact, String string) {
        return contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals(string);
    }

    private boolean checkFixtureB(Contact contact, String string) {
        return contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals(string);
    }

    private void antelopeBodyContact(Contact contact) {
        if (checkFixtureA(contact, "ANTELOPE")) {
            if (contact.getFixtureA().getBody().getLinearVelocity().x != 0) {
                if (!checkFixtureB(contact, "PINGUIN")) {
                    contact.setEnabled(false);
                }
            }
        }
        if (checkFixtureB(contact, "ANTELOPE")) {
            if (contact.getFixtureB().getBody().getLinearVelocity().x != 0) {
                if (!checkFixtureA(contact, "PINGUIN")) {
                    contact.setEnabled(false);
                }
            }
        }
        if (contact.getFixtureA().getUserData() instanceof Antelope) {
            if (contact.getFixtureA().getBody().getLinearVelocity().x != 0) {
                contact.setEnabled(false);
            }
        }
        if (contact.getFixtureB().getUserData() instanceof Antelope) {
            if (contact.getFixtureB().getBody().getLinearVelocity().x != 0) {
                contact.setEnabled(false);
            }
        }
    }

    private void contactGiraffe(Contact contact) {

        Giraffe g;
        if (contact.getFixtureB().getUserData() instanceof Giraffe
                && contact.getFixtureA().getUserData().equals("PINGUIN")) {
            contact.setEnabled(false);
            g = (Giraffe) contact.getFixtureB().getUserData();
            g.throwPinguin(pinguin);

        } else if (contact.getFixtureA().getUserData() instanceof Giraffe
                && contact.getFixtureB().getUserData().equals("PINGUIN")) {
            contact.setEnabled(false);
            g = (Giraffe) contact.getFixtureA().getUserData();
            g.throwPinguin(pinguin);
        }

    }

    private void snakeContact(Contact contact) {
        if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("SNAKE")) {
            if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("PINGUIN")) {
                contact.setEnabled(false);
                snakeJump();
            }
        }
        if (contact.getFixtureB().getUserData() != null && contact.getFixtureB().getUserData().equals("SNAKE")) {
            if (contact.getFixtureA().getUserData() != null && contact.getFixtureA().getUserData().equals("PINGUIN")) {
                contact.setEnabled(false);
                snakeJump();
            }
        }
    }

    private void snakeJump() {
        if (pinguin.getBody().getLinearVelocity().y <= 50f) {
            pinguin.getBody().setLinearVelocity(new Vector2(pinguin.getBody().getLinearVelocity().x, 100f));
        } else if (pinguin.getBody().getLinearVelocity().y <= 200f) {
            pinguin.getBody().setLinearVelocity(
                    new Vector2(pinguin.getBody().getLinearVelocity().x,
                            pinguin.getBody().getLinearVelocity().y * 1.2f));
        } else {
            pinguin.getBody().setLinearVelocity(
                    new Vector2(pinguin.getBody().getLinearVelocity().x,
                            200f));
        }

        if (pinguin.getBody().getLinearVelocity().x >= 0) {
            if (pinguin.getBody().getLinearVelocity().x <= 50) {
                pinguin.getBody().setLinearVelocity(new Vector2(50f,
                        pinguin.getBody().getLinearVelocity().y));
            }
        } else if (pinguin.getBody().getLinearVelocity().x >= -50) {
            pinguin.getBody().setLinearVelocity(new Vector2(-50f,
                    pinguin.getBody().getLinearVelocity().y));
        }
    }

}
