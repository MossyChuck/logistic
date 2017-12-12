package park;

import java.util.Comparator;

public class Vehicle{
    private String model;
    private double maxSpeed;
    private double volume;
    private double maxWeight;


    public Vehicle(){
        this.maxSpeed = 60;
        this.volume = 10;
        this.maxWeight = 100;
        this.model = "";
    }
    public Vehicle(String model,double maxSpeed,double volume,double maxWeight){
        this.maxSpeed = maxSpeed;
        this.volume = volume;
        this.maxWeight = maxWeight;
        this.model = model;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getVolume() {
        return volume;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public String getModel() {
        return model;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public static Comparator<Vehicle> VehicleComparator = new Comparator<Vehicle>(){
        public int compare(Vehicle v1, Vehicle v2) {
            double deltaWeight = v1.getMaxWeight() - v2.getMaxWeight();
            if(deltaWeight>0){
                return 1;
            }else if(deltaWeight < 0){
                return -1;
            }else {
                double deltaVolume = v1.getVolume() - v2.getVolume();
                if (deltaVolume > 0) {
                    return 1;
                } else if (deltaVolume < 0){
                    return -1;
                }else {
                    double deltaSpeed = v1.getMaxSpeed() - v2.getMaxSpeed();
                    if(deltaSpeed > 0){
                        return 1;
                    }else if(deltaSpeed < 0){
                        return -1;
                    }else {
                        return 0;
                    }
                }
            }

        }
    };

}
