package gui;

import items.Item;
import place.DestinationPlace;
import place.Place;
import place.Stock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OrderDialog extends JDialog {
    private JDialog owner;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel addedItemsCountLabel;
    private JLabel stockLabel;
    private JLabel destinationPlaceLabel;
    private JComboBox<Place> stockComboBox;
    private JComboBox<Place> destinationPlaceComboBox;
    private ArrayList<Item> items;
    private JButton addItemButton;
    private JButton okButton;
    private JButton cancelButton;
    private Container pane;
    private AddingItemDialog addingItemDialog;

    private JLabel addLabel(String name){
        JLabel label = new JLabel(name);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setSize(new Dimension(500,50));
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

    public OrderDialog(JFrame owner){
        super(owner,"Make order",true);
        this.owner = this;
        items = new ArrayList<>();
        pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        nameLabel = addLabel("Your name");
        nameTextField = new JTextField();
        nameTextField.setMaximumSize(new Dimension(500,30));
        pane.add(nameTextField);
        stockLabel = addLabel("From");
        Place[] stocks = {new Stock("place1"),new Stock("place2")};
        //addComboBox(stockComboBox, stocks);
        stockComboBox = new JComboBox<>(stocks);
        stockComboBox.setMaximumSize(new Dimension(500,30));
        pane.add(stockComboBox);
        destinationPlaceLabel = addLabel("Where");
        Place[] destinationPlaces = {new DestinationPlace("place3"),new DestinationPlace("place4")};
        destinationPlaceComboBox = new JComboBox<>(destinationPlaces);
        destinationPlaceComboBox.setMaximumSize(new Dimension(500,30));
        pane.add(destinationPlaceComboBox);
        addedItemsCountLabel = addLabel("Items added: "+items.size());
        JPanel p2 = new JPanel();
        addItemButton = new JButton("Add item");
        p2.setLayout(new BoxLayout(p2,BoxLayout.X_AXIS));
        p2.add(addItemButton);
        pane.add(p2);
        okButton = new JButton("Ok");
        okButton.addActionListener(ActionListener -> okButtonAction());
        addItemButton.addActionListener(ActionListener -> addItemButtonAction());
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(ActionListener -> cancelButtonAction());
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        p.add(okButton);
        p.add(cancelButton);
        pane.add(p);

        setSize(new Dimension(400,300));
        //pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    private void okButtonAction(){
        System.out.println("ok");
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
        stockComboBox.setSelectedIndex(0);
        destinationPlaceComboBox.setSelectedIndex(0);
        changeAddedItemsCountLabel();
        setVisible(false);
    }
}
