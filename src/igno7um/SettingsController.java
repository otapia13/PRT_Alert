package igno7um;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Oscar on 1/27/17.
 */
public class SettingsController implements Initializable {

    @FXML
    TableView settingsTableView;
    @FXML
    TableColumn nameCol, urlCol;
    @FXML
    Button closeButton, insertButton, deleteButton;
    @FXML
    ChoiceBox intervalChoiceBox;

    private Configuration configs = new Configuration();
    private PrefSaver prefSaver = new PrefSaver();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        intervalChoiceBox.setItems(FXCollections.observableArrayList(1,3,5,10,20,30,60));
        nameCol = new TableColumn("Name");
        urlCol = new TableColumn("URL");
        settingsTableView.getColumns().addAll(nameCol, urlCol);
        settingsTableView.setEditable(true);
        ObservableList<Configuration.IMMOBI> data = (ObservableList<Configuration.IMMOBI>) configs.immobi;
        settingsTableView.setItems(data);
        nameCol.setCellValueFactory(new PropertyValueFactory<Configuration.IMMOBI, String[]>("name"));


    }



    public int getInterval(){
        return 1;

    }

    public void setInterval(){

    }

    public void add(){

    }

    public void remove(){

    }

    public void saveSettings(){

    }

    public void getSettings(){

    }
}
