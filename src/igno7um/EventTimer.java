package igno7um;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by vfc90279 on 1/18/2017.
 */
public class EventTimer {
    private Timer timer;
    private static int counter;
    private long msTime = 60000;    //run every minute by default
    private int timeInterval;

    public void setEventTimer(long t){
        msTime = t;
    }

    public long getEventTimer(){
        return msTime;
    }

    //Constructors can be called by including interval parameter and how the rate at which the timer repeats
    EventTimer(int i){
        timeInterval = i;
    }
    EventTimer(long t, int i){
        msTime = t;
        timeInterval = i;
    }




    private int x = 0;
    public void start(){
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                activator();
            }

            public void activator(){
                x++;
                if (x > timeInterval){
                    x = 0;
                }

            }

        }, 0, msTime);
    }


}
