package mgr;

import java.util.ArrayList;
import java.util.Scanner;

//import app.RRPSSApp;
import classes.Order;
import classes.Restaurant;
import classes.Invoice;
import classes.SaleItem;
import mgr.OrderMgr;



public class InvoiceManager {
	
	public static boolean checkId(int orderId) {

		//check 1 - if there are no orders
		if(Restaurant.orders.size() == 0) {
			return false;
		}
		
		//check 2 - if there is no such orderId
		for(int i = 0; i < Restaurant.orders.size(); i++) {
		
			if(Restaurant.orders.get(i).getId() == orderId) {
				return true;
			}
		}
		return false;
	}
	
	public static void printInvoice(int orderId) {
		
		Order order = null;
		
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
