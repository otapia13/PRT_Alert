package igno7um; /**
 * Created by vfc90279 on 1/24/2017.
 */
import java.util.prefs.*;

public class PrefSaver {


    Preferences prefs = Preferences.userRoot().node(this.getClass().getName());

    //String Preference
    public void setPref(String prefName, String prefVal){
        prefs.put(prefName, prefVal);
    }
    //  Integer Preference
    public void setPref(String prefName, int prefVal){
        prefs.putInt(prefName, prefVal);
    }
    //  Boolean Preference
    public void setPref(String prefName, Boolean prefVal){
        prefs.putBoolean(prefName, prefVal);
    }
    // returns -999 by default in order to handle exception
    public String getPref(String prefName){
        return prefs.get(prefName, "-999");
    }

    //clear individual preference
    public void clearPref(String prefName){
        prefs.remove(prefName);
    }

    //clear all preferences
    public void clearAll(){
        try {
            prefs.clear();
        } catch (BackingStoreException e) {
            System.out.println("Exception on clear all prefs");
        }
    }
}
