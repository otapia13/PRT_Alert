package igno7um;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by vfc90279 on 1/25/2017.
 * Gets configurations from config.txt file
 *
 */

public class Configuration {
    Configuration(){
        getConfiguration();
        //printAll();
        //printSampleConfig();
    }

    private Log log = new Log();

    public void printSampleConfig(){
        FileIO sampleConfig = new FileIO("sampleConfig."+this.getClass().getName()+".txt");
        sampleConfig.write();
        sampleConfig.writeLine("!This is a comment");
        sampleConfig.writeLine("!"+ this.getClass().getName());
        sampleConfig.writeLine("#IM");
        sampleConfig.writeLine("IMMOBI1|url.com");
        sampleConfig.writeLine("#END_IM");
        sampleConfig.writeLine("#EI");
        sampleConfig.writeLine("#END_EI");
        sampleConfig.writeLine("#PMK");
        sampleConfig.writeLine("#END_PMK");
        sampleConfig.writeLine("#MC");
        sampleConfig.writeLine("#END_MC");
        sampleConfig.close();

    }

    class IMMOBI{
        String[] name = new String[100];
        String[] url = new String[100];
        int length = 0;
        int interval = 3;
    }
    class EITHREADS{
        String[] name = new String[100];
        String[] url = new String[100];
        int length = 0;
        int interval = 10;
    }
    class PRINTER{
        String[] name = new String[100];
        String[] url = new String[100];   //ip
        int length = 0;
        int interval = 10;
    }
    class MODELCHANGE{
        String[] name = new String[100];
        String[] url = new String[100];
        int length = 0;
        int interval = 10;
    }

    PRINTER printer = new PRINTER();
    MODELCHANGE modelChange = new MODELCHANGE();
    IMMOBI immobi = new IMMOBI();
    EITHREADS ei = new EITHREADS();

    private FileIO file = new FileIO("Config.txt");

    private String extractName(String s){

        return s.substring(0, s.indexOf("|"));
    }
    private String extractURL(String s){
        return s.substring(s.indexOf("|")+1);
    }

    private void saveConfigs(){

    }

    private String[][] extractConfigs(String configName){
        String[][] strArray = new String[2][100];
        String start = "#" + configName;
        String end = "#END_" + configName;
        String nextLine;
        if((nextLine = file.nextLine())!= null) {
            int x = 0;
            while (!nextLine.contains(start)) {
                nextLine = file.nextLine();
            }
            String intevalName = extractName(nextLine);
            int intervalTime = Integer.parseInt(extractURL(nextLine));
            System.out.println(intevalName + " " + intervalTime);


            switch(intevalName){
                case "#IM":  {
                    immobi.interval = intervalTime;
                    globalSettings.setImmobiInterval(intervalTime);
                    System.out.println("this ran");
                    break;}
                case "#EI":  ei.interval = intervalTime;
                    break;
                case "#PMK": printer.interval = intervalTime;
                    break;
                case "#MC":  modelChange.interval = intervalTime;
            }

            nextLine = file.nextLine();
            while (!nextLine.contains(end)) {
                strArray[0][x] = extractName(nextLine);
                strArray[1][x] = extractURL(nextLine);
                nextLine = file.nextLine();
                x++;

            }
        }

        return strArray;

    }

    private void getConfiguration(){

        if (file.read()){
            String[][] myArray = null;
            if((myArray = extractConfigs("IM"))!=null){
                for(int i = 0; myArray[0][i] != null; ++i){
                    immobi.name[i] = myArray[0][i];
                    immobi.url[i] = myArray[1][i];
                    immobi.length = i+1;
                    System.out.println(immobi.interval);
                }

            }

            if((myArray = extractConfigs("EI"))!=null){
                for(int i = 0; myArray[0][i] != null; ++i){
                    ei.name[i] = myArray[0][i];
                    ei.url[i] = myArray[1][i];
                    ei.length = i+1;
                }
            }

            if((myArray = extractConfigs("PMK"))!=null){
                for(int i = 0; myArray[0][i] != null; ++i){
                    printer.name[i] = myArray[0][i];
                    printer.url[i] = myArray[1][i];
                    printer.length = i+1;
                }
            }

            if((myArray = extractConfigs("MC"))!=null){
                for(int i = 0; myArray[0][i] != null; ++i){
                    modelChange.name[i] = myArray[0][i];
                    modelChange.url[i] = myArray[1][i];
                    modelChange.length = i+1;
                }
            }
            file.close();
        }else
            System.out.println("Error Retrieving Configurations. File Does Not Exist or May be Corrupted");
    }

    // adds configuration to file
    public boolean addConfiguration(String id, String name, String url){
        boolean success = false;
        FileIO tempFile = new FileIO("temp.txt");
        tempFile.write();
        if(file.read()){
            String nextLine;
            while((nextLine = file.nextLine())!= null){
                tempFile.writeLine(nextLine);
                if(nextLine.contains(id)){
                    success = true;
                    tempFile.writeLine(name + "|" + url);
                }
            }
            file.close();
        }else
            System.out.print("****************************");

        tempFile.close();
        file.File.delete();
        tempFile.File.renameTo(file.File);

        return success;

    }

    public boolean deleteConfiguration(String id, String name, String url){
        boolean success = false;
        FileIO tempFile = new FileIO("temp.txt");
        tempFile.write();
        if(file.read()){
            String nextLine;
            while((nextLine = file.nextLine())!= null){
                if(nextLine.contains(name) && nextLine.contains(url)){
                    success = true;
                    continue;
                }
                tempFile.writeLine(nextLine);

            }
            file.close();
        }else
            System.out.print("****************************");

        tempFile.close();
        file.File.delete();
        tempFile.File.renameTo(file.File);

        return success;
    }


    /*public void printAll(){
        for(int i = 0; immobi.name[i] != null; i++){
            System.out.println(immobi.name[i]);
        }

        for(int i = 0; immobi.url[i] != null; i++){
            System.out.println(immobi.url[i]);
        }

        for(int i = 0; ei.name[i] != null; i++){
            System.out.println(ei.name[i]);
        }

        for(int i = 0; ei.url[i] != null; i++){
            System.out.println(ei.url[i]);
        }

        for(int i = 0; printer.name[i] != null; i++){
            System.out.println(printer.name[i]);
        }

        for(int i = 0; printer.url[i] != null; i++){
            System.out.println(printer.url[i]);
        }

        for(int i = 0; modelChange.name[i] != null; i++){
            System.out.println(modelChange.name[i]);
        }

        for(int i = 0; modelChange.url[i] != null; i++){
            System.out.println(modelChange.url[i]);
        }
    }*/

    private GlobalSettings globalSettings = new GlobalSettings();
    /*public void runServices(){
        if(globalSettings.getImmobiSettings())
            runImmobi();
    }*/

    void runServices(int i){
        if(i == 1 && globalSettings.getImmobiSettings()){
            runImmobi();
        }

    }

    private void runImmobi(){
        log.write("IMMOBI invoked");
        for(int x = 0; x < immobi.length; x++){
            globalSettings.appendConsoleString(immobi.name[x] + " running...");
            log.write(immobi.name[x] + " running...");

            URLGrep urlGrep = new URLGrep(immobi.url[x]);

            String errorString = null;
            String tempString;
            boolean errorFound = false;

            while((tempString = urlGrep.findNext("Creating")) != null){
                errorFound = true;
                errorString = tempString;
            }

            if(errorFound){
                globalSettings.appendConsoleString("Error Found: \n" + errorString);
                log.write("Error Found: \n" + errorString);
                String logTime = errorString.substring(0,errorString.indexOf(": "));    // extract error time as a string
                Time time = new Time();
                time.stringToTime(logTime);
                Compare compare = new Compare();
                if (compare.lastXmins(globalSettings.getImmobiInterval(), time)){
                    globalSettings.appendConsoleString("Requesting to send Alert...");
                    Alert alert = new Alert();
                    System.out.println("LogsAnalixer: request to send alert");
                    int alertStatus = alert.sendEmail("IMMOBI Error " + immobi.name[x], errorString);
                    if(alertStatus == 1){
                        globalSettings.appendConsoleString("Alert Sent");
                    }else{
                        globalSettings.appendConsoleString("Alert Failed to send: Code " + alertStatus);
                    }

                }else{
                    globalSettings.appendConsoleString("Error Found is Old... Alert Not Sent");
                }
            }else{
                globalSettings.appendConsoleString("No Error Found... " + immobi.name[x] + " done running...\n");
            }


        }
    }
}
