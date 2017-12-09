package gui;

import app.Main;
import db.Database;
import items.Item;
import park.Vehicle;
import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;

public class AddingVehicleDialog extends JDialog {
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel volumeLabel;
    private JTextField volumeTextField;
    private JLabel weightLabel;
    private JTextField weightTextField;
    private JLabel maxSpeedLabel;
    private JTextField maxSpeedTextField;
    private JButton okButton;
    private JButton cancelButton;
    private Container pane;

    private JLabel addLabel(String name){
        JLabel label = new JLabel(name);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(new Dimension(500,50));
        pane.add(label);
        return label;
    }
    private JTextField addTextField(){
        JTextField textField = new JTextField();
        textField.setSize(new Dimension(500,30));
        pane.add(textField);
        return textField;
    }

    public AddingVehicleDialog(JFrame owner){
        super(owner,"Add vehicle",true);
        pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        nameLabel = addLabel("Model");
        nameTextField = addTextField();
        volumeLabel = addLabel("Volume");
        volumeTextField = addTextField();
        weightLabel = addLabel("Carrying capacity");
        weightTextField = addTextField();
        maxSpeedLabel = addLabel("Max speed");
        maxSpeedTextField = addTextField();
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

        String model = nameTextField.getText();
        try {
            double volume = Double.parseDouble(volumeTextField.getText());
            double weight = Double.parseDouble(weightTextField.getText());
            double maxSpeed  = Double.parseDouble(maxSpeedTextField.getText());
            Vehicle v = new Vehicle(model,maxSpeed,volume,weight);
            Database.insertVehicle(v);
            //Main.getGui().buildVehicleTable();
        }catch (NumberFormatException exception) {
            System.out.println("parse err");
        }finally {
            nameTextField.setText("");
            volumeTextField.setText("");
            weightTextField.setText("");
            maxSpeedTextField.setText("");
            setVisible(false);
        }
    }
    private void cancelButtonAction(){
        nameTextField.setText("");
        volumeTextField.setText("");
        weightTextField.setText("");
        maxSpeedTextField.setText("");
        setVisible(false);
    }
}