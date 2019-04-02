package ui;

import java.util.Scanner;
import java.util.ArrayList;

import classes.Order;
import classes.Restaurant;
import mgr.InvoiceManager;


public class InvoiceUI {
	
	public static Scanner sc = new Scanner(System.in);
	
	private static Restaurant restaurant;
	
	public static void showInvoiceUI() {
		
		int orderId = 0;
		
		ArrayList<Order> orders = restaurant.getOrders();
		
		Order order = null;
		
		if(orders.size() == 0) {
			System.out.println("No orders found. Unable to print invoice.");
		}
		
		//need to add check for integer input only
		System.out.println("Please enter the order ID: ");
		orderId = sc.nextInt();

		for(int i = 0; i < orders.size(); i++) {
			if(orders.get(i).getId() == orderId) {
				order = orders.get(i);
				InvoiceManager.printInvoice(orderId);
			}
			if(i+1 == orders.size()){
				System.out.println("Order not found. Unable to print invoice");
			}
		}
	}
}
