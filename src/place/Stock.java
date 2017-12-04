package place;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Stock extends Place{
    public Stock(){}
    public Stock(String name){
        super(name);
    }
    public Stock(String name, Road[] roads){
        super(name);
    }
    public Stock(String name, ArrayList<Road> roads){
        super(name);
    }
}
