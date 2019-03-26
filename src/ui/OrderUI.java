package ui;

import java.util.ArrayList;
import java.util.Scanner;

import app.RRPSSApp;
import classes.*;

/**
 * 
 * Displays UI to the staff when creating, editing and removing orders and also printing invoices after payment.
 * @author Ann
 * 
 */
public class OrderUI {
	
	public static Scanner sc = new Scanner(System.in);
	
	/**
	 * 
	 * Displays options for staff.
	 * 
	 */
	public static void showOrderUI() {
		
		
		int option = 0;
		
		do {
			System.out.println("\n================================");
			System.out.println("=== [Orders] ===");
			System.out.println("Choose an option:");
			System.out.println("[1] View all Orders");
			System.out.println("[2] Create Order");
			System.out.println("[3] Edit Order");
			System.out.println("[4] Remove Order");
			System.out.println("[5] Print Invoice");
			System.out.println("[6] Return to Main Menu");
			System.out.println("[0] Exit RRPSS");
			System.out.println("================================");
			
			option = sc.nextInt();
			
			switch(option){
				case 1:	//View list of Orders
					viewOrder();
					break;
				case 2: //Create an order
					createOrder();
					break;
				case 3: //Edit an order
					break;
				case 4: //Remove an order
					break;
				case 5: //Print invoice
					break;
				case 6: //Return to Main Menu
					break;
					
				default: 
					System.out.println("No such option.");
					break;
			}
			
		}while(option != 0);
	}
	
	public static void viewOrder() {
		System.out.println("\n[View all Orders]\n");
		
		if(Restaurant.orders.size() > 0)
		{
			for(int i = 0; i < Restaurant.orders.size();i++)
			{
				System.out.println("("+ Restaurant.orders.get(i).getId() + ") " + Restaurant.orders.get(i).getItems() + " | " 
									+ Restaurant.orders.get(i).getTable() + " | " + Restaurant.orders.get(i).getOrderDateTime());
			}
		}
		else
		{
			System.out.println("No orders found.");
		}
	}
	
	public static void createOrder() {
		
		ArrayList<SaleItem> saleItems = new ArrayList<SaleItem>();
		ArrayList<Order> orders = new ArrayList<Order>();
		int choice = 0, orderSize = 0;
		char option;
		
		System.out.println("\n[Menu]\n");
		
		for(int i = 0; i < Restaurant.menuItems.size();i++)
		{
			System.out.println("("+ Restaurant.menuItems.get(i).getId() + ") " + Restaurant.menuItems.get(i).getName() + " | $" 
								+ Restaurant.menuItems.get(i).getPrice() + " | " + Restaurant.menuItems.get(i).getMenuType());
		}
		
		
		
		do{
			System.out.println("Add food to order: ");
			choice = sc.nextInt();
			
			for(int i = 0; i < Restaurant.menuItems.size();i++)
			{
				if(Restaurant.menuItems.get(i).getId() == choice)
				{	
					if(orderSize == 0)
					{
						saleItems.add(Restaurant.menuItems.get(i));
					}
					else
					{
						System.out.println("Menu item already been added.");
					}
					
					break;
				}
				
				if(i+1 == Restaurant.menuItems.size())
				{
					System.out.println("Menu item not found.");
				}
					
				
			}
			
			System.out.print("Continue adding to order? (Y/N): ");
			option = Character.toUpperCase(sc.next().charAt(0));
			
		}while(option=='Y');
		
		orders.get(Restaurant.orders.size()).setId(Restaurant.orders.size());
		orders.get(Restaurant.orders.size()).setItems(saleItems);
		orders.get(Restaurant.orders.size()).setStaff(RRPSSApp.currentStaff);
		
		System.out.print("Order complete!");
		
	}
}
