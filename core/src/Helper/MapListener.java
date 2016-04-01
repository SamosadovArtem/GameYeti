/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import GameObjects.Button;
import GameObjects.Map;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 *
 * @author Admin
 */
public class MapListener extends InputListener {
    //Ссфлка на кнопку, которую обработчик будет слушать

    private Map map;
    //Конструктор, в который передаём ссылку на кнопку, события которой нужно обрабатывать

    public MapListener(Map map) {
        super();
        this.setMap(map);
    }
    //Сеттер и геттер для ссылки на кнопку

    public Map getMap() {
        return this.map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
    //Метод, который будет вызываться при нажатии на кнопку

    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        //Устанавливаем флаг нажатия в "нажата"
    //    this.getMap().setTouched(true);
        return true;
    }
    //Метод, который будет вызываться при отпускании кнопки

    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        //Устанавливаем флаг нажатия в "отжата"
        this.getMap().setTouched(false);
        map.action();
        //Выполняем метод action кнопки
        this.getMap().action();
    }
}
