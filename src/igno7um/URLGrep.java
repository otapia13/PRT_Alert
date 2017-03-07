package igno7um;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Oscar on 1/15/17.
 */
public class URLGrep {



    private Scanner scanner;
    private String searchString;

    URLGrep(String url) {
        URL u = null;
        try {
            u = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            scanner = new Scanner(u.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //returns line containing string "str"
    public String findNext(String str) {
        setSearchString(str);
        String currentLine;
        while(!this.eof()) {
            currentLine = scanner.nextLine();
            if(currentLine.contains(str)){
                return currentLine;
            }

        }
        return null;
    }

    //returns the extracted string between specified characters.
    //also it skips to next line if "from" and "to" strings do not exist in current line
    public String extract(String string, String from, String to) throws IOException {

        int offset = from.length();
        //skip to next line if "from" and "to" strings do not exist in current line
        while(!(string.contains(from) && string.contains(to))) {
            string = this.findNext(getSearchString());
        }
        if(string.contains(from) && string.contains(to))
            return string.substring(string.indexOf(from) + offset,string.indexOf(to));
        else
            return "Error: Not Found";
    }
    //returns extracted string after specified character
    //also it skips to next line if "from" string does not exist in current line
    public String extract(String string, String from) throws IOException {
        int offset = from.length();
        //skip to next line if "from" and "to" strings do not exist in current line
        while(!string.contains(from)) {
            string = this.findNext(getSearchString());
        }
        if(string.contains(from))
            return string.substring(string.indexOf(from) + offset);
        else
            return "Error: Not Found";
    }

    private void setSearchString(String s){
        searchString = s;
    }

    private String getSearchString(){
        return searchString;
    }

    public boolean eof(){
        if (scanner.hasNextLine())
            return false;
        else
            return true;
    }
}
