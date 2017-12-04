package gui;

import items.Item;
import place.DestinationPlace;
import place.Place;
import place.Road;
import place.Stock;
import db.Database;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddingRoadDialog extends JDialog {
    private JDialog owner;
    private JLabel lengthLabel;
    private JTextField lengthTextField;
    private JLabel stockLabel;
    private JLabel destinationPlaceLabel;
    private JComboBox<Stock> stockComboBox;
    private JComboBox<DestinationPlace> destinationPlaceComboBox;
    private JButton okButton;
    private JButton cancelButton;
    private Container pane;

    private JLabel addLabel(String name){
        JLabel label = new JLabel(name);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setSize(new Dimension(500,50));
        pane.add(label);
        return label;
    }

    public AddingRoadDialog(JFrame owner){
        super(owner,"Add road",true);
        this.owner = this;
        pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        lengthLabel = addLabel("Length");
        lengthTextField = new JTextField();
        lengthTextField.setMaximumSize(new Dimension(500,30));
        pane.add(lengthTextField);
        stockLabel = addLabel("From");
        Stock[] stocks = Database.getStocks();
        //addComboBox(stockComboBox, stocks);
        stockComboBox = new JComboBox<>(stocks);
        stockComboBox.setMaximumSize(new Dimension(500,30));
        pane.add(stockComboBox);
        destinationPlaceLabel = addLabel("Where");
        DestinationPlace[] destinationPlaces = Database.getDestinationPlaces();
        destinationPlaceComboBox = new JComboBox<>(destinationPlaces);
        destinationPlaceComboBox.setMaximumSize(new Dimension(500,30));
        pane.add(destinationPlaceComboBox);
        okButton = new JButton("Ok");
        okButton.addActionListener(ActionListener -> okButtonAction());
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
        int length;
        try{
            length = Integer.parseInt(lengthTextField.getText());
            Road road  = new Road((Stock)stockComboBox.getSelectedItem(),(DestinationPlace) destinationPlaceComboBox.getSelectedItem(),length);
            Database.insertRoad(road);
        }catch (NumberFormatException exception){

        }finally {
            setVisible(false);
        }
    }
    private void cancelButtonAction(){
        lengthTextField.setText("");
        stockComboBox.setSelectedIndex(0);
        destinationPlaceComboBox.setSelectedIndex(0);
        setVisible(false);
    }
}
