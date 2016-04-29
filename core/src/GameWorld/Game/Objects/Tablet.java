/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameWorld.Game.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Pablo
 */
public class Tablet extends Actor {

    private Texture sprite;

    private BitmapFont font;

    private CharSequence text;

    public Tablet(String name, Texture sprite, float value, BitmapFont font) {
        super();
        this.setName(name);
        this.sprite = sprite;
        this.setOrigin(this.getWidth() / 2.0f, this.getHeight() / 2.0f);
        this.text = (int)value + " m.";
        this.font = font;
    }

    private BitmapFont getFont() {
        return font;
    }

    private CharSequence getText() {
        return text;
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(sprite, getX(), getY(), getWidth(), getHeight());
        //Устанавливаем цвет фона
        font.setFixedWidthGlyphs(text);
        this.getFont().setColor(0, 0, 0, 1);
        //Рисуем текст по центру кнопки
        this.getFont().draw(batch, this.getText(), this.getX(),
                this.getY() + this.getHeight() / 2 + this.getFont().getCapHeight() / 2);
    }
}
