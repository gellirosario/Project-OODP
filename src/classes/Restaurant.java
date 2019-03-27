package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
	
	public static final int		BOOKING_MTHINADVANCE		= 1;
	public static final	int		SESSION_AMSTARTTIME			= 11;
	public static final	int		SESSION_AMENDTIME			= 15;
	public static final	int		SESSION_PMSTARTTIME			= 18;
	public static final	int		SESSION_PMENDTIME			= 22;
	
	public static final Path 	DATAPATH 					= Paths.get(System.getProperty("user.dir"), "data");
	public static final String 	RESTAURANT_FILE_NAME		= "data.dat";	
	
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
		Object[] restaurantMember 	= null;
		Path saveData 				= Paths.get(DATAPATH.toString(), RESTAURANT_FILE_NAME);
		FileInputStream fis 		= null;
		ObjectInputStream ois 		= null;
		
		try {
			fis = new FileInputStream(saveData.toString());
			ois = new ObjectInputStream(fis);
			restaurantMember = (Object[]) ois.readObject();			
			if(restaurantMember != null){
				customers = (ArrayList<Customer>) restaurantMember[0];
				staffs = (ArrayList<Staff>) restaurantMember[1];
				menuItems = (ArrayList<MenuItem>) restaurantMember[2];
				promotions = (ArrayList<Promotion>) restaurantMember[3];
				invoices = (ArrayList<Invoice>) restaurantMember[4];
				orders = (ArrayList<Order>) restaurantMember[5];
				completedOrders = (ArrayList<Order>) restaurantMember[6];
				reservations = (ArrayList<Reservation>) restaurantMember[7];
				tables = (ArrayList<Table>) restaurantMember[8];
			}
			ois.close();
		} catch (IOException ex) {
			System.out.println(RESTAURANT_FILE_NAME + " not found or does not exists. Default settings will be loaded.");
			initRestaurant();
		} catch (ClassCastException|ClassNotFoundException ex) {
			System.out.println("Data file " + RESTAURANT_FILE_NAME + " is corrupted. Default settings will be loaded instead.");
			initRestaurant();
		}

	}
	
	/**
	 * 
	 * Save restaurant data
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void saveData(){
		//add code here
		
		if(!Files.exists(DATAPATH)){
			System.out.println("Data folder not found!");
			File dir = new File(DATAPATH.toString());
			if(dir.mkdir()){
				System.out.println("Directory " + DATAPATH + " created...");
			}
		}
		
		Object[] restaurantMember 	= {customers,
										staffs, 
										menuItems,
										promotions,
										invoices, 
										orders, 
										completedOrders, 
										reservations,
										tables};
		
		Path 				saveFileName 	= Paths.get(DATAPATH.toString(), RESTAURANT_FILE_NAME);
		FileOutputStream   	fos 			= null;
		ObjectOutputStream 	oos 			= null;
		
		try {
			fos = new FileOutputStream(saveFileName.toString());
			oos = new ObjectOutputStream(fos);
			oos.writeObject((Object[])restaurantMember);
			oos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
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
