package mgr;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import classes.MenuItem;
import classes.Order;
import classes.SaleItem;
import classes.Staff;
import classes.Table;
import classes.Table.Status;

public class OrderMgr {

	public static Scanner sc = new Scanner(System.in);

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

	public static void createOrder(ArrayList<MenuItem> menuItems, ArrayList<Order> orders, ArrayList<Table> tables,
			Staff currentStaff) { 
		
		//need check if user has any reservation

		ArrayList<SaleItem> saleItems = new ArrayList<SaleItem>();
		Order order = null;
		Table occupiedTable = null;

		int pax = 0;

		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		saleItems = addItemToOrder(menuItems, new ArrayList<SaleItem>());

		do {
			System.out.print("Please enter pax: ");
			pax = sc.nextInt();

			if (pax < 1 || pax > 10) {
				System.out.println("No tables found for this amount of pax. Please try again.");
			}

		} while (pax < 1 || pax > 10);

		for (int i = 0; i < tables.size(); i++) {
			if (tables.get(i).getSeatingCapacity() >= pax && tables.get(i).getStatus() == Status.Vacated) {
				occupiedTable = tables.get(i);

				break;
			}

			if (i + 1 == tables.size()) {
				System.out.println("Table is all occupied. Please wait for a vacated table.");
			}
		}

		if (currentStaff != null && occupiedTable != null && saleItems != null) {
			
			order = new Order(orders.size() + 1, currentStaff, saleItems, occupiedTable, date);

			orders.add(order);

			System.out.println("Order complete!");
			System.out.println("[Order No." + orders.size() + " | Created by " + currentStaff.getName() + " on "
					+ dateFormat.format(date) + "]");

		} else {
			System.out.println("Order incomplete. Please try again later.");
		}

	}

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
						order = orders.get(i); // order data retrieved
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

			/*
			 * System.out.println("Quantity of " + saleItems.get(orderSize).getName() +
			 * ": "); quantity = sc.nextInt();
			 * 
			 * for(int i = 0; i < quantity; i++) {
			 * saleItems.add(Restaurant.menuItems.get(i)); }
			 */

			System.out.print("Continue adding to order? (Y/N): ");
			option = Character.toUpperCase(sc.next().charAt(0)); // check if user input is char

		} while (option == 'Y');

		return saleItems;
	}

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
	 * adds this order to completed orders and removes this order from current orders
	 * 
	 * called after successfully printing the invoice 
	 * @param order
	 * @param orders
	 * @param previousOrders
	 */
	public static void moveToCompletedOrder(Order order, ArrayList<Order> orders, ArrayList<Order> previousOrders) {
		
		previousOrders.add(order);
		orders.remove(order);
	}
}
