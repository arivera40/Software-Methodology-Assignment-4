package sample;

public class Fish extends Sandwich {

    public double price(){
        return 12.99 + (extras.size() * PER_EXTRA);
    }

    @Override
    public String basicIngredients() {
        return "Grilled Snapper, Cilantro, Lime";
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
