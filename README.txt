The following tasks have been implemented in this project:
	Order vehicle by ascending price
	"Calculate" the specification from the 4 character sipp string
	Get the best supplier per car type
	Get the "Sum" of scores of each vehicle

There were some things i wasnt 100% what was wanted in the spec:
	In task 2 "calculate specification": 	The format of the examples were different to the format 
						of the output described above. so i just followed the original output.
 					      
						Also, im not sure what was meant by "door/ car type". I have assumed it means
						that this character can either describe the number of doors. If the "Value" of the 
						character isnt in column 2, or doesnt contain "door" in the text, i have treated it
						as the "second half" of the door type. As such, the numberOfDoors is null in these cases and
						so isnt printed out.

Note that wherever the program is Run, the 2 files "Vehicles.json" and "SIPPCodes.json" must also be there. For simplicity, the program jsut reads in these 2 files.

Furthermore. If you go into the "JarForRest" folder, you will find a jar file that will run the springboot application. Once this is run using "java -jar Run-0.0.1-SNAPSHOT.jar"
you can then send GET requests to "localhost:8080/{nameOfTask}" where {taskName} can be one of the following
	ascendingPrice
	calculateSpecification
	highestRatedPerType
	vehicleScoring

I.e. these are names of the classes that perform the tasks. Rest gets the appropriate Bean from this parameter, and runs the correct task.

If you dont want to run the program in Rest, there is also another class called "RentalCarsNoRest" that has a main method. When you run this in intelliJ it will go through each of the
tasks 1 by 1 and print them to the console.

Finally, there is a test class called "RunApplicationTests". It simply has hardcoded in values and checks if the output of each of the 4 tasks is as expected. Running this class as a JUnit
application will show that the tasks worked as intended.



