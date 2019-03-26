package app;
import java.util.ArrayList;
import java.util.Scanner;

import classes.*;
import ui.*;

/**
 * 
 * RRPSS is an application to computerize the processes of making reservation, 
 * recording of orders and displaying of sale records. 
 * It will be solely used by the restaurant staff.
 * 
 */
public class RRPSSApp {
	
	public static Staff currentStaff = null;	
	
	public static void main(String[] args) {
		
		System.out.println("Starting RRPSS System....");
		
		Restaurant.loadRestaurant();
		
		while(currentStaff == null)
			currentStaff = getStaffIdentity(Restaurant.staffs); //Get staff's identity
		
		System.out.println("\nWelcome Back, " + currentStaff.getName() + "!"); //Staff successfully logged in
		
		showMainMenu();
		
		System.out.println("Saving system information...");
		//Restaurant.saveRestaurant();
		
		System.out.println("Exiting system...");
		System.exit(0);
	}
	
	/**
	 * 
	 * Method to get the Staff's identity
	 * @param staffs List of Staff Registered
	 * @return existingStaff Returning staff
	 * 
	 */
	public static Staff getStaffIdentity(ArrayList<Staff> staffs){
		
		Staff existingStaff = null;
		
		System.out.println("Please enter your Staff ID:");
		
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		

		for(int i = 0; i < staffs.size(); i++)
		{
			if(id == staffs.get(i).getId())
			{
				existingStaff = staffs.get(i); //Staff exist
				break;
			}
		}
		
		if(existingStaff == null)
		{
			System.out.println("Staff ID not found.\nPlease try again.\n");
		}
		
		return existingStaff;

	}
	
	/**
	 * 
	 * Displays Main Menu of the Application 
	 * which shows the list of features that 
	 * the staff can do in this application 
	 * 
	 */
	public static void showMainMenu(){
		
		Scanner sc = new Scanner(System.in);
		int option = 0;
		
		do {
			System.out.println("\n================================");
			System.out.println("=== [RRPSS System Main Menu] ===");
			System.out.println("Choose an option:");
			System.out.println("[1] Restaurant Menu");
			System.out.println("[2] Orders");
			System.out.println("[3] Reservations");
			System.out.println("[4] Sales Revenue Report");
			//System.out.println("[5] Manage Staffs");
			//System.out.println("[6] Manage Customers");
			System.out.println("[0] Exit RRPSS");
			System.out.println("================================");
			
			option = sc.nextInt();
			
			switch(option){
				case 1: //Restaurant Menu
					break;
				case 2: //Orders
					OrderUI.showOrderUI();
					break;
				case 3: //Reservations
					break;
				case 4: //Sales Revenue Report
					break;

				default: 
					System.out.println("No such option.");
					break;
			}
			
		}while(option != 0);
	}
}
