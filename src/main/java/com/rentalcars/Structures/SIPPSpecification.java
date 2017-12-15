package com.rentalcars.Structures;

/**
 * SIPPSpecification is a simple class just used to encapsulate the SIPP in a readable way
 */
public class SIPPSpecification
{
    //the variables used to define a SIPP specification
    private String carType;
    private String numberOfDoors;
    private String transmission;
    private String fuel;
    private String AC;


    public SIPPSpecification()
    {

    }

    /**
     * The constructor that is used when we want to copy another SIPP Specification
     * @param toCopy - the sipp to be copied
     */
    public SIPPSpecification(SIPPSpecification toCopy)
    {
        this.carType = toCopy.getCarType();
        this.numberOfDoors = toCopy.getNumberOfDoors();
        this.transmission = toCopy.getTransmission();
        this.fuel = toCopy.getFuel();
        this.AC = toCopy.getAC();
    }

    /**
     * The constructor used when we want to create a new SIPP
     * @param carType
     * @param numberOfDoors - null if its an extension of carType
     * @param transmission
     * @param fuel
     * @param AC
     */
    public SIPPSpecification(String carType, String numberOfDoors, String transmission, String fuel, String AC)
    {
        this.carType = carType;
        this.numberOfDoors = numberOfDoors;
        this.transmission = transmission;
        this.fuel = fuel;
        this.AC = AC;
    }


    /**Just a Bunch of setters and getters for the object*/
    public String getCarType()
    { return carType; }

    public void setCarType(String carType) { this.carType = carType; }

    public String getNumberOfDoors() { return numberOfDoors; }

    public void setNumberOfDoors(String numberOfDoors) { this.numberOfDoors = numberOfDoors; }

    public String getTransmission() { return transmission; }

    public void setTransmission(String transmission) { this.transmission = transmission; }

    public String getFuel() { return fuel; }

    public void setFuel(String fuel) { this.fuel = fuel; }

    public String getAC() { return AC; }

    public void setAC(String AC) { this.AC = AC; }
}
