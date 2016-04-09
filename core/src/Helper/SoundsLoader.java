/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public final class SoundsLoader {
   
    private static Music coin;
    private static Music back;
    
    private static ArrayList<Music> sounds = new ArrayList<Music>();
    private static ArrayList<Music> music = new ArrayList<Music>();
    
    public static void LoadGameSounds(){
        coin = Gdx.audio.newMusic(Gdx.files.internal("gfx/sounds/coin.mp3")); 
        
        sounds.add(coin);
        
        updateMusic();
        updateSound();
    }
    
    public static void LoadBasicSounds(){
        
        if (back==null){
        back = Gdx.audio.newMusic(Gdx.files.internal("gfx/sounds/back.mp3"));
        
        
        music.add(back);
        
        updateMusic();
        //updateSound();
        }
    }
    public static void DisposedGameSounds(){
        if (coin!= null){
            coin.dispose();
        } else{
            throw new IllegalArgumentException("Coin sound is not load");
        }
    }
    public static void PlayCoinSound(){
        if (coin!=null){
            if (coin.isPlaying()){
                coin.stop();
            }
        coin.play();
        } else{
            throw new IllegalArgumentException("Coin sound is not load");
        }
    }
    
    public static void PlayBackSound(){
               if (back!=null){
                   
                   System.out.println("Играет ли - "+back.isPlaying());
                   
            if (!back.isPlaying()){
                back.setLooping(true);
                back.play();
            }
        
        } else{
            throw new IllegalArgumentException("Back sound is not load");
        } 
    }
    
    public static void DisposedBasicSounds(){
            if (back!= null){
            back.dispose();
        } else{
            throw new IllegalArgumentException("Back sound is not load");
        }
    }
    
    public static void updateMusic(){
        
        float value = ((float)Statistic.getMusicLevel())/100;
        
        for(Music m:music){
            if (m!=null){
                m.setVolume(value);
            }
        }
    }
    
        public static void updateSound(){
        
        float value = ((float)Statistic.getSoundLevel())/100;
        
        for(Music s:sounds){
            if (s!=null){
                s.setVolume(value);
            }
        }
    }
    
}
