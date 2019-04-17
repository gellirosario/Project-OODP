package mgr;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import classes.MenuItem;
import classes.Order;
import classes.Reservation;
import classes.SaleItem;
import classes.Set;
import classes.Staff;
import classes.Table;
import classes.Table.Status;

/**
 * 
 * Handles the order's create/update/remove functions
 * 
 * @author Ann
 * 
 */
public class OrderManager {

	/**
	 * 
	 * Static scanner variable For shortening code and prevents creating new scanner
	 * variable for each method
	 * 
	 */
	public static Scanner sc = new Scanner(System.in);

	private static MenuItemManager menuItemManager = new MenuItemManager();
	private static SetManager setManager = new SetManager();

	/**
	 * 
	 * View existing orders Prints out all existing orders
	 * 
	 * @param ArrayList<Order> orders
	 * 
	 */
	public static void viewOrder(ArrayList<Order> orders) {

		System.out.println("\n[View all Orders]\n");

		if (orders.size() > 0) {
			for (int i = 0; i < orders.size(); i++) {

				System.out.println("========= Order No. " + orders.get(i).getId() + " =========");
				System.out.println("Table No. " + orders.get(i).getTable().getId());

				System.out.println("-----------Items-----------");
				for (int j = 0; j < orders.get(i).getItems().size(); j++) {
					System.out.println(orders.get(i).getItems().get(j).getName());
				}
				System.out.println("----------------------------");

				System.out.println("Created by " + orders.get(i).getStaff().getName() + "\n"
						+ orders.get(i).getOrderDateTime().getTime());
				System.out.println("================================\n");
			}
		} else {
			System.out.println("No orders found.");
		}
	}

	/**
	 * 
	 * Creates a new order according to reservation and walk in orders
	 * 
	 * @param ArrayList<MenuItem>    menuItems
	 * @param ArrayList<Order>       orders
	 * @param ArrayList
	 *                               <Table>
	 *                               tables
	 * @param ArrayList<Reservation> reservations
	 * @param Staff                  currentStaff
	 * 
	 */
	public static void createOrder(ArrayList<MenuItem> menuItems, ArrayList<Set> setItems, ArrayList<Order> orders, ArrayList<Order> prevOrders,
			ArrayList<Table> tables, Reservation reservation, Staff currentStaff) {

		ArrayList<SaleItem> saleItems = new ArrayList<SaleItem>();
		Order order = null;
		Table occupiedTable = null;
		int pax = 0;
		String input = null;
		Calendar date = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		if (reservation != null) // Customer has reservation
		{
			occupiedTable = reservation.getTableReservation();
			pax = reservation.getNumOfPax();
			saleItems = addItemToOrder(menuItems, setItems, new ArrayList<SaleItem>()); // Add menu items to order
		} else if (reservation == null) // Customer has no reservation
		{
			saleItems = addItemToOrder(menuItems, setItems, new ArrayList<SaleItem>()); // Add menu items to order

			if(!saleItems.isEmpty())
			{
				System.out.println("Enter -1 at any time to exit current action.");
				
				do {
					// Get pax
					System.out.println("Please enter pax:");
					input = sc.next();
					
					try {
						pax = Integer.parseInt(input);
					}
					catch (NumberFormatException e)  
					{
						System.out.println("***Please only enter numbers");
						continue;
					}

					if (pax < 0) {
						pax = 0;
						break;
					}

					if (pax < 1 || pax > 10) {
						System.out.println("No tables found for this amount of pax. Please try again.");
					}

				} while (pax < 1 || pax > 10);

				// Auto allocation of table according to pax
				for (int i = 0; i < tables.size(); i++) {
					if (tables.get(i).getSeatingCapacity() >= pax && tables.get(i).getStatus() == Status.Vacated) {
						occupiedTable = tables.get(i);
						occupiedTable.setStatus(Status.Occupied);

						break;
					}

					if (i + 1 == tables.size()) {
						System.out.println("Table is all occupied. Please wait for a vacated table.");
					}
				}
			}
		}

		// Adds to order
		if (currentStaff != null && occupiedTable != null && saleItems != null && pax != 0) {
			
			int orderId = 0;
			
			if(prevOrders.size() > 0)
			{
				for(int i = 0; i < prevOrders.size(); i++)
				{
					if(i+1 == prevOrders.size())
					{
						orderId = prevOrders.get(i).getId() + 1;
					}
				}
			}
			else
			{
				orderId =  orders.size() + 1;
			}
			
			
			order = new Order(orderId, currentStaff, saleItems, occupiedTable, date);

			if (order != null) {

				orders.add(order);
				System.out.println("Order complete!");
				System.out.println("[Order No." + orderId + " | Table No." + occupiedTable.getId()
						+ " | Created by " + currentStaff.getName() + " on " + dateFormat.format(date.getTime()) + "]");
			}

		} else {
			System.out.println("Unable to create an Order. Please try again.");
		}

	}

	/**
	 * 
	 * Update order by adding or removing order item
	 * 
	 * @param ArrayList<MenuItem> menuItems
	 * @param ArrayList<Order>    orders
	 * 
	 */
	public static void updateOrder(ArrayList<MenuItem> menuItems, ArrayList<Set> setItems, ArrayList<Order> orders) {

		int choice = 0;
		int orderId = 0;
		String input = null;
		
		if (orders.size() == 0) {
			System.out.println("No orders found. Unable to edit any orders.");
		} else {
			viewOrder(orders);

			System.out.println("\n[Update Orders]\n");

			ArrayList<SaleItem> saleItems = null;
			Order order = null;

			do {
				System.out.println("Enter -1 at any time to exit current action.");
				System.out.println("Please enter the order ID you want to edit:");
				input = sc.next();
				
				try {
					orderId = Integer.parseInt(input);
				}
				catch (NumberFormatException e)  
				{
					System.out.println("***Please only enter numbers");
					continue;
				}
				
				if (orderId < 0) {
					break;
				}

				for (int i = 0; i < orders.size(); i++) {
					if (orders.get(i).getId() == orderId) {
						order = orders.get(i); // Order data retrieved
						saleItems = order.getItems();
						break;
					}

					if (i + 1 == orders.size()) {
						System.out.println("Order not found. Please try again.");
					}
				}

			} while (order == null);

			if (order != null) {
				System.out.println("Please select an option: ");
				System.out.println("[1] Add Items");
				System.out.println("[2] Remove Items");
				System.out.println("[0] Return to Order Menu");

				choice = sc.nextInt();

				switch (choice) {
				case 0:
					break;
				case 1:
					saleItems = addItemToOrder(menuItems, setItems, saleItems);
					break;
				case 2:
					removeItemFromOrder(menuItems, saleItems);
					break;
				default:
					System.out.println("No such option.");
				}
			}

		}

	}

	/**
	 * 
	 * Add item to existing/new order
	 * 
	 * @param ArrayList<MenuItem> menuItems
	 * @param ArrayList<SaleItem> items
	 * @return ArrayList<SaleItem> list of sale items
	 * 
	 */
	public static ArrayList<SaleItem> addItemToOrder(ArrayList<MenuItem> menuItems, ArrayList<Set> setItems,
			ArrayList<SaleItem> items) {

		ArrayList<SaleItem> saleItems = null;
		
		if(items != null)
		{
			saleItems = items;
		}
		
		int choice = 0;
		char option = ' ';
		String input = null;

		System.out.println("\n[Menu]\n");
		System.out.println("================================");
		menuItemManager.viewAllMenuItem(menuItems);
		System.out.println("================================\n");

		System.out.println("\n[Sets]\n");
		System.out.println("================================");
		setManager.viewAllSet(setItems);
		System.out.println("================================\n");
		
		System.out.println("Enter -1 at any time to exit current action.");
		
		do {
			
			System.out.println("Add menu/set item to order:");
			input = sc.next();
			
			try {
				choice = Integer.parseInt(input);
			}
			catch (NumberFormatException e)  
			{
				System.out.println("***Please only enter numbers");
				continue;
			}
			
			boolean found = false;

			if (choice < 0) {
				break;
			}

			for (int i = 0; i < menuItems.size(); i++) {
				if (menuItems.get(i).getId() == choice) {
					saleItems.add(menuItems.get(i));
					System.out.println("Menu item added successfully!");
					found = true;
					break;
				}
			}

			for (int i = 0; i < setItems.size(); i++) {
				if (setItems.get(i).getId() == choice) {
					saleItems.add(setItems.get(i));
					System.out.println("Set item added successfully!");
					found = true;
					break;
				}
			}

			if (found == false) {
				System.out.println("Item not found.");
			}

			do {
				System.out.print("Continue adding item to order? (Y/N): ");
				input = sc.next();
				
				try {
					if(Integer.parseInt(input) < 0)
					{
						break;
					}
				}
				catch(Exception e)
				{
					
				}
				
				if (input.length() > 1) {
					System.out.println("Please enter single character.");
				}
				
				if(Character.toUpperCase(input.charAt(0)) != 'Y' && Character.toUpperCase(input.charAt(0)) !=  'N')
				{
					System.out.println("Please enter Y or N.");
				}
				
				
				
			}while(input.length() > 1 || (Character.toUpperCase(input.charAt(0)) != 'Y' && Character.toUpperCase(input.charAt(0)) != 'N'));
			
			option = Character.toUpperCase(input.charAt(0));

		} while (option == 'Y');

		return saleItems;
	}

	/**
	 * 
	 * Remove item from existing order
	 * 
	 * @param ArrayList<MenuItem> menuItems
	 * @param ArrayList<SaleItem> items
	 * 
	 */
	public static void removeItemFromOrder(ArrayList<MenuItem> menuItems, ArrayList<SaleItem> items) {
		ArrayList<SaleItem> saleItems = items;

		int choice = 0;
		String input = null;
		char option = ' ';

		System.out.println("\n[Ordered List]\n");
		System.out.println("================================");
		for (int i = 0; i < items.size(); i++) {
			System.out.println(
					"(" + items.get(i).getId() + ") " + items.get(i).getName() + " | $" + items.get(i).getPrice());
		}
		System.out.println("================================\n");
		
		System.out.println("Enter -1 at any time to exit current action.");
		do {
			
			System.out.println("Remove menu item from order:");
			input = sc.next();
			
			try {
				choice = Integer.parseInt(input);
			}
			catch (NumberFormatException e)  
			{
				System.out.println("***Please only enter numbers");
				continue;
			}

			if (choice < 0) {
				break;
			}

			if (saleItems.size() == 1) {
				System.out.println("You can't delete the last item from the order.\nPlease go to 'Remove Order' to remove the order.");
				break;
			}

			for (int i = 0; i < items.size(); i++) {

				if (items.get(i).getId() == choice) {
					saleItems.remove(i);
					System.out.println("Menu item removed successfully!");
					break;
				}

				if (i + 1 == menuItems.size()) {
					System.out.println("Menu item not found.");
				}
			}
			
			do {
				System.out.print("Continue removing item to order? (Y/N): ");
				input = sc.next();
				
				try {
					if(Integer.parseInt(input) < 0)
					{
						break;
					}
				}
				catch(Exception e)
				{
					
				}
				
				if (input.length() > 1) {
					System.out.println("Please enter single character.");
				}
				
				if(Character.toUpperCase(input.charAt(0)) != 'Y' && Character.toUpperCase(input.charAt(0)) !=  'N')
				{
					System.out.println("Please enter Y or N.");
				}
				
				
				
			}while(input.length() > 1 || (Character.toUpperCase(input.charAt(0)) != 'Y' && Character.toUpperCase(input.charAt(0)) != 'N'));
			
			option = Character.toUpperCase(input.charAt(0));
			
		} while (option == 'Y');

	}

	/**
	 * 
	 * Remove existing order
	 * 
	 * @param ArrayList<MenuItem> menuItems
	 * @param ArrayList<Order>    orders
	 * 
	 */
	public static void removeOrder(ArrayList<MenuItem> menuItems, ArrayList<Order> orders) {

		int choice = 0;
		char option = ' ';
		String input = null;

		if (orders.size() == 0) {
			System.out.println("No orders found. Unable to remove any orders.");
		} else {

			System.out.println("\n[Menu]\n");
			System.out.println("================================");
			menuItemManager.viewAllMenuItem(menuItems);
			System.out.println("================================\n");

			viewOrder(orders);

			System.out.println("\n[Remove Orders]\n");

			do {
				System.out.println("Enter -1 at any time to exit current action.");
				System.out.println("Please enter the order ID you want to remove :");
				input = sc.next();
				
				try {
					choice = Integer.parseInt(input);
				}
				catch (NumberFormatException e)  
				{
					System.out.println("***Please only enter numbers");
					continue;
				}

				if (choice < 0) {
					break;
				}

				for (int i = 0; i < orders.size(); i++) {
					if (orders.get(i).getId() == choice) {
						orders.remove(orders.get(i));
						System.out.println("Order successfully removed!");
						break;
					}

					if (i + 1 == orders.size()) {
						System.out.println("Order not found.");
					}
				}

				do {
					System.out.print("Continue removing order? (Y/N): ");
					input = sc.next();
					
					try {
						if(Integer.parseInt(input) < 0)
						{
							break;
						}
					}
					catch(Exception e)
					{
						
					}
					
					if (input.length() > 1) {
						System.out.println("Please enter single character.");
					}
					
					if(Character.toUpperCase(input.charAt(0)) != 'Y' && Character.toUpperCase(input.charAt(0)) !=  'N')
					{
						System.out.println("Please enter Y or N.");
					}
					
					
					
				}while(input.length() > 1 || (Character.toUpperCase(input.charAt(0)) != 'Y' && Character.toUpperCase(input.charAt(0)) != 'N'));
				
				option = Character.toUpperCase(input.charAt(0));

			} while (option == 'Y');

		}

	}

	/**
	 * Adds this order to completed orders and removes this order from current
	 * orders
	 * 
	 * Called after successfully printing the invoice
	 * 
	 * @param order
	 * @param orders
	 * @param previousOrders
	 */
	public static void moveToCompletedOrder(Order order, ArrayList<Order> orders, ArrayList<Order> previousOrders) {

		previousOrders.add(order);
		orders.remove(order);
	}
}
