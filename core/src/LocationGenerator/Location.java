/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LocationGenerator;

import Helper.Constants;
import com.badlogic.gdx.Gdx;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Admin
 */
public final class Location {
  
    static Random rnd = new Random();
    static ArrayList<Barrier> barriers;
    static int pushCounter = 0;
    static int stopCounter = 0;
    private static final int typeCount = BarrierTypes.values().length;
    static int[] typeCounter = new int[typeCount];
    
    public static ArrayList<Barrier> GetBarrierList(int startPosition, int barriersCount, boolean course){
        barriers = new ArrayList<Barrier>();
        if (startPosition<0){
            throw new IllegalArgumentException("Start Position must be > 0");
        }
        int previousLocation = startPosition;
        int location;
        Barrier tempBarrier;
        
        for (int i = 0;i<barriersCount;i++){
            location = GetRandomLocation()+previousLocation;
            previousLocation = location;
            BarrierTypes type = GetRandomType();
            if (course){
            tempBarrier = new Barrier(location,0, type);
            }
            else
            {
               tempBarrier = new Barrier(location*(-1),0, type); 
            }
            barriers.add(tempBarrier);
        }
        barriers = AddCoinsToList(barriers);
        return barriers;
    }
    private static int GetRandomLocation(){
        return Constants.MIN_STEP + (int)(Math.random() * ((Constants.MAX_STEP - Constants.MIN_STEP) + 1));
    }
    private static ArrayList<Barrier> AddCoinsToList(ArrayList<Barrier> allBarriers){
        int distance;
        //ArrayList<Barrier> coinsList = new ArrayList<Barrier>();
        int listSize = allBarriers.size();
        for(int i = 0; i < listSize-1;i++){
            distance = allBarriers.get(i+1).GetX() - allBarriers.get(i).GetX();
            if ((distance>(Constants.MAX_STEP - Constants.MIN_STEP)/2)&&
                    (allBarriers.get(i).GetType()!=BarrierTypes.COIN)&&
                    (allBarriers.get(i+1).GetType()!=BarrierTypes.COIN)&&
                    (isGenarateCoin())){
             Barrier coin = new Barrier(allBarriers.get(i).GetX()+distance/2, 
                     GetRandomY(), BarrierTypes.COIN);
             allBarriers.add(coin);
            }
        }
        //allBarriers.addAll(coinsList);
        
        return allBarriers;
    }
        private static int GetRandomY(){
            /*
           float grY = Constants.GROUND_Y+Constants.GROUND_HEIGHT/2;
           int yCoordinate = rnd.nextInt((int) (Constants.APP_HEIGHT - 
                   Constants.GROUND_Y+Constants.GROUND_HEIGHT)) + (int)grY;
           if (yCoordinate<Constants.GROUND_Y){throw new IndexOutOfBoundsException("no");}
           */
           float grY = Constants.GROUND_Y+Constants.GROUND_HEIGHT/8;
           int yCoordinate = rnd.nextInt((int) (Constants.APP_HEIGHT - 
                   (Constants.GROUND_Y+Constants.GROUND_HEIGHT)))+(int)grY;
            
           
            Float a = Constants.GROUND_Y;//120
            
            Float b = Constants.GROUND_HEIGHT;//60
            
            int c = Constants.APP_HEIGHT;//480
            
           return yCoordinate;
        }
        private static BarrierTypes GetRandomType(){
        //int randomValue = rnd.nextInt(2);
        int randomValue = rnd.nextInt(typeCount);
        
        //не монетка
        
        
        if (typeCounter[randomValue]==2){
            typeCounter[randomValue]=0;
            int oldRandom = randomValue;
            while (randomValue==oldRandom){  //Если 2 раза подряд появилось, то нельзя повторять
                randomValue = rnd.nextInt(typeCount);
            }
            
            if (BarrierTypes.values()[randomValue]!=BarrierTypes.COIN){
            
                typeCounter[randomValue]++;
            
                return BarrierTypes.values()[randomValue];
            }else{
                
                typeCounter[1]++; //Если монетка, то лучше стоп добавим
                
                return BarrierTypes.STOP; // И вернем
                
            }
            
        }
        
        
        if (BarrierTypes.values()[randomValue]!=BarrierTypes.COIN){
            typeCounter[randomValue]++;
            return BarrierTypes.values()[randomValue];
        } else{
            typeCounter[0]++; //А тут - если не монетка, то пусть змейка
            
            return BarrierTypes.SNAKE;
        }
        
 
        
//        if (pushCounter == 2) {
//            pushCounter=0;
//            stopCounter++;
//            return BarrierTypes.STOP;
//        }
//        if (stopCounter == 2) {
//            stopCounter=0;
//            pushCounter++;
//            return BarrierTypes.PUSH;
//        }
//        switch (randomValue){
//            case 0:
//                pushCounter++;
//                return BarrierTypes.PUSH;
//                //break;
//            case 1:
//                stopCounter++;
//                return BarrierTypes.STOP;
//                //break;
//        }
//        throw new IllegalStateException("Somthing wrong");
    }
        private static boolean isGenarateCoin(){
            int random = rnd.nextInt(2);
            if (random==1){
                return true;
            }
            return false;
        }

}
