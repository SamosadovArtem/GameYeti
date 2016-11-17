/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author qw
 */
public class FontLoader {
    
    public static BitmapFont font;
    
    public static void load(){
        Texture texture = new Texture(Gdx.files.internal("fonts/font.png"), true);
        //Устанавливаем фильтр для сглаживания при уменьшении
        //Устанавливаем шрифт используя текстуру и файл fnt
        font = new BitmapFont(Gdx.files.internal("fonts/font.fnt"), new TextureRegion(texture), false);
    }
    
    public static void dispose() {
        font.dispose();
    }
}
