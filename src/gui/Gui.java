package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JButton orderButton;
    private JButton driverLoginButton;
    private JButton adminLoginButton;
    private OrderDialog orderDialog;
    private AddingRoadDialog addingRoadDialog;
    private AddingPlaceDialog addingPlaceDialog;
    private AddingVehicleDialog addingVehicleDialog;

    public Gui(){

    }

    public void build(){
        mainFrame = new JFrame();
        mainPanel = new JPanel();
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(ActionListener -> System.exit(0));
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        JMenu orders = new JMenu("Orders");
        JMenuItem newOrder = new JMenuItem("New order");
        newOrder.addActionListener(ActionListener -> newOrderAction());
        orders.add(newOrder);
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
        addVehicleMenuItem.addActionListener(ActionListener -> addVehicleAction());
        parkMenu.add(addVehicleMenuItem);
        menuBar.add(parkMenu);



        mainFrame.setJMenuBar(menuBar);
        mainFrame.getContentPane().add(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(400,400));
        mainFrame.setVisible(true);
    }
    private void newOrderAction(){
        if(orderDialog == null){
            orderDialog = new OrderDialog(mainFrame);
        } else{
            orderDialog.setVisible(true);
        }
    }
    private void addVehicleAction(){
        if (addingVehicleDialog == null){
            addingVehicleDialog = new AddingVehicleDialog(mainFrame);
        }else{
            addingVehicleDialog.setVisible(true);
        }
    }
    private void addPlaceAction(){
        if(addingPlaceDialog == null){
            addingPlaceDialog = new AddingPlaceDialog(mainFrame);
        }else{
            addingPlaceDialog.setVisible(true);
        }
    }
    private void addRoadAction(){
        if(addingRoadDialog == null){
            addingRoadDialog = new AddingRoadDialog(mainFrame);
        }else{
            addingRoadDialog.setVisible(true);
        }
    }

}
