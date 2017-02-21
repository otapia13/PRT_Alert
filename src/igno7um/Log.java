package igno7um; /**
 * Created by Oscar on 1/15/17.
 */

import java.io.IOException;
import java.util.logging.*;


public class Log {

    private final static Logger logger = Logger.getLogger(Logger.class.getName());
    private static FileHandler fh = null;
    private static String title = "myLog.log";

    Log(String s){
        title = s;
        this.init();
    }


    public static void init(){
        try {
            fh=new FileHandler(title, false);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        Logger l = Logger.getLogger("");
        fh.setFormatter(new SimpleFormatter());
        l.addHandler(fh);
        l.setLevel(Level.CONFIG);
    }


    // pass message to write. level from 0 - info, 1 - warning, 2 - severe
    public void write(String msg){
        logger.log(Level.INFO, msg);
    }
}