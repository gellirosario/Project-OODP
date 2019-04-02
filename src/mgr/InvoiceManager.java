package mgr;

import java.util.ArrayList;
import java.util.Scanner;

import classes.Order;
import classes.Restaurant;
import classes.Invoice;
import classes.SaleItem;
import mgr.OrderMgr;
import ui.InvoiceUI;



public class InvoiceManager {
	
	public static final double GST = 0.07;
	public static final double SVC_CHRG = 0.10;
	
	private static Restaurant restaurant;
	
	public static void printInvoice(Order order) {
		
		double tax = 0.0;
		double svcChrg = 0.0;
		double subTotal = 0.0;
		
		Invoice invoice = null;
		
		ArrayList<Invoice> invoices = restaurant.getInvoices();
		
		ArrayList<SaleItem> saleItems = order.getItems();
		
		subTotal = calSubTotal(order);
		
		tax = GST * subTotal;
		
		svcChrg = SVC_CHRG * subTotal;
		
		invoice = new Invoice(invoices.size()+1, order, tax, svcChrg, subTotal);
		
		invoices.add(invoice);

		
	}
	
	public static double calSubTotal(Order order) {
		
		double subTotal = 0.0;
		
		ArrayList<SaleItem> items = order.getItems();
		
		for(int i = 0; i < items.size(); i++) {
			subTotal += items.get(i).getPrice();
		}
		
		return subTotal;
	}
	
	public static ArrayList<SaleItem> removeDuplicate(ArrayList<SaleItem> items){
		
		ArrayList<SaleItem> newItems = new ArrayList<SaleItem>();
		
		for(SaleItem item : items) {
			
			if(!newItems.contains(item)) {
				newItems.add(item);
			}
		}
		
		return newItems;
	}
	
	
}
