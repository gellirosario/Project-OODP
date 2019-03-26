package classes;

import java.util.ArrayList;

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
	//public static ArrayList<Order> previousOrders;
	
	public static ArrayList<Invoice> invoices;
	
	public static ArrayList<Reservation> reservations;
	//public static ArrayList<Reservation> previousReservations;
	
	public static ArrayList<Table> tables;
	
	public static ArrayList<Staff> staffs;
	public static ArrayList<Customer> customers;
	
	
	/**
	 * 
	 * Load restaurant data from previous saved data.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void loadRestaurant(){
		initRestaurant();
		
		//enter code
	}
	
	/**
	 * 
	 * Save restaurant data
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void saveRestaurant(){
		
		//enter code
	}
	
	/**
	 * Initialize restaurant static members
	 */
	public static void initRestaurant(){
		initMenu();
		initPeople();
		initTables();
		initOrders();
		initInvoices();
		initReservations();
	}
	
	/**
	 * Initialize restaurant menu
	 */
	public static void initMenu(){
		
		ArrayList<SaleItem> saleItems = new ArrayList<SaleItem>();
		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
		ArrayList<Promotion> promoItems = new ArrayList<Promotion>();
		
		MenuItem foodExample = new MenuItem(11,"Cheese Beef Burger","Beef patty with melted cheddar cheese, tomatoes and lettuce",20.45,MenuType.MAIN);
		
		menuItems.add(foodExample);
		
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
		
		for(int i=0; i<5; i++) //5 x 10 seats
			tables.add(new Table(i, Status.Vacated, 10));	
		
		for(int i=5; i<10; i++) //5 x 8 seats
			
			tables.add(new Table(i, Status.Vacated, 8));
		
		for(int i=10; i<20; i++) //10 x 4 seats
			tables.add(new Table(i, Status.Vacated, 4));
		
		for(int i=20; i<30; i++) //10 x 2 seats
			tables.add(new Table(i, Status.Vacated, 2));
		
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
	 * Initialize restaurant reservations
	 */
	public static void initReservations(){
        Restaurant.reservations = new ArrayList<Reservation>();
	}

}
