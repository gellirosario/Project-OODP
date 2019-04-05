package ui;

import java.util.Scanner;
import java.util.ArrayList;

import classes.Order;
import classes.Restaurant;
import classes.Invoice;
import mgr.InvoiceManager;
import mgr.OrderManager;

/**
 * Displays UI to the staff when printing order invoices
 * 
 * @author wongwanting
 *
 */
public class InvoiceUI {

	public static Scanner sc = new Scanner(System.in);

	/**
	 * Displays prompts to the staff to input the order ID
	 * 
	 * if print is successful, order is added to ArrayList<Order> previousOrder and
	 * removed from current order
	 * 
	 * @param restaurant
	 */
	public static void showInvoiceUI(Restaurant restaurant) {

		int orderId = 0;

		ArrayList<Order> orders = restaurant.getOrders();
		ArrayList<Order> previousOrders = restaurant.getPreviousOrders();
		ArrayList<Invoice> invoices = restaurant.getInvoices();

		Order order = null;

		if (orders.size() == 0) {
			System.out.println("No orders found. Unable to print invoice.");
			return;
		}

		Boolean checkInput = false;

		System.out.println("Please enter the order ID: ");

		do {
			if (sc.hasNextInt()) {
				orderId = sc.nextInt();
				if (orderId > 0) {
					checkInput = false;
				} else {
					System.out.println("Invalid Input. Please enter a valid order ID.");
					checkInput = true;
				}
			} else {
				System.out.println("Invalid Input. Please enter a valid order ID.");
				checkInput = true;
				sc.next();
			}

		} while (checkInput);

		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getId() == orderId) {
				order = orders.get(i);
				System.out.println("printing... \n");
				InvoiceManager.printInvoice(order, invoices);

				// after printing
				OrderManager.moveToCompletedOrder(order, orders, previousOrders);
				System.out.println("\nCheckout Complete!");
				return;
			}

			else if (i + 1 == orders.size()) {
				System.out.println("Order not found in current orders. Unable to print invoice.");
			}
		}
	}
}
