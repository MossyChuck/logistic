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

public class AddingItemDialog extends JDialog {
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel volumeLabel;
    private JTextField volumeTextField;
    private JLabel weightLabel;
    private JTextField weightTextField;
    private JButton okButton;
    private JButton cancelButton;
    private Container pane;

    private void addLabel(JLabel label, String name){
        label = new JLabel(name);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setSize(new Dimension(500,50));
        pane.add(label);
    }

    public AddingItemDialog(JDialog owner){
        super(owner,"Add item",true);
        pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        addLabel(nameLabel,"Name");
        nameTextField = new JTextField();
        nameTextField.setSize(new Dimension(500,30));
        pane.add(nameTextField);
        addLabel(volumeLabel,"Volume");
        volumeTextField = new JTextField();
        volumeTextField.setSize(new Dimension(500,30));
        pane.add(volumeTextField);
        addLabel(weightLabel,"Weight");
        weightTextField = new JTextField();
        weightTextField.setSize(new Dimension(500,30));
        pane.add(weightTextField);
        okButton = new JButton("Ok");
        okButton.addActionListener(new okButtonActionListener());
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

    class okButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            OrderDialog od = (OrderDialog)getOwner();
            String name = nameTextField.getText();
            double volume = Double.parseDouble(volumeTextField.getText());
            double weight = Double.parseDouble(weightTextField.getText());
            Item item = new Item(name,volume,weight);
            od.addItem(item);
        }
    }
}
