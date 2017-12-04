package db;

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

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

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
    public static void insertPlace(Place place){

    }
    public static ArrayList<Place> getAllPlaces(){
        String query = "select * from places";
        try{
            rs = stmt.executeQuery(query);

            while(rs.next()){

            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }finally {
            try{
                rs.close();
            }catch (SQLException exception){
                System.out.println("closing connection error");
            }
        }
        return new ArrayList<>();
    }
    public static void insertRoads(ArrayList<Road> roads){
        for (Road road:roads) {
            insertRoad(road);
        }
    }
    public static void  insertRoad(Road road){
        String query = "insert into roads (destinationPlace,stock,length) values(\""+road.getDestinationPlace().getName()+"\",\""+road.getStock().getName()+"\","+road.getLength()+");";
        try {
            stmt.executeUpdate(query);
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

//    public static void main(String args[]) {
//        init();
//        ArrayList<Road> roads = new ArrayList<>();
//        roads.add(new Road("stock1","place1",20));
//        roads.add(new Road("stock1","place2",30));
//        roads.add(new Road("stock2","place2",50));
//        insertRoads(roads);
//    }

}
