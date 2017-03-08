package igno7um;

import javafx.application.Platform;

/**
 * Created by Oscar on 2/28/17.
 */

//Global settings used to access weather IM,EI,PMK, or MC should be runnning
public class GlobalSettings {

    private static boolean runImmobi;
    private static boolean runEI;
    private static boolean runPrinter;
    private static boolean runModel;

    private static String globalConsoleStr = "Console Running...";

    private static int immobiInterval = 10;

    public void ImmobiSettings(boolean b){
        runImmobi = b;
    }

    public void setImmobiInterval(int i){ immobiInterval = i; }

    public void EISettings(boolean b){
        runEI = b;
    }

    public void PrinterSettings(boolean b){
        runPrinter = b;
    }

    public void ModelSettings(boolean b){
        runModel = b;
    }

    public boolean getImmobiSettings(){
        return runImmobi;
    }

    public int getImmobiInterval(){
        return immobiInterval;
    }

    public boolean getEISettings(){
        return runEI;
    }

    public boolean getPrinterSettings(){
        return runPrinter;
    }

    public boolean getModelSettings(){
        return runModel;
    }

    public void setConsoleString(String s){
        globalConsoleStr = s;
    }

    public void appendConsoleString(String s) {
        globalConsoleStr = globalConsoleStr + "\n" + s;
    }

    public String getGlobalConsoleStr(){
        return globalConsoleStr;
    }

}
