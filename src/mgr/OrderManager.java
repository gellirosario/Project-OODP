package mgr;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import classes.MenuItem;
import classes.Order;
import classes.Reservation;
import classes.SaleItem;
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
	 * Static scanner variable
	 * For shortening code and prevents creating new scanner variable for each function 
	 * 
	 */
	public static Scanner sc = new Scanner(System.in);

	/**
	 * 
	 * View existing orders
	 * Prints out all existing orders
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

				System.out.println(
						"Created by " + orders.get(i).getStaff().getName() + "\n" + orders.get(i).getOrderDateTime());
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
	 * @param ArrayList<MenuItem> menuItems
	 * @param ArrayList<Order> orders
	 * @param ArrayList<Table> tables
	 * @param ArrayList<Reservation> reservations
	 * @param Staff currentStaff
	 * 
	 */
	public static void createOrder(ArrayList<MenuItem> menuItems, ArrayList<Order> orders, ArrayList<Table> tables,
			ArrayList<Reservation> reservations, Staff currentStaff) {

		ArrayList<SaleItem> saleItems = new ArrayList<SaleItem>();
		Order order = null;
		Table occupiedTable = null;
		Reservation reserved = null;
		int option = 0;
		int pax = 0;
		int contactNo = 0;

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		do {
			// Check for customer's reservation
			System.out.println("Customer has reservations? (Y/N)");
			option = Character.toUpperCase(sc.next().charAt(0)); // Check if user input is char

		} while (option != 'Y' && option != 'N');

		if (option == 'Y') // Customer has reservation
		{
			do {

				System.out.println("Please enter the Customer's contact number: (Enter -1 to exit)");
				contactNo = sc.nextInt();

				if (contactNo < 0) {
					break;
				}

				for (int i = 0; i < reservations.size(); i++) // Check if Reservation exist
				{
					if ((int) contactNo == reservations.get(i).getCustContact()) {
						reserved = reservations.get(i);

						long curTimeInMs = reserved.getReservationTime().getTime().getTime();
						Date expiredTime = new Date(curTimeInMs + (30 * 60000));

						if (date.before(expiredTime)) // Check if Reservation is within reservation time after 30 minutes
						{
							System.out.println("Reservation found!");
							
							occupiedTable = reserved.getTableReservation();
							
						} else {
							System.out.println("Reservation has expired.");
							reserved = null;
							option = 'N';
						}

						break;
					}

					if (i + 1 == reservations.size()) {
						System.out.println("Reservation not found. Please try again.");
						break;
					}
				}

				if (reserved == null && reservations.size() == 0) {
					System.out.println("Reservation not found. Please try again.");
					break;
				}

			} while (reserved == null);
		}
		
		if(reserved != null)
		{
			saleItems = addItemToOrder(menuItems, new ArrayList<SaleItem>()); // Add menu items to order
		}
		
		if (option == 'N') // Customer has no reservation
		{
			saleItems = addItemToOrder(menuItems, new ArrayList<SaleItem>()); // Add menu items to order
			
			do {
				// Get pax
				System.out.print("Please enter pax: ");
				pax = sc.nextInt();

				if (pax < 1 || pax > 10) {
					System.out.println("No tables found for this amount of pax. Please try again.");
				}

			} while (pax < 1 || pax > 10);

			// Auto allocation of table according to pax
			for (int i = 0; i < tables.size(); i++) {
				if (tables.get(i).getSeatingCapacity() >= pax && tables.get(i).getStatus() == Status.Vacated) {
					occupiedTable = tables.get(i);

					break;
				}

				if (i + 1 == tables.size()) {
					System.out.println("Table is all occupied. Please wait for a vacated table.");
				}
			}
		}

		// Adds to order
		if (currentStaff != null && occupiedTable != null && saleItems != null) {

			order = new Order(orders.size() + 1, currentStaff, saleItems, occupiedTable, date);

			if (order != null) {
				orders.add(order);

				System.out.println("Order complete!");
				System.out.println("[Order No." + orders.size() + " | Created by " + currentStaff.getName() + " on "
						+ dateFormat.format(date) + "]");
			}

		} else {
			System.out.println("Order incomplete. Please try again later.");
		}

	}

	/**
	 * 
	 * Update order by adding or removing order item
	 * 
	 * @param ArrayList<MenuItem> menuItems
	 * @param ArrayList<Order> orders
	 * 
	 */
	public static void updateOrder(ArrayList<MenuItem> menuItems, ArrayList<Order> orders) {

		int choice = 0;
		int orderId = 0;

		if (orders.size() == 0) {
			System.out.println("No orders found. Unable to edit any orders.");
		} else {
			viewOrder(orders);

			System.out.println("\n[Update Orders]\n");

			ArrayList<SaleItem> saleItems = null;
			Order order = null;

			do {
				System.out.println("Please enter the order ID you want to edit: (Enter -1 to exit)");
				orderId = sc.nextInt();

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
				case 1:
					saleItems = addItemToOrder(menuItems, saleItems);
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
	public static ArrayList<SaleItem> addItemToOrder(ArrayList<MenuItem> menuItems, ArrayList<SaleItem> items) {

		ArrayList<SaleItem> saleItems = items;

		int choice = 0;
		char option;

		System.out.println("\n[Menu]\n");
		System.out.println("================================");
		for (int i = 0; i < menuItems.size(); i++) {
			System.out.println("(" + menuItems.get(i).getId() + ") " + menuItems.get(i).getName() + " | $"
					+ menuItems.get(i).getPrice() + " | " + menuItems.get(i).getMenuType());
		}
		System.out.println("================================\n");

		do {
			System.out.println("Add menu item to order: ");
			choice = sc.nextInt();

			for (int i = 0; i < menuItems.size(); i++) {
				if (menuItems.get(i).getId() == choice) {
					saleItems.add(menuItems.get(i));
					System.out.println("Menu item added successfully!");
					break;
				}

				if (i + 1 == menuItems.size()) {
					System.out.println("Menu item not found.");
				}

			}

			System.out.print("Continue adding to order? (Y/N): ");
			option = Character.toUpperCase(sc.next().charAt(0)); // check if user input is char

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
		char option;

		System.out.println("\n[Ordered List]\n");
		System.out.println("================================");
		for (int i = 0; i < items.size(); i++) {
			System.out.println(
					"(" + items.get(i).getId() + ") " + items.get(i).getName() + " | $" + items.get(i).getPrice());
		}
		System.out.println("================================\n");

		do {

			System.out.println("Remove menu item from order: ");
			choice = sc.nextInt();

			if (saleItems.size() == 1) {
				System.out.println("You can't delete the last item from the order.");
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

			System.out.print("Continue removing from order? (Y/N): ");
			option = Character.toUpperCase(sc.next().charAt(0)); // check if user input is char

		} while (option == 'Y');

	}

	/**
	 * 
	 * Remove existing order
	 * 
	 * @param ArrayList<MenuItem> menuItems
	 * @param ArrayList<Order> orders
	 * 
	 */
	public static void removeOrder(ArrayList<MenuItem> menuItems, ArrayList<Order> orders) {

		int choice = 0;
		if (orders.size() == 0) {
			System.out.println("No orders found. Unable to remove any orders.");
		} else {

			System.out.println("\n[Menu]\n");

			for (int i = 0; i < menuItems.size(); i++) {
				System.out.println("(" + menuItems.get(i).getId() + ") " + menuItems.get(i).getName() + " | $"
						+ menuItems.get(i).getPrice() + " | " + menuItems.get(i).getMenuType());
			}

			viewOrder(orders);

			System.out.println("\n[Remove Orders]\n");

			System.out.println("Please enter the order ID you want to remove : ");
			choice = sc.nextInt();

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
