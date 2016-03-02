/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LocationGenerator;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Admin
 */
public final class Location {
    static final int MIN_STEP = 5;
    static final int MAX_STEP = 45;
    static Random rnd = new Random();
    static ArrayList<Barrier> barriers = new ArrayList<>();
    static int pushCounter = 0;
    static int stopCounter = 0;
    
    public static ArrayList<Barrier> GetBarrierList(int startPosition, int barriersCount, boolean course){
        if ((startPosition<0)&&(course==true)){
            throw new IllegalArgumentException("Start Position must be > 0 if course is true");
        }
        if ((startPosition>0)&&(course==false)){
            throw new IllegalArgumentException("Start Position must be < 0 if course is false");
        }
        int previousLocation = startPosition;
        int location =0;
        Barrier tempBarrier;
        
        for (int i = 0;i<barriersCount;i++){
            if (course){
            location = GetRandomLocation()+previousLocation;
            previousLocation = location;
            }
            else{
            int temp = GetRandomLocation()*(-1);
            location = temp+previousLocation;
            previousLocation = location;
            }
            BarrierTypes type = GetRandomType();
               tempBarrier = new Barrier(location, type); 
            barriers.add(tempBarrier);
        }
        return barriers;
    }
    private static int GetRandomLocation(){
        return MIN_STEP + (int)(Math.random() * ((MAX_STEP - MIN_STEP) + 1));
    }
        private static BarrierTypes GetRandomType(){
        int randomValue = rnd.nextInt(2);
        if (pushCounter == 2) {
            pushCounter=0;
            stopCounter++;
            return BarrierTypes.Stop;
        }
        if (stopCounter == 2) {
            stopCounter=0;
            pushCounter++;
            return BarrierTypes.Push;
        }
        switch (randomValue){
            case 0:
                pushCounter++;
                return BarrierTypes.Push;
                //break;
            case 1:
                stopCounter++;
                return BarrierTypes.Stop;
                //break;
        }
        throw new IllegalStateException("Somthing wrong");
    }

}
