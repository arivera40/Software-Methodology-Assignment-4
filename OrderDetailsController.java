package sample;

import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import  javafx.scene.control.Button;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class OrderDetailsController {

    private ObservableList<String> orderLineObservable;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField totalField;

    @FXML
    private Button selectionButton;

    @FXML
    private Button confirmButton;

    @FXML
    private Button duplicateButton;

    @FXML
    private Button removeButton;

    @FXML
    private Button clearButton;

    @FXML
    private ListView<String> orderLineListView;

    protected static Order orders;

    public void start(Order orders){
        this.orders = orders;

        orderLineObservable = loadListView();
        orderLineListView.setItems(orderLineObservable);
        orderLineListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        quantityField.setText("" + orders.getSize());
        totalField.setText("" + orders.orderTotal());
    }

    @FXML
    void duplicateOrderLine(ActionEvent event){
        if(orderLineListView.getSelectionModel().getSelectedIndices().size() < 1){
            return;
        }
        int lineNum = orderLineListView.getSelectionModel().getSelectedIndices().get(0) + 1;
        String orderLineStr = orders.duplicateOrderLine(lineNum);
        orderLineObservable.add(orderLineStr);
        quantityField.setText("" + orders.getSize());
        totalField.setText("" + orders.orderTotal());
        return;
    }

    @FXML
    void removeOrderLine(ActionEvent event){
        if(orderLineListView.getSelectionModel().getSelectedIndices().size() < 1){
            return;
        }
        int lineNum = orderLineListView.getSelectionModel().getSelectedIndices().get(0) + 1;
        orders.remove(lineNum);
        orderLineObservable = loadListView();
        orderLineListView.setItems(orderLineObservable);
        quantityField.setText("" + orders.getSize());
        totalField.setText("" + orders.orderTotal());
        return;
    }

    private ObservableList<String> loadListView(){
        ArrayList<String> orderLineList = new ArrayList<String>();
        for(int i=0; i < orders.getSize(); i++){
            String orderLine = orders.printOrderLine(i);
            orderLineList.add(orderLine);
        }
        return FXCollections.observableArrayList(orderLineList);
    }

    @FXML
    void clearOrders(ActionEvent event){
        orders.clear();
        orderLineObservable.clear();
        quantityField.setText("0");
        totalField.setText("$0.00");
        return;
    }

    @FXML
    void selection(ActionEvent event)throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Selection.fxml"));
        Parent orderDetailsParent = loader.load();
        Scene orderDetailsScene = new Scene(orderDetailsParent);

        OrderDetailsController orderDetails = loader.getController();
        orderDetails.start(orders);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(orderDetailsScene);
        window.show();

    }

    @FXML
    void confirmOrder(ActionEvent event){
         FileChooser chooser = new FileChooser();
         chooser.setTitle("Specify Text File to Export to");
         chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                 new FileChooser.ExtensionFilter("All Files", "*.*"));
         Stage stage = new Stage();

         try{
             File outputFile = chooser.showSaveDialog(stage);
             if(!outputFile.getName().endsWith(".txt")){
                 return;
             }
             FileWriter writer = new FileWriter(outputFile);
            String status = orders.printOrder();
             if(status.equals("Database is empty.")){
                orders.printOrder();
             }else{
                 writer.write(status);
             }
             writer.close();
         }catch(IOException e){
             return;
         }catch(NullPointerException e){
        	 return;
         }

    }
}