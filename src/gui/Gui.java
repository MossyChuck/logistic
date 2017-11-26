package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JButton orderButton;
    private JButton driverLoginButton;
    private JButton adminLoginButton;
    private OrderDialog orderDialog;

    public Gui(){
        orderButton = new JButton("make order");
        adminLoginButton = new JButton("admin page");
        driverLoginButton = new JButton("driver page");
    }

    public void build(){
        mainFrame = new JFrame();
        mainPanel = new JPanel();

        mainPanel.add(driverLoginButton);
        mainPanel.add(orderButton);
        orderButton.addActionListener(new orderButtonActionListener());
        mainPanel.add(adminLoginButton);
        mainFrame.getContentPane().add(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(400,400));
        mainFrame.setVisible(true);
    }

    class orderButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(orderDialog == null){
                orderDialog = new OrderDialog(mainFrame);
            } else{
                orderDialog.setVisible(true);
            }

        }
    }
}
