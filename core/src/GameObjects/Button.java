/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Helper.ButtonListener;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 *
 * @author qw
 */
public class Button extends Actor {
    
  private TextureRegion textureNormal;
  
  private TextureRegion textureTouched;
  
  private boolean touched = false;
  
  private BitmapFont font;
  
  private CharSequence text;

  public Button (String name, TextureRegion textureNormal, TextureRegion textureTouched, CharSequence newText, BitmapFont font) {
    super();
    this.addListener(new ButtonListener(this));
    this.setName(name);
    this.setTexture(textureNormal, textureTouched);
    this.setText(newText);
    this.setFont(font);
    this.setWidth(textureNormal.getRegionWidth());
    this.setHeight(textureNormal.getRegionHeight());
    this.setOrigin(this.getWidth() / 2.0f, this.getHeight() / 2.0f);
  }
  //Далее идут методы установки и получения свойств класса (сеттеры и геттеры)
  public void setTouched(boolean touched) {
    this.touched = touched;
  }
  public boolean getTouched() {
    return this.touched;
  }
  public void setFont(BitmapFont font) {
    this.font = font;
  }
  public BitmapFont getFont() {
    return this.font;
  }
  public void setText(CharSequence text) {
    this.text = text;
  }
  public CharSequence getText() {
    return this.text;
  }
  public void setTexture(TextureRegion textureNormal, TextureRegion textureTouched) {
    this.textureNormal = textureNormal;
    this.textureTouched = textureTouched;
  }
  public TextureRegion getTextureNormal() {
    return this.textureNormal;
  }
  public TextureRegion getTextureTouched() {
    return this.textureTouched;
  }
  //Данный метод будет выполняться при нажатии кнопки
  public void action() {
  }
  //Метод отрисовки кнопки (вызывается в цикле при отрисовке сцены)
  public void draw (Batch batch, float parentAlpha) {
    //Если кнопка нажата, то рисуем текстуру нажатого состояния, если нет - нормального
    if (!getTouched()) {
      batch.draw(textureNormal, getX(), getY(), getWidth(), getHeight());
    } else {
      batch.draw(textureTouched, getX(), getY(), getWidth(), getHeight());
    }
    //Устанавливаем цвет фона
    BitmapFont font = new BitmapFont();
    font.setFixedWidthGlyphs(text);
    this.getFont().setColor(0,0,0,1);
    //Рисуем текст по центру кнопки
    this.getFont().draw(batch, this.getText(), this.getX(), 
            this.getY() + this.getHeight() /2 + this.getFont().getCapHeight() / 2,
            this.getWidth(),Align.center, false);
  }
}
