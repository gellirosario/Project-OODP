package manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import app.RRPSSApp;
import classes.Order;
import classes.Restaurant;
import classes.SaleItem;
import classes.Table;
import classes.Table.Status;
import ui.OrderUI;

public class OrderManager {
		
		public static Scanner sc = new Scanner(System.in);
		
		//Incomplete need to add error prevention
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
						
						/*
						if(j+1 !=  Restaurant.orders.get(i).getItems().size())
						{
							System.out.print(", ");
						}
						*/
								
					}
					
					System.out.println("\nTable No: " + Restaurant.orders.get(i).getTable().getId() + "\n" + Restaurant.orders.get(i).getOrderDateTime() + "");
					System.out.println("Created by " + Restaurant.orders.get(i).getStaff().getName() + "\n");
				}
			}
			else
			{
				System.out.println("No orders found.");
			}
		}

		//Incomplete need to add error prevention
		public static void createOrder() {
			
			ArrayList<SaleItem> saleItems = new ArrayList<SaleItem>();
			Order order = null;
			Table occupiedTable = null;
			int choice = 0, pax = 0;
			char option;
			
			Date date = new Date();
			//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			//System.out.println(dateFormat.format(date));
			
			RRPSSApp.currentStaff = RRPSSApp.getStaffIdentity(Restaurant.staffs);
			
			saleItems = addItemToOrder(new ArrayList<SaleItem>());
			
			System.out.print("Please enter pax: ");
			pax = sc.nextInt();
			
			for(int i = 0; i < Restaurant.tables.size();i++)
			{
				if(Restaurant.tables.get(i).getSeatingCapacity() >= pax && Restaurant.tables.get(i).getStatus() == Status.Vacated)
				{
					occupiedTable = Restaurant.tables.get(i);
					
					break;
				}
				
				if(i+1 == Restaurant.tables.size())
				{
					System.out.println("Table is all occupied. Please wait for a vacated table.");
				}
			}
			
			if(RRPSSApp.currentStaff != null && occupiedTable != null && saleItems != null)
			{
				//need change - according to reservation?
				//table selected from reservation
				
				order = new Order(Restaurant.orders.size()+1,RRPSSApp.currentStaff,saleItems,occupiedTable,date);
				
				Restaurant.orders.add(order);
				
				System.out.print("Order complete!");
			}
			else
			{
				System.out.print("Order incomplete. Please try again later");
			}
			
		}
		
		//Incomplete need to add error prevention
		public static void updateOrder() {
			
			int choice = 0;
			int orderId = 0;
			
			if(Restaurant.orders.size() == 0)
			{
				System.out.println("No orders found. Unable to edit any orders.");
			}
			else
			{
				viewOrder();
				
				System.out.println("\n[Update Orders]\n");
				
				ArrayList<SaleItem> saleItems = null;
				Order order = null;
				
				System.out.println("Please enter the order ID you want to edit : ");
				orderId = sc.nextInt();
				
				for(int i = 0; i < Restaurant.orders.size();i++)
				{
					if(Restaurant.orders.get(i).getId() == orderId)
					{
						order = Restaurant.orders.get(i); //order data retrieved
						saleItems = order.getItems();
						break;
					}
						
					if(i+1 == Restaurant.orders.size())
					{
						System.out.println("Order not found.");
					}
				}
				
				System.out.println("Please select an option: ");
				System.out.println("[1] Add Items");
				System.out.println("[2] Remove Items");
				System.out.println("[3] Return to Order Menu");
				
				choice = sc.nextInt();
				
				switch(choice)
				{
					case 1: 
						saleItems = addItemToOrder(saleItems);
						break;
					case 2:
						removeItemFromOrder(saleItems);
						break;
					case 3: 
						OrderUI.showOrderUI();
						break;
				}
				
				
			}
			
		}
		
		public static ArrayList<SaleItem> addItemToOrder(ArrayList<SaleItem> items) {
			
			ArrayList<SaleItem> saleItems = items;
			
			int choice = 0;
			char option;
			
			System.out.println("\n[Menu]\n");
			
			for(int i = 0; i < Restaurant.menuItems.size();i++)
			{
				System.out.println("("+ Restaurant.menuItems.get(i).getId() + ") " + Restaurant.menuItems.get(i).getName() + " | $" 
									+ Restaurant.menuItems.get(i).getPrice() + " | " + Restaurant.menuItems.get(i).getMenuType());
			}
			
			do{
				System.out.println("Add menu item to order: ");
				choice = sc.nextInt();
				
				for(int i = 0; i < Restaurant.menuItems.size();i++)
				{
					if(Restaurant.menuItems.get(i).getId() == choice)
					{	
						saleItems.add(Restaurant.menuItems.get(i));
						System.out.println("Menu item added successfully!");
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
			
			return saleItems;
		}
		
		public static void removeItemFromOrder(ArrayList<SaleItem> items) {
			ArrayList<SaleItem> saleItems = items;
			
			int choice = 0;
			char option;
			
			System.out.println("\n[List ]\n");
			
			for(int i = 0; i < items.size();i++)
			{
				System.out.println("("+ items.get(i).getId() + ") " + items.get(i).getName() + " | $" 
									+ items.get(i).getPrice() + " | " + items.get(i).getDescription()); //can't get menu type
			}
			
			do{
				System.out.println("Remove menu item from order: ");
				choice = sc.nextInt();
				
				for(int i = 0; i < items.size();i++)
				{
					if(items.get(i).getId() == choice)
					{	
						saleItems.remove(i);
						System.out.println("Menu item removed successfully!");
						break;
					}
					
					if(i+1 == Restaurant.menuItems.size())
					{
						System.out.println("Menu item not found.");
					}
				}
				
				System.out.print("Continue removing from order? (Y/N): ");
				option = Character.toUpperCase(sc.next().charAt(0)); //check if user input is char
				
			}while(option=='Y');
			
		}
		
		//Incomplete need to add error prevention
		public static void removeOrder() {
			
			int choice = 0;
			
			System.out.println("\n[Menu]\n");
			
			for(int i = 0; i < Restaurant.menuItems.size();i++)
			{
				System.out.println("("+ Restaurant.menuItems.get(i).getId() + ") " + Restaurant.menuItems.get(i).getName() + " | $" 
									+ Restaurant.menuItems.get(i).getPrice() + " | " + Restaurant.menuItems.get(i).getMenuType());
			}
			
			if(Restaurant.orders.size() == 0)
			{
				System.out.println("No orders found. Unable to remove any orders.");
			}
			else
			{
				viewOrder();
				
				System.out.println("\n[Remove Orders]\n");
				
				System.out.println("Please enter the order ID you want to remove : ");
				choice = sc.nextInt();
				
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
			
		}
		
		//for invoice printing
		public static void moveToCompletedOrder(Order order){
			Restaurant.completedOrders.add(order);
			Restaurant.orders.remove(order);
		}
}
