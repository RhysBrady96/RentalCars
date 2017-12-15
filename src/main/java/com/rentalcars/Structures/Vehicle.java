package com.rentalcars.Structures;

import java.util.Comparator;

public class Vehicle {
    private String sipp;
    private SIPPSpecification sippSpec;
    private String name;
    private Double price;
    private Supplier supplier;
    private int vehicleScore;
    private double sumScore;
    /**
     * Constuctor for making a new Vehicle
     * @param sipp - Sipp 4 letter code
     * @param sippSpec - Instance of SIPPSpec that holds sipp as a meaningful string
     * @param name - name of car
     * @param price - price of car
     * @param supplier - instance of the supplier of the car
     */
    public Vehicle(String sipp, SIPPSpecification sippSpec, String name, Double price, Supplier supplier)
    {

        this.sipp = sipp;
        this.sippSpec = sippSpec;
        this.name = name;
        this.price = price;
        this.supplier = supplier;
    }

    /**
     * Constructor used when you want to make a copy of the vehicle
     * @param toCopy - the vehicle to be copied
     */
    public Vehicle(Vehicle toCopy)
    {

        this.sipp = toCopy.getSipp();
        this.sippSpec = new SIPPSpecification(toCopy.getSippSpec());
        this.name = toCopy.getName();
        this.price = toCopy.getPrice();
        this.supplier = new Supplier(toCopy.getSupplier());
    }

    /**Setters and Getters of the class*/
    public int getVehicleScore() { return vehicleScore; }

    public void setVehicleScore(int vehicleScore) { this.vehicleScore = vehicleScore; }

    public double getSumScore() { return sumScore; }

    public void setSumScore(double sumScore) { this.sumScore = sumScore; }

    public String getSipp() { return sipp; }

    public void setSipp(String sipp) { this.sipp = sipp; }

    public SIPPSpecification getSippSpec() { return sippSpec; }

    public void setSippSpec(SIPPSpecification sippSpec) { this.sippSpec = sippSpec; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public Supplier getSupplier() { return supplier; }

    public void setSupplier(Supplier supplier) { this.supplier = supplier; }


    /**
     * A comparator that is used to order the ArrayList passed in, by vehicle prices
     */
    public static Comparator<Vehicle> Vehicleprice = new Comparator<Vehicle>()
    {
        @Override
        public int compare(Vehicle v1, Vehicle v2) {
            double price1 = v1.getPrice();
            double price2 = v2.getPrice();

            return Double.compare(price1, price2);
        }
    };

    /**
     * A comparator that is used to order the ArrayList is passed in, by the vehicleSumScores
     */
    public static Comparator<Vehicle> VehicleSumScores = new Comparator<Vehicle>()
    {
        @Override
        public int compare(Vehicle v1, Vehicle v2) {
            double sumScore1 = v1.getSumScore();
            double sumScore2 = v2.getSumScore();
            return Double.compare(sumScore2, sumScore1);
        }
    };

}