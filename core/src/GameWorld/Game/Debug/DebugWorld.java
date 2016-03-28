/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameWorld.Game.Debug;

import GameObjects.Button;
import GameObjects.Interface;
import GameWorld.AbstractWorld;
import Helper.AssetLoader;
import Helper.Constants;
import Helper.FontLoader;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.screen.GameScreen;

/**
 *
 * @author Pablo
 */
public class DebugWorld extends AbstractWorld {

    private Button back;

    private Label gravity;
    private Button gIncrease, gDecrease;

    private Label powercof;
    private Button pcIncrease, pcDecrease;

    private Label pinguin, fPinguin, rPinguin;
    private Button fPIncrease, fPDecrease, rPIncrease, rPDecrease;

    private TextureRegion normalState = AssetLoader.btn;
    private TextureRegion pressedState = AssetLoader.btnPress;

    public DebugWorld(Interface ui, GameLibGDX g) {
        super(ui, g);
        initObjects();
    }

    @Override
    public void update(float delta) {
        gravity.setText("gravity: " + Constants.WORLD_GRAVITY.y);
        powercof.setText("powercof: " + Constants.POWERCOF);
        fPinguin.setText("friction: " + Constants.PINGUIN_FRICTION);
        rPinguin.setText("restitution: " + Constants.PINGUIN_RESTITUTION);
    }

    private void initObjects() {
        initBlock(ui.getStage().getWidth() / 5, ui.getStage().getHeight() * 5 / 6);
        initBackButton();
    }

    private void initBlock(float x, float y) {
        gravity = initLabel(x, y);
        ui.getGuiStage().addActor(gravity);
        initGButtons(x, y, gDecrease, gIncrease);

        powercof = initLabel(x, y - ui.getStage().getHeight() / 5);
        ui.getGuiStage().addActor(powercof);
        initPCButtons(x, y - ui.getStage().getHeight() / 5, pcDecrease, pcIncrease);

        pinguin = initLabel(x + ui.getStage().getWidth() / 2, y + ui.getStage().getHeight() / 20);
        pinguin.setText("Pinguin:");
        fPinguin = initLabel(x + ui.getStage().getWidth() / 2, y);
        rPinguin = initLabel(x + ui.getStage().getWidth() / 2, y - ui.getStage().getHeight() / 20);
        ui.getGuiStage().addActor(pinguin);
        ui.getGuiStage().addActor(fPinguin);
        ui.getGuiStage().addActor(rPinguin);
        initPinguinButtons(x + ui.getStage().getWidth() / 2, y, fPDecrease, fPIncrease, rPDecrease, rPIncrease);
    }

    private Label initLabel(float x, float y) {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        Label label;
        label = new Label("", labelS);
        label.setPosition(x, y);
        label.setFontScale(1);
        label.setSize(ui.getStage().getWidth() / 8, ui.getStage().getHeight() / 5);
        return label;
    }

    private void initGButtons(float x, float y, Button b1, Button b2) {
        b1 = new Button("-", normalState, pressedState, "-", FontLoader.font) {
            public void action() {
                Constants.WORLD_GRAVITY.y--;
            }
        };
        b1.setSize(ui.getStage().getWidth() / 20, ui.getStage().getHeight() / 20);
        b1.setPosition(x - ui.getStage().getWidth() / 8, y);
        ui.getGuiStage().addActor(b1);

        b2 = new Button("-", normalState, pressedState, "-", FontLoader.font) {
            public void action() {
                Constants.WORLD_GRAVITY.y++;
            }
        };
        b2.setSize(ui.getStage().getWidth() / 20, ui.getStage().getHeight() / 20);
        b2.setPosition(x + ui.getStage().getWidth() / 8, y);
        ui.getGuiStage().addActor(b2);
    }

    private void initPCButtons(float x, float y, Button b1, Button b2) {
        b1 = new Button("-", normalState, pressedState, "-", FontLoader.font) {
            public void action() {
                Constants.POWERCOF--;
            }
        };
        b1.setSize(ui.getStage().getWidth() / 20, ui.getStage().getHeight() / 20);
        b1.setPosition(x - ui.getStage().getWidth() / 8, y);
        ui.getGuiStage().addActor(b1);

        b2 = new Button("-", normalState, pressedState, "-", FontLoader.font) {
            public void action() {
                Constants.POWERCOF++;
            }
        };
        b2.setSize(ui.getStage().getWidth() / 20, ui.getStage().getHeight() / 20);
        b2.setPosition(x + ui.getStage().getWidth() / 8, y);
        ui.getGuiStage().addActor(b2);
    }

    private void initPinguinButtons(float x, float y, Button b1, Button b2, Button b3, Button b4) {
        b1 = new Button("-", normalState, pressedState, "-", FontLoader.font) {
            public void action() {
                Constants.PINGUIN_FRICTION = (float) (Constants.PINGUIN_FRICTION - 0.1);
            }
        };
        b1.setSize(ui.getStage().getWidth() / 20, ui.getStage().getHeight() / 20);
        b1.setPosition(x - ui.getStage().getWidth() / 8, y + ui.getStage().getHeight() / 20);
        ui.getGuiStage().addActor(b1);

        b2 = new Button("-", normalState, pressedState, "-", FontLoader.font) {
            public void action() {
                Constants.PINGUIN_FRICTION = (float) (Constants.PINGUIN_FRICTION + 0.1);
            }
        };
        b2.setSize(ui.getStage().getWidth() / 20, ui.getStage().getHeight() / 20);
        b2.setPosition(x + ui.getStage().getWidth() / 6, y + ui.getStage().getHeight() / 20);
        ui.getGuiStage().addActor(b2);

        b3 = new Button("-", normalState, pressedState, "-", FontLoader.font) {
            public void action() {
                Constants.PINGUIN_RESTITUTION = (float) (Constants.PINGUIN_RESTITUTION - 0.1);
            }
        };
        b3.setSize(ui.getStage().getWidth() / 20, ui.getStage().getHeight() / 20);
        b3.setPosition(x - ui.getStage().getWidth() / 8, y);
        ui.getGuiStage().addActor(b3);

        b4 = new Button("-", normalState, pressedState, "-", FontLoader.font) {
            public void action() {
                Constants.PINGUIN_RESTITUTION = (float) (Constants.PINGUIN_RESTITUTION + 0.1);
            }
        };
        b4.setSize(ui.getStage().getWidth() / 20, ui.getStage().getHeight() / 20);
        b4.setPosition(x + ui.getStage().getWidth() / 6, y);
        ui.getGuiStage().addActor(b4);
    }

    private void initBackButton() {
        back = new Button("Back", normalState, pressedState, "BACK", FontLoader.font) {
            public void action() {
                game.setScreen(new GameScreen(game));
            }
        };
        back.setSize(ui.getStage().getWidth() / 12, ui.getStage().getHeight() / 12);
        back.setPosition(ui.getStage().getWidth() * 7 / 8, ui.getStage().getHeight() / 10);
        ui.getGuiStage().addActor(back);
    }
}
