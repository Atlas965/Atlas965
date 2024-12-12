/*
 *  File name: B_E_ElectionSimulator.java
* Purpose: To predict the electoral college randomly by simulating the 2024 U.S. election.
 * Coder: Benson Ezeamah
 * Date: Nov 2, 2024
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class B_E_ElectionSimulator {

    public static void main(String[] args)throws FileNotFoundException
    {
   	 //display welcome to election simulator
   	 
        System.out.println("Welcome to Benson Ezeamah's 2024 U.S. Election Simulator!");
        System.out.println("This program will run a simulation of the 2024 U.S. presidential election.");
        System.out.println("based on probabilities obtained from www.270towin.com 6 days\r\n"
        		+ "before the election.");
        //create scanner
        Scanner scanner = new Scanner(System.in);

        
		// Prompt for file path
        System.out.print("Please enter the path to the projections data file : ");
        String filePath = scanner.nextLine();
       
        // Verify file existence
        File file = new File("C:\\Users\\benso\\eclipse-workspace\\My First Project\\src\\projections2024.csv");
        while (!file.exists()) {
            System.out.print("File not found. Please enter a valid file path: ");
            filePath = scanner.nextLine();
            file = new File(filePath);
        }
        
        System.out.println("Press ENTER to start the simulation...");
        scanner.nextLine();  // Wait for ENTER key
        
        // Initialize arrays
        String[] regions = new String[56];//Array to store regions
        int[] electoralVotes = new int[56];// Array to store electoralVotes
        int[] trumpProbabilities = new int[56];//Array to store trumpProbabilities
        boolean[] outcomes = new boolean[56];//Array to store outcomes

        // Read data from CSV file
       
        boolean dataLoaded = false;
		try
		{
			dataLoaded = B_E_ProjectMethods.readAllElectionData(filePath, regions, electoralVotes, trumpProbabilities);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
        if (!dataLoaded) {
            System.out.println("Failed to load election data.");
            return;
        }

        // Run simulation
        for (int i = 0; i < outcomes.length; i++) {
            outcomes[i] = B_E_ProjectMethods.getRegionResult(trumpProbabilities[i]);
        }

        // Report individual region results
        int trumpTotalVotes = 0;// total votes for Trump
        int harrisTotalVotes = 0;// total votes for Harris

        System.out.println("Republican Donald Trump has won the following Electoral College Votes:");
        for (int i = 0; i < outcomes.length; i++) {
            if (outcomes[i]) {
                System.out.println(regions[i] + " (" + electoralVotes[i] + ")");
                trumpTotalVotes += electoralVotes[i];
            }
        }

        System.out.println("\nDemocrat Kamala Harris has won the following Electoral College Votes:");
        for (int i = 0; i < outcomes.length; i++) {
            if (!outcomes[i]) {
                System.out.println(regions[i] + " (" + electoralVotes[i] + ")");
                harrisTotalVotes += electoralVotes[i];
            }
        }

        // Report final outcome
        B_E_ProjectMethods.reportOutcome(trumpTotalVotes, harrisTotalVotes);
        scanner.close();
    }
}

