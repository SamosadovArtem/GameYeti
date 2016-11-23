/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import Helper.ButtonListener;
import Helper.MapListener;
import LocationGenerator.BarrierTypes;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;

/**
 *
 * @author Admin
 */
public class Map extends Actor {
    
  private TextureRegion textureNormal;
  
  private TextureRegion textureUnbought; //Текстура некупленного
  
  private TextureRegion textureTouched;
  
  private boolean touched = false;
  
  private boolean isBought; //Куплена или нет
  
  private int price; //Цена
  
  private BitmapFont font;
  
  private BarrierTypes[] currentBarrierTypes;
  
  private CharSequence text;
  
  public MapListener mapListener;
  
  public Map (String name, TextureRegion textureNormal, TextureRegion textureTouched,
          TextureRegion textureUnbought, CharSequence newText, BitmapFont font, BarrierTypes[] barrierTypes) {
    super();
    mapListener = new MapListener(this);
    this.addListener(mapListener);
    this.setName(name);
    this.setTexture(textureNormal, textureTouched,textureUnbought);
    this.setText(newText);
    this.setFont(font);
    this.setWidth(textureNormal.getRegionWidth());
    this.setHeight(textureNormal.getRegionHeight());
    this.setOrigin(this.getWidth() / 2.0f, this.getHeight() / 2.0f);
    this.currentBarrierTypes = barrierTypes;
  }
  
  
  //Все свойства, доступ к полям
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
  public void setTexture(TextureRegion textureNormal, TextureRegion textureTouched,
          TextureRegion textureUnbought) {
    this.textureNormal = textureNormal;
    this.textureTouched = textureTouched;
    this.textureUnbought = textureUnbought;
  }
  public TextureRegion getTextureNormal() {
    return this.textureNormal;
  }
  public TextureRegion getTextureTouched() {
    return this.textureTouched;
  }
  public TextureRegion getTextureUnbought(){
      return this.textureUnbought;
  }
  public int GetPrice(){
      return this.price;
  }
  public void SetPrice(int price){
      if (price>0){
          this.price = price;
      }
      else{
          throw new IllegalArgumentException("Price must be > 0");
      }
  }
  
  public boolean IsMapBought(){
      return this.isBought;
  }
  
  public void BuyMap(){
    this.isBought = true;
}
  
 //Метод, выполняющийся при нажатии
    public void action() {
  }
    
    public void draw (Batch batch, float parentAlpha) {
    //Проверяем куплена ли карта, если нет - рисуем соответсвующую текстуру
    if (!isBought){
        batch.draw (textureUnbought, getX(), getY(), getWidth(), getHeight());
    }
    else{
    //Если кнопка нажата, то рисуем текстуру нажатого состояния, если нет - нормального
        if (!getTouched()) {
            batch.draw(textureNormal, getX(), getY(), getWidth(), getHeight());
        } else {
            batch.draw(textureTouched, getX(), getY(), getWidth(), getHeight());
          }
    }
    //Устанавливаем цвет фона
   }

    public BarrierTypes[] getCurrentBarrierTypes() {
        return currentBarrierTypes;
    }
    
}

