package com.rentalcars.Tasks;

import com.rentalcars.Structures.Vehicle;

import java.util.ArrayList;

/**
 * This is the interface that should be implemented if we want to create a new TaskClass
 */
public interface ITask
{
    /**
     * Performs the defined task of the class that implements this
     * @param vehicleList - list of vehicles to perform the task on
     * @return - a String that is the results of the task
     */
    String performTask(ArrayList<Vehicle> vehicleList);
}
