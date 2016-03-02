package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screen.AbstractScreen;
import com.mygdx.game.screen.MainScreen;

public class GameLibGDX extends ApplicationAdapter {
    
    private AbstractScreen screen;
	
    @Override
    public void create () {
        Gdx.app.log("YetiGame", "app created");
        setScreen(new MainScreen(this));
    }
   
        
    public void setScreen (AbstractScreen screen) {
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
    
    public AbstractScreen getScreen () {
        return screen;
    }
}

