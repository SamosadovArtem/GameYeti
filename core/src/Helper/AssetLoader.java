/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author qw
 */
public class AssetLoader {

    public static Texture textureBtnNormal, textureBtnPress;
    public static Texture pinguin, elephant, giraffe;
    public static Texture gameBackground;
    public static Texture coinTexture, pause;
    public static Texture ground;
    public static TextureRegion gbc, ctr, pauseTexture, elephantTexture, giraffeTexture;
    public static TextureRegion pinguinTexture;
    public static TextureRegion btnPress, btn;
    public static Animation birdAnimation;
    public static Sound dead, flap, coin;
    public static BitmapFont font, shadow;
    public static Texture protectiveLayerTexture;

    public static void load() {

        elephant = new Texture(Gdx.files.internal("gfx/gameScreen.Objects/elephant.png"));
        elephant.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        elephantTexture = new TextureRegion(elephant, 0, 0, 2000, 1990);

        giraffe = new Texture(Gdx.files.internal("gfx/gameScreen.Objects/giraffe.png"));
        giraffe.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        giraffeTexture = new TextureRegion(giraffe, 0, 0, 256, 256);

        ground = new Texture(Gdx.files.internal("gfx/gameScreen.Objects/ground.png"));
        ground.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        ground.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        coinTexture = new Texture(Gdx.files.internal("gfx/gameScreen.Objects/coin.png"));
        coinTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        ctr = new TextureRegion(coinTexture, 0, 0, 48, 48);

        pause = new Texture(Gdx.files.internal("gfx/gameScreen.Objects/pause.png"));
        pause.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        pauseTexture = new TextureRegion(pause, 0, 0, 64, 64);

        gameBackground = new Texture(Gdx.files.internal("gfx/gameScreen.Objects/background.png"));
        gameBackground.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        gbc = new TextureRegion(gameBackground, 0, 0, 1200, 676);

        textureBtnNormal = new Texture(Gdx.files.internal("gfx/buttons/button.png"));
        textureBtnNormal.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        btn = new TextureRegion(textureBtnNormal, 0, 0, 600, 200);
        //btn = new TextureRegion(textureBtnNormal, 0, 0, 128, 89);

        pinguin = new Texture(Gdx.files.internal("gfx/gameScreen.Objects/pinguin.png"));
        pinguin.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        pinguinTexture = new TextureRegion(pinguin, 0, 0, 73, 51);

        textureBtnPress = new Texture(Gdx.files.internal("gfx/buttons/buttonPress.png"));
        textureBtnPress.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        btnPress = new TextureRegion(textureBtnPress, 0, 0, 600, 200);

        protectiveLayerTexture = new Texture(Gdx.files.internal("gfx/buttons/protectField.png"));
        protectiveLayerTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        /*
        logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);

        texture = new Texture(Gdx.files.internal("data/texture.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);

        zbLogo = new TextureRegion(texture, 0, 55, 135, 24);
        zbLogo.flip(false, true);

        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        grass = new TextureRegion(texture, 0, 43, 143, 11);
        grass.flip(false, true);

        birdDown = new TextureRegion(texture, 136, 0, 17, 12);
        birdDown.flip(false, true);

        bird = new TextureRegion(texture, 153, 0, 17, 12);
        bird.flip(false, true);

        birdUp = new TextureRegion(texture, 170, 0, 17, 12);
        birdUp.flip(false, true);

        TextureRegion[] birds = { birdDown, bird, birdUp };
        birdAnimation = new Animation(0.06f, birds);
        birdAnimation.setPlayMode(Animation.LOOP_PINGPONG);

        skullUp = new TextureRegion(texture, 192, 0, 24, 14);
        skullDown = new TextureRegion(skullUp);
        skullDown.flip(false, true);

        bar = new TextureRegion(texture, 136, 16, 22, 3);
        bar.flip(false, true);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
        flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
        coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));

        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        

        prefs = Gdx.app.getPreferences("ZombieBird");

        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }
         */
    }

    public static void dispose() {
        textureBtnNormal.dispose();
    }

}
