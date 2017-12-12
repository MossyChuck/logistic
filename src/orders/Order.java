package orders;

import place.DestinationPlace;
import place.Stock;

import java.util.ArrayList;

public class Order {
    private ArrayList<Item> items;
    private String customer;
    private Stock stock;
    private DestinationPlace destinationPlace;

    public Order(String customer, Stock stock, DestinationPlace destinationPlace, ArrayList<Item> items){
        this.customer = customer;
        this.stock = stock;
        this.destinationPlace = destinationPlace;
        this.items = items;
    }
    public Order(String customer, Stock stock, DestinationPlace destinationPlace){
        this.customer = customer;
        this.stock = stock;
        this.destinationPlace = destinationPlace;
        this.items = new ArrayList<>();
    }
    public void addItem(Item item){
        this.items.add(item);
    }

    public Stock getStock() {
        return stock;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public DestinationPlace getDestinationPlace() {
        return destinationPlace;
    }

    public String getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Customer: "+customer+", Stock: " + stock + ", destination place: " + destinationPlace + ", items: " + itemsToString();
    }
    public String itemsToString(){
        String result = "";
        for(Item item: items){
            result+=item.getName()+", ";
        }
        return result;
    }
}
