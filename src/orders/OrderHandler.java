package orders;

import app.Main;
import db.Database;
import exception.MySqlException;
import park.Vehicle;
import place.DestinationPlace;
import place.Road;
import place.Stock;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class OrderHandler {
    private static ArrayList<Order> orders;
    private static ArrayList<Vehicle> vehicles;
    private static String message ;
    private static ArrayList<Order> handledOrders;

    public static void handle(){
        init();
        for(Order order: orders){
            ArrayList<Road> roads = loadRoads(order.getStock());
            double volume = 0, weight = 0;
            for(Item item: order.getItems()){
                volume += item.getVolume();
                weight += item.getWeight();
            }
            handleOrder(order,roads,weight,volume);
            setEndOfMessage();
        }

    }

    private static void setEndOfMessage(){
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
        if(isDeletingOrders()){
            deleteOrders();
        }
    }
    private static void init(){
        loadData();
        message = "";
        handledOrders = new ArrayList<>();
    }
    private static boolean isDeletingOrders(){
        int isDeletingOrders = JOptionPane.showConfirmDialog(null, message);
        if(isDeletingOrders == JOptionPane.YES_OPTION){
            return true;
        }
        return false;
    }
    private static void deleteOrders(){
        try{
            for(Order order: handledOrders) {
                Database.deleteOrder(order);
            }
            Main.getGui().buildOrderTable();
        }catch (MySqlException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
    private static void handleOrder(Order order, ArrayList<Road> roads, double weight, double volume){
        for(Vehicle v: vehicles){
            if(v.getMaxWeight()>= weight && v.getVolume() >= volume){
                double time = getLength(roads,order.getDestinationPlace()) / v.getMaxSpeed();
                message += getHandledOrderMessage(v,time);
                vehicles.remove(v);
                handledOrders.add(order);
                break;
            }
        }
    }
    private static String getHandledOrderMessage(Vehicle v, double time){
        return  "Order #1 handled by " + v.getModel() + ", it takes " + String.format("%.2f",time) + " hours.\n";

    }
    private static int getLength(ArrayList<Road> roads, DestinationPlace destinationPlace){
        int length = 1;
        for(Road road: roads){
            if(destinationPlace.getName().equals(road.getDestinationPlace().getName())){
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
