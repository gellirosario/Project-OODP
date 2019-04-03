package ui;

import java.util.Scanner;
import java.util.ArrayList;

import classes.Order;
import classes.Restaurant;
import mgr.InvoiceManager;


public class InvoiceUI {
	
	public static Scanner sc = new Scanner(System.in);
	
	public static void showInvoiceUI(Restaurant restaurant) {
		
		int orderId = 0;
		
		ArrayList<Order> orders = restaurant.getOrders();
		ArrayList<Order> completedOrders = restaurant.getOrders();
		
		Order order = null;
		
		if(orders.size() == 0) {
			System.out.println("No orders found. Unable to print invoice.");
		}
		
		//need to prevent error - non int/neg int
		System.out.println("Please enter the order ID: ");
		orderId = sc.nextInt();

		for(int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getId() == orderId) {
				order = orders.get(i);
				InvoiceManager.printInvoice(order);
			}
			if(i+1 == orders.size()){
				for(int j = 0; j < completedOrders.size(); j++) {
					if(completedOrders.get(j).getId() == orderId) {
						System.out.println("This order has already been checked out.");
						break;
						return; //call from order
					}
				}
				System.out.println("Order not found. Unable to print invoice");
			}
		}
	}
}
