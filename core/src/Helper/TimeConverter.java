/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

/**
 *
 * @author qw
 */
public class TimeConverter {
    
    private long time;
    
    public TimeConverter(long time){
        this.time = time;
    }
    
    public boolean getStatus(){
        if(time == 0){
            return false;
        } else {
            return true;
        }
    }
    
    public void removeTime(long second){
        time -= second * 1000l;
        if(time < 0){
           time = 0;
        }
    }
    
    public String getTime(){
        int seconds = (int)(time / 1000l);
        int minutes = (int)(seconds / 60);
        int hours = (int)(minutes / 60);
        int days = (int)(hours / 24);
        
        seconds -= minutes * 60;
        minutes -= hours * 60;
        hours -= days * 24;
        return days+":"+hours+":"+minutes+":"+seconds;
    }
}
