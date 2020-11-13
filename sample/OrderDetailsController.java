package sample;

import javafx.scene.Scene;
import  javafx.scene.control.Button;
import java.io.IOException;
import java.util.ArrayList;
import com.sun.javafx.logging.Logger;
import com.sun.javafx.logging.PlatformLogger.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class OrderDetailsController {
	
    @FXML
    private Button order_done;

    @FXML
    private Button selection_done;

    @FXML
    private Button clear;

    @FXML
    private TextArea show_order_list;

    @FXML
    void back_to_selection(ActionEvent event) {
    	System.out.println("back");
    }

    @FXML
    void clear_order(ActionEvent event) {
    	System.out.println("clear");
    }

    @FXML
    void complete_order(ActionEvent event) {
    	System.out.println("complete");
    }
    

}
