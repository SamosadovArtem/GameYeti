/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Statistic;

import GameObjects.Button;
import GameObjects.Interface;
import GameObjects.Scroll;
import GameWorld.AbstractWorld;
import Helper.AssetLoader;
import Helper.FontLoader;
import Helper.Statistic;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author qw
 */
public class StatisticWorld extends AbstractWorld {

    private Label allGames, allJumps, allLength;

    public StatisticWorld(Interface ui, GameLibGDX g) {
        super(ui, g);
        initUI();
        ui.addBack(game);
    }

    @Override
    public void update(float delta) {
        //
    }

    private void initUI() {
        initLabelGames();
        initLabelJumps();
        initLabelLength();
    }

    private void initLabelGames() {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        allGames = new Label("", labelS);
        allGames.setAlignment(Align.center);
        allGames.setFontScale(1);
        allGames.setSize(ui.getStage().getWidth() * 0.4f, ui.getStage().getHeight() / 5);
        allGames.setPosition(0, ui.getHeight() * 2 / 5);
        allGames.setText("You play " + Statistic.getGames() + " games");
        ui.getGuiStage().addActor(allGames);
    }
    
    private void initLabelJumps() {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        allJumps = new Label("", labelS);
        allJumps.setAlignment(Align.center);
        allJumps.setFontScale(1);
        allJumps.setSize(ui.getStage().getWidth() * 0.4f, ui.getStage().getHeight() / 5);
        allJumps.setPosition(0, ui.getHeight() * 3 / 5);
        allJumps.setText("You jump " + Statistic.getJumps() + " rows");
        ui.getGuiStage().addActor(allJumps);
    }
    
    private void initLabelLength() {
        Label.LabelStyle labelS = new Label.LabelStyle();
        labelS.font = new BitmapFont();
        labelS.fontColor = Color.WHITE;
        allLength = new Label("", labelS);
        allLength.setAlignment(Align.center);
        allLength.setFontScale(1);
        allLength.setSize(ui.getStage().getWidth() * 0.4f, ui.getStage().getHeight() / 5);
        allLength.setPosition(0, ui.getHeight() * 4 / 5);
        allLength.setText("You walk " + Statistic.getLength() + " meters");
        ui.getGuiStage().addActor(allLength);
    }

   
}
