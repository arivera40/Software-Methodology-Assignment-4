package sample;

public class Chicken extends Sandwich {

    public Chicken(){
        super();
    }

    public double price(){
        return 8.99 + (extras.size() * PER_EXTRA);
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