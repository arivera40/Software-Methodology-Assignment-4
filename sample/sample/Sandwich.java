package sample;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class Sandwich implements Customizable {
    static final int MAX_EXTRAS = 6;
    static final double PER_EXTRA = 1.99;
    protected ArrayList<Extra> extras;
    

    public abstract double price();

    //Returns string of basic ingredients unique to each sandwich type
    public abstract String basicIngredients();

    //Returns string of extra ingredients (Not unique)
    public String extraIngredients(){
        String toppings = "";
        for(Extra topping : extras){
            toppings += topping;
        }
        return toppings;
    }

    //Returns string of sandwich type
    public String toString(){
        if(this instanceof Chicken){
            return "Chicken";
        }else if(this instanceof Beef){
            return "Beef";
        }else{
            return "Fish";
        }
    }

    //Adds extra topping to extras list
    @Override
    public boolean add(Object obj) {
        //Too many ingredients added
        if(extras.size() >= MAX_EXTRAS){
            return false;
        }
        if(obj instanceof Extra){   //Might need to check if Extra topping already added (but may be handled by controller)
            extras.add((Extra) obj);
            return true;
        }
        return false;
    }

    //Removes extra topping from extras list
    @Override
    public boolean remove(Object obj) {
        //No extra ingredients added
        if(extras.size() == 0){
            return false;
        }
        if(obj instanceof Extra){   //Might need to check if Extra topping exists to remove (but may be handled by controller)
            extras.remove((Extra) obj);
            return true;
        }
        return false;
    }

}
