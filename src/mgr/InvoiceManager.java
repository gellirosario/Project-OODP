package mgr;

import java.util.ArrayList;
import java.util.Scanner;

//import app.RRPSSApp;
import classes.Order;
import classes.Restaurant;
import classes.Invoice;
import classes.SaleItem;
import mgr.OrderMgr;
import ui.InvoiceUI;



public class InvoiceManager {
	
	public static void printInvoice(Order order) {
		
		//retrieve order
		for(int i = 0; i < Restaurant.orders.size(); i++) {
			
			if(Restaurant.orders.get(i).getId() == orderId) {
				order = Restaurant.orders.get(i);
			}
		}
		//retrieve total tax
		//retrieve total svcChrg
		//retrieve subTotal
		
	}
	
	public static double calSubTotal(Order order) {
		
		double subTotal = 0.0;
		
		SaleItem[] items = order.getItems();
		
		for(SaleItem item : items) {
			subTotal += item.getPrice();
		}
		
		return subTotal;
	}
	
	
}
