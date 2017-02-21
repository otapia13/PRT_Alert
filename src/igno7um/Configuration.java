package igno7um;

/**
 * Created by vfc90279 on 1/25/2017.
 * Gets configurations from config.txt file
 *
 */

public class Configuration {

    Configuration(){
        getConfiguration();
        printAll();
        printSampleConfig();

    }

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
        public String[] name = new String[100];
        public String[] url = new String[100];
        public int length = 0;
    }
    class EITHREADS{
        public String[] name = new String[100];
        public String[] url = new String[100];
        public int length = 0;

    }
    class PRINTER{
        public String[] name = new String[100];
        public String[] url = new String[100];   //ip
        public int length = 0;
    }
    class MODELCHANGE{
        public String[] name = new String[100];
        public String[] url = new String[100];
        public int length = 0;

    }

    PRINTER printer = new PRINTER();
    MODELCHANGE modelChange = new MODELCHANGE();
    IMMOBI immobi = new IMMOBI();
    EITHREADS ei = new EITHREADS();

    FileIO file = new FileIO("Config.txt");

    private String extractName(String s){

        return s.substring(0, s.indexOf("|"));
    }
    private String extractURL(String s){
        return s.substring(s.indexOf("|")+1);
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
            System.out.println("error");
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


    public void printAll(){
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
    }

}
