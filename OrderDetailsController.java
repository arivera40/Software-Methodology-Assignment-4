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

import javafx.stage.Stage;

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
    void selection(ActionEvent event){

    }

    @FXML
    void confirmOrder(ActionEvent event){

    }
}
