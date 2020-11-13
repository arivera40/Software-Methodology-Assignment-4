package sample;

import java.util.ArrayList;

public class Chicken extends Sandwich {
	
	//idk what i was trying here but i tried making an instance of extras
	//ArrayList<Extra> chicken_test = new ArrayList<>(); 

    public double price(){
        return 8.99 + (chicken_test.size() * PER_EXTRA);
    }

    @Override
    public String basicIngredients() {
        return "Fried Chicken, Spicy Sauce, Pickles";
    }

    public String extraIngredients(){
        return super.extraIngredients();
    }

    @Override
    public String toString(){
       return super.toString();
    }

    @Override
    public boolean add(Object obj) {
        return super.add(obj);
    }

    @Override
    public boolean remove(Object obj) {
        return super.remove(obj);
    }
}
