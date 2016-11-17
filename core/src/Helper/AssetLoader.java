/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import Enums.BuffType;
import Enums.GiftType;
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
    public static Texture ground, prizeFieldFull, prizeFieldEmpty;
    public static TextureRegion gbc, ctr, pauseTexture, elephantTexture, giraffeTexture;
    public static TextureRegion pinguinTexture;
    public static TextureRegion btnPress, btn;
    public static Animation birdAnimation;
    public static Sound dead, flap, coin;
    public static BitmapFont font, shadow;
    public static Texture protectiveLayerTexture;

    public static Texture back, backPressed;
    public static TextureRegion backTexture, backPressedTexture;

    //Main screen
    public static Texture ticket, ticketPressed, settings, settingsPressed,
            statistic, statisticPressed, buff, buffPressed,
            coins, coinsPressed, skins, skinsPressed, play, playPressed,
            dailyGiftFull, dailyGiftEmpty;
    public static TextureRegion ticketTexture, ticketPressedTexture,
            settingsTexture, settingsPressedTexture,
            statisticTexture, statisticPressedTexture,
            buffTexture, buffPressedTexture,
            coinsTexture, coinsPressedTexture,
            skinsTexture, skinsPressedTexture,
            playTexture, playPressedTexture;

    //Maps screen
    public static Texture leftSlide, leftSlidePressed, rightSlide, rightSlidePressed;
    public static TextureRegion leftSlideTexture, leftSlidePressedTexture,
            rightSlideTexture, rightSlidePressedTexture;

    //Buff screen
    public static Texture textureBuffsArrow, textureBuffsClock, textureBuffsTime,
            jumpCountBuff, coinsBuff, directionCoffBuff, frictionBuff, gravityBuff,
            heightSkyCoffBuff, jumpPowerBuff, powerCoffBuff;
    public static TextureRegion buffsArrow, buffsClock;

    public static void load() {

        back = new Texture(Gdx.files.internal("gfx/buttons/back.png"));
        back.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        backTexture = new TextureRegion(back, 0, 0, 512, 512);
        backPressed = new Texture(Gdx.files.internal("gfx/buttons/backPressed.png"));
        backPressed.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        backPressedTexture = new TextureRegion(backPressed, 0, 0, 512, 512);

        loadMainScreenElements();
        loadMapsScreenElements();
        loadBuffScreenElements();

        prizeFieldFull = new Texture(Gdx.files.internal("gfx/gameScreen.Objects/ticketFull.png"));
        prizeFieldFull.setFilter(TextureFilter.Linear, TextureFilter.Linear);

        prizeFieldEmpty = new Texture(Gdx.files.internal("gfx/gameScreen.Objects/ticketEmpty.png"));
        prizeFieldEmpty.setFilter(TextureFilter.Linear, TextureFilter.Linear);

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

        gbc = new TextureRegion(gameBackground, 0, 0, 8000, 4500);

        textureBtnNormal = new Texture(Gdx.files.internal("gfx/buttons/button.png"));
        textureBtnNormal.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        //     btn = new TextureRegion(textureBtnNormal, 0, 0, 600, 200);
        btn = new TextureRegion(textureBtnNormal, 0, 0, 128, 89);

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

    private static void loadMapsScreenElements() {
        leftSlide = new Texture(Gdx.files.internal("gfx/buttons/leftSlide.png"));
        leftSlide.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        leftSlideTexture = new TextureRegion(leftSlide, 0, 0, 512, 512);
        leftSlidePressed = new Texture(Gdx.files.internal("gfx/buttons/leftSlidePressed.png"));
        leftSlidePressed.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        leftSlidePressedTexture = new TextureRegion(leftSlidePressed, 0, 0, 512, 512);

        rightSlide = new Texture(Gdx.files.internal("gfx/buttons/rightSlide.png"));
        rightSlide.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        rightSlideTexture = new TextureRegion(rightSlide, 0, 0, 512, 512);
        rightSlidePressed = new Texture(Gdx.files.internal("gfx/buttons/rightSlidePressed.png"));
        rightSlidePressed.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        rightSlidePressedTexture = new TextureRegion(rightSlidePressed, 0, 0, 512, 512);
    }

    private static void loadMainScreenElements() {
        ticket = new Texture(Gdx.files.internal("gfx/buttons/ticket.png"));
        ticket.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        ticketTexture = new TextureRegion(ticket, 0, 0, 512, 512);
        ticketPressed = new Texture(Gdx.files.internal("gfx/buttons/ticketPressed.png"));
        ticketPressed.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        ticketPressedTexture = new TextureRegion(ticketPressed, 0, 0, 512, 512);

        settings = new Texture(Gdx.files.internal("gfx/buttons/settings.png"));
        settings.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        settingsTexture = new TextureRegion(settings, 0, 0, 512, 512);
        settingsPressed = new Texture(Gdx.files.internal("gfx/buttons/settingsPressed.png"));
        settingsPressed.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        settingsPressedTexture = new TextureRegion(settingsPressed, 0, 0, 512, 512);

        statistic = new Texture(Gdx.files.internal("gfx/buttons/statistic.png"));
        statistic.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        statisticTexture = new TextureRegion(statistic, 0, 0, 512, 512);
        statisticPressed = new Texture(Gdx.files.internal("gfx/buttons/statisticPressed.png"));
        statisticPressed.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        statisticPressedTexture = new TextureRegion(statisticPressed, 0, 0, 512, 512);

        buff = new Texture(Gdx.files.internal("gfx/buttons/buff.png"));
        buff.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        buffTexture = new TextureRegion(buff, 0, 0, 512, 512);
        buffPressed = new Texture(Gdx.files.internal("gfx/buttons/buffPressed.png"));
        buffPressed.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        buffPressedTexture = new TextureRegion(buffPressed, 0, 0, 512, 512);

        coins = new Texture(Gdx.files.internal("gfx/buttons/coins.png"));
        coins.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        coinsTexture = new TextureRegion(coins, 0, 0, 512, 512);
        coinsPressed = new Texture(Gdx.files.internal("gfx/buttons/coinsPressed.png"));
        coinsPressed.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        coinsPressedTexture = new TextureRegion(coinsPressed, 0, 0, 512, 512);

        skins = new Texture(Gdx.files.internal("gfx/buttons/skins.png"));
        skins.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        skinsTexture = new TextureRegion(skins, 0, 0, 512, 512);
        skinsPressed = new Texture(Gdx.files.internal("gfx/buttons/skinsPressed.png"));
        skinsPressed.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        skinsPressedTexture = new TextureRegion(skinsPressed, 0, 0, 512, 512);

        play = new Texture(Gdx.files.internal("gfx/buttons/play.png"));
        play.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        playTexture = new TextureRegion(play, 0, 0, 512, 512);
        playPressed = new Texture(Gdx.files.internal("gfx/buttons/playPressed.png"));
        playPressed.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        playPressedTexture = new TextureRegion(playPressed, 0, 0, 512, 512);

        dailyGiftFull = new Texture(Gdx.files.internal("gfx/interface/dailyGiftFull.png"));
        dailyGiftFull.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        dailyGiftEmpty = new Texture(Gdx.files.internal("gfx/interface/dailyGiftEmpty.png"));
        dailyGiftEmpty.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    }

    private static void loadBuffScreenElements() {
        textureBuffsArrow = new Texture(Gdx.files.internal("gfx/interface/buffsArrow.png"));
        textureBuffsArrow.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        buffsArrow = new TextureRegion(textureBuffsArrow, 0, 0, 512, 512);

        textureBuffsClock = new Texture(Gdx.files.internal("gfx/interface/buffsClock.png"));
        textureBuffsClock.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        buffsClock = new TextureRegion(textureBuffsClock, 0, 0, 512, 512);

        textureBuffsTime = new Texture(Gdx.files.internal("gfx/interface/buffsTime.png"));
        textureBuffsTime.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        jumpCountBuff = new Texture(Gdx.files.internal("gfx/interface/buffs/jumpCountBuff.png"));
        jumpCountBuff.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        coinsBuff = new Texture(Gdx.files.internal("gfx/interface/buffs/coinsBuff.png"));
        coinsBuff.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        directionCoffBuff = new Texture(Gdx.files.internal("gfx/interface/buffs/directionCoffBuff.png"));
        directionCoffBuff.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        frictionBuff = new Texture(Gdx.files.internal("gfx/interface/buffs/frictionBuff.png"));
        frictionBuff.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        gravityBuff = new Texture(Gdx.files.internal("gfx/interface/buffs/gravityBuff.png"));
        gravityBuff.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        heightSkyCoffBuff = new Texture(Gdx.files.internal("gfx/interface/buffs/heightSkyCoffBuff.png"));
        heightSkyCoffBuff.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        jumpPowerBuff = new Texture(Gdx.files.internal("gfx/interface/buffs/jumpPowerBuff.png"));
        jumpPowerBuff.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        powerCoffBuff = new Texture(Gdx.files.internal("gfx/interface/buffs/powerCoffBuff.png"));
        powerCoffBuff.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
    }

    public static Texture loadTicketGift(GiftType type) {
        Texture gift;
        switch (type) {
            case COIN:
                gift = new Texture(Gdx.files.internal("gfx/interface/ticketGifts/coins.png"));
                gift.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
                return gift;
            case TICKET:
                gift = new Texture(Gdx.files.internal("gfx/interface/ticketGifts/ticket.png"));
                gift.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
                return gift;
            case BUFF:
                return null;
            default:
                return textureBtnNormal;
        }
    }

    public static Texture loadTicketGift(BuffType type) {
        Texture res;
        switch (type) {
            case JUMPCOUNT:
                res = new Texture(Gdx.files.internal("gfx/interface/Buffs/jumpCountBuff.png"));
                res.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
                return res;
            case JUMPPOWER:
                res = new Texture(Gdx.files.internal("gfx/interface/Buffs/jumpPowerBuff.png"));
                res.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
                return res;
            case DIRECTION_COFF:
                res = new Texture(Gdx.files.internal("gfx/interface/Buffs/directionCoffBuff.png"));
                res.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
                return res;
            case FRICTION:
                res = new Texture(Gdx.files.internal("gfx/interface/Buffs/frictionBuff.png"));
                res.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
                return res;
            case HEIGHT_SKY_COFF:
                res = new Texture(Gdx.files.internal("gfx/interface/Buffs/heightSkyCoffBuff.png"));
                res.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
                return res;
            case POWER_COFF:
                res = new Texture(Gdx.files.internal("gfx/interface/Buffs/powerCoffBuff.png"));
                res.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
                return res;
            case COINS:
                res = new Texture(Gdx.files.internal("gfx/interface/Buffs/coinsBuff.png"));
                res.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
                return res;
            case GRAVITY:
                res = new Texture(Gdx.files.internal("gfx/interface/Buffs/gravityBuff.png"));
                res.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
                return res;
            default:
                return null;
        }
    }

    public static void dispose() {
        textureBtnNormal.dispose();
    }

}
