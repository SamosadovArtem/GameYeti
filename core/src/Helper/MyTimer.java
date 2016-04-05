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
    
    private Date start;
    private Date finish;
    
    public MyTimer(long length){
        start = new Date();
        finish = new Date(start.getTime() + length * 1000l);
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
    
    public Date getDateStart(){
        return start;
    }
    
    public Date getDateFinish(){
        return finish;
    }
}
