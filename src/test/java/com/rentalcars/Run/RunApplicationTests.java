package com.rentalcars.Run;

import com.rentalcars.Parsing.VehicleParser;
import com.rentalcars.Structures.SIPPSpecification;
import com.rentalcars.Structures.Supplier;
import com.rentalcars.Structures.Vehicle;
import com.rentalcars.Tasks.AscendingPrice;
import com.rentalcars.Tasks.HighestRatedPerType;
import com.rentalcars.Tasks.ITask;
import com.rentalcars.Tasks.VehicleScoring;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Performs tests on all of the tasks in the system
 */
public class RunApplicationTests {

	//Just a bunch of hardcoded vehicles to use in testing
	private ArrayList<Vehicle> testOfVehicles = new ArrayList();

	private SIPPSpecification forVehicle1 = new SIPPSpecification("Mini", "2 doors",
			"Manual", "Petrol", "AC");
	private Supplier supp1 = new Supplier("Hertz", 8.9);
	private Vehicle toAdd1 = new Vehicle("MBMR", forVehicle1, "VW Jetta", 1.0, supp1);

	private SIPPSpecification forVehicle2 = new SIPPSpecification("Economy", "2 doors",
			"Manual", "Petrol", "no AC");
	private Supplier supp2 = new Supplier("Hertz", 5.1);
	private Vehicle toAdd2 = new Vehicle("EBMN", forVehicle2, "VW Jetta", 2.0, supp2);

	private SIPPSpecification forVehicle3 = new SIPPSpecification("Premium", "2 doors",
			"Automatic", "Petrol", "AC");
	private Supplier supp3 = new Supplier("Hertz", 6.6);
	private Vehicle toAdd3 = new Vehicle("PBAR", forVehicle3, "VW Jetta", 3.0, supp3);




	@Test
	public void checkAscendingPriceProducesTheCorrectOutput()
	{
		testOfVehicles.add(toAdd1);
		testOfVehicles.add(toAdd2);
		testOfVehicles.add(toAdd3);

		ITask ascending = new AscendingPrice();
		String[] ordered = ascending.performTask(testOfVehicles).split("\n");
		Assert.assertTrue(ordered[0].contains("1.0"));
		Assert.assertTrue(ordered[1].contains("2.0"));
		Assert.assertTrue(ordered[2].contains("3.0"));
	}


	/**
	 * SIPPCodes used in determining if the correct SIPPSpec is produced
	 */
	private String sipp1 = "MDAR";
	private String sipp2 = "LCMR";
	private String sipp3 = "IWAN";
	@Test
	public void checkThatTheCorrectSIPPSpecificationsAreProducedFromOrigionalString()
	{
		VehicleParser vehicleParserForTest = new VehicleParser();
		vehicleParserForTest.produceSippIndex();
		SIPPSpecification results1 =  vehicleParserForTest.createSpecification(sipp1);
		SIPPSpecification results2 =  vehicleParserForTest.createSpecification(sipp2);
		SIPPSpecification results3 =  vehicleParserForTest.createSpecification(sipp3);



		Assert.assertTrue(results1.getCarType().equals("Mini") && results1.getNumberOfDoors().equals("5 doors")
				&& results1.getTransmission().equals("Automatic") && results1.getFuel().equals("Petrol")
				&& results1.getAC().equals("AC"));

		Assert.assertTrue(results2.getCarType().equals("Luxury") && results2.getNumberOfDoors().equals("4 doors")
				&& results2.getTransmission().equals("Manual") && results2.getFuel().equals("Petrol")
				&& results2.getAC().equals("AC"));

		Assert.assertTrue(results3.getCarType().equals("Intermediate Estate") && (results3.getNumberOfDoors() == null)
				&& results3.getTransmission().equals("Automatic") && results3.getFuel().equals("Petrol")
				&& results3.getAC().equals("no AC"));
	}


	//2 new vehicles to check if, of 2 cars of the same type, it gets the correct car with the highest supplier rating
	private SIPPSpecification getForVehicle4 = new SIPPSpecification("Mini", "2 doors",
			"Automatic", "Petrol", "no AC");
	private Supplier supp4 = new Supplier("Sixt", 1.1);
	private Vehicle repeatedType1 = new Vehicle("MBMR", getForVehicle4, "VW Jetta", 3.0, supp4);

	private SIPPSpecification getForVehicle5 = new SIPPSpecification("Premium", "4 doors",
			"Automatic", "Petrol", "no AC");
	private Supplier supp5 = new Supplier("Sixt", 9.9);
	private Vehicle repeatedType2 = new Vehicle("MBMR", getForVehicle5, "VW Jetta", 3.0, supp5);

	@Test
	public void checkThatTheTaskReturnsTheHighestRatedSupplierPerVehicleType()
	{
		testOfVehicles.add(toAdd1);
		testOfVehicles.add(toAdd2);
		testOfVehicles.add(toAdd3);
		testOfVehicles.add(repeatedType1);
		testOfVehicles.add(repeatedType2);

		ITask toPerform = new HighestRatedPerType();

		String results[] = toPerform.performTask(testOfVehicles).split("\n");

		Assert.assertTrue(results[0].contains("Sixt") && results[0].contains("9.9"));
		Assert.assertTrue(results[1].contains("Hertz") && results[1].contains("8.9"));
		Assert.assertTrue(results[2].contains("Hertz") && results[2].contains("5.1"));
	}



	@Test
	public void checkThatTheVehicleScoringIsCorrectAndOrdered()
	{
		testOfVehicles.add(toAdd1);
		testOfVehicles.add(toAdd2);
		testOfVehicles.add(toAdd3);
		testOfVehicles.add(repeatedType1);
		testOfVehicles.add(repeatedType2);

		ITask taskToPerform = new VehicleScoring();
		String results[] = taskToPerform.performTask(testOfVehicles).split("\n");

		Assert.assertTrue(results[0].contains("14.9"));
		Assert.assertTrue(results[1].contains("13.6"));
		Assert.assertTrue(results[2].contains("11.9"));
		Assert.assertTrue(results[3].contains("6.1"));
		Assert.assertTrue(results[4].contains("6.1"));
	}
}
