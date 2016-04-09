/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Helper.ButtonListener;
import Helper.FontLoader;
import Helper.ScrollListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.game.screen.GameScreen;

/**
 *
 * @author Pablo
 */
public class Scroll extends Actor {

    private Texture backSprite, valueSprite;

    private float value;
    private boolean touched;

    public Scroll(String name, Texture backSprite, Texture valueSprite, float value) {
        super();
        this.setTouched(false);
        this.addListener(new ScrollListener(this));
        this.setName(name);
        this.setTexture(backSprite, valueSprite);
        this.setOrigin(this.getWidth() / 2.0f, this.getHeight() / 2.0f);
        this.value = value;
    }
    //Далее идут методы установки и получения свойств класса (сеттеры и геттеры)

    public void setTouched(boolean touched) {
        this.touched = touched;
    }

    public boolean getTouched() {
        return this.touched;
    }

    public void setTexture(Texture textureNormal, Texture textureTouched) {
        this.backSprite = textureNormal;
        this.valueSprite = textureTouched;
    }

    public Texture getBackSprite() {
        return this.backSprite;
    }

    public Texture getValueSprite() {
        return this.valueSprite;
    }
    //Данный метод будет выполняться при нажатии кнопки

    public void action(float x, float y) {
        //value =((x - this.getX() + this.getWidth()/2 +10) / this.getWidth());
        value = x / getWidth();
        if (value >= 1) {
            value = 1;
        } else if (value <= 0) {
            value = 0;
        }

    }
    //Метод отрисовки кнопки (вызывается в цикле при отрисовке сцены)
public void save(){
    //
}
    
    
    public void draw(Batch batch, float parentAlpha) {
        //Если кнопка нажата, то рисуем текстуру нажатого состояния, если нет - нормального
        //   batch.draw(backSprite, getX(), getY(), getWidth(), getHeight());
        batch.draw(valueSprite, getX(), getY(), (float) (getWidth() * value), getHeight(),
                0, 0,
                (int) (valueSprite.getWidth() * value), valueSprite.getHeight(), false, false);
    }

    public float getValue() {
    
        return this.value;
    }
}
