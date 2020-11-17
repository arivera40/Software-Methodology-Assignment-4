package sample;

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


public class SelectionController {

    Image chickenImage = new Image("/sample/images/chicken.jpg");
    Image beefImage = new Image("/sample/images/beef.jpg");
    Image fishImage = new Image("/sample/images/fish.jpg");

    private ObservableList<Extra> extraObservableList = FXCollections.observableArrayList(
            Extra.Lettuce,
            Extra.Mayonnaise,
            Extra.Tomatoes,
            Extra.Ketchup,
            Extra.Cheese,
            Extra.Onions,
            Extra.Tuna,
            Extra.Oil,
            Extra.Vinegar,
            Extra.Avocado
    );

    String prevSandwich = "Chicken";

    private ObservableList<Extra> currentObservableList = FXCollections.observableArrayList();

    @FXML
    private ComboBox meatBox;

    @FXML
    private ListView<Extra> extraListView;

    @FXML
    private ListView<Extra> currentListView;

    @FXML
    private ImageView sandwichView;

    @FXML
    private Button submitButton;

    @FXML
    private Button orderButton;

    @FXML
    private Button addButton;

    @FXML private Button removeButton;

    @FXML
    private TextField priceField;

    @FXML
    private TextField orderNumberField;

    private double price = 8.99;

    private String pattern = "0.00";

    private int orderNumber = 1;

    private Order orders;

    public void start(Order orders){
        this.orders = orders;
        orders.updateLineNumber(orders.getSize() + 1);
        orderNumber = orders.getLineNumber();
        orderNumberField.setText("" + orderNumber);
    }

    @FXML
    void initialize(){
        //Loads Meat Selection ComboBox and sets Chicken as default
        meatBox.getItems().addAll(
                "Chicken",
                "Beef",
                "Fish"
        );
        meatBox.setValue("Chicken");

        orders = new Order();

        //Sets default price and order number fields
        priceField.setText("$" + formatPrice(price));
        orderNumberField.appendText("" + orderNumber);

        extraListView.setItems(extraObservableList);
        currentListView.setItems(currentObservableList);
        extraListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        currentListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    void addIngredients(ActionEvent event){
        int currentListSize = currentObservableList.size();
        currentObservableList.addAll(extraListView.getSelectionModel().getSelectedItems());
        extraObservableList.removeAll(extraListView.getSelectionModel().getSelectedItems());
        int itemsAdded = currentObservableList.size() - currentListSize;
        double priceChange = 1.99 * itemsAdded;
        price += priceChange;
        priceField.setText("$" + formatPrice(price));
        return;
    }

    @FXML
    void removeIngredients(ActionEvent event){
        int extraListSize = extraObservableList.size();
        extraObservableList.addAll(currentListView.getSelectionModel().getSelectedItems());
        currentObservableList.removeAll(currentListView.getSelectionModel().getSelectedItems());
        int itemsRemoved = extraObservableList.size() - extraListSize;
        double priceChange = 1.99 * itemsRemoved;
        price -= priceChange;
        priceField.setText("$" + formatPrice(price));
        return;
    }

    private String formatPrice(double price){
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String numberStr = decimalFormat.format(price);
        return numberStr;
    }

    @FXML
    void changeSandwichType(ActionEvent event){
        //No changes made
        if(meatBox.getValue().toString().equals(prevSandwich)) return;

        //Remove sandwich price of previous sandwich and adds price and updates image of choosen sandwich
        if(meatBox.getValue().toString().equals("Chicken")){
            if(prevSandwich.equals("Beef")){
                price -= 10.99;
            }else{
                price -= 12.99;
            }
            prevSandwich = "Chicken";
            price += 8.99;
            sandwichView.setImage(chickenImage);
        }else if(meatBox.getValue().toString().equals("Beef")){
            if(prevSandwich.equals("Chicken")){
                price -= 8.99;
            }else{
                price -= 12.99;
            }
            prevSandwich = "Beef";
            price += 10.99;
            sandwichView.setImage(beefImage);
        }else{
            if(prevSandwich.equals("Chicken")){
                price -= 8.99;
            }else {
                price -= 10.99;
            }
            prevSandwich = "Fish";
            price += 12.99;
            sandwichView.setImage(fishImage);
        }
        priceField.setText("$" + formatPrice(price));
    }

    @FXML
    void submitOrder(ActionEvent event){

        if(meatBox.getValue().toString().equals("Chicken")){
            Chicken sandwich = new Chicken();
            for(int i=0; i < currentObservableList.size(); i++){
                sandwich.add(currentObservableList.get(i));
            }
            double price = sandwich.price();
            OrderLine orderLine = new OrderLine(orders, sandwich, price);
            orders.add(orderLine);
        }else if(meatBox.getValue().toString().equals("Beef")){
            Beef sandwich = new Beef();
            for(int i=0; i < currentObservableList.size(); i++){
                sandwich.add(currentObservableList.get(i));
            }
            double price = sandwich.price();
            OrderLine orderLine = new OrderLine(orders, sandwich, price);
            orders.add(orderLine);
        }else{
            Fish sandwich = new Fish();
            for(int i=0; i < currentObservableList.size(); i++){
                sandwich.add(currentObservableList.get(i));
            }
            double price = sandwich.price();
            OrderLine orderLine = new OrderLine(orders, sandwich, price);
            orders.add(orderLine);
        }
        orderNumber = orders.getLineNumber();
        orderNumberField.setText("" + orderNumber);
        defaultOrderSelection();
    }

    private void defaultOrderSelection(){
        //Remove all current ingredient selections
        extraObservableList.addAll(currentObservableList);
        currentObservableList.clear();

        //Set meat selection to default of Chicken if not already
        if(!meatBox.getValue().toString().equals("Chicken")){
            meatBox.setValue("Chicken");
        }

        //sets price to default of chicken with no ingredients added
        price = 8.99;
        priceField.setText("$" + formatPrice(price));
        return;
    }

    @FXML
    void orderDetails(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("OrderDetails.fxml"));
        Parent orderDetailsParent = loader.load();
        Scene orderDetailsScene = new Scene(orderDetailsParent);

        OrderDetailsController orderDetails = loader.getController();
        orderDetails.start(orders);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(orderDetailsScene);
        window.show();
    }
}
