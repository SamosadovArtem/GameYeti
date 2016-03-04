/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

import GameWorld.Renderer;
import GameWorld.World;
import Helper.Statistic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author Broff
 */
public abstract class AbstractScreen implements Screen {
            
    protected GameLibGDX game;
    protected Stage stage;
    
    protected void initScene(){};
    
    public AbstractScreen(GameLibGDX game){
        this.game = game;
        this.stage = new Stage();
        initScene();
    }    
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }  
    
    //геттер для сцены. метод для получения сцены экрана. Если сцена не задана, то создаётся пустая сцена для данного экрана
    public Stage getStage() {
      if (stage == null) {
        stage = new Stage();
      }
      return stage;
    }
  
    //сеттер для обекта игры. метод для установки ссылки на объект игры для данного экрана
    public void setGame(GameLibGDX game) {
        this.game = game;
    }
    
    //геттер для обекта игры. метод для получения ссылки на объект игры для данного экрана
    public GameLibGDX getGame() {
        return this.game;
    }  
  
    
    
    @Override
    public void resize(int width, int height) {
        //вызываем метод setViewport для сцены с новыми размерами экрана. Этот метод изменяет размер сцены так, чтобы она занимала весь экран
        getStage().getViewport().update(width, height);
        getStage().getCamera().update();
    }

    @Override
    public void show() {
        //устанавливаем сцену экрана как обработчик событий ввода. это нужно для того, чтобы можно было отлавливать нажатия на актёров
        Gdx.input.setInputProcessor(getStage());
    }
    //метод, который требует реализовать интерфейс Screen. Он вызывается при закрытии экрана. Экран закрыт - можно очистить ресурсы
    @Override
    public void dispose() {
        if (stage != null) stage.dispose();
    }
  
    @Override
    public void hide() {
    }
    
    @Override
    public void pause() {
    }
    
    @Override
    public void resume() {
    }
    
}
