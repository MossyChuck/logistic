package app;

import db.Database;
import gui.Gui;

public class Main {
    private static Gui gui;

    public static void main(String[] args){
        Database.init();
        gui = new Gui();
        gui.build();
        //new AddingOrderDialog(new JFrame("frame"));
        Database.closeAll();
    }

    public static Gui getGui() {
        return gui;
    }
}
