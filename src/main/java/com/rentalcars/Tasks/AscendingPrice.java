package com.rentalcars.Tasks;

import com.rentalcars.Structures.Vehicle;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Ascending price takes in the vehicleList and then sorts them based on the price, in ascending order
 */
@Component
public class AscendingPrice implements ITask
{

    private ArrayList<Vehicle> vehicleList;

    public AscendingPrice()
    {

    }

    /**
     * This is the Ascending price implementation of performTask (from ITask)
     * @param vehicleList - list of vehicles to perform the task on
     * @return - String of all vehicles sorted by price
     */
    @Override
    public String performTask(ArrayList<Vehicle> vehicleList)
    {
        this.vehicleList = vehicleList;
        return printFormat();
    }

    protected String printFormat()
    {
        StringBuilder builder = new StringBuilder();
        //sort the vehicles
        Collections.sort(vehicleList, Vehicle.Vehicleprice);
        //go through all of the vehicles and print send the result back
        for(Vehicle currentVehicle : vehicleList)
        {
            builder.append("{" + currentVehicle.getName() + "} - {" + currentVehicle.getPrice() + "}\n");
        }
        return builder.toString();
    }
}
