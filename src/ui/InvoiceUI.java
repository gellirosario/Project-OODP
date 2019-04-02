package ui;

import java.util.Scanner;

import mgr.InvoiceManager;


public class InvoiceUI {
	
	public static Scanner sc = new Scanner(System.in);
	
	public static void showInvoiceUI() {
		int orderId = 0;
		
		System.out.println("Please enter the order ID: ");
		orderId = sc.nextInt();
		
		//call printInvoice() in InvoiceManager
		InvoiceManager.printInvoice(orderId);
	}
}
