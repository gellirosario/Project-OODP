package ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
			System.out.println("[1] View all Orders"); //maybe can do for current/completed orders?
			System.out.println("[2] Create Order");
			System.out.println("[3] Edit Order");
			System.out.println("[4] Remove Order");
			//System.out.println("[5] Print Invoice");
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
					editOrder();
					break;
				case 4: //Remove an order
					removeOrder();
					break;
				case 5: //Print invoice
					break;
				case 6: //Return to Main Menu
					RRPSSApp.showMainMenu();
					break;
					
				default: 
					System.out.println("No such option.");
					break;
			}
			
		}while(option != 0);
	}
	
	//Add to Manager?
	//Incomplete
	public static void viewOrder() {
		System.out.println("\n[View all Orders]\n");
		
		if(Restaurant.orders.size() > 0)
		{
			//need change the ui
			for(int i = 0; i < Restaurant.orders.size();i++)
			{
				System.out.println("(Order No: "+ Restaurant.orders.get(i).getId() + ")");
				
				for(int j = 0; j < Restaurant.orders.get(i).getItems().size(); j++)
				{
					System.out.println(Restaurant.orders.get(i).getItems().get(j).getName());
					
					if(j+1 !=  Restaurant.orders.get(i).getItems().size())
					{
						System.out.print(", ");
					}
							
				}
				
				System.out.println("\n(Table No: " + Restaurant.orders.get(i).getTable().getId() + ")\n(" + Restaurant.orders.get(i).getOrderDateTime() + ")");
				System.out.println("Created by " + Restaurant.orders.get(i).getStaff().getName());
			}
		}
		else
		{
			System.out.println("No orders found.");
		}
	}

	//Add to Manager?
	//Incomplete
	public static void createOrder() {
		
		ArrayList<SaleItem> saleItems = new ArrayList<SaleItem>();
		Order order;
		int choice = 0, orderSize = 0, quantity = 0;
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
					saleItems.add(Restaurant.menuItems.get(i));
					
					break;
				}
				
				if(i+1 == Restaurant.menuItems.size())
				{
					System.out.println("Menu item not found.");
				}
					
				
			}
			
			/*
			System.out.println("Quantity of " + saleItems.get(orderSize).getName() + ": ");
			quantity = sc.nextInt();
			
			for(int i = 0; i < quantity; i++)
			{
				saleItems.add(Restaurant.menuItems.get(i));
			}*/
			
			
			System.out.print("Continue adding to order? (Y/N): ");
			option = Character.toUpperCase(sc.next().charAt(0)); //check if user input is char
			
		}while(option=='Y');
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		//need change - according to reservation?
		order = new Order(Restaurant.orders.size()+1,RRPSSApp.currentStaff,saleItems,Restaurant.tables.get(Restaurant.orders.size()),date);
		
		Restaurant.orders.add(order);
		
		System.out.print("Order complete!");
		
	}
	
	//Add to Manager?
	//Incomplete
	public static void editOrder() {
		
	}
	
	//Add to Manager?
	//Incomplete
	public static void removeOrder() {
		
		int choice = 0;
		
		viewOrder();
		
		System.out.println("\n[Remove Orders]\n");
		
		System.out.println("Please enter the order ID to remove : ");
		choice = sc.nextInt();
		
		if(Restaurant.orders.size() > 0)
		{
			for(int i = 0; i < Restaurant.orders.size();i++)
			{
				if(Restaurant.orders.get(i).getId() == choice)
				{
					Restaurant.orders.remove(Restaurant.orders.get(i));
					System.out.println("Order successfully removed!");
					break;
				}
				
				if(i+1 == Restaurant.orders.size())
				{
					System.out.println("Order not found.");
				}
			}
		}
		else
		{
			System.out.println("No orders found.");
		}
		
	}
}
