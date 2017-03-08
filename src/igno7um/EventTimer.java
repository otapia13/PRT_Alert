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
    private int imInterval = 10;    //default 10 mins in case settings file is not found
    private int eiInterval = 10;
    private int pmkInterval = 10;
    private int mcInterval = 10;
    private int ex1Interval = 10;    //additional feature in case it's needed
    private int ex2Interval = 10;    //additional feature 2
    private int ex3Interval = 10;    //additional feature 3

    public void setIMinterval(int i){
        imInterval = i;
    }
    public void setEIinterval(int i){
        eiInterval = i;
    }
    public void setPMKinterval(int i){
        pmkInterval = i;
    }
    public void setMCinterval(int i){
        mcInterval = i;
    }
    public void setEX1interval(int i){
        ex1Interval = i;
    }
    public void setEX2interval(int i){
        ex2Interval = i;
    }
    public void setEX3interval(int i){
        ex3Interval = i;
    }

    public long getEventTimer(){
        return msTime;
    }


    Configuration config = new Configuration();
    GlobalSettings globalSettings = new GlobalSettings();

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
                System.out.println("this is running" + globalSettings.getImmobiInterval());
                if (x%globalSettings.getImmobiInterval() == 0){
                    config.runServices(1);
                    System.out.println("this ran" + x);
                }
                if (x%eiInterval == 0){
                    //runEI
                }
                if (x%pmkInterval == 0){
                    //run PMK
                }
                if (x%mcInterval == 0){
                    //run MC
                }
                if (x%ex1Interval == 0){
                    //run EX1
                }
                if (x%ex2Interval == 0){
                    //run EX2
                }
                if (x%ex3Interval == 0){
                    //run EX3
                }

                //reset x to avoid int overflow (10 years)
                if(x > 5256000){
                    x = 0;
                }

            }

        }, 0, msTime);
    }


}
