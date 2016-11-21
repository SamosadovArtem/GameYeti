
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
*/

package com.mygdx.game.screen;

import GameObjects.Interface;
import Helper.Constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.GameLibGDX;

/**
 * @author Broff
 */

public abstract class AbstractScreen implements Screen {

    protected GameLibGDX game;
    protected Interface ui;

    protected void initScene() {
    }

    public AbstractScreen(GameLibGDX game) {
        this.game = game;
        float a = Constants.APP_WIDTH;
        float b = Constants.APP_HEIGHT;
        final GameLibGDX g = game;

        ui = new Interface(new Stage(new FillViewport(a, b)),
                new Stage(new FillViewport(a, b)) {
                    @Override
                    public boolean keyDown(int keyCode) {
                        if (keyCode == Input.Keys.BACK) {
                            g.getScreen().backPress();
                        }
                        return super.keyDown(keyCode);
                    }
                });
        initScene();
    }

    //геттер для сцены. метод для получения сцены экрана. Если сцена не задана, то создаётся пустая сцена для данного экрана
    public Stage getStage() {
        return ui.getStage();
    }

    //сеттер для обекта игры. метод для установки ссылки на объект игры для данного экрана
    public void setGame(GameLibGDX game) {
        this.game = game;
    }

    //геттер для обекта игры. метод для получения ссылки на объект игры для данного экрана
    public GameLibGDX getGame() {
        return this.game;
    }

    protected void setCameraX(float x) {
        this.getStage().getCamera().position.x = x;
        ui.getStage().getCamera().position.x = x;
        ui.getStage().getCamera().update();
        getStage().getCamera().update();
    }

    protected float getCameraX() {
        return ui.getStage().getCamera().position.x;
    }

    @Override
    public void resize(int width, int height) {
        //вызываем метод setViewport для сцены с новыми размерами экрана. Этот метод изменяет размер сцены так, чтобы она занимала весь экран
        getStage().getViewport().update(width, height);
        ui.getGuiStage().getViewport().update(width, height);
        ui.getStage().getCamera().update();
        ui.getGuiStage().getCamera().update();
    }

    @Override
    public void show() {
        //устанавливаем сцену экрана как обработчик событий ввода. это нужно для того, чтобы можно было отлавливать нажатия на актёров
        Gdx.input.setInputProcessor(ui.getGuiStage());
    }

    //метод, который требует реализовать интерфейс Screen. Он вызывается при закрытии экрана. Экран закрыт - можно очистить ресурсы
    @Override
    public void dispose() {
        if (ui.getStage() != null) {
            ui.dispose();
        }
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

    public void backPress(){};
}
