package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import classes.*;
import classes.MenuItem.MenuType;
import classes.Person.Gender;
import classes.Table.Status;

/**
 * 
 * The Restaurant class will provide a static database for the application.
 * 
 */
public class Restaurant {
	
	public static ArrayList<SaleItem> saleItems;
	public static ArrayList<MenuItem> menuItems;
	public static ArrayList<Promotion> promotions;
	
	public static ArrayList<Order> orders;
	public static ArrayList<Order> completedOrders;
	
	public static ArrayList<Invoice> invoices;
	
	public static ArrayList<Reservation> reservations;
	//public static ArrayList<Reservation> completedReservations;
	//public static ArrayList<Reservation> expiredReservations;
	
	public static ArrayList<Table> tables;
	
	public static ArrayList<Staff> staffs;
	public static ArrayList<Customer> customers;
	
	
	/**
	 * 
	 * Load restaurant data from previous saved data.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void loadData(){
		initRestaurant();
		
		//add code here
	}
	
	/**
	 * 
	 * Save restaurant data
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void saveData(){
		//add code here
	}
	
	/**
	 * Initialize restaurant static members
	 */
	public static void initRestaurant(){
		initMenu();
		initPeople();
		initTables();
		initOrders();
		initCompletedOrders();
		initInvoices();
		initReservations();
	}
	
	/**
	 * Initialize restaurant menu
	 */
	public static void initMenu(){
		
		//ArrayList<SaleItem> saleItems = new ArrayList<SaleItem>();
		
		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
		ArrayList<Promotion> promoItems = new ArrayList<Promotion>();
		
		MenuItem foodExample = new MenuItem(11,"Cheese Beef Burger","Beef patty with melted cheddar cheese, tomatoes and lettuce",20.45,MenuType.MAIN);
		MenuItem foodExample2 = new MenuItem(21,"Caesar Salad","Lettuce, eggs, parmesan cheese and cheese croutons with Caesar dressing",6.4,MenuType.SIDE);
		
		menuItems.add(foodExample);
		menuItems.add(foodExample2);
		
		Promotion promoExample = new Promotion(101,"Lunch Set A","All time popular lunch set!",18.5,menuItems);
		
		promoItems.add(promoExample);
		
		Restaurant.menuItems = menuItems;
		Restaurant.promotions = promoItems;
		
	}
	
	/**
	 * Initialize restaurant staff and customers
	 */
	public static void initPeople(){
		
		ArrayList<Staff> staffs = new ArrayList<Staff>();
		
		String[] name = {"John", "Mary", "Susan", "Henry"};
		Gender[] gender = {Gender.Male, Gender.Female, Gender.Female, Gender.Male};
		String[] jobTitle = {"Manager", "Chef", "Waiter", "Waiter"};
		
		for(int i=0; i<4; i++)
		{
			staffs.add(new Staff(i+1,name[i],gender[i],jobTitle[i]));
		}
		
		Restaurant.staffs = staffs;
		
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		String[] cName = {"Kannan", "Jennifer", "Jackson", "Katarina"};
		Gender[] cGender = {Gender.Female, Gender.Female, Gender.Male, Gender.Female};
		int[] cNo = {91234567,91234566,91234565,91234564};
		
		for(int i=0; i<4; i++) 
		{
			customers.add(new Customer(i+1,cName[i],cGender[i],cNo[i]));
		}
		
		Restaurant.customers = customers;
	}
	
	/**
	 * Initialize restaurant tables
	 */
	public static void initTables(){
		
		ArrayList<Table> tables = new ArrayList<Table>();
		
		for(int i=1; i<6; i++) //5 x 2 seats [ID: 1-5]
			tables.add(new Table(i, Status.Vacated, 2));	
		
		for(int i=6; i<11; i++) //5 x 4 seats [ID: 6-10]
			tables.add(new Table(i, Status.Vacated, 4)); 
		
		for(int i=11; i<21; i++) //10 x 8 seats [ID: 11-20]
			tables.add(new Table(i, Status.Vacated, 8));
		
		for(int i=21; i<31; i++) //10 x 10 seats [ID: 21-30]
			tables.add(new Table(i, Status.Vacated, 10));
		
		Restaurant.tables = tables;
	}
	
	/**
	 * Initialize restaurant invoices
	 */
	public static void initInvoices(){
        Restaurant.invoices = new ArrayList<Invoice>();
	}

	/**
	 * Initialize restaurant orders
	 */
	public static void initOrders(){
		Restaurant.orders = new ArrayList<Order>();
	}
	
	/**
	 * Initialize restaurant completed orders
	 */
	public static void initCompletedOrders(){
		Restaurant.completedOrders = new ArrayList<Order>();
	}


	/**
	 * Initialize restaurant reservations
	 */
	public static void initReservations(){
        Restaurant.reservations = new ArrayList<Reservation>();
	}

}
