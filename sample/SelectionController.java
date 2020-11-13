package sample;

import javafx.scene.Scene;
import  javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.sun.javafx.logging.Logger;
import com.sun.javafx.logging.PlatformLogger.Level;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SelectionController extends Sandwich {
	
    ObservableList<String> extraList = FXCollections.observableArrayList("Lettuce","Mayonnaise","Tomatoes","Ketchup","Cheese",
    		"Onions","Tuna","Oil","Vinegar","Avocado");
	ObservableList<String> meatsList = FXCollections.observableArrayList("chicken", "beef", "fish");
		
	ArrayList<String> RightList = new ArrayList<String>();
	
	ArrayList<Extra> daisy = new ArrayList<>(); 
	
    @FXML
    private ComboBox selectmeat;
    

    @FXML
    private ListView<String> ingredients;


    @FXML
    private ListView ingred2;

    @FXML
    private Button button1;

    @FXML
    private Button button2;
    
    @FXML
    private Button button_ingred;
    
    @FXML
    private ImageView food_image;
    
    @FXML
    private TextArea price;

    
    @FXML
    void addThem(ActionEvent event) {
    
    	
    	ObservableList<String> observableList = FXCollections.observableList(RightList);
    	String r = ingredients.getSelectionModel().getSelectedItems().toString();
    	r = r.substring(1, r.length() - 1);
    	RightList.add(r);
    	
        Chicken chick = new Chicken();

      	ingred2.setItems(observableList);
      	
      	/**
      	 *  a lot of random bs here messing with the program!!!!
      	 */
      	
        Order neworder = new Order();
        OrderLine zuzu = new OrderLine(neworder);
        
        
        
      	if(RightList.contains(extraList.get(0))) {
      		System.out.println(Extra.Lettuce);
      		//System.out.println(daisy);
      		chick.chicken_test.add(Extra.Mayonnaise);
      		chick.add(Extra.Avocado);
      		System.out.println(zuzu.getPrice());
      		//extras.add(Extra.Avocado);
      		//System.out.println(extras);
      	}
      	 if(RightList.contains(extraList.get(1))) {
      		System.out.println(Extra.Mayonnaise);
      		//extras.add(Extra.Mayonnaise);
      	}
      	 if(RightList.contains(extraList.get(2))) {
      		System.out.println(Extra.Tomatoes);
      	}
      	if(RightList.contains(extraList.get(3))) {
      		System.out.println(Extra.Ketchup);
      	}
      	if(RightList.contains(extraList.get(4))) {
      		System.out.println(Extra.Cheese);
      	}
      	if(RightList.contains(extraList.get(5))) {
      		System.out.println(Extra.Onions);
      	}
      	if(RightList.contains(extraList.get(6))) {
      		System.out.println(Extra.Tuna);
      	}
      	if(RightList.contains(extraList.get(7))) {
      		System.out.println(Extra.Oil);
      	}
      	if(RightList.contains(extraList.get(8))) {
      		System.out.println(Extra.Vinegar);
      	}
      	if(RightList.contains(extraList.get(9))) {
      		System.out.println(Extra.Avocado);
      	}
      	
      	
      	System.out.println(Order.lineNumber);
    	//System.out.println("working");
    }
    
    @FXML
    void remove(ActionEvent event) {
    	int selected_int = ingred2.getSelectionModel().getSelectedIndex();
    	if(selected_int > -1) {
    		RightList.remove(selected_int);
    	}
    	
    	 ObservableList<String> observableList = FXCollections.observableList(RightList);
         extras.remove(Extra.Lettuce);
        
       	ingred2.setItems(observableList);
       	
    	
    }
    @FXML
    void print_details(ActionEvent event) {
    	try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("details.fxml"));
            /*
             * if "fx:controller" is not set in fxml
             * fxmlLoader.setController(NewWindowController);
             */
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            Stage stage = new Stage();
            stage.setTitle("Order Details");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
          //  Logger logger = Logger.getLogger(getClass().getName());
          //  logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }

    
    }

    @FXML
    void sumbit_order(ActionEvent event) {
    	System.out.println("working 3");
    	
    	if(selectmeat.getValue() == meatsList.get(0)) {
         //   ImageView chicken_image = new ImageView(("C:\\Users\\jsham\\eclipse-workspace\\projectfour\\src\\meatType.chicken.jpg"));
    	//	System.out.println("chicken man");
    		Chicken chick = new Chicken();
    		System.out.println(chick);
    		}
    	else if(selectmeat.getValue() == meatsList.get(1)) {
    		System.out.println("beef man");
    		Beef b = new Beef();
    		System.out.println(b.basicIngredients() + b.toString() );
    		System.out.println(b);
    		System.out.println(b.basicIngredients() + " " + RightList);

    	}
    	else{
    		System.out.println("fish man");
    		Fish f = new Fish();
    		System.out.println(f);

    		
    	}
    }
    
    @FXML
    void change_meat(ActionEvent event) {
    	
    	
    	if(selectmeat.getValue() == meatsList.get(0)) {
    		
    		Chicken chick = new Chicken();
      		String man = Double.toString(PER_EXTRA);
      		price.appendText("Price is $8.99" + man);
      		System.out.println(chick.basicIngredients() + ", " + Extra.Avocado);
      		
      		
      	}
    	else if(selectmeat.getValue() == meatsList.get(0)) {
    		
    		Beef b = new Beef();
      		System.out.println(b.price());
    		
    		
    		
    	}
    	else {
    		Fish f = new Fish();
    		
    	}

    }

    
    @FXML
    void initialize() {
    	selectmeat.setValue(meatsList.get(0));
    	selectmeat.setItems(meatsList);
    	ingredients.setItems(extraList);
    //	ingredients.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);  
    	
    }

	@Override
	public double price() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String basicIngredients() {
		// TODO Auto-generated method stub
		return null;
	}
    


    
    	
    }

