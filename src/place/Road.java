package place;

public class Road {
    private DestinationPlace destinationPlace;
    private Stock stock;
    private int length;

    public Road(Stock stock,DestinationPlace place,int length){
        this.destinationPlace = place;
        this.length = length;
        this.stock = stock;
    }

    public DestinationPlace getDestinationPlace() {
        return destinationPlace;
    }
    public Stock getStock() {
        return stock;
    }
    public int getLength() {
        return length;
    }
    public void setDestinationPlace(DestinationPlace destinationPlace) {
        this.destinationPlace = destinationPlace;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
