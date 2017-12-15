package com.rentalcars.Structures;

/**
 * A simple object used to encapsulate the data of a supplier
 */
public class Supplier
{
    private String supplierName;
    private Double rating;

    /**
     * Constructor used when we want to produce a new supplier
     * @param supplierName
     * @param rating
     */
    public Supplier(String supplierName, Double rating) {
        this.supplierName = supplierName;
        this.rating = rating;
    }

    /**
     * Constructor used when we want to copy a supplier from another car
     * @param toCopy
     */
    public Supplier(Supplier toCopy)
    {
        this.supplierName = toCopy.getSupplierName();
        this.rating = toCopy.getRating();
    }


    /**Just a bunch of setters and getters of the supplier*/
    public String getSupplierName()
    { return supplierName; }

    public void setSupplierName(String supplierName)
    { this.supplierName = supplierName; }

    public Double getRating()
    { return rating; }

    public void setRating(Double rating)
    { this.rating = rating; }
}
