import gui.Gui;
import gui.OrderDialog;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        Database.init();
        new Gui().build();
        //new OrderDialog(new JFrame("frame"));
    }

}
