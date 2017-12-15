package com.rentalcars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RunApplication is run when the user wants to start up a server
 */
@SpringBootApplication
public class RunApplication
{

    //runs the server and all requests are recieved by the "@RestController", which is "RentalCars"
	public static void main(String[] args)
	{
		SpringApplication.run(RunApplication.class, args);
	}
}
