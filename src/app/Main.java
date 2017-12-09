package app;

import db.Database;
import gui.Gui;

public class Main {
    private static Gui gui;
    public static void main(String[] args){
        Database.init();
        gui = new Gui();
        gui.build();
        //new OrderDialog(new JFrame("frame"));
    }

    public static Gui getGui() {
        return gui;
    }
}
