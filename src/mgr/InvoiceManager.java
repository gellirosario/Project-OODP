package mgr;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
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
	
	private static ArrayList<Invoice> invoices = restaurant.getInvoices();
	
	public static void printInvoice(Order order) {
		
		String itemName;
		
		int qty = 0;
		
		double tax = 0.0;
		double svcChrg = 0.0;
		double subTotal = 0.0;
		double grandTotal = 0.0;
		double price = 0.0;
		
		Invoice invoice = null;
		
		ArrayList<SaleItem> saleItems = order.getItems();
		
		ArrayList<Integer> count = new ArrayList<Integer>();
		
		//Calculations
		subTotal = calSubTotal(order);
		
		tax = GST * subTotal;
		
		svcChrg = SVC_CHRG * subTotal;
		
		grandTotal = subTotal + tax + svcChrg;
		
		//Create new invoice, append to arraylist invoices
		invoice = new Invoice(invoices.size()+1, order, tax, svcChrg, subTotal, grandTotal);
		
		invoices.add(invoice);
		
		//count quantity of each saleitem
		count = countItems(saleItems);
		
		saleItems = removeDuplicate(saleItems);
		
		//need to add time
		Date curDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String paymentDate = dateFormat.format(curDate);
		
		//start of print
		System.out.println("****************************************");
		System.out.println("                 INVOICE                ");
		System.out.println("****************************************");
		System.out.println("Table: " + order.getTable().getTableId());
		System.out.println("Date-Time: " + paymentDate);
		System.out.print("\n");
		System.out.println("-----------------------------------------");
		
		for(int i = 0; i < saleItems.size(); i++) {
			itemName = saleItems.get(i).getName();
			qty = count.get(i);
			price = saleItems.get(i).getPrice();
			price *= qty;
			
			System.out.println(String.format("%s %-30.30s %8.2f", qty, itemName, price));
		}
		
		System.out.println("-----------------------------------------");
		System.out.println(String.format("%30.30s: %9.2f", "Sub-Total", subTotal));
		System.out.println(String.format("%30.30s: %9.2f", "GST", tax));
		System.out.println(String.format("%30.30s: %9.2f", "Svc Chrg", svcChrg));
		System.out.println("-----------------------------------------");
		System.out.println(String.format("%30.30s: %9.2f", "TOTAL", grandTotal));
		System.out.print("\n");
		System.out.println("*****************************************");
		System.out.println("      Thank you for dining with us!      ");
		System.out.println("*****************************************");
		//end of print
		
		//uncomment in ordermanager
		moveToCompletedOrder(order);
		//need to update table to available?
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
	
	//print sales revenue report
	public static void printByDate(String tgtDate) {//dd/MM/yyyy
		
		Date date;
		String dateStr = null;
		SaleItem item;
		
		double totalRevenue = 0.0;
		
		ArrayList<Invoice> foundInvoices = new ArrayList<Invoice>();
		ArrayList<SaleItem> saleItems = new ArrayList<SaleItem>();
		ArrayList<SaleItem> totalSaleItems = new ArrayList<SaleItem>();
		
		for(int i = 0; i < invoices.size(); i++) {
			
			date = invoices.get(i).getOrder().getOrderDateTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateStr = dateFormat.format(date);
			
			if(tgtDate.equals(dateStr)){
				foundInvoices.add(invoices.get(i));
			}
		}
		
		if(foundInvoices.size() == 0) {
			//print no records found
			System.out.println("No sales recorded on " + tgtDate);
		}
		
		else {
			String itemName;
			int qty = 0;
			double price = 0.0;
			
			//maybe can be a separate function
			for(int j = 0; j < foundInvoices.size(); j++) {
				
				saleItems = foundInvoices.get(j).getOrder().getItems();
				
				for(int k = 0; k < saleItems.size(); k++) {
					totalSaleItems.add(saleItems.get(k));
				}
			}
			
			ArrayList<Integer> count = new ArrayList<Integer>();
			
			count = countItems(totalSaleItems);
			totalSaleItems = removeDuplicate(totalSaleItems);
			
			totalRevenue = calTotalRevenue(foundInvoices);
			
			//start print
			System.out.println("------------------------------------------------------------------");
			System.out.println("                SALE REVENUE REPORT                   ");
			System.out.println("------------------------------------------------------------------");
			System.out.println("Date: " + dateStr + "\n");
			System.out.println("------------------------------------------------------------------");
			System.out.println(String.format("%s %15.30s %30.20s %15.20s", "Qty", "Item", "Price per qty", "Price*Qty"));
			System.out.println("------------------------------------------------------------------");
			
			for(int k = 0; k < totalSaleItems.size(); k++) {
				itemName = totalSaleItems.get(k).getName();
				qty = count.get(k);
				price = totalSaleItems.get(k).getPrice();
				
				System.out.println(String.format("%-8s %-32.30s %-16.2f %-30.2f", qty, itemName, price, price*qty));
			}
			
			System.out.println("------------------------------------------------------------------");
			System.out.println(String.format("%56s %8.2f", "TOTAL REVENUE: ", totalRevenue));
			//end print
			
			
		}
	}
	
	public static void printByMonth() {
		
	}
	
	public static double calTotalRevenue(ArrayList<Invoice> foundInvoices) {
		
		double totalRevenue = 0.0;
		
		for(int i = 0; i < foundInvoices.size(); i++) {
			totalRevenue += foundInvoices.get(i).getGrandTotal();
		}
		
		return totalRevenue;
	}
	
	
	
	
	
	
}
