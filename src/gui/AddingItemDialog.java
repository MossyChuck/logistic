package gui;

import exception.MySqlException;
import orders.Item;

import javax.swing.*;
import java.awt.*;

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

    private JLabel addLabel(String name){
        JLabel label = new JLabel(name);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setHorizontalAlignment(SwingConstants.LEFT);
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

    public AddingItemDialog(JDialog owner){
        super(owner,"Add item",true);
        pane = getContentPane();
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        nameLabel = addLabel("Name");
        nameTextField = addTextField();
        volumeLabel = addLabel("Volume");
        volumeTextField = addTextField();
        weightLabel = addLabel("Weight");
        weightTextField = addTextField();
        okButton = new JButton("Ok");
        okButton.addActionListener(ActionListener -> okButtonAction());
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(ActionListener -> cancelButtonAction());
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p,BoxLayout.X_AXIS));
        p.add(okButton);
        p.add(cancelButton);
        pane.add(p);

        //setSize(new Dimension(400,300));
        setLocationRelativeTo(null);

        pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);

    }

    private void okButtonAction(){
        OrderDialog od = (OrderDialog)getOwner();
        try {
            String name = nameTextField.getText();
            double volume = Double.parseDouble(volumeTextField.getText());
            double weight = Double.parseDouble(weightTextField.getText());
            if(name == "" || volume<=0 || weight <=0 || name == null){
                JOptionPane.showMessageDialog(null,"Check your input");
                return;
            }
            Item item = new Item(name,volume,weight);
            od.addItem(item);
            JOptionPane.showMessageDialog(null,"success");
        }catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(null,"Check your input");
            return;
        }
        nameTextField.setText("");
        volumeTextField.setText("");
        weightTextField.setText("");
        setVisible(false);
    }
    private void cancelButtonAction(){
        nameTextField.setText("");
        volumeTextField.setText("");
        weightTextField.setText("");
        setVisible(false);
    }
}
