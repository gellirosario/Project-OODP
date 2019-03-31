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
import mgr.MenuItemManager;

/**
 * 
 * The Restaurant class will provide a static database for the application.
 * 
 */
public class Restaurant {
	// ArrayList of objects
	private static ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
	private static ArrayList<Set> sets = new ArrayList<Set>();

	private static ArrayList<Order> orders = new ArrayList<Order>();
	// private static ArrayList<Order> previousOrders;
	private static ArrayList<Invoice> invoices = new ArrayList<Invoice>();
	private static ArrayList<Reservation> reservations = new ArrayList<Reservation>();
	// private static ArrayList<Reservation> previousReservations;
	private static ArrayList<Table> tables = new ArrayList<Table>();

	private static ArrayList<Staff> staffs = new ArrayList<Staff>();
	private static ArrayList<Customer> customers = new ArrayList<Customer>();

	// Manager classes
	private static MenuItemManager menuItemManager = new MenuItemManager();

	public Restaurant() {
		loadRestaurant();
	}

	/**
	 * 
	 * Load restaurant data from previous saved data.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void loadRestaurant() {
		System.out.println("Loading data...");
		initRestaurant();
		System.out.println("Loading data done.");
	}

	/**
	 * 
	 * Save restaurant data
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void saveRestaurant() {
		System.out.println("Saving data...");
		saveSaleItem();
		System.out.println("Saving data done.");
	}

	/**
	 * Initialise restaurant static members
	 */
	private void initRestaurant() {
		initSaleItem();
		initPeople();
		initTables();
		initOrders();
		initInvoices();
		initReservations();
	}

	/**
	 * Initialize restaurant's SaleItem: MenuItem and Set
	 */
	private void initSaleItem() {
//		ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
//		ArrayList<Set> sets = new ArrayList<Set>();
//		
//		MenuItem foodExample = new MenuItem(11,"Cheese Beef Burger","Beef patty with melted cheddar cheese, tomatoes and lettuce",20.45,MenuType.MAIN);
//		
//		menuItems.add(foodExample);
//		
//		Set setExample = new Set(101,"Lunch Set A","All time popular lunch set!",18.5,menuItems);
//		
//		setItems.add(setExample);
//		
//		Restaurant.menuItems = menuItems;
//		Restaurant.sets = setItems;

		// ========== PLACEHOLDER VALUES ========== //TODO remove when not needed
//		menuItems.add(new MenuItem(31, "Ice Lemon Tea", "Homemade fresh ice lemon tea", 1.8, MenuType.DRINK));

		// read/load data from text file, data.txt
		try {
			String line = "";
			BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
			while ((line = reader.readLine()) != null) { // check and read next line
//				System.out.println("Line: " + line); //TODO remove b4 submit

				// used '|' as char to separate values, as ',' is used in description
				// NOTE: used "\\|" as "|" is interpret as logical operator OR
				String[] tokens = line.split("\\|");

				if (tokens[0].equals("MenuItem")) { // MenuItem
					menuItems.add(new MenuItem(Integer.parseInt(tokens[1]), tokens[2], tokens[3],
							Double.parseDouble(tokens[4]), MenuType.valueOf(tokens[5])));
				} else if (tokens[0].equals("Set")) { // Set
					String[] stringIds = tokens[5].split(","); // menuItemIds are split by ','
					int[] menuItemIds = Arrays.asList(stringIds).stream().mapToInt(Integer::parseInt).toArray();
					MenuItem setItem = null;
					ArrayList<MenuItem> setItems = new ArrayList<MenuItem>();
					for (int i = 0; i < menuItemIds.length; i++) {
						setItem = menuItemManager.getMenuItemById(menuItems, menuItemIds[i]);
						setItems.add(setItem);
					}
					sets.add(new Set(Integer.parseInt(tokens[1]), tokens[2], tokens[3], Double.parseDouble(tokens[4]),
							setItems));
				}
				// ####### u can ADD YOUR OWN READ DATA HERE ####### //TODO remove b4 submit
				else {
					System.out.println("Error reading data.");
				}

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize restaurant staff and customers
	 */
	private void initPeople() {

		ArrayList<Staff> staffs = new ArrayList<Staff>();

		String[] name = { "John", "Mary", "Susan", "Henry" };
		Gender[] gender = { Gender.Male, Gender.Female, Gender.Female, Gender.Male };
		String[] jobTitle = { "Manager", "Chef", "Waiter", "Waiter" };

		for (int i = 0; i < 4; i++) {
			staffs.add(new Staff(i + 1, name[i], gender[i], jobTitle[i]));
		}

		Restaurant.staffs = staffs;

		ArrayList<Customer> customers = new ArrayList<Customer>();

		String[] cName = { "Kannan", "Jennifer", "Jackson", "Katarina" };
		Gender[] cGender = { Gender.Female, Gender.Female, Gender.Male, Gender.Female };
		int[] cNo = { 91234567, 91234566, 91234565, 91234564 };

		for (int i = 0; i < 4; i++) {
			customers.add(new Customer(i + 1, cName[i], cGender[i], cNo[i]));
		}

		Restaurant.customers = customers;
	}

	/**
	 * Initialize restaurant tables
	 */
	private void initTables() {

		ArrayList<Table> tables = new ArrayList<Table>();

		for (int i = 0; i < 5; i++) // 5 x 10 seats
			tables.add(new Table(i, Status.Vacated, 10));

		for (int i = 5; i < 10; i++) // 5 x 8 seats

			tables.add(new Table(i, Status.Vacated, 8));

		for (int i = 10; i < 20; i++) // 10 x 4 seats
			tables.add(new Table(i, Status.Vacated, 4));

		for (int i = 20; i < 30; i++) // 10 x 2 seats
			tables.add(new Table(i, Status.Vacated, 2));

		Restaurant.tables = tables;
	}

	/**
	 * Initialize restaurant invoices
	 */
	private void initInvoices() {
		Restaurant.invoices = new ArrayList<Invoice>();
	}

	/**
	 * Initialize restaurant orders
	 */
	private void initOrders() {
		Restaurant.orders = new ArrayList<Order>();
	}

	/**
	 * Initialize restaurant reservations
	 */
	private void initReservations() {
		Restaurant.reservations = new ArrayList<Reservation>();
	}

	/**
	 * Save all SaleItem: MenuItem and Set, into data.txt
	 */
	private void saveSaleItem() {
		// output to text
		try {
			PrintWriter out = new PrintWriter("data.txt");

			// save all MenuItem
			for (int i = 0; i < menuItems.size(); i++) {
				String line = menuItems.get(i).toString(); // generate line
				out.println(line); // add a line to text file
			}
			// save all Set
			for (int i = 0; i < sets.size(); i++) {
				String line = sets.get(i).toString(); // generate line
				out.println(line); // add a line to text file
			}
			// ####### u can ADD YOUR OWN SAVE DATA HERE ####### //TODO remove b4 submit

			out.close(); // close before exit
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(ArrayList<MenuItem> menuItems) {
		Restaurant.menuItems = menuItems;
	}

	public ArrayList<Set> getSets() {
		return sets;
	}

	public void setSets(ArrayList<Set> sets) {
		Restaurant.sets = sets;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		Restaurant.orders = orders;
	}

	public ArrayList<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(ArrayList<Invoice> invoices) {
		Restaurant.invoices = invoices;
	}

	public ArrayList<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(ArrayList<Reservation> reservations) {
		Restaurant.reservations = reservations;
	}

	public ArrayList<Table> getTables() {
		return tables;
	}

	public void setTables(ArrayList<Table> tables) {
		Restaurant.tables = tables;
	}

	public ArrayList<Staff> getStaffs() {
		return staffs;
	}

	public void setStaffs(ArrayList<Staff> staffs) {
		Restaurant.staffs = staffs;
	}

	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		Restaurant.customers = customers;
	}

}
