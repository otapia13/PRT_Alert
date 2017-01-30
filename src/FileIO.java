import java.io.*;

/**
 * Created by vfc90279 on 1/25/2017.
 */
public class FileIO {

    private String filename = "default.txt";
    private String line = null;
    private FileReader fileRead;
    private BufferedReader buffRead;
    private FileWriter fileWriter;
    private BufferedWriter buffWrite;

    File File;

    FileIO(String s){
        filename = s;
        File = new File(s);
    }

    public String getFilename(){
        return filename;
    }

    // opens "filename.txt" if it does not exist it creates it; if "filename.txt" exists this will overwrite it.
    public void write(){
        try {
            fileWriter = new FileWriter(filename);
            buffWrite = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            System.out.println("ioexceipino on write");
        }

    }

    // verirfies "filename.txt" exists and opens file for reading; returns success status (T or F)
    public boolean read() {
        if(File.exists() && File.isFile()) {
            try {
                fileRead = new FileReader(filename);
                buffRead = new BufferedReader(fileRead);
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("file not found exception");
            }

        }
        return false;
    }

    public void writeLine(String s){
        try {
            buffWrite.write(s + "\n");
        } catch (IOException e) {
            System.out.println("io exception on writeline");
        }
    }

    // returns next line. returns null if EOF
    public String nextLine(){
        try {
            return buffRead.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    //closes your file if open.
    public void close(){

        if (buffWrite != null) {
            try {
                buffWrite.close();
            } catch (IOException e) {
                System.out.println("ioexpection on close write");
            }
        }


        if(buffRead != null) {
            try {
                buffRead.close();
            } catch (IOException e) {
                System.out.println("ioexpection on close read");
            }
        }
    }


}
