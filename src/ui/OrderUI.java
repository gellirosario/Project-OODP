package ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import app.RRPSSApp;
import classes.*;
import manager.OrderManager;

/**
 * 
 * Displays UI to the staff when creating, editing and removing orders and also printing invoices after payment.
 * @author Ann
 * 
 */
public class OrderUI {
	
	public static Scanner sc = new Scanner(System.in);
	
	/**
	 * 
	 * Displays options for staff.
	 * 
	 */
	public static void showOrderUI() {
		
		
		int option = 0;
		
		do {
			System.out.println("\n================================");
			System.out.println("=== [Orders] ===");
			System.out.println("Choose an option:");
			System.out.println("[1] View all Orders"); //maybe can do for current/completed orders?
			System.out.println("[2] Create Order");
			System.out.println("[3] Update Order");
			System.out.println("[4] Remove Order");
			//System.out.println("[5] Print Invoice");
			System.out.println("[6] Return to Main Menu");
			System.out.println("[0] Exit RRPSS");
			System.out.println("================================");
			
			option = sc.nextInt();
			
			switch(option){
				case 1:	//View list of Orders
					OrderManager.viewOrder();
					break;
				case 2: //Create an order
					OrderManager.createOrder();
					break;
				case 3: //Edit an order
					OrderManager.updateOrder();
					break;
				case 4: //Remove an order
					OrderManager.removeOrder();
					break;
				case 5: //Print invoice
					break;
				case 6: //Return to Main Menu
					RRPSSApp.showMainMenu();
					break;
					
				default: 
					System.out.println("No such option.");
					break;
			}
			
		}while(option != 0);
	}
	
	
}
