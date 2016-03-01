/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.GameLibGDX;

/**
 *
 * @author Broff
 */
public abstract class AbstractScreen implements Screen {

    abstract void loadResources();
    abstract void initScene();
            
    protected GameLibGDX game;
    protected Stage stage;
    
    
    public AbstractScreen(GameLibGDX game){
        this.game = game;
        this.stage = new Stage();
        loadResources();
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
  
    //метод, который требует реализовать интерфейс Screen. он вызывается в цикле
    @Override
    public void render(float delta) {
        //вызываем метод act для сцены. В этом методе происходят изменения актёров, если это необходимо
        getStage().act(delta);
        //устанавливаем чёрный цвет, как цвет очистки экрана. используется палитра RGBA
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        //очищаем экран
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //вызываем метод draw для сцены. Этот метод рисует актёров на экране
        getStage().draw();
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
