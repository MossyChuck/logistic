package db;


import exception.MySqlException;
import gui.AddingPlaceDialog;
import orders.Item;
import orders.Order;
import park.Vehicle;
import place.DestinationPlace;
import place.Place;
import place.Road;
import place.Stock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/logistic";
    private static final String user = "root";
    private static final String password = "12345";
    private static final String errorMessage = "Database error";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static ResultSet getRs() {
        return rs;
    }
    public static Connection getCon() {
        return con;
    }
    public static Statement getStmt() {
        return stmt;
    }

    public static void init(){
        try{
            con = DriverManager.getConnection(url,user,password);
            stmt = con.createStatement();
        }catch (SQLException exception){
            System.out.println("connection error");
        }
    }
    public static void closeAll(){
        try{
            con.close();
            stmt.close();
        }catch (SQLException exception){
            System.out.println("closing connection error");
        }
    }
    public static void insertPlace(Place place) throws MySqlException{
        AddingPlaceDialog.Type type;
        if(place instanceof Stock){
            type = AddingPlaceDialog.Type.STOCK;
        }else {
            type = AddingPlaceDialog.Type.DESTINATION_PLACE;
        }
        String query = "insert into places (name,type) values (\""+place.getName()+"\",\""+type.toString()+"\");";
        try {
            stmt.executeUpdate(query);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new MySqlException(errorMessage);
        }

    }
    public static Stock[] getStocks() throws MySqlException{
        String query = "select * from places where type=\""+AddingPlaceDialog.Type.STOCK+"\";";
        ArrayList<Stock> stocks = new ArrayList<>();
        try{
            rs = stmt.executeQuery(query);

            while(rs.next()){
                String name = rs.getString("name");
                stocks.add(new Stock(name));
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new MySqlException(errorMessage);
        }finally {
            try{
                rs.close();
            }catch (SQLException exception){
                System.out.println("closing connection error");
            }
        }
        return stocks.toArray(new Stock[stocks.size()]);
    }
    public static DestinationPlace[] getDestinationPlaces() throws MySqlException{
        String query = "select * from places where type=\""+AddingPlaceDialog.Type.DESTINATION_PLACE+"\";";
        ArrayList<DestinationPlace> destinationPlaces = new ArrayList<>();
        try{
            rs = stmt.executeQuery(query);

            while(rs.next()){
                String name = rs.getString("name");
                destinationPlaces.add(new DestinationPlace(name));
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new MySqlException(errorMessage);
        }finally {
            try{
                rs.close();
            }catch (SQLException exception){
                System.out.println("closing connection error");
                throw new MySqlException(errorMessage);
            }
        }
        return destinationPlaces.toArray(new DestinationPlace[destinationPlaces.size()]);
    }
    public static void  insertRoad(Road road) throws MySqlException{
        String query = "insert into roads (destinationPlace,stock,length) values(\""+road.getDestinationPlace().getName()+"\",\""+road.getStock().getName()+"\","+road.getLength()+");";
        try {
            stmt.executeUpdate(query);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new MySqlException(errorMessage);
        }finally {
            try {
                rs.close();
            } catch (SQLException e){
                throw new MySqlException(errorMessage);
            }
        }
    }
    public static void insertVehicle(Vehicle vehicle) throws MySqlException{
        String query = "insert into vehicles (model,maxSpeed,volume,maxWeight) values(\""+vehicle.getModel()+"\","+vehicle.getMaxSpeed()+","+vehicle.getVolume()+","+vehicle.getMaxWeight()+");";
        try {
            stmt.executeUpdate(query);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new MySqlException(errorMessage);
        }finally {
            try{
                rs.close();
            }catch (SQLException e){
                throw new MySqlException(errorMessage);
            }
        }
    }
    public static Road[] getRoadsFrom(Stock stock) throws MySqlException{

        String query = "select * from roads where stock = '"+stock.getName()+"';";
        ArrayList<Road> roads = new ArrayList<>();
        try{
            rs = stmt.executeQuery(query);

            while(rs.next()){
                int length = rs.getInt("length");
                String destinatonPlace = rs.getString("destinationPlace");
                roads.add(new Road(stock,new DestinationPlace(destinatonPlace),length));
            }
        }catch (SQLException exception){
            throw new MySqlException(errorMessage);
        }finally {
            try {
                rs.close();
            }catch (SQLException e){
                throw new MySqlException(errorMessage);
            }
        }
        return roads.toArray(new Road[roads.size()]);
    }

    public static Vehicle[] getVehicles() throws MySqlException{
        String query = "select * from vehicles;";
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try{
            rs = stmt.executeQuery(query);

            while(rs.next()){
                String model = rs.getString("model");
                double maxSpeed = rs.getFloat("maxSpeed");
                double volume = rs.getFloat("volume");
                double maxWeight = rs.getFloat("maxWeight");
                vehicles.add(new Vehicle(model,maxSpeed,volume,maxWeight));
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new MySqlException(errorMessage);
        }finally {
            try{
                rs.close();
            }catch (SQLException exception){
                System.out.println("closing connection error");
                throw new MySqlException(errorMessage);
            }
        }
        return vehicles.toArray(new Vehicle[vehicles.size()]);
    }

    public static void deleteVehicle(Vehicle vehicle) throws MySqlException{
        String query = "delete from vehicles where model='"+vehicle.getModel()+"' and maxSpeed = "+vehicle.getMaxSpeed()+" and volume = "+vehicle.getVolume()+" and maxWeight="+vehicle.getMaxWeight()+";";
        try{
            stmt.executeUpdate(query);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new MySqlException(errorMessage);
        }
    }
    public static void deleteOrder(Order order) throws MySqlException{
        String query = "delete from orders where customer='"+order.getCustomer()+"' and stock='"+order.getStock().getName()+"' and destinationPlace='"+order.getDestinationPlace().getName()+"';";
        try{
            stmt.executeUpdate(query);
        }catch (SQLException exception){
            throw  new MySqlException(errorMessage);
        }
    }

    public static void insertItem(Item item,int orderId) throws MySqlException{
        String query = "insert into items (name,volume,weight,orderId) values('"+item.getName()+"',"+item.getVolume()+","+item.getWeight()+","+orderId+");";
        try{
            stmt.executeUpdate(query);
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new MySqlException(errorMessage);
        }finally {
            try{
                rs.close();
            }catch (SQLException e){
                throw new MySqlException(errorMessage);
            }
        }
    }

    public static void insertOrder(Order order) throws MySqlException{
        String query = "insert into orders (customer,stock,destinationPlace) values('"+order.getCustomer()+"','"+order.getStock().getName()+"','"+order.getDestinationPlace().getName()+"');";
        try {
            stmt.executeUpdate(query);

            query = "select id from orders where customer='"+order.getCustomer()+"' and stock = '"+order.getStock().getName()+"' and destinationPlace = '"+order.getDestinationPlace().getName()+"';";
            rs = stmt.executeQuery(query);
            rs.next();
            int orderId = rs.getInt("id");
            System.out.println(orderId);
            for(Item item: order.getItems()){
                insertItem(item, orderId);
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new MySqlException(errorMessage);
        }finally {
            try {
                rs.close();
            }catch (SQLException e){
                throw new MySqlException(errorMessage);
            }
        }

    }

    public static Order[] getOrders() throws MySqlException{
        ArrayList<Order> orders = new ArrayList<>();
        String query = "select * from orders";
        try {
            rs = stmt.executeQuery(query);


            while (rs.next()){
                int id = rs.getInt("id");
                String customer = rs.getString("customer");
                String stock = rs.getString("stock");
                String destinationPlace = rs.getString("destinationPlace");
                Order order = new Order(customer,new Stock(stock),new DestinationPlace(destinationPlace));
                Item[] items = getItemsByOrderId(id);
                for(Item item: items){
                    order.addItem(item);
                }
                orders.add(order);
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new MySqlException(errorMessage);
        }finally {
            try{
                rs.close();
            }catch (SQLException e){
                throw new MySqlException(errorMessage);
            }
        }

        return orders.toArray(new Order[orders.size()]);
    }
    public static Item[] getItemsByOrderId(int orderId) throws MySqlException{
        ArrayList<Item> items = new ArrayList<>();
        String query = "select * from items where orderId="+orderId+";";
        try {
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String name = resultSet.getString("name");
                double volume = resultSet.getFloat("volume");
                double weight = resultSet.getFloat("weight");
                items.add(new Item(name,volume,weight));

            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new MySqlException(errorMessage);
        }

        return items.toArray(new Item[items.size()]);
    }

}
