package gui;

import db.Database;
import exception.MySqlException;
import orders.Item;
import orders.Order;
import place.DestinationPlace;
import place.Road;
import place.Stock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddingOrderDialog extends JDialog {
    private JDialog owner;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel addedItemsCountLabel;
    private JLabel stockLabel;
    private JLabel destinationPlaceLabel;
    private JComboBox<Stock> stockComboBox;
    private JComboBox<DestinationPlace> destinationPlaceComboBox;
    private ArrayList<Item> items;
    private JButton addItemButton;
    private JButton okButton;
    private JButton cancelButton;
    private Container pane;
    private Container dpcbPane;
    private AddingItemDialog addingItemDialog;

    private JLabel addLabel(String name){
        JLabel label = new JLabel(name);
        //label.setAlignmentX(Component.LEFT_ALIGNMENT);
        //label.setHorizontalAlignment(SwingConstants.LEFT);
        //label.setSize(new Dimension(300,50));
        pane.add(label);
        return label;
    }
    public void addItem(Item item){
        items.add(item);
        changeAddedItemsCountLabel();
    }
    private void changeAddedItemsCountLabel(){
        addedItemsCountLabel.setText("Items added: "+items.size());
    }

    public AddingOrderDialog(JFrame owner){
        super(owner,"Make order",true);
        try {
            this.owner = this;
            items = new ArrayList<>();
            pane = getContentPane();
            pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
            nameLabel = addLabel("Your name");
            nameTextField = new JTextField();
            nameTextField.setMaximumSize(new Dimension(500, 30));
            pane.add(nameTextField);
            stockLabel = addLabel("From");
            Stock[] stocks = Database.getStocks();
            stockComboBox = new JComboBox<>(stocks);
            stockComboBox.setMaximumSize(new Dimension(400, 30));
            stockComboBox.setSelectedItem(null);
            stockComboBox.addActionListener(ActionListener -> stockChanged());
            pane.add(stockComboBox);
            dpcbPane = new JPanel();
            dpcbPane.setLayout(new BoxLayout(dpcbPane,BoxLayout.Y_AXIS));
            pane.add(dpcbPane);
            addedItemsCountLabel = addLabel("Items added: " + items.size());
            JPanel p2 = new JPanel();
            addItemButton = new JButton("Add item");
            p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
            p2.add(addItemButton);
            pane.add(p2);
            okButton = new JButton("Ok");
            okButton.addActionListener(ActionListener -> okButtonAction());
            addItemButton.addActionListener(ActionListener -> addItemButtonAction());
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(ActionListener -> cancelButtonAction());
            JPanel p = new JPanel();
            p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
            p.add(okButton);
            p.add(cancelButton);
            pane.add(p);

            setSize(new Dimension(300,210));
            setResizable(false);
            setLocationRelativeTo(null);

            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            setVisible(true);
        }catch (MySqlException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    private void okButtonAction(){
        String name = nameTextField.getText();
        Stock stock = (Stock) stockComboBox.getSelectedItem();
        DestinationPlace destinationPlace = (DestinationPlace) destinationPlaceComboBox.getSelectedItem();
        if(name == "" || stock == null || destinationPlace == null || items.size() == 0){
            JOptionPane.showMessageDialog(null,"Check your input");
            return;
        }
        Order order = new Order(name,stock,destinationPlace,items);
        try {
            Database.insertOrder(order);
        } catch (MySqlException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        cancelButtonAction();
    }
    private void addItemButtonAction(){
        if(addingItemDialog == null){
            addingItemDialog = new AddingItemDialog(this);
        }else {
            addingItemDialog.setVisible(true);
        }
    }
    private void cancelButtonAction(){
        nameTextField.setText("");
        items.clear();
        stockComboBox.removeActionListener(stockComboBox.getActionListeners()[0]);
        stockComboBox.setSelectedItem(null);
        destinationPlaceComboBox = new JComboBox<>();
        dpcbPane.removeAll();
        dpcbPane.validate();
        dpcbPane.repaint();
        stockComboBox.addActionListener(ActionListener -> stockChanged());
        changeAddedItemsCountLabel();
        setVisible(false);
    }
    private void stockChanged(){
        try {
            Stock stock = (Stock) stockComboBox.getSelectedItem();

            Road[] roads = Database.getRoadsFrom(stock);
            DestinationPlace[] dps = new DestinationPlace[roads.length];
            for (int i = 0; i < roads.length; i++) {
                dps[i] = roads[i].getDestinationPlace();
            }
            destinationPlaceComboBox = new JComboBox<>(dps);
            destinationPlaceComboBox.setSelectedItem(null);
            destinationPlaceComboBox.setSize(new Dimension(300, 30));

            if(destinationPlaceLabel == null){
                destinationPlaceLabel = new JLabel("Where");

            }
            dpcbPane.removeAll();
            dpcbPane.add(destinationPlaceLabel);
            dpcbPane.add(destinationPlaceComboBox);
            dpcbPane.validate();
            dpcbPane.repaint();
            pane.validate();
            pane.repaint();
        } catch (MySqlException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error");
        }

    }
}
