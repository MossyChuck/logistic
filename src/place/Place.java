package place;

public class Place {
    private String name;

    public Place(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
