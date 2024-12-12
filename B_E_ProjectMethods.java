/*
 *  File name: B_E_ProjectMethods.java
 * Purpose: Contains helper methods for the Election Simulator.
 * Coder: Benson Ezeamah
 * Date: Nov 2, 2024
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
public class B_E_ProjectMethods {
	
	/*
	 * Method name: 	readAllElectionData
	 * Purpose:			to  to read all election data		
	 * Accepts:			the data file path, a region names array, a EC Votes array and a probabilities array incorporates exception handling and prints an error message if the file isn’t found
	 * Returns:			boolean true only if the arrays were successfully populated from the file data
	 */
    // Method to read all election data
    public static boolean readAllElectionData(String filePath, String[] regions, int[] electoralVotes, int[] probabilities)throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;
            // Read each line of the CSV file
            while ((line = reader.readLine()) != null && index < regions.length) {
                String[] data = line.split(",");
                regions[index] = data[0]; // Region name
                electoralVotes[index] = Integer.parseInt(data[1]); // Electoral votes
                probabilities[index] = Integer.parseInt(data[2]); // Trump's winning probability
                index++;
            }
            return true; // Successfully populated arrays
        } catch (FileNotFoundException e) {
            System.out.println("Error: The file was not found.");
        } catch (Exception e) {
            System.out.println("Error: Unable to read the file.");
        }
        return false; // Failed to populate arrays
    }
    
    /*
 	 * Method name: 	getRegionResult
 	 * Purpose:			to get the region result		
 	 * Accepts:			a probability
 	 * Returns:			boolean true if Trump wins or false if Harris wins
 	 */
    // Method to get region result based on probability
    public static boolean getRegionResult(int probability) {
   	 	// Generate a random number between 0 and 100
        double randomValue = Math.random() * 100;
        // Return true if Trump's probability is greater than the random value
        return probability > randomValue;
    }
    
    /*
  	 * Method name: 	reportOutcome
  	 * Purpose:			to reportOutcome		
  	 * Accepts:			the number of EC Votes won by Trump and the number won by Harris prints a formatted summary of the election simulation’s results
  	 * Returns:			nothing
  	 */
    

    // Method to report outcome
    public static void reportOutcome(int trumpVotes, int harrisVotes) {
        System.out.println("\nTotal Electoral College Votes won:");
        System.out.println("Trump: " + trumpVotes);
        System.out.println("Harris: " + harrisVotes);
        
     // Determine and print the winner

        if (trumpVotes >= 270) {
            System.out.println("The next U.S. president is Donald Trump.");
        } else if (harrisVotes >= 270) {
            System.out.println("The next U.S. president is Kamala Harris.");
        } else {
            System.out.println("Neither candidate has enough Electoral College votes to be declared the winner.");
        }
    }

	
}
