package ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import classes.Reservation;
import classes.Staff;
import classes.Table;
import mgr.ReservationManager;
import mgr.TableManager;


/**
 * Displays user interface for user interaction
 * Create, Edit, Delete Reservations
 * 
 * @author Lexx Audrey Pecson Manalansan
 * 
 */


public class ReservationUI {

	private static Scanner sc = new Scanner(System.in);
	
	public static void showReservationUI(Staff staff){

		int option = 0;
		
        do {
        	System.out.println("\n================================");
			System.out.println("=== [Reservation] ===");
			System.out.println("Choose an option:");
			System.out.println("[1] Show Available Tables");
			System.out.println("[2] Show Reservations");
			System.out.println("[3] Make Reservation");
			System.out.println("[4] Accept Reservation");
			System.out.println("[5] Remove Reservation");
			System.out.println("[0] Return to Main Menu");

			System.out.println("================================");
			
			while (!sc.hasNextInt()) { 
				sc.next();
				System.out.println("Invalid Input, please enter valid input.");
			}
			
        	option = sc.nextInt();
            
            switch (option) {
	            case 0:
						System.out.println("Returning to Main Menu...");
						break;
				case 1:
                		ReservationManager.showAvailableTables();
                        break;
                case 2:
                		ReservationManager.showReservations();
                		break;
                case 3:
                		ReservationManager.makeReservation();
                        break;
                case 4:
                		ReservationManager.acceptReservation(staff);
                    	break;
                case 5:
                		ReservationManager.removeReservation();
                		break;

                default:
    				System.out.println("No such option.");
    				break;
			}
			
		}while(option != 0);

	}


	
}