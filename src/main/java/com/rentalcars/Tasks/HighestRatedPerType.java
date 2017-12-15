package com.rentalcars.Tasks;



import com.rentalcars.Structures.Supplier;
import com.rentalcars.Structures.Vehicle;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * RankHighest goes through the entire vehicleList, checks if the supplier of the carType is better than the current best
 * If so, it gets put onto the hashmap of best values
 */
@Component
public class HighestRatedPerType implements ITask
{

    //unordered hashmaps of best suppliers of a carType
    HashMap<String, Vehicle> highestRated = new HashMap<>();
    //ordered version of the hashmap
    ArrayList<Map.Entry<String, Vehicle>> highestRatedOrdered;
    private ArrayList<Vehicle> vehicleList;

    public HighestRatedPerType()
    {

    }

    /**
     * firstly gets the best supplier per car type, then it makes an unordered arraylist of the results
     * finally it calls "printformat", which orders the arraylist and sends the results back
     * @param vehicleList - list of vehicles to perform the task on
     * @return - A string of the results
     */
    @Override
    public String performTask(ArrayList<Vehicle> vehicleList)
    {
        this.vehicleList = vehicleList;
        rankHighest();
        highestRatedOrdered = new ArrayList<>(highestRated.entrySet());
        return printFormat();
    }

    /**
     * Takes the unordered arraylist, orders it by supplier rating
     * @return - A string of the results
     */
    protected String printFormat()
    {
        StringBuilder builder = new StringBuilder();
        //order the arraylist on supplier rating
        Collections.sort(highestRatedOrdered, new Comparator<Map.Entry<String, Vehicle>>() {
            @Override
            public int compare(Map.Entry<String, Vehicle> type1, Map.Entry<String, Vehicle> type2) {
                double rating1 = type1.getValue().getSupplier().getRating();
                double rating2 = type2.getValue().getSupplier().getRating();
                return Double.compare(rating2, rating1);
            }
        });
        //go through the ordered carTypes and return the results
        for(Map.Entry<String, Vehicle> bestOfType : highestRatedOrdered)
        {
            Vehicle best = bestOfType.getValue();
            builder.append("{" + best.getName() + "}-{" + best.getSippSpec().getCarType() +
                    "}-{" + best.getSupplier().getSupplierName() + "}-{" + best.getSupplier().getRating()
                    + "}\n");
        }
        return builder.toString();
    }

    /**
     * Goes through all of the vehicles and makes a hashmap of the best suppliers of each vehicle
     */
    private void rankHighest()
    {
        Supplier currentSupplier;
        for (Vehicle currentVehicle : vehicleList)
        {
            currentSupplier = currentVehicle.getSupplier();
            String type;
            type = currentVehicle.getSippSpec().getCarType();

            //Check if this type of car has already been seen - If So :
            if (highestRated.containsKey(type))
            {
                //check if this car has a higher rated supplier than the previous one of the same type
                if(highestRated.get(type).getSupplier().getRating() < currentSupplier.getRating())
                {
                    highestRated.put(type, new Vehicle(currentVehicle));
                }
            }
            //otherwise just add this car to the list
            else
            {
                highestRated.put(type, new Vehicle(currentVehicle));
            }
        }
    }
}
