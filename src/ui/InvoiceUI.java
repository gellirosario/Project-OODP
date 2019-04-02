package ui;

import java.util.Scanner;

import mgr.InvoiceManager;


public class InvoiceUI {
	
	public static Scanner sc = new Scanner(System.in);
	
	//need to add check for integer input only
	public static void showInvoiceUI() {
		int orderId = 0;
		
		System.out.println("Please enter the order ID: ");
		orderId = sc.nextInt();
		
		//if it is a valid id - call printInvoice
		if(InvoiceManager.checkId(orderId)) {
			InvoiceManager.printInvoice(orderId);
		}
		else {
			System.out.println("Order not found. Unable to print invoice.");
		}
	}
}
