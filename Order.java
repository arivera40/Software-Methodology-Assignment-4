package sample;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Order implements Customizable {
    public static int lineNumber; //reset for each new order;
    private ArrayList<OrderLine> orderlines;

    final private String pattern = "0.00";

    public Order(){
        lineNumber = 1; //Used to keep track of what Serial # is assigned
        orderlines = new ArrayList<OrderLine>();
    }

    //Adds OrderLine to List
    @Override
    public boolean add(Object obj) {
        if(obj instanceof OrderLine){
            orderlines.add((OrderLine)obj);
            lineNumber++;
            return true;
        }
        return false;
    }

    //Removes OrderLine from List
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof OrderLine){
            orderlines.remove((OrderLine)obj);
            return true;
        }
        return false;
    }

    //Clears OrderLine List
    public void clear(){
        orderlines.clear();
        lineNumber = 1;
    }

    //Formats list content to be used to export to file
    public String printOrder(){
        String output = "Order\n--------------------\n";
        for(OrderLine orderLine : orderlines){
            output += "\n"; //Extra blank line after each orderLine
            output += "Sandwich Selection: " + orderLine.getSandwich() + "\n";
            output += "Basic Ingredients: " + orderLine.getBasicIngredients() + "\n";
            String extraIngredients = orderLine.getExtraIngredients();
            if(!extraIngredients.equals("")) output += "Extra Ingredients: " + extraIngredients + "\n";
            output += "Price: " + orderLine.getPrice() + "\n";
        }
        output += "--------------------\n";
        output += orderQuantity() + "\n";
        output += orderTotal();
        return output;
    }

    //Calculates order total by adding all prices of each order line in list
    public String orderTotal(){
        double orderTotal = 0;
        for(OrderLine orderLine : orderlines){
            orderTotal += orderLine.getPrice();
        }
        String orderTotalStr = formatNumber(orderTotal);
        return orderTotalStr;
    }

    // formats the decimal to pattern set
    private String formatNumber(double number){
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String numberStr = decimalFormat.format(number);
        return numberStr;
    }

    //Gives you the total number of order lines
    public int orderQuantity(){
        return orderlines.size();   //May be more efficient to return lineNumber
    }

}
