package ui;

import java.util.ArrayList;
import java.util.Scanner;

import classes.*;
import mgr.OrderManager;

/**
 * 
 * Displays UI to the staff when creating, editing and removing orders and also
 * printing invoices after payment.
 * 
 * @author Ann
 * 
 */
public class OrderUI {
	
	/**
	 * 
	 * Static scanner variable For shortening code and prevents creating new scanner
	 * variable for each method
	 * 
	 */
	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * 
	 * Displays options for staff.
	 * 
	 */
	public static void showOrderUI(Restaurant restaurant, Staff currentStaff) {

		ArrayList<Order> orders = restaurant.getOrders();
		ArrayList<Table> tables = restaurant.getTables();
		ArrayList<MenuItem> menuItems = restaurant.getMenuItems();
		ArrayList<Set> setItems = restaurant.getSets();
		
		int option = 0;

		do {
			System.out.println("\n================================");
			System.out.println("=== [Orders] ===");
			System.out.println("Choose an option:");
			System.out.println("[1] View all Orders"); 
			System.out.println("[2] Create Order"); 
			System.out.println("[3] Update Order");
			System.out.println("[4] Remove Order");
			System.out.println("[5] Print Invoice");
			System.out.println("[0] Return to Main Menu");
			System.out.println("================================");

			while (!sc.hasNextInt()) { // check if user entered int
				sc.next(); // move buffer
				System.out.println("Invalid Input, please enter valid input.");
			}

			option = sc.nextInt();

			switch (option) {
			case 0: // Return to Main Menu
				System.out.println("Returning to Main Menu...");
				break;
			case 1: // View list of Orders
				OrderManager.viewOrder(orders);
				break;
			case 2: // Create an order
				OrderManager.createOrder(menuItems,setItems, orders, tables, null, currentStaff);
				break;
			case 3: // Edit an order
				OrderManager.updateOrder(menuItems,setItems, orders);
				break;
			case 4: // Remove an order
				OrderManager.removeOrder(menuItems, orders);
				break;
			case 5: // Print invoice after order is completed
				InvoiceUI.showInvoiceUI(restaurant);
				break;
			default:
				System.out.println("No such option.");
				break;
			}

		} while (option != 0);
	}
}

