import db.Database;
import gui.Gui;

public class Main {
    public static void main(String[] args){
        Database.init();
        new Gui().build();
        //new OrderDialog(new JFrame("frame"));
    }

}
