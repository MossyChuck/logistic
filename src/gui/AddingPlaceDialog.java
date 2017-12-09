package gui;

import db.Database;
import place.DestinationPlace;
import place.Place;
import place.Road;
import place.Stock;

import javax.swing.*;
import java.awt.*;

public class AddingPlaceDialog extends JDialog {
    private JDialog owner;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel typeLabel;
    private JComboBox<Type> typeComboBox;
    private JButton okButton;
    private JButton cancelButton;
    private Container pane;

    public enum Type{
        STOCK,
        DESTINATION_PLACE;

        @Override
        public String toString() {
            if(this == Type.STOCK){
                return "Stock";
            }else if(this == Type.DESTINATION_PLACE){
                return "Destination place";
            }else{
                return "";
            }
        }
    }

    private JLabel addLabel(String name){
        JLabel label = new JLabel(name);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(new Dimension(500,50));
        pane.add(label);
        return label;
    }

    public AddingPlaceDialog(JFrame owner){
        super(owner,"Add road",true);
        this.owner = this;
        pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        nameLabel = addLabel("Name");
        nameTextField = new JTextField();
        nameTextField.setMaximumSize(new Dimension(500,30));
        pane.add(nameTextField);
        Type[] types = {Type.STOCK, Type.DESTINATION_PLACE};
        typeComboBox = new JComboBox<>(types);
        typeComboBox.setMaximumSize(new Dimension(500,30));
        pane.add(typeComboBox);
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
        Place place;
        if(typeComboBox.getSelectedItem() == Type.STOCK){
            place = new Stock(nameTextField.getText());
        }else {
            place = new DestinationPlace(nameTextField.getText());
        }
        Database.insertPlace(place);
        setVisible(false);
    }
    private void cancelButtonAction(){
        nameTextField.setText("");
        typeComboBox.setSelectedIndex(0);
        setVisible(false);
    }
}