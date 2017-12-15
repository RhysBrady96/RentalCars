package com.rentalcars.Run;

import com.rentalcars.Parsing.IParser;
import com.rentalcars.Parsing.VehicleParser;
import com.rentalcars.Structures.Vehicle;
import com.rentalcars.Tasks.*;
import java.util.ArrayList;

/**
 * This is the main method used when we simply want to print out the results of the task to the console
 */
public class RentalCarsNoREST
{

    private ITask task;
    private IParser vehicleParser;
    private ArrayList<Vehicle> vehicleList;


    public void setVehicleParser(IParser vehicleParser)
    {
        this.vehicleParser = vehicleParser;
    }

    public void setTask(ITask task)
    {
        this.task = task;
    }


    /**
     * performs the parsing of the parser IV, setting the vehicleList as the parsedList from the method
     */
    public void parseVehicles()
    {
        vehicleList = vehicleParser.parseFile("Vehicles.json");
    }

    /**
     *  tells the task to perform itself on the parsed vehicleList
     * @return - a string of the results
     */
    public String performTask()
    {
        return task.performTask(this.vehicleList);
    }

    /**
     * if main is run, each of the tasks are run immediatley after the other with no rest API
     * @param args - unused currently
     */
    public static void main(String [] args)
    {
        //perform parsing and then ascending price
        RentalCarsNoREST application = new RentalCarsNoREST();
        application.setVehicleParser(new VehicleParser());
        application.parseVehicles();
        application.setTask(new AscendingPrice());
        System.out.println(application.performTask());

        //perform the other tasks one after the other
        application.setTask(new CalculateSpecification());
        System.out.println(application.performTask());

        application.setTask(new HighestRatedPerType());
        System.out.println(application.performTask());

        application.setTask(new VehicleScoring());
        System.out.println(application.performTask());
    }
}
