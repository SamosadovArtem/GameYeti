/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author qw
 */
public class MyTimer {

    private Date finish;
    
    public MyTimer(Date now, long length){
        finish = new Date(now.getTime() + length * 1000l);
    }

    public MyTimer(long finishLength){
        finish = new Date(finishLength);
    }

    public TimeConverter getTimeLeft(){
        Date nowTime = getDateNow();
        long t = finish.getTime() - nowTime.getTime(); 
        return new TimeConverter(t);
    }
    
    public boolean getTimeStatus(){
        if((getDateNow().getTime() - finish.getTime()) > 0){
            return false;
        } else {
            return true;
        }
    }
    
    public Date getDateNow(){
        return new Date();
    }
    
    public Date getDateFinish(){
        return finish;
    }
}
