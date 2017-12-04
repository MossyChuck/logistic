package place;

public class Place {
    private String name;

    public Place(){
        this.name = "Place";
    }
    public Place(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
