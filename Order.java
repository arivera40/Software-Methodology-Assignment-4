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

    //Returns lineNumber
    public int getLineNumber(){
        return lineNumber;
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

    public String duplicateOrderLine(int lineNumber){
        for(int i=0; i < orderlines.size(); i++){
            if(orderlines.get(i).getLineNumber() == lineNumber){
                OrderLine orderLine = orderlines.get(i);
                Sandwich sandwich = orderLine.copySandwich();
                if(sandwich.toString().equals("Chicken")){
                    Chicken duplicateSandwich = new Chicken();
                    for(Extra extra : sandwich.extras){
                        duplicateSandwich.add(extra);
                    }
                    OrderLine duplicateOrderLine = new OrderLine(this, duplicateSandwich, duplicateSandwich.price());
                    add(duplicateOrderLine);
                }else if(sandwich.toString().equals("Beef")){
                    Beef duplicateSandwich = new Beef();
                    for(Extra extra : sandwich.extras){
                        duplicateSandwich.add(extra);
                    }
                    OrderLine duplicateOrderLine = new OrderLine(this, duplicateSandwich, duplicateSandwich.price());
                    add(duplicateOrderLine);
                }else{
                    Fish duplicateSandwich = new Fish();
                    for(Extra extra : sandwich.extras){
                        duplicateSandwich.add(extra);
                    }
                    OrderLine duplicateOrderLine = new OrderLine(this, duplicateSandwich, duplicateSandwich.price());
                    add(duplicateOrderLine);
                }
                break;
            }
        }
        return printOrderLine(orderlines.size() - 1);
    }


    //Removes OrderLine from List
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Integer){
            int lineNumber = (Integer) obj;
            for(int i=0; i < orderlines.size(); i++){
                if(orderlines.get(i).getLineNumber() == lineNumber){
                    orderlines.remove(i);
                    fixOrderLines();
                    return true;
                }
            }
            return false;
        }
        return false;
    }



    //Traverses orderlines list until finding inconsistent lineNumber for which it corrects it and all orderLines after
    private void fixOrderLines(){
        int lineNum = 1;
        for(int i=0; i < orderlines.size(); i++){
            if(orderlines.get(i).getLineNumber() != lineNum){
                for(int j=i; j < orderlines.size(); j++){
                    orderlines.get(j).setLineNumber(lineNum);
                    lineNum++;
                }
                break;
            }
            lineNum++;
        }
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
            output += "Sandwich Selection: " + orderLine.getSandwich() + "\n";
            output += "Basic Ingredients: " + orderLine.getBasicIngredients() + "\n";
            String extraIngredients = orderLine.getExtraIngredients();
            if(!extraIngredients.equals("")) output += "Extra Ingredients: " + extraIngredients + "\n";
            output += "Serial #: " + orderLine.getLineNumber() + "\n";
            output += "Price: $" + orderLine.getPrice() + "\n";
        }
        output += "--------------------\n";
        output += "Qty: " + orderQuantity() + "\n";
        output += "Price: $" + orderTotal();
        return output;
    }

    public String printOrderLine(int index){
        String output = "";
        OrderLine orderLine = orderlines.get(index);
        output += "Sandwich Selection: " + orderLine.getSandwich() +"\n";
        output += "Basic Ingredients: " + orderLine.getBasicIngredients() + "\n";
        output += "Extra Ingredients: " + orderLine.getExtraIngredients() + "\n";
        output += "Price: $" + orderLine.getPrice() + "\n";
        output += "Serial #: " + orderLine.getLineNumber() + "\n";
        return output;
    }

    public int getSize(){
        return orderlines.size();
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
    public String formatNumber(double number){
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String numberStr = decimalFormat.format(number);
        return numberStr;
    }

    //Gives you the total number of order lines
    public int orderQuantity(){
        return orderlines.size();   //May be more efficient to return lineNumber
    }

    public double orderLinePrice(int lineNumber){
        for(int i=0; i < orderlines.size(); i++){
            if(orderlines.get(i).getLineNumber() == lineNumber){
                return orderlines.get(i).getPrice();
            }
        }
        return 0;
    }

}
