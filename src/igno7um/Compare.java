package igno7um;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Oscar on 11/14/16.
 */
public class Compare {




    public Boolean lastXmins(int mins, Time t){

        System.out.println("time passed: " + t.time2String());

        Boolean returnBool = false;


        Calendar date =  new GregorianCalendar();

        //add x mins to log time to compare later
        t.min = t.min+mins;
        if (t.min > 59){
            t.min = t.min%60;    // if t.min is 60 then t.min is 0 and hour++
            t.hour++;
            if (t.hour > 23){
                t.hour = 0;
                t.day++;
                if (t.day > date.getActualMaximum(Calendar.DAY_OF_MONTH)){
                    t.day = t.day % date.getActualMaximum(Calendar.DAY_OF_MONTH);
                    t.month++;
                    if (t.month > 12){
                        t.month = 1;
                        t.year++;
                    }
                }
            }
        }

        //if logTime + 10 mins
        System.out.println(t.time2String());


        if (t.year >= date.get(Calendar.YEAR)){
            System.out.println("t.month: " + t.month + " calendarmonth:" + date.get(Calendar.MONTH));
            if(t.month >= date.get(Calendar.MONTH)+1){
                if(t.day >= date.get(Calendar.DAY_OF_MONTH)){
                    if(t.hour == date.get(Calendar.HOUR_OF_DAY)){
                        if (t.min >= date.get(Calendar.MINUTE)){
                            returnBool = true;
                            System.out.println("email should be sent");
                        }
                    }
                    if (t.hour > date.get(Calendar.HOUR_OF_DAY)){
                        returnBool = true;
                        System.out.println("email should be sent");
                    }
                }
            }
        }
        System.out.print(returnBool);
        return returnBool;

    }
}
