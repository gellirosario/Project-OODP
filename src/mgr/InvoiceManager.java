package mgr;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import classes.Order;
import classes.Invoice;
import classes.SaleItem;


public class InvoiceManager {
	
	public static Scanner sc = new Scanner(System.in);
	
	public static final double GST = 0.07;
	public static final double SVC_CHRG = 0.10;
	
	public static void printInvoice(Order order, ArrayList<Invoice> invoices) {
		
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
		
		svcChrg = SVC_CHRG * subTotal;
		
		tax = GST * (subTotal + svcChrg);
		
		grandTotal = subTotal + tax;
		
		//Create new invoice, append to arraylist invoices
		invoice = new Invoice(invoices.size()+1, order, tax, svcChrg, subTotal, grandTotal);
		
		invoices.add(invoice);
		
		//count quantity of each saleitem
		count = countItems(saleItems);
		
		saleItems = removeDuplicate(saleItems);
		
		Date curDate = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String paymentDate = dateFormat.format(curDate);
		
		//start of print
		System.out.println("****************************************");
		System.out.println("                 INVOICE                ");
		System.out.println("****************************************");
		System.out.println("Table: " + order.getTable().getId());
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
		System.out.println(String.format("%30.30s: %9.2f", "Svc Chrg 10%", svcChrg));
		System.out.println(String.format("%30.30s: %9.2f", "GST 7%", tax));
		System.out.println("-----------------------------------------");
		System.out.println(String.format("%30.30s: %9.2f", "TOTAL", grandTotal));
		System.out.print("\n");
		System.out.println("*****************************************");
		System.out.println("      Thank you for dining with us!      ");
		System.out.println("*****************************************");
		//end of print
		
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
	
	public static boolean checkDate(String date) {
		
		Date curDate;
		Date tgtDate;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		
		try {
			//check if date in correct format
			dateFormat.parse(date);
			
			tgtDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			
			curDate = new Date();
			
			//check if date is in the future
			if(curDate.compareTo(tgtDate) < 0) {
				System.out.println("Invalid input, date entered is in the future.");
				System.out.println("Please try again.\n");
				return false;
			}
			
			//accounts for cases like 4/4/19 instead of 04/04/2019
			if(date.length() != 10) {
				System.out.println("Invalid input, please try again.");
				System.out.println("Sample input: 05/03/2019 \n");
				return false;
			}
			return true;
			
		}
		catch (ParseException e) {
			System.out.println("Invalid input, please try again.");
			System.out.println("Sample input: 18/03/2019 \n");
			return false;
		}
	}
	
	public static boolean checkMonth(String month) {
		Date curMonth;
		Date tgtMonth;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
		dateFormat.setLenient(false);
		
		try {
			//check if input is in correct format
			dateFormat.parse(month);
			
			tgtMonth = new SimpleDateFormat("MM/yyyy").parse(month);
			
			curMonth = new Date();
			
			//check if date is in the future
			if(curMonth.compareTo(tgtMonth) < 0) {
				System.out.println("Invalid input, month entered is in the future.");
				System.out.println("Please try again.\n");
				return false;
			}
			
			//accounts for cases like 4/19 instead of 04/2019
			if(month.length() != 7) {
				System.out.println("Invalid input, please try again.");
				System.out.println("Sample input: 03/2019 \n");
				return false;
			}
			
			return true;
			
		}
		catch (ParseException e) {
			System.out.println("Invalid input, please try again.");
			System.out.println("Sample input: 03/2019 \n");
			return false;
		}
	}
	
	//print sales revenue report
	public static void printByDay(String day, ArrayList<Invoice> invoices) {//dd/MM/yyyy
		
		Date date;
		String dateStr = null;
		
		double totalRevenue = 0.0;
		
		ArrayList<Invoice> foundInvoices = new ArrayList<Invoice>();
		ArrayList<SaleItem> saleItems = new ArrayList<SaleItem>();
		ArrayList<SaleItem> totalSaleItems = new ArrayList<SaleItem>();
		
		for(int i = 0; i < invoices.size(); i++) {
			
			date = invoices.get(i).getOrder().getOrderDateTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateStr = dateFormat.format(date);
		
			if(day.equals(dateStr)){
				foundInvoices.add(invoices.get(i));
			}
		}
		
		if(foundInvoices.size() == 0) {
			//print no records found
			System.out.println("No sales recorded on " + day);
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
			
			System.out.println("generating...\n");
			//start print
			System.out.println("------------------------------------------------------------------");
			System.out.println("                         SALE REVENUE REPORT                      ");
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
			System.out.println(String.format("%s %8.2f", "TOTAL REVENUE (incl. gst & svcChrg): ", totalRevenue));
			//end print
			
			
		}
	}
	
	public static void printByMonth(String month, ArrayList<Invoice> invoices) {//MM/yyyy
		
		Date date;
		String monthStr = null;
		
		double totalRevenue = 0.0;
		
		ArrayList<Invoice> foundInvoices = new ArrayList<Invoice>();
		ArrayList<SaleItem> saleItems = new ArrayList<SaleItem>();
		ArrayList<SaleItem> totalSaleItems = new ArrayList<SaleItem>();
		
		for(int i = 0; i < invoices.size(); i++) {
			
			date = invoices.get(i).getOrder().getOrderDateTime();
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
			monthStr = dateFormat.format(date);
		
			if(month.equals(monthStr)){
				foundInvoices.add(invoices.get(i));
			}
		}
		
		if(foundInvoices.size() == 0) {
			//print no records found
			System.out.println("No sales recorded on " + month);
		}
		
		else {
			String itemName;
			int qty = 0;
			double price = 0.0;
			
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
			
			System.out.println("generating...\n");
			//start print
			System.out.println("------------------------------------------------------------------");
			System.out.println("                         SALE REVENUE REPORT                      ");
			System.out.println("------------------------------------------------------------------");
			System.out.println("Month: " + monthStr + "\n");
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
			System.out.println(String.format("%s %8.2f", "TOTAL REVENUE (incl. gst & svcChrg): ", totalRevenue));
			//end print
		}
		
	}
	
	public static double calTotalRevenue(ArrayList<Invoice> foundInvoices) {
		
		double totalRevenue = 0.0;
		
		for(int i = 0; i < foundInvoices.size(); i++) {
			totalRevenue += foundInvoices.get(i).getGrandTotal();
		}
		
		return totalRevenue;
	}
	
}
