package gui;

import items.Item;
import place.Place;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OrderDialog extends JDialog {

    private JLabel sourcePlaceLabel;
    private JLabel destinationPlaceLabel;
    private JComboBox<Place> soursePlaceComboBox;
    private JComboBox<Place> destinationPlaceComboBox;
    private ArrayList<Item> items;


    public OrderDialog(JFrame owner){
        super(owner,"Make order",true);
        Place[] places = {new Place("place1"),new Place("place2")};
        soursePlaceComboBox = new JComboBox<>(places);
        add(soursePlaceComboBox);
        setSize(new Dimension(100,100));
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);

    }


}
