package igno7um;

/**
 * Created by vfc90279 on 11/14/2016.
 */
public class Time {

    public int hour;
    public int min;
    public int day;
    public int month;
    public int year;
    private Boolean pm;



    private String timeString;


    // assuming string is in form: "11/11/2016 6:11:30 AM"  MM/DD/YYYY H:MM:SS AM
    // it extracts the hour, min, day, month, and year.
    public void stringToTime(String s){
        timeString = s;
        month = extractNext(timeString, '/');
        day = extractNext(timeString, '/');
        year = extractNext(timeString, ' ');
        hour = extractNext(timeString, ':');
        min = extractNext(timeString, ':');
        if(isPM(timeString)){
            if(hour == 12){hour = 0;}
            hour = hour + 12;
            System.out.println("hour is "+ hour);
        }

        /*
        System.out.println(month);
        System.out.println(day);
        System.out.println(year);
        System.out.println(hour);
        System.out.println(min);
        System.out.println(pm);
        */

    }

    public int extractNext(String s, char c){

        int i = 0;
        String temp =  s.substring(i, s.indexOf(c));
        i = s.indexOf(c);
        timeString = s.substring(i+1,s.length());

        return Integer.parseInt(temp);

    }

    public Boolean isPM(String s){
        if (s.contains("PM"))
            return true;
        else{
            return false;
        }
    }

    public String time2String(){
        String temp = month + "/" + day + "/" + year + " " + hour + ":" + min + "...";
        return temp;
    }



}
