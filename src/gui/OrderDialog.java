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
        addLabel(nameLabel,"Your name");
        nameTextField = new JTextField();
        nameTextField.setMaximumSize(new Dimension(500,30));
        pane.add(nameTextField);
        addLabel(stockLabel,"From");
        Place[] stocks = {new Stock("place1"),new Stock("place2")};
        //addComboBox(stockComboBox, stocks);
        stockComboBox = new JComboBox<>(stocks);
        stockComboBox.setMaximumSize(new Dimension(500,30));
        pane.add(stockComboBox);
        addLabel(destinationPlaceLabel,"Where");
        Place[] destinationPlaces = {new DestinationPlace("place3"),new DestinationPlace("place4")};
        destinationPlaceComboBox = new JComboBox<>(destinationPlaces);
        destinationPlaceComboBox.setMaximumSize(new Dimension(500,30));
        pane.add(destinationPlaceComboBox);
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
        okButton.addActionListener(new OkButtonActionListener());
        addItemButton.addActionListener(new AddItemButtonActionListener());
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new CancelButtonActionListener());
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
    class OkButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    class CancelButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            nameTextField.setText("");
            items.removeAll(items);
            stockComboBox.setSelectedIndex(0);
            destinationPlaceComboBox.setSelectedIndex(0);
            changeAddedItemsCountLabel();
            setVisible(false);
        }
    }
    class AddItemButtonActionListener implements ActionListener{
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
