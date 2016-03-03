package com.mygdx.game;

import Helper.AssetLoader;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.screen.AbstractScreen;
import com.mygdx.game.screen.MainScreen;

public class GameLibGDX extends ApplicationAdapter {
    
    private Screen screen;
	
    @Override
    public void create () {
        Gdx.app.log("YetiGame", "app created");
        AssetLoader.load();
        setScreen(new MainScreen(this));
    }
   
        
    public void setScreen (Screen screen) {
        if (this.screen != null) this.screen.hide();
        this.screen = screen;
        if (this.screen != null) {
            this.screen.show();
            this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }
    
    @Override
    public void render(){
        screen.render(Gdx.graphics.getDeltaTime());
    }
    
    @Override
    public void dispose(){
        //AssetLoader.dispose();
    }
    
    public Screen getScreen () {
        return screen;
    }
}
