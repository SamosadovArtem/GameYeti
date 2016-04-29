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
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.game.GameLibGDX;
import com.mygdx.game.screen.MainScreen;

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

    private Float maxY;
    private Float minY;

    private Thread tr;
    private BuffsThread t;

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
        float width = ui.getWidth() * 3 / 5;
        float height = ui.getHeight() / 4;
        float space = ui.getHeight() / 15;
        
        int i = 0;
        for(Buff b : buffList){
            containerList.add(new BuffContainer(b, ui.getWidth() / 5,
                ui.getHeight() - height*i - space * (i+1), width, height, ui.getStage()));
            i++;
        }
        /*
        containerList.add(new BuffContainer(BuffsInfo.getJumpCountBuff(), ui.getWidth() / 5,
                ui.getHeight() - space, width, height, ui.getStage()));
        containerList.add(new BuffContainer(BuffsInfo.getGravityBuff(), ui.getWidth() / 5,
                ui.getHeight() - height - space * 2, width, height, ui.getStage()));
        containerList.add(new BuffContainer(BuffsInfo.getJumpPowerBuff(), ui.getWidth() / 5,
                ui.getHeight() - height * 2 - space * 3, width, height, ui.getStage()));        
        containerList.add(new BuffContainer(BuffsInfo.getFrictionBuff(), ui.getWidth() / 5,
                ui.getHeight() - height * 3 - space * 4, width, height, ui.getStage()));
        containerList.add(new BuffContainer(BuffsInfo.getPowerCoffBuff(), ui.getWidth() / 5,
                ui.getHeight() - height * 4 - space * 5, width, height, ui.getStage()));
        containerList.add(new BuffContainer(BuffsInfo.getDirectionCoffBuff(), ui.getWidth() / 5,
                ui.getHeight() - height * 5 - space * 6, width, height, ui.getStage()));
        containerList.add(new BuffContainer(BuffsInfo.getCoinsBuff(), ui.getWidth() / 5,
                ui.getHeight() - height * 6 - space * 7, width, height, ui.getStage()));
        containerList.add(new BuffContainer(BuffsInfo.getHeightSkyCoffBuff(), ui.getWidth() / 5,
                ui.getHeight() - height * 7 - space * 8, width, height, ui.getStage()));
        */
        minY = containerList.get(containerList.size() - 1).getY()
                + containerList.get(containerList.size() - 1).getHeight();

        maxY = containerList.get(0).getY() - containerList.get(0).getHeight() * 2;
        System.out.println("Самый верхний, блять - " + maxY);
        System.out.println("Самый нижний, блять - " + minY);
        //- containerList.get(0).getHeight()/4 ;
    }

    public float getMaxY() {
        return this.maxY;
    }

    public float getMinY() {
        return this.minY;
    }

    @Override
    public void update(float delta) {
        if(inertion < -5 || inertion > 5){
            moveCamera(inertion);
            if(inertion < 0 ) {
                inertion+=0.5f;
            } else {
                inertion-=0.5f;
            }
            if(inertion > -5 && inertion < 5){
                inertion = 0;
            }
        } else {
            moveToBuff();
        }
    }

    private void moveToBuff(){
        float s = 0;
        if ((this.getUI().getStage().getCamera().position.y)<=containerList.get(containerList.size()-1).getY()+
                containerList.get(containerList.size()-1).getHeight()) {
            s = ((containerList.get(containerList.size()-1).getY()+ containerList.get(containerList.size()-1).getHeight())
            -(this.getUI().getStage().getCamera().position.y))/10.0f;
        }
        if ((this.getUI().getStage().getCamera().position.y)>=containerList.get(0).getY()-
                containerList.get(0).getHeight()*2) {
            s = ((containerList.get(0).getY()- containerList.get(0).getHeight()*2)
                    -(this.getUI().getStage().getCamera().position.y))/10.0f;
        }
        this.getUI().getStage().getCamera().position.y += s;
    }
    private void moveCamera(float y) {
        if(!scrollArea) {
            if ((this.getUI().getStage().getCamera().position.y+y)<=containerList.get(containerList.size()-1).getY()+
                    containerList.get(containerList.size()-1).getHeight()) {inertion=0;}
            if ((this.getUI().getStage().getCamera().position.y+y)>=containerList.get(0).getY()-
                    containerList.get(0).getHeight()*2) {inertion=0;}

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
