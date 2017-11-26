package items;

public class Item {
    private double volume;
    private double weight;
    private String name;

    public Item(){
        name = "item";
        weight = 1;
        volume = 1;
    }
    public Item(String name,double volume, double weight){
        this.name = name;
        this.volume = volume;
        this.weight = weight;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }

    public double getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
