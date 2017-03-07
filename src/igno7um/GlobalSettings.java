package igno7um;

/**
 * Created by Oscar on 2/28/17.
 */

//Global settings used to access weather IM,EI,PMK, or MC should be runnning
public class GlobalSettings {


    private static boolean runImmobi;
    private static boolean runEI;
    private static boolean runPrinter;
    private static boolean runModel;

    public void ImmobiSettings(boolean b){
        runImmobi = b;
    }

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

    public boolean getEISettings(){
        return runEI;
    }

    public boolean getPrinterSettings(){
        return runPrinter;
    }

    public boolean getModelSettings(){
        return runModel;
    }
}
