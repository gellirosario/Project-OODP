package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import classes.*;
import classes.MenuItem.MenuType;
import classes.Person.Gender;
import classes.Table.Status;
import mgr.MenuItemManager;
import mgr.SetManager;

/**
 * 
 * The Restaurant class will provide a static database for the application.
 * 
 */
public class Restaurant {

	public static final int BOOKING_MTHINADVANCE = 1;
	public static final int SESSION_AMSTARTTIME = 11;
	public static final int SESSION_AMENDTIME = 15;
	public static final int SESSION_PMSTARTTIME = 18;
	public static final int SESSION_PMENDTIME = 22;

	// ArrayList of objects
	public static ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
	public static ArrayList<Set> sets = new ArrayList<Set>();

	public static ArrayList<Order> orders = new ArrayList<Order>();
	public static ArrayList<Order> previousOrders = new ArrayList<Order>();

	public static ArrayList<Invoice> invoices = new ArrayList<Invoice>();

	public static ArrayList<Reservation> reservations = new ArrayList<Reservation>();
	public static ArrayList<Reservation> pastReservations = new ArrayList<Reservation>();
	public static ArrayList<Table> tables = new ArrayList<Table>();

	private static ArrayList<Staff> staffs = new ArrayList<Staff>();
	private static ArrayList<Customer> customers = new ArrayList<Customer>();

	// Manager classes
	private static MenuItemManager menuItemManager = new MenuItemManager();
	private static SetManager setManager = new SetManager();

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
		saveOrder();
		saveInvoice();
		saveReservation();
		System.out.println("Saving data done.");
	}

	/**
	 * Initialize restaurant static members
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
		// read/load data from text file, data.txt
		try {
			String line = "";
			BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
			while ((line = reader.readLine()) != null) { // check and read next line
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

		for (int i = 1; i < 11; i++) // 10 x 2 seats [ID: 1-5]
			tables.add(new Table(i, Status.Vacated, 2));

		for (int i = 11; i < 21; i++) // 10 x 4 seats [ID: 6-10]
			tables.add(new Table(i, Status.Vacated, 4));

		for (int i = 21; i < 26; i++) // 5 x 8 seats [ID: 11-20]
			tables.add(new Table(i, Status.Vacated, 8));

		for (int i = 26; i < 31; i++) // 5 x 10 seats [ID: 21-30]
			tables.add(new Table(i, Status.Vacated, 10));

		Restaurant.tables = tables;
	}

	/**
	 * Initialize restaurant invoices
	 */
	private void initInvoices() {
		Restaurant.invoices = new ArrayList<Invoice>();

		try {
			String line = "";
			BufferedReader reader = new BufferedReader(new FileReader("invoice.txt"));
			while ((line = reader.readLine()) != null) { // check and read next line

				// used '|' as char to separate values, as ',' is used in description
				// NOTE: used "\\|" as "|" is interpret as logical operator OR
				String[] tokens = line.split("\\|");

				Order order = null;

				if (tokens[0].equals("Invoice")) {

					// Get order
					String orderString = tokens[2];

					for (int i = 0; i < previousOrders.size(); i++) {
						if (previousOrders.get(i).getId() == Integer.parseInt(orderString)) {
							order = previousOrders.get(i);
							break;
						}
					}

					invoices.add(new Invoice(Integer.parseInt(tokens[1]), order, Double.parseDouble(tokens[3]),
							Double.parseDouble(tokens[4]), Double.parseDouble(tokens[5]),
							Double.parseDouble(tokens[6])));
				} else {
					System.out.println("Error reading invoice data.");
				}
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Initialize restaurant orders
	 */
	private void initOrders() {
		Restaurant.orders = new ArrayList<Order>();

		try {
			String line = "";
			BufferedReader reader = new BufferedReader(new FileReader("order.txt"));
			while ((line = reader.readLine()) != null) { // check and read next line

				// used '|' as char to separate values, as ',' is used in description
				// NOTE: used "\\|" as "|" is interpret as logical operator OR
				String[] tokens = line.split("\\|");

				ArrayList<SaleItem> setItems = new ArrayList<SaleItem>();
				Staff staff = null;
				Table table = null;
				Calendar orderDate = Calendar.getInstance();

				if (tokens[0].equals("Order") || tokens[0].equals("PreviousOrder")) // Get Data
				{
					// Get Sale Items
					String[] stringIds = tokens[5].split(","); // saleItemIds are split by ','
					int[] saleItemIds = Arrays.asList(stringIds).stream().mapToInt(Integer::parseInt).toArray();

					MenuItem menuItem = null;
					Set setItem = null;

					for (int i = 0; i < saleItemIds.length; i++) {
						menuItem = menuItemManager.getMenuItemById(menuItems, saleItemIds[i]);
						setItem = setManager.getSetById(sets, saleItemIds[i]);

						if (menuItem != null) {
							setItems.add(menuItem);
						} else if (setItem != null) {
							setItems.add(setItem);
						}
					}

					// Get Staff
					String staffString = tokens[2];

					for (int i = 0; i < staffs.size(); i++) {
						if (staffs.get(i).getId() == Integer.parseInt(staffString)) {
							staff = staffs.get(i);
						}
					}

					// Get Table
					String tableString = tokens[3];

					for (int i = 0; i < tables.size(); i++) {
						if (tables.get(i).getId() == Integer.parseInt(tableString)) {
							table = tables.get(i);

							if (tokens[0].equals("Order")) {
								table.setStatus(Status.Occupied);
							}
						}
					}

					// Get Order Date
					String orderString = tokens[4];
					SimpleDateFormat readFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy");
					Date date = null;
					try {
						date = readFormat.parse(orderString);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					orderDate.setTime(date);
				}

				if (tokens[0].equals("Order")) { // Add to Order
					orders.add(new Order(Integer.parseInt(tokens[1]), staff, setItems, table, orderDate));

				} else if (tokens[0].equals("PreviousOrder")) // Add to Previous Order
				{

					previousOrders.add(new Order(Integer.parseInt(tokens[1]), staff, setItems, table, orderDate));
				} else {
					System.out.println("Error reading order data.");
				}

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize restaurant reservations
	 */
	private void initReservations() {
		Restaurant.reservations = new ArrayList<Reservation>();

		try {
			String line = "";
			BufferedReader reader = new BufferedReader(new FileReader("reservation.txt"));
			while ((line = reader.readLine()) != null) { // check and read next line

				// used '|' as char to separate values, as ',' is used in description
				// NOTE: used "\\|" as "|" is interpret as logical operator OR
				String[] tokens = line.split("\\|");

				Table table = null;
				Calendar orderDate = Calendar.getInstance();

				if (tokens[0].equals("Reservation") || tokens[0].equals("pastReservation")) // Get Data
				{

					// Get Table
					String tableString = tokens[5];

					for (int i = 0; i < tables.size(); i++) {
						if (tables.get(i).getId() == Integer.parseInt(tableString)) {
							table = tables.get(i);
						}
					}

					// Get Reservation Date
					String reservationString = tokens[4];
					SimpleDateFormat readFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy");
					Date date = null;
					try {
						date = readFormat.parse(reservationString);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					orderDate.setTime(date);
				}

				if (tokens[0].equals("Reservation")) { // Add to Reservation
					reservations.add(new Reservation(tokens[1], Integer.parseInt(tokens[2]),
							Integer.parseInt(tokens[3]), orderDate, table));

				} else if (tokens[0].equals("pastReservation")) // Add to Past Reservation
				{

					pastReservations.add(new Reservation(tokens[1], Integer.parseInt(tokens[2]),
							Integer.parseInt(tokens[3]), orderDate, table));
				} else {
					System.out.println("Error reading order data.");
				}

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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

			out.close(); // close before exit
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Save all Order(existing/previous) into orders.txt
	 */
	private void saveOrder() {
		// output to text
		try {
			PrintWriter out = new PrintWriter("order.txt");

			// save all Orders
			for (int i = 0; i < orders.size(); i++) {
				// generate line
				String line = orders.get(i).toString("Order");

				out.println(line); // add a line to text file
			}
			// save all Previous Orders
			for (int i = 0; i < previousOrders.size(); i++) {
				// generate line

				String line = previousOrders.get(i).toString("PreviousOrder");

				out.println(line); // add a line to text file

			}

			out.close(); // close before exit
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Save all Invoices into invoice.txt
	 */
	private void saveInvoice() {
		// output to text
		try {
			PrintWriter out = new PrintWriter("invoice.txt");

			// save all Invoices
			for (int i = 0; i < invoices.size(); i++) {
				// generate line
				String line = invoices.get(i).toString("Invoice");

				out.println(line); // add a line to text file
			}

			out.close(); // close before exit
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Save all Reservations into reservation.txt
	 */
	private void saveReservation() {
		// output to text
		try {
			PrintWriter out = new PrintWriter("reservation.txt");

			// save all Reservations
			out.println("//reservationStatus|custName|custContact|numOfPax|reservationTime|TableID");

			for (int i = 0; i < reservations.size(); i++) {
				// generate line
				String line = "Reservation|" + reservations.get(i).toString();

				out.println(line); // add a line to text file
			}

			for (int i = 0; i < pastReservations.size(); i++) {
				// generate line
				String line = "pastReservation|" + pastReservations.get(i).toString();

				out.println(line); // add a line to text file
			}

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

	public ArrayList<Order> getPreviousOrders() {
		return previousOrders;
	}

	public void setPreviousOrders(ArrayList<Order> previousOrders) {
		Restaurant.previousOrders = previousOrders;
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

	public ArrayList<Reservation> getPastReservations() {
		return pastReservations;
	}

	public void setPastReservations(ArrayList<Reservation> pastReservations) {
		Restaurant.pastReservations = pastReservations;
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
