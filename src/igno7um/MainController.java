package igno7um;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class MainController implements Initializable{

    @FXML
    Label eiStatus, immobiStatus, serviceStatus, modelStatus, printerStatus, consoleLabel;
    @FXML
    CheckMenuItem immobiMenuItem, eiMenuItem, modelChangeMenuItem, printerMenuItem;
    @FXML
    MenuItem start, stop, close, emailSettings, immobiSettings, eiSettings, printerSettings,
    modelChangeSettings, showConsole, resize;
    @FXML
    public TextArea console;
    @FXML
    VBox consoleVBox;
    @FXML
    GridPane statsGridPane;

    //Scene stage;

    private PrefSaver prefSaver = new PrefSaver();
    GlobalSettings globalSettings = new GlobalSettings();


    private void resize(int w, int h) {
        Stage stage = (Stage)console.getScene().getWindow();

        stage.setHeight(h);
        stage.setWidth(w);
    }
    private void setMins(int w, int h){
        Stage stage = (Stage)console.getScene().getWindow();
        stage.setMinWidth(w);
        stage.setMinHeight(h);
    }

    private void setMaxs(int w, int h){
        Stage stage = (Stage)console.getScene().getWindow();
        stage.setMaxHeight(h);
        stage.setMaxWidth(w);
    }

    public void showConsole(){
        //disable console
        if (consoleVBox.isVisible()){
            consoleVBox.setVisible(false);
            consoleVBox.setMaxSize(0,0);
            this.setMaxs(600,400);
            this.setMins(400, 300);
            statsGridPane.setMaxHeight(10000);
            showConsole.setText("Show Console");
        }
        else{// enable console
            consoleVBox.setVisible(true);
            consoleVBox.setMaxSize(10000,10000);
            this.setMins(600, 400);
            this.setMaxs(1000,1000);
            statsGridPane.setMaxHeight(100);
            showConsole.setText("Hide Console");
        }
    }

    public void close(){
        prefSaver.setPref("Immobi", immobiMenuItem.isSelected());
        prefSaver.setPref("EI", eiMenuItem.isSelected());
        prefSaver.setPref("Printer", printerMenuItem.isSelected());
        prefSaver.setPref("MC", modelChangeMenuItem.isSelected());

        prefSaver.setPref("ImmobiStatus", immobiStatus.getText());
        prefSaver.setPref("EIStatus", eiStatus.getText());
        prefSaver.setPref("PrinterStatus", printerStatus.getText());
        prefSaver.setPref("ModelStatus", modelStatus.getText());

        prefSaver.setPref("ShowConsole", consoleVBox.isVisible());
        prefSaver.setPref("ShowConsoleTXT", showConsole.getText());

        Platform.exit();
        System.exit(0);
    }

    public void runImmobi(){
        if(immobiMenuItem.isSelected()) {
            immobiStatus.setText("Running");
            globalSettings.ImmobiSettings(true);
        }
        else {
            immobiStatus.setText("Not Running");
            globalSettings.ImmobiSettings(false);

        }

    }

    public void runPrinter(){
        if(printerMenuItem.isSelected()) {
            printerStatus.setText("Running");
            globalSettings.PrinterSettings(true);
        }
        else {
            printerStatus.setText("Not Running");
            globalSettings.PrinterSettings(false);

        }
    }

    public void runEI(){
        if(eiMenuItem.isSelected()) {
            eiStatus.setText("Running");
            globalSettings.EISettings(true);
        }
        else {
            eiStatus.setText("Not Running");
            globalSettings.EISettings(false);

        }
    }
    public void runModelChange(){
        if(modelChangeMenuItem.isSelected()) {
            modelStatus.setText("Running");
            globalSettings.ModelSettings(true);
        }
        else {
            modelStatus.setText("Not Running");
            globalSettings.ModelSettings(false);
        }
    }

    private void updateConsole(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(consoleVBox.isVisible()){
                    console.setText(globalSettings.getGlobalConsoleStr());
                }
            }
        }, 0, 500);
    }

    public void emailSettings(){
        settings("Email Settings");
    }

    public void immobiSettings(){
        settings("Immobi Settings");
    }

    public void eiSettings(){
        settings("EI Threads Settings");
    }

    public void printerSettings(){
        settings("Printer Settings");
    }

    public void modelChangeSettings(){
        settings("Model Change Settings");
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

    public void runService(){
        //EventTimer c = new EventTimer();
        //c.start();
        console.setText("testing");
        EventTimer et = new EventTimer();
        et.start();
    }


    public void settings(String title){

        Stage settingsWindow = new Stage();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Settings.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        settingsWindow.setTitle(title);
        settingsWindow.setScene(new Scene(root));
        settingsWindow.show();
        settingsWindow.setOnCloseRequest(event -> {

            settingsWindow.close();
            });
        }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //this.showConsole();
        immobiMenuItem.setSelected(Boolean.parseBoolean(prefSaver.getPref("Immobi")));
        eiMenuItem.setSelected(Boolean.parseBoolean(prefSaver.getPref("EI")));
        printerMenuItem.setSelected(Boolean.parseBoolean(prefSaver.getPref("Printer")));
        modelChangeMenuItem.setSelected(Boolean.parseBoolean(prefSaver.getPref("MC")));

        immobiStatus.setText(prefSaver.getPref("ImmobiStatus"));
        eiStatus.setText(prefSaver.getPref("EIStatus"));
        printerStatus.setText(prefSaver.getPref("PrinterStatus"));
        modelStatus.setText(prefSaver.getPref("ModelStatus"));

        consoleVBox.setVisible(Boolean.parseBoolean(prefSaver.getPref("ShowConsole")));
        showConsole.setText(prefSaver.getPref("ShowConsoleTXT"));

        //Initialize your global settings to avoid exceptions
        globalSettings.ImmobiSettings(immobiMenuItem.isSelected());
        globalSettings.EISettings(eiMenuItem.isSelected());
        globalSettings.PrinterSettings(printerMenuItem.isSelected());
        globalSettings.ModelSettings(modelChangeMenuItem.isSelected());

        updateConsole();



        //get saved states and update them.
    }
}