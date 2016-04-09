/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import GameObjects.Scroll;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 *
 * @author Pablo
 */
public class ScrollListener extends InputListener {

    private Scroll scroll;
    //Конструктор, в который передаём ссылку на кнопку, события которой нужно обрабатывать

    public ScrollListener(Scroll scroll) {
        super();
        this.setScroll(scroll);
    }
    //Сеттер и геттер для ссылки на кнопку

    public Scroll getScroll() {
        return this.scroll;
    }

    public void setScroll(Scroll scroll) {
        this.scroll = scroll;
    }
    //Метод, который будет вызываться при нажатии на кнопку

    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        //Устанавливаем флаг нажатия в "нажата"
        this.getScroll().action(x, y);
        this.getScroll().setTouched(true);
        return true;
    }
    //Метод, который будет вызываться при отпускании кнопки

    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        //Устанавливаем флаг нажатия в "отжата"
        this.getScroll().setTouched(false);
        
//        System.out.println("Скрол нажат и значение- " + 
//                (int)(this.scroll.getValue()*100)+this.scroll.getName());
        
        if (this.scroll.getName()=="Sound"){
            int value = (int)(this.scroll.getValue()*100);
            Statistic.setSoundsLevel(value);
            SoundsLoader.updateSound();
            
        }
        if (this.scroll.getName()=="Music"){
            int value = (int)(this.scroll.getValue()*100);
            Statistic.setMusicLevel(value);
            SoundsLoader.updateMusic();
        }
        
        this.scroll.save();
        
        //Выполняем метод action кнопки
        //this.getButton().action();
    }

    public boolean touchDragged(int screenX, int screenY, int pointer) {
        
        Gdx.app.log("log","x: "+screenX);
        return true;
    }
    public void touchDragged (InputEvent event, float x, float y, int pointer) {
        this.getScroll().action(x, y);
	}
}
