package com.rentalcars.Tasks;

import com.rentalcars.Structures.Vehicle;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Vehicle scoring adds a VehicleScore to each car, and then adds this to supplier score to create a sum of
 * the scores for the vehicle
 */
@Component
public class VehicleScoring implements ITask
{

    private ArrayList<Vehicle> vehicleList;

    public VehicleScoring()
    {

    }

    /**
     * calls a method to produce the vehicle scores and save them to the vehicle object (might be needed later)
     * Sorts the vehicles by the sum of scores
     * @param vehicleList - list of vehicles to perform the task on
     * @return - a String of the results
     */
    @Override
    public String performTask(ArrayList<Vehicle> vehicleList)
    {
        this.vehicleList = vehicleList;
        produceVehicleScores();
        produceSumScores();
        //sort the results by the sum of scores
        Collections.sort(vehicleList, Vehicle.VehicleSumScores);
        return printFormat();
    }

    /**
     * sends the results back as a string
     * @return
     */
    protected String printFormat()
    {
        StringBuilder builder = new StringBuilder();
        Vehicle currentVehicle;
        for(int i = 0; i < vehicleList.size(); i++)
        {
            currentVehicle = vehicleList.get(i);
            builder.append("{" + currentVehicle.getName() + "}-{" + currentVehicle.getVehicleScore() + "}-{" +
                    currentVehicle.getSupplier().getRating() + "}-{" + currentVehicle.getSumScore() + "}\n");
        }
        return builder.toString();
    }

    /**
     * Goes through each vehicle and makes a correct vehicle score
     */
    private void produceVehicleScores()
    {
        HashMap<String, Integer> scoreMapping = new HashMap<>();
        scoreMapping.put("Manual", 1);
        scoreMapping.put("Automatic", 5);
        scoreMapping.put("no AC", 2);
        int vehicleScore;
        //goes through all vehicles
        for (Vehicle currentVehicle : vehicleList)
        {
            //checks if the vehicle is manual or automatic
            vehicleScore = scoreMapping.get(currentVehicle.getSippSpec().getTransmission());
            //checks if the vehicle is NOT "no AC", i.e... it has AC
            if(!currentVehicle.getSippSpec().getAC().contains("no AC"))
            {
                vehicleScore += 2;
            }
            //sets the vehicle Score of the vehicle
            currentVehicle.setVehicleScore(vehicleScore);
        }
    }

    /**
     * Sum Scores just adds the vehicle scores from before and adds it to the vehicles supplier score
     */
    private void produceSumScores()
    {
        for (Vehicle currentVehicle : vehicleList)
        {
            currentVehicle.setSumScore(currentVehicle.getVehicleScore() + currentVehicle.getSupplier().getRating());
        }
    }
}
