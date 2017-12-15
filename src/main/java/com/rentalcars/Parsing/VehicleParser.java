package com.rentalcars.Parsing;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.rentalcars.Structures.SIPPSpecification;
import com.rentalcars.Structures.Supplier;
import com.rentalcars.Structures.Vehicle;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.ArrayList;

/**
 * VehiclePArser implements the IParser interface and is also a "Component", i.e. sets it up
 * as a Bean in spring
 */
@Component
public class VehicleParser implements IParser
{

    ObjectMapper map = new ObjectMapper();
    //The sipp index is read in, which is a json which maps a single character of sippCode into its human readable name
    private ArrayList<JsonNode> sippIndex = new ArrayList<>();
    //4 character string
    private String sipp;
    private String name;
    private Double price;
    private Supplier supplier;
    private SIPPSpecification vehicleSipp;
    private JsonNode currentVehicle;

    /**
     * Parses the file that is requested and changes it into an ArrayList of vehicles
     * @param fileName - the name of file to be parsed
     * @return  - An ArrayList of parsed vehicles from the json file
     */
    public ArrayList<Vehicle> parseFile(String fileName)
    {
        ArrayList<Vehicle> vehicleListParsed = new ArrayList<>();
        try
        {
            JsonNode coll = map.readTree(new FileReader(fileName));
            JsonNode searchList = coll.get("Search");
            ArrayNode vehicleList = (ArrayNode) searchList.get("VehicleList");
            //reads the sipp file and produces an object that allows you to map "M" to "Mini" etc.
            produceSippIndex();

            //for every vehicle
            for(int i = 0; i < vehicleList.size(); i++)
            {
                //get the current vehicle and collect all of the needed IVs to produce a new Vehicle
                currentVehicle = vehicleList.get(i);
                sipp = currentVehicle.get("sipp").toString().replaceAll("\"", "");
                name = currentVehicle.get("name").toString().replaceAll("\"", "");
                price = Double.parseDouble(currentVehicle.get("price").toString().replaceAll("\"", ""));
                //make a new supplier to be passed to vehicle
                supplier = new Supplier(currentVehicle.get("supplier").toString(), Double.parseDouble(currentVehicle.get("rating").toString().replaceAll("\"", "")));
                //make a new SIPPSpec to be passed into vehicle
                vehicleSipp = createSpecification(sipp);
                //create new vhiecle and add to the parsed list
                Vehicle vehicle = new Vehicle(sipp, vehicleSipp, name, price, supplier);
                vehicleListParsed.add(vehicle);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        //the parsed vehicle list
        return vehicleListParsed;
    }


    /**
     * creates a new instance of SIPPSpec to be passed into the vehicle that is being created
     * @param sipp - the 4 character string to be converted to a meaningful structure
     * @return - A new SIPPSpecification instance
     */
    public SIPPSpecification createSpecification(String sipp)
    {
        SIPPSpecification spec = new SIPPSpecification();
        //map first char to carType
        sippIndex.get(0).get(Character.toString(sipp.charAt(0))).asText();
        spec.setCarType(sippIndex.get(0).get(Character.toString(sipp.charAt(0))).asText());
        //check if second "Column" contains the required string, if so: check if it contains "door", if not : its a second part of "type"
        if(sippIndex.get(1).has(Character.toString(sipp.charAt(1))))
        {
            if(sippIndex.get(1).get(Character.toString(sipp.charAt(1))).asText().contains("door"))
            {
                spec.setNumberOfDoors(sippIndex.get(1).get(Character.toString(sipp.charAt(1))).asText().replaceAll("\"", ""));
            }
            else
            {
                spec.setCarType(spec.getCarType() + " " + sippIndex.get(1).get(Character.toString(sipp.charAt(1))).asText().replaceAll("\"", ""));
            }
        }
        //if not : check if it isnt a "second part" of car type e.g. "Luxury SUV"
        else if (sippIndex.get(0).has(Character.toString(sipp.charAt(1))))
        {
            spec.setCarType(spec.getCarType() + " "  + sippIndex.get(0).get(Character.toString(sipp.charAt(1))).asText());
        }
        //set the rest of the values for the new Spec
        spec.setTransmission(sippIndex.get(2).get(Character.toString(sipp.charAt(2))).asText());
        String[] fuelAndAC = (sippIndex.get(3).get(Character.toString(sipp.charAt(3))).asText()).split("/");
        spec.setFuel(fuelAndAC[0]);
        spec.setAC(fuelAndAC[1]);
        //return the new Spec that will have a meaningful structure for the cars SIPP
        return spec;
    }

    /**
     * Called at the start of parsing: it reads a JSON file that contains key : value list for each sipp character
     * easier than hardcoding and means the file can be changed easily
     */
    public void produceSippIndex()
    {
        try
        {
            //produce a JSONObject of the file
            JsonNode sippCodes = map.readTree(new FileReader("SIPPCodes.json"));
            //add all of the lists that within the file
            sippIndex.add(sippCodes.get("Type"));
            sippIndex.add(sippCodes.get("Doors"));
            sippIndex.add(sippCodes.get("Transmission"));
            sippIndex.add(sippCodes.get("FuelAC"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
