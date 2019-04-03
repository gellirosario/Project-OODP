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
				
				//after printing
				completedOrders.add(order);
				orders.remove(order);
			}
			else if(i+1 == orders.size()){
				System.out.println("Order not in current orders. Unable to print invoice.");
			}
		}
	}
}
