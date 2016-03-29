/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import GameObjects.Button;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

/**
 *
 * @author qw
 */
public class ButtonListener extends InputListener {
  //Ссфлка на кнопку, которую обработчик будет слушать
  private Button button;
  //Конструктор, в который передаём ссылку на кнопку, события которой нужно обрабатывать
  public ButtonListener(Button button) {
    super();
    this.setButton(button);
  }
  //Сеттер и геттер для ссылки на кнопку
  public Button getButton() {
    return this.button;
  }
  public void setButton(Button button) {
    this.button = button;
  }
  //Метод, который будет вызываться при нажатии на кнопку
  public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
    //Устанавливаем флаг нажатия в "нажата"this.getButton().action();
    this.getButton().setTouched(true);
    return true;
  }
  //Метод, который будет вызываться при отпускании кнопки
  public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
    //Устанавливаем флаг нажатия в "отжата"
    this.getButton().action();
    this.getButton().setTouched(false);
    //Выполняем метод action кнопки
    
  }
}

 