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

    private void addLabel(JLabel label, String name){
        label = new JLabel(name);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setSize(new Dimension(500,50));
        pane.add(label);
    }
    private void addComboBox(JComboBox<Place> comboBox, Place[] places){
        comboBox = new JComboBox<>(places);
        comboBox.setMaximumSize(new Dimension(500,30));
        pane.add(comboBox);
    }

    public void addItem(Item item){
        items.add(item);
        addedItemsCountLabel.setText("Items added: "+items.size());
    }

    public OrderDialog(JFrame owner){
        super(owner,"Make order",true);
        this.owner = this;
        items = new ArrayList<>();
        pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        addLabel(nameLabel,"Your name");
        nameTextField = new JTextField();
        nameTextField.setMaximumSize(new Dimension(500,30));
        pane.add(nameTextField);
        addLabel(stockLabel,"From");
        Place[] stocks = {new Stock("place1"),new Stock("place2")};
        addComboBox(stockComboBox, stocks);
        addLabel(destinationPlaceLabel,"Where");
        Place[] destinationPlaces = {new DestinationPlace("place3"),new DestinationPlace("place4")};
        addComboBox(destinationPlaceComboBox, destinationPlaces);
        //addLabel(addedItemsCountLabel,"Items added: "+items.size());
        addedItemsCountLabel = new JLabel("Items added: "+ items.size());
        addedItemsCountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        pane.add(addedItemsCountLabel);
        JPanel p2 = new JPanel();
        addItemButton = new JButton("Add item");
        p2.setLayout(new BoxLayout(p2,BoxLayout.X_AXIS));
        p2.add(addItemButton);
        //pane.add(addItemButton);
        pane.add(p2);
        okButton = new JButton("Ok");
        addItemButton.addActionListener(new addItemButtonActionListener());
        cancelButton = new JButton("Cancel");
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

    class addItemButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(addingItemDialog == null){
                addingItemDialog = new AddingItemDialog(owner);
            }else{
                addingItemDialog.setVisible(true);
            }

        }
    }


}
