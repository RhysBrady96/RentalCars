package com.rentalcars.Tasks;


import com.rentalcars.Structures.SIPPSpecification;
import com.rentalcars.Structures.Vehicle;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

/**
 * Calculate Specification is already done in "Parser" (because SIPPSpec is an instance of Vehicle)
 * Therefore this task simply goes through each vehicle and prints out its SIPPSpec instance
 */
@Component
public class CalculateSpecification implements ITask
{
    private ArrayList<Vehicle> vehicleList;

    public CalculateSpecification()
    {

    }

    /**
     * implemennts performTask from ITask
     * @param vehicleList - list of vehicles to perform the task on
     * @return
     */
    @Override
    public String performTask(ArrayList<Vehicle> vehicleList)
    {
        this.vehicleList = vehicleList;
        return printFormat();
    }

    /**
     * Formats the results in the specified way and returns it as a string
     * @return - a String of the results
     */
    private String printFormat()
    {
        StringBuilder builder = new StringBuilder();
        SIPPSpecification currentSIPP;
        //go through all the vehicles, get its SIPPSpec, and print it out in a readable way
        for (Vehicle currentVehicle : vehicleList)
        {
            currentSIPP = currentVehicle.getSippSpec();
            builder.append("{" + currentVehicle.getName() + "}-{" + currentVehicle.getSipp() + "}-{" +
                    currentSIPP.getCarType() + "}-{" + currentSIPP.getNumberOfDoors() +
                    "}-{" + currentSIPP.getTransmission() + "}-{" + currentSIPP.getFuel() + "}-{" +
                    currentSIPP.getAC() + "}\n");
        }
        //remove "null" pointers where "doors/ carType" was a second part of carType (and therefore "doors" was null)
        return builder.toString().replaceAll("\\{null}-", "");
    }
}
