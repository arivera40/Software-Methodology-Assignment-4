package sample;

public class OrderLine {
    private int lineNumber; //a serial number created when a sandwich is added to the order
    private Sandwich sandwich;
    private double price;

    public OrderLine(Order orderLine){
        this.lineNumber = orderLine.lineNumber;
        sandwich = new Chicken();
        price = 8.99;
    }

    //Idk if this casting is necessary but just in case
    public double getPrice(){
        return price;
    }

    //Sets price to specified price in parameter (which will pass price() method of specific sandwich)
    public void setPrice(double price){
        this.price = price;
    }

    //Changes sandwich to Beef or Fish (not chicken because it already is set as default)
    public void setSandwich(Sandwich sandwich){
        if(sandwich instanceof Beef){
            this.sandwich = (Beef)sandwich;
        }else{
            this.sandwich = (Fish)sandwich;
        }
    }

    public String getSandwich(){
        return sandwich.toString();
    }

    //returns a string of basic ingredients for specific sandwich in OrderLine (Not sure if casting needed but just incase)
    public String getBasicIngredients(){
        if(sandwich instanceof Chicken){
            return ((Chicken)sandwich).basicIngredients();
        }else if(sandwich instanceof Beef){
            return ((Beef)sandwich).basicIngredients();
        }else{
            return ((Fish)sandwich).basicIngredients();
        }
    }

    //returns a string of extra ingredients for specific sandwich OrderLine
    public String getExtraIngredients(){
        return sandwich.extraIngredients();
    }
}
