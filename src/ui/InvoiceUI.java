package ui;

import java.util.Scanner;
import java.util.ArrayList;

import classes.Order;
import classes.Restaurant;
import classes.Invoice;
import mgr.InvoiceManager;
import mgr.OrderMgr;


public class InvoiceUI {
	
	public static Scanner sc = new Scanner(System.in);
	
	public static void showInvoiceUI(Restaurant restaurant) {
		
		int orderId = 0;
		
		ArrayList<Order> orders = restaurant.getOrders();
		ArrayList<Order> previousOrders = restaurant.getPreviousOrders();
		ArrayList<Invoice> invoices = restaurant.getInvoices();
		
		Order order = null;
		
		if(orders.size() == 0) {
			System.out.println("No orders found. Unable to print invoice.");
			return;
		}
		
		//need to prevent error - non int/neg int
		System.out.println("Please enter the order ID: ");
		orderId = sc.nextInt();

		for(int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getId() == orderId) {
				order = orders.get(i);
				System.out.println("printing... \n");
				InvoiceManager.printInvoice(order, invoices);
				
				//after printing
				OrderMgr.moveToCompletedOrder(order, orders, previousOrders);
				System.out.println("\nCheckout Complete!");
				return;
			}
				
			else if(i+1 == orders.size()){
				System.out.println("Order not found in current orders. Unable to print invoice.");
			}
		}
	}
}
