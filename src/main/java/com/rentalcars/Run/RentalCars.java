package com.rentalcars.Run;

import com.rentalcars.Parsing.IParser;
import com.rentalcars.Structures.Vehicle;
import com.rentalcars.Tasks.ITask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;

/**
 * RentalCars is the RestController and so handles all Rest Requests to the application
 */
@RestController
public class RentalCars
{
    @Autowired
    private ApplicationContext context;

    /***
     * when a get request to "index/<taskName>" is sent, this method is run
     * @param task - the name of the task to be run e.g. ascendingPrice
     * @return - A string that is the result of the task
     */
    @RequestMapping(value = "/{task}",
            method = RequestMethod.GET,
            produces = "text/plain")
    public String performTasks(@PathVariable("task") String task)
    {
        if(context.containsBean(task))
        {
            //gets an instance of the parser and task to be run
            ITask taskToPerform = (ITask) context.getBean(task);
            IParser parser = (IParser) context.getBean("vehicleParser");
            //gets the parsed vehicle Lsit and tthe results of the task
            ArrayList<Vehicle> vehicleList = parser.parseFile("Vehicles.json");
            return taskToPerform.performTask(vehicleList);
        }
        else
        {
            return "No Such Task exists";
        }
    }
}
