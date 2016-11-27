/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Buffs;

import Enums.TutorialType;
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
import com.mygdx.game.tutorial.TutorialHandler;

import static java.lang.Thread.sleep;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

/**
 * @author qw
 */
public class BuffsWorld extends AbstractWorld {

    private Float maxY;
    private Float minY;

    private Thread tr;
    private BuffsThread t;
    private int count;

    private List<BuffContainer> containerList = new ArrayList();
    private List<Buff> buffList;

    public float inertion = 0;
    public boolean scrollArea = false;

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

        count = 0;

        initContainer(BuffsInfo.getJumpCountBuff());
        initContainer(BuffsInfo.getGravityBuff());
        initContainer(BuffsInfo.getJumpPowerBuff());
        initContainer(BuffsInfo.getDirectionCoffBuff());
        initContainer(BuffsInfo.getFrictionBuff());
        initContainer(BuffsInfo.getHeightSkyCoffBuff());
        initContainer(BuffsInfo.getPowerCoffBuff());
        initContainer(BuffsInfo.getCoinsBuff());
        

        minY = containerList.get(containerList.size() - 1).getY()
                + containerList.get(containerList.size() - 1).getHeight();

        maxY = containerList.get(0).getY() - containerList.get(0).getHeight() * 2;
    }

    private void initContainer(Buff buff) {
        float width = ui.getWidth() * 3 / 5;
        float height = ui.getHeight() / 4;
        float space = ui.getHeight() / 15;

        boolean isTutorial = false;
        boolean isActive = true;

        if (TutorialHandler.getType() == TutorialType.BUFF) {
            if (count == 0) {
                isTutorial = true;
            } else {
                isActive = false;
            }
        }

        containerList.add(new BuffContainer(buff, ui.getWidth() / 5,
                ui.getHeight() - height * count - space * (count + 1), width, height, ui.getStage(),
                isTutorial, isActive));

        count++;
    }

    public float getMaxY() {
        return this.maxY;
    }

    public float getMinY() {
        return this.minY;
    }

    @Override
    public void update(float delta) {
        if(TutorialHandler.getType() != TutorialType.BUFF) {
            if (inertion < -5 || inertion > 5) {
                moveCamera(inertion);
                if (inertion < 0) {
                    inertion += 0.5f;
                } else {
                    inertion -= 0.5f;
                }
                if (inertion > -5 && inertion < 5) {
                    inertion = 0;
                }
            } else {
                moveToBuff();
            }
        }
    }

    private void moveToBuff() {
        float s = 0;
        if ((this.getUI().getStage().getCamera().position.y) <= containerList.get(containerList.size() - 1).getY() +
                containerList.get(containerList.size() - 1).getHeight()) {
            s = ((containerList.get(containerList.size() - 1).getY() + containerList.get(containerList.size() - 1).getHeight())
                    - (this.getUI().getStage().getCamera().position.y)) / 10.0f;
        }
        if ((this.getUI().getStage().getCamera().position.y) >= containerList.get(0).getY() -
                containerList.get(0).getHeight() * 2) {
            s = ((containerList.get(0).getY() - containerList.get(0).getHeight() * 2)
                    - (this.getUI().getStage().getCamera().position.y)) / 10.0f;
        }
        this.getUI().getStage().getCamera().position.y += s;
    }

    private void moveCamera(float y) {
        if (!scrollArea) {
            if ((this.getUI().getStage().getCamera().position.y + y) <= containerList.get(containerList.size() - 1).getY() +
                    containerList.get(containerList.size() - 1).getHeight()) {
                inertion = 0;
            }
            if ((this.getUI().getStage().getCamera().position.y + y) >= containerList.get(0).getY() -
                    containerList.get(0).getHeight() * 2) {
                inertion = 0;
            }

            this.getUI().getStage().getCamera().position.y += y;
        }
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
