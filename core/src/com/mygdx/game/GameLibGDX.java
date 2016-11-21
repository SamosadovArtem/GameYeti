package com.mygdx.game;

import GameWorld.Skins.Elements.SkinsStatistic;
import Helper.AssetLoader;
import Helper.BuffsInfo;
import Helper.Constants;
import Helper.DailyGiftHandler;
import Helper.FontLoader;
import Helper.Statistic;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.screen.AbstractScreen;
import com.mygdx.game.screen.MainScreen;

public class GameLibGDX extends ApplicationAdapter {

    private AbstractScreen screen;
	
    @Override
    public void create () {
        loadResources();
        Gdx.input.setCatchBackKey(true);
        setScreen(new MainScreen(this));
    }

    private void loadResources(){
        AssetLoader.load();
        FontLoader.load();
        Statistic.load();
        BuffsInfo.load();
        SkinsStatistic.load();
        DailyGiftHandler.load();
    }

    public void setScreen (AbstractScreen screen) {
        if (this.screen != null) {
            this.screen.hide();
            //this.screen.dispose();
        }
        this.screen = screen;
        if (this.screen != null) {
            this.screen.show();
            this.screen.resize(Constants.APP_WIDTH, Constants.APP_HEIGHT);
        }
    }
    
    @Override
    public void render(){
        screen.render(Gdx.graphics.getDeltaTime());
    }
    
    @Override
    public void dispose(){
        AssetLoader.dispose();
        FontLoader.dispose();
    }

    public AbstractScreen getScreen () {
        return screen;
    }
}
