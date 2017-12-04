package place;

public class Road {
    private DestinationPlace destinationPlace;
    private Stock stock;
    private int length;

    public Road(){}
    public Road(Stock stock,DestinationPlace place,int length){
        this.destinationPlace = place;
        this.length = length;
        this.stock = stock;
    }
    public Road(String stockName,String destinationPlaceName, int length){
        this.destinationPlace = new DestinationPlace(destinationPlaceName);
        this.length = length;
        this.stock = new Stock(stockName);
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
}
