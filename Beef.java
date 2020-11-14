package sample;

public class Beef extends Sandwich {

    public Beef(){
        super();
    }

    public double price(){
        return 10.99 + (extras.size() * PER_EXTRA);
    }

    @Override
    public String basicIngredients() {
        return "Roast Beef, Provolone Cheese, Mustard";
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
