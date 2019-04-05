package ui;

import java.util.Scanner;
import java.util.ArrayList;

import classes.Order;
import classes.Restaurant;
import classes.Invoice;
import mgr.InvoiceManager;

/**
 * Displays UI to the staff when generating sales revenue report
 * 
 * @author wongwanting
 *
 */
public class SaleReportUI {

	public static Scanner sc = new Scanner(System.in);

	/**
	 * Displays options for staff
	 * 
	 * prints "Nothing to display" if no orders found in ArrayList<Order>
	 * previousOrders
	 * 
	 * @param restaurant
	 */
	public static void showSaleReportUI(Restaurant restaurant) {

		int option = 0;
		Scanner sc = new Scanner(System.in);

		ArrayList<Invoice> invoices = restaurant.getInvoices();
		ArrayList<Order> previousOrders = restaurant.getPreviousOrders();

		if (previousOrders.size() == 0) {
			System.out.println("No completed order records. Nothing to display.");
			return;
		}

		do {
			System.out.println("\n================================");
			System.out.println("=== [Sales Revenue Report] ===");
			System.out.println("Choose an option:");
			System.out.println("[1] Print by day  ");
			System.out.println("[2] Print by month");
			System.out.println("[0] Return to Main Menu");
			System.out.println("================================");

			while (!sc.hasNextInt()) {
				sc.next();
				System.out.println("Invalid Input, please enter valid input.");
			}

			option = sc.nextInt();

			switch (option) {

			case 0:
				System.out.println("Returning to Main Menu...\n");
				break;

			case 1:
				String date;

				do {
					System.out.println("Enter Date (dd/mm/yyyy): ");
					date = sc.next();
				} while (!InvoiceManager.checkDate(date));

				InvoiceManager.printByDay(date, invoices);

				break;

			case 2:
				String month;

				do {
					System.out.println("Enter Date (mm/yyyy): ");
					month = sc.next();
				} while (!InvoiceManager.checkMonth(month));

				InvoiceManager.printByMonth(month, invoices);

				break;

			default:
				System.out.println("No such option.");
				break;
			}

		} while (option != 0);

	}
}
