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

    public Gui(){
        orderButton = new JButton("make order");
        adminLoginButton = new JButton("admin page");
        driverLoginButton = new JButton("driver page");
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
        JMenu addPlaceMenu = new JMenu("Add place");
        JMenuItem addStockMenuItem = new JMenuItem("Add stock");
        JMenuItem addDestinationPlaceMenuItem = new JMenuItem("Add destination place");
        addPlaceMenu.add(addStockMenuItem);
        addPlaceMenu.add(addDestinationPlaceMenuItem);
        placeMenu.add(addPlaceMenu);
        JMenuItem deletePlaceMenuItem = new JMenuItem("Delete place");
        placeMenu.add(deletePlaceMenuItem);
        menuBar.add(placeMenu);


        mainFrame.setJMenuBar(menuBar);
        mainPanel.add(driverLoginButton);
        mainPanel.add(orderButton);
        mainPanel.add(adminLoginButton);
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

}
