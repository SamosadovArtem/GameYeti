/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Buffs;

import GameObjects.BuffContainer;
import GameObjects.Buffs.Buff;
import GameObjects.Buffs.JumpCountBuff;
import GameObjects.Button;
import GameObjects.Interface;
import GameWorld.AbstractWorld;
import GameWorld.Game.Objects.Antelope;
import GameWorld.Game.Objects.Pinguin;
import Helper.AssetLoader;
import Helper.BuffsInfo;
import Helper.Constants;
import Helper.FontLoader;
import Helper.MyTimer;
import Helper.TimeConverter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.game.GameLibGDX;
import static java.lang.Thread.sleep;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author qw
 */
public class BuffsWorld extends AbstractWorld {

    private Label jumpCountLabel;
    private Thread tr;
    private BuffsThread t;
    private BuffContainer bc;

    public BuffsWorld(Interface ui, GameLibGDX g) {
        super(ui, g);
        setUpWorld();
        t = new BuffsThread(bc);
        tr = new Thread(t);
        tr.start();
    }

    private void setUpWorld() {
        TextureRegion normalState = AssetLoader.btn;
        TextureRegion pressedState = AssetLoader.btnPress;

        jumpCountLabel();
        bc = new BuffContainer(BuffsInfo.getGravityBuff(),
                ui.getWidth()/5,ui.getHeight()*4/5,ui.getWidth()*3/5,ui.getHeight()/4,ui.getGuiStage());
        ui.addBack(game);
    }

    @Override
    public void update(float delta) {
    }

    private void jumpCountLabel() {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        jumpCountLabel = new Label("", labelS);
        jumpCountLabel.setAlignment(Align.center);
        jumpCountLabel.setFontScale(2);
        jumpCountLabel.setSize(ui.getStage().getWidth() * 0.4f, ui.getStage().getHeight() / 5);
        jumpCountLabel.setPosition(ui.getStage().getWidth() / 2,
                ui.getStage().getHeight() / 2);
        ui.getGuiStage().addActor(jumpCountLabel);
    }

    public Thread getThread() {
        return tr;
    }
}

class BuffsThread implements Runnable {

    public boolean isActive = true;
    Label l;
    TimeConverter tc = new TimeConverter(30 * 1000l);
    private BuffContainer bc;

    public BuffsThread(BuffContainer bc) {
        this.bc = bc;

    }

    @Override
    public void run() {
        try {
            while (tc.getStatus()) {
                sleep(1000);
                bc.update();
                Gdx.app.log("Timer", "tick");
                if (!isActive) {
                    break;
                }
            }
        } catch (InterruptedException ex) {

        }
    }
}
