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
		
		ArrayList<Integer> count = new ArrayList<Integer>();
		
		//Calculations
		subTotal = calSubTotal(order);
		
		tax = GST * subTotal;
		
		svcChrg = SVC_CHRG * subTotal;
		
		//Create new invoice, append to arraylist invoices
		invoice = new Invoice(invoices.size()+1, order, tax, svcChrg, subTotal);
		
		invoices.add(invoice);
		
		//count quantity of each saleitem
		count = countItems(saleItems);
		
		saleItems = removeDuplicate(saleItems);
		
		//print invoice
		
		
		
		
		

		
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
	
	//counts the quantity of each sale item from the list of sale items.
	//count quantity in arraylist count corresponds to first instance of each saleitem
	public static ArrayList<Integer> countItems(ArrayList<SaleItem> items) {
		
		Integer value = 0;
		
		ArrayList<Integer> count = new ArrayList<Integer>();
		
		ArrayList<SaleItem> temp = new ArrayList<SaleItem>();
		
		for(int i = 0; i < items.size(); i++) {
			if(temp.contains(items.get(i))) {
				for(int j = 0; j < temp.size(); j++) {
					if(items.get(i).getId() == temp.get(j).getId()) {
						value = count.get(j);
						value += 1;
						count.set(j, value);
					}
				}
			}
			else if(!temp.contains(items.get(i))) {
				temp.add(items.get(i));
				count.add(1);
			}
		}
		return count;
		
	}
	
	
	
	
}
