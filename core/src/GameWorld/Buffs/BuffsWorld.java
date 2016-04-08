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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.lang.Thread.sleep;

/**
 *
 * @author qw
 */
public class BuffsWorld extends AbstractWorld {

    private Thread tr;
    private BuffsThread t;

    private List<BuffContainer> containerList = new ArrayList();
    private List<Buff> buffList;

    public BuffsWorld(Interface ui, GameLibGDX g) {
        super(ui, g);
        setUpWorld();
        t = new BuffsThread(containerList);
        tr = new Thread(t);
        tr.start();
    }

    private void setUpWorld() {

        buffList = BuffsInfo.getBuffs();
        initContainers();
        ui.addBack(game);
    }

    private void initContainers() {
        float width = ui.getWidth() * 3 / 5;
        float height = ui.getHeight() / 4;
        float space = ui.getHeight() / 15;

        containerList.add(new BuffContainer(BuffsInfo.getJumpCountBuff(), ui.getWidth() / 5,
                ui.getHeight() - space, width, height, ui.getStage()));
        containerList.add(new BuffContainer(BuffsInfo.getGravityBuff(), ui.getWidth() / 5,
                ui.getHeight() - height - space * 2, width, height, ui.getStage()));
        containerList.add(new BuffContainer(BuffsInfo.getJumpPowerBuff(), ui.getWidth() / 5,
                ui.getHeight() - height * 2 - space * 3, width, height, ui.getStage()));
    }

    @Override
    public void update(float delta) {
    }

    public Thread getThread() {
        return tr;
    }
}

class BuffsThread implements Runnable {

    public boolean isActive = true;
    private List<BuffContainer> list;

    public BuffsThread(List<BuffContainer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        try {
            for (BuffContainer bc : list) {
                    bc.update();
                }
            while (true) {
                for (BuffContainer bc : list) {
                    bc.update();
                }
                sleep(1000);
                if (!isActive) {
                    break;
                }
            }
        } catch (InterruptedException ex) {

        }
    }
}
