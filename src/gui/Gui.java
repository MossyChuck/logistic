package gui;

import db.Database;
import exception.MySqlException;
import orders.Order;
import park.Vehicle;
import place.DestinationPlace;
import place.Stock;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame{
    private JPanel mainPanel;
    private OrderDialog orderDialog;
    private AddingRoadDialog addingRoadDialog;
    private AddingPlaceDialog addingPlaceDialog;
    private AddingVehicleDialog addingVehicleDialog;
    private VehicleTableModel vehicleTableModel;
    private JTable vehicleTable;
    private JScrollPane vehicleTableSP;
    JTable orderTable;


    public Gui(){

    }

    public void build(){
        mainPanel = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(ActionListener -> System.exit(0));
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        JMenu orders = new JMenu("Orders");
        JMenuItem newOrder = new JMenuItem("New order");
        JMenuItem showOrderMenuItem = new JMenuItem("Show orders");
        JMenuItem deleteOrderMenuItem = new JMenuItem("Delete order");
        newOrder.addActionListener(ActionListener -> newOrderAction());
        showOrderMenuItem.addActionListener(ActionListener -> buildOrderTable());
        deleteOrderMenuItem.addActionListener(ActionListener -> deleteOrderAction());
        orders.add(newOrder);
        orders.add(showOrderMenuItem);
        orders.add(deleteOrderMenuItem);
        menuBar.add(orders);
        JMenu placeMenu = new JMenu("Place");
        JMenuItem addPlaceMenuItem = new JMenuItem("Add place");
        addPlaceMenuItem.addActionListener(ActionListener -> addPlaceAction());
        JMenuItem addRoadMenuItem = new JMenuItem("Add road");
        addRoadMenuItem.addActionListener(ActionListener -> addRoadAction());
        placeMenu.add(addPlaceMenuItem);
        placeMenu.add(addRoadMenuItem);
        JMenuItem deletePlaceMenuItem = new JMenuItem("Delete place");
        placeMenu.add(deletePlaceMenuItem);
        menuBar.add(placeMenu);
        JMenu parkMenu = new JMenu("Park");
        JMenuItem addVehicleMenuItem = new JMenuItem("Add vehicle");
        JMenuItem showVehicleMenuItem = new JMenuItem("Show vehicle");
        JMenuItem deleteVehicleMenuItem = new JMenuItem("Delete vehicle");
        deleteVehicleMenuItem.addActionListener(ActionListener -> deleteVehicleAction());
        showVehicleMenuItem.addActionListener(ActionListener ->buildVehicleTable());
        addVehicleMenuItem.addActionListener(ActionListener -> addVehicleAction());
        parkMenu.add(addVehicleMenuItem);
        parkMenu.add(showVehicleMenuItem);
        parkMenu.add(deleteVehicleMenuItem);
        menuBar.add(parkMenu);

        buildOrderTable();


        setJMenuBar(menuBar);
        //getContentPane().add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        //setSize(new Dimension(400,400));

        setVisible(true);
    }

    private void deleteVehicleAction(){
        int x = vehicleTable.getSelectedRow();
        if(x == -1){
            JOptionPane.showMessageDialog(null,"Select vehicle");
            return;
        }
        Vehicle v;
        String model = (String) vehicleTable.getValueAt(x,0);
        double maxSpeed = (double) vehicleTable.getValueAt(x,1);
        double volume = (double) vehicleTable.getValueAt(x,2);
        double maxWeight = (double) vehicleTable.getValueAt(x,3);
        v = new Vehicle(model,maxSpeed,volume,maxWeight);
        try {
            Database.deleteVehicle(v);
        }catch (MySqlException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        JOptionPane.showMessageDialog(null,"Deleted");
        buildVehicleTable();
    }
    private void deleteOrderAction(){
        int x = orderTable.getSelectedRow();
        if(x == -1){
            JOptionPane.showMessageDialog(null, "Select order");
            return;
        }
        String customer = (String) orderTable.getValueAt(x,0);
        Stock stock = new Stock((String) orderTable.getValueAt(x,1));
        DestinationPlace destinationPlace = new DestinationPlace((String) orderTable.getValueAt(x,2));
        Order o = new Order(customer,stock,destinationPlace);
        try{
            Database.deleteOrder(o);
        }catch (MySqlException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        JOptionPane.showMessageDialog(null ,"Deleted");
        buildOrderTable();
    }

    public void buildVehicleTable(){
        vehicleTableModel = new VehicleTableModel();
        vehicleTable = new JTable(vehicleTableModel);
        vehicleTable.setAutoCreateRowSorter(true);

        vehicleTableSP = new JScrollPane(vehicleTable);

        getContentPane().removeAll();
        getContentPane().add(vehicleTableSP);
        getContentPane().validate();
        getContentPane().repaint();
        pack();

    }
    public void buildOrderTable(){
        OrderTableModel orderTableModel = new OrderTableModel();
        orderTable = new JTable(orderTableModel);
        orderTable.setAutoCreateRowSorter(true);

        JScrollPane sp = new JScrollPane(orderTable);
        getContentPane().removeAll();
        getContentPane().add(sp);
        getContentPane().validate();
        getContentPane().repaint();
        pack();
    }
    private void newOrderAction(){
        if(orderDialog == null){
            orderDialog = new OrderDialog(this);
        } else{
            orderDialog.setVisible(true);
        }
        buildOrderTable();
    }
    private void addVehicleAction(){
        if (addingVehicleDialog == null){
            addingVehicleDialog = new AddingVehicleDialog(this);
        }else{
            addingVehicleDialog.setVisible(true);
        }
        buildVehicleTable();
    }
    private void addPlaceAction(){
        if(addingPlaceDialog == null){
            addingPlaceDialog = new AddingPlaceDialog(this);
        }else{
            addingPlaceDialog.setVisible(true);
        }
    }
    private void addRoadAction(){
        if(addingRoadDialog == null){
            addingRoadDialog = new AddingRoadDialog(this);
        }else{
            addingRoadDialog.setVisible(true);
        }
    }

}
