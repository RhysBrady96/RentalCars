package com.rentalcars.Parsing;


import com.rentalcars.Structures.Vehicle;

import java.util.ArrayList;


/**
 * Interface that should be implemented if a new vehicle parsing technique is to be made
 */
public interface IParser
{
    /**
     *
     * @param fileName - the name of file to be parsed
     * @return - an ArrayList of vehicles
     */
    public ArrayList<Vehicle> parseFile(String fileName);

}
