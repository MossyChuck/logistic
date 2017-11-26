import javax.swing.*;
import java.awt.*;

public class Gui {
    private JFrame mainFrame;
    private JPanel mainPanel;
    private JButton button;

    public Gui(){

    }

    public void build(){
        mainFrame = new JFrame();
        mainPanel = new JPanel();
        button = new JButton("qwe");
        mainPanel.add(button);
        mainFrame.getContentPane().add(mainPanel);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(400,400));
        mainFrame.setVisible(true);
    }
}
