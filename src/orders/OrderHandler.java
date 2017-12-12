package orders;

import db.Database;
import exception.MySqlException;
import park.Vehicle;
import place.DestinationPlace;
import place.Road;
import place.Stock;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class OrderHandler {
    private static ArrayList<Order> orders;
    private static ArrayList<Vehicle> vehicles;
    private static String message ;
    public static void handle(){
        loadData();
        message = "";
        ArrayList<Order> handledOrders = new ArrayList<>();
        for(Order order: orders){
            ArrayList<Road> roads = loadRoads(order.getStock());
            double volume = 0;
            double weight = 0;
            for(Item item: order.getItems()){
                volume += item.getVolume();
                weight += item.getWeight();

            }
            Vehicle currentVehicle;
            for(Vehicle v: vehicles){
                if(v.getMaxWeight()>= weight && v.getVolume() >= volume){
                    currentVehicle = v;
                    double time = getLength(roads,order.getDestinationPlace()) / v.getMaxSpeed();
                    vehicles.remove(v);
                    message += "Order #1 handled by " + v.getModel() + ", it takes " + time + " hours\n.";
                    handledOrders.add(order);
                    break;
                }
            }

        }
        orders.removeAll(handledOrders);
        if(orders.size() == 0){
            message += "No unhandled orders.";
        }else {
            message += "Unhandled orders:\n";
            for (Order order: orders){
                message += order + "\n";
            }
        }
        if(handledOrders.size() == 0){
            JOptionPane.showMessageDialog(null, message);
            return;
        }
        message += "Delete handled orders?";
        int isDeletingOrders = JOptionPane.showConfirmDialog(null, message);
        if(isDeletingOrders != 0){
            try{
                for(Order order: handledOrders) {
                    Database.deleteOrder(order);
                }
            }catch (MySqlException e){
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }

    private static int getLength(ArrayList<Road> roads, DestinationPlace destinationPlace){
        int length = 1;
        for(Road road: roads){
            if(destinationPlace == road.getDestinationPlace()){
                length = road.getLength();
                break;
            }
        }

        return length;
    }
    private static void loadOrders(){
        try{
            Order[] os = Database.getOrders();
            if(orders == null){
                orders = new ArrayList<>();
            }else{
                orders.clear();
            }
            for(Order o: os){
                orders.add(o);
            }
        } catch (MySqlException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    private static void loadVehicles(){
        try{
            Vehicle[] vs = Database.getVehicles();
            if(vehicles == null){
                vehicles = new ArrayList<>();
            }else{
                vehicles.clear();
            }
            for(Vehicle v: vs){
                vehicles.add(v);
            }
            Collections.sort(vehicles,Vehicle.VehicleComparator);
        } catch (MySqlException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    private static void loadData(){
        loadOrders();
        loadVehicles();
    }
    private static ArrayList<Road> loadRoads(Stock stock){
        ArrayList<Road> roads = new ArrayList<>();
        try{
            Road[] rs = Database.getRoadsFrom(stock);
            for(Road r: rs){
                roads.add(r);
            }
        }catch (MySqlException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return roads;
    }
}
