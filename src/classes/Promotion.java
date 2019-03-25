package classes;

import java.util.ArrayList;
import java.util.Scanner;

public class Promotion extends SaleItem {
	private ArrayList<MenuItem> menuItems;

	public Promotion() {

	}

	public Promotion(int id, String name, String description, double price, ArrayList<MenuItem> menuItems) {
		super(id, name, description, price);
		this.menuItems = menuItems;
	}

	/**
	 * check if input id is valid; invalid if id already exist
	 * 
	 * @param promotions
	 * @param id
	 * @return
	 */
	public static boolean isValidId(ArrayList<Promotion> promotions, int id) {
		for (int i = 0; i < promotions.size(); i++) {
			if (promotions.get(i).getId() == id) {
				return false;
			}
		}
		return true;
	}

	public static Promotion getPromotionById(ArrayList<Promotion> promotions, int id) {
		for (int i = 0; i < promotions.size(); i++) {
			if (promotions.get(i).getId() == id) {
				return promotions.get(i);
			}
		}
		return null;
	}

	/**
	 * creates new ArrayList<MenuItem>, menuItems, of a Promotion
	 * 
	 * @param menuItems
	 * @return null if input menuItems size == 0; <br>
	 *         valid ArrayList<MenuItem>
	 */
	public static ArrayList<MenuItem> createPromotionItems(ArrayList<MenuItem> menuItems) {
		Scanner sc = new Scanner(System.in);
		int option = 0;
		int menuItemId = 0;
		int promoItemsSize = 0;
		MenuItem promoItem = null;
		ArrayList<MenuItem> promoItems = new ArrayList<MenuItem>();

		if (menuItems.size() == 0) {
			return null;
		}

		System.out.println("List of all Menu Item(s):");
		MenuItem.printAll(menuItems);

		System.out.println("Enter number of Menu Item(s) to be added into Promotion:");
		do {
			while (!sc.hasNextInt()) { // check if user entered int
				sc.next(); // move buffer
				//RRPSSApp.printInvalidInputMsg();
			}
			// user entered int
			promoItemsSize = sc.nextInt();
			sc.nextLine(); // get rid of \n
			if (promoItemsSize < 1 || promoItemsSize > menuItems.size()) { // invalid value
				//RRPSSApp.printInvalidInputMsg();
			} else { // valid value
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		for (int i = 0; i < promoItemsSize; i++) {
			System.out.println("Enter Menu Item Id that will be added to Promotion:");
			do {
				while (!sc.hasNextInt()) { // check if user entered int
					sc.next(); // move buffer
					//RRPSSApp.printInvalidInputMsg();
				}
				// user entered int
				menuItemId = sc.nextInt();
				sc.nextLine(); // get rid of \n
				promoItem = MenuItem.getMenuItemById(menuItems, menuItemId);
				if (promoItem == null) { // invalid value
					//RRPSSApp.printInvalidInputMsg();
				} else { // valid value
					promoItems.add(promoItem);
					break; // exit do while loop
				}
			} while (true); // only exit do while loop when user input is valid
		}

		return promoItems;
	}

	/**
	 * prints Promotion id, name, description, price, and list of its Menu Items'
	 * names
	 */
	public void print() {
		System.out.println("Id: " + getId() + " | Name: " + getName() + " | Description: " + getDescription()
				+ " | Price: " + getPrice() + " | Promotion's Menu Item(s): ");
		String s = "";
		for (int i = 0; i < menuItems.size(); i++) {
			if (menuItems.get(i) == null) { // promotion may still have deleted menu item
				s += "MENU_ITEM_NOT_FOUND";
			} else {
				s += menuItems.get(i).getName() + ", ";
			}
		}
		String strEnd = s.substring(s.length() - 2);
		if (strEnd.equals(", ")) {
			s = s.substring(0, s.length() - 2); // remove last 2 char ', '
		}
		System.out.println(s);
	}

	public static void printAll(ArrayList<Promotion> promotions) {
		for (int i = 0; i < promotions.size(); i++) {
			Promotion item = promotions.get(i);
			System.out.print((i + 1) + ") ");
			item.print();
		}
	}

	public String toString() {
		String s = "";
		s = "Promotion|" + getId() + "|" + getName() + "|" + getDescription() + "|" + getPrice();
		if (menuItems.size() != 0) {
			s += "|";
			int menuItemId = 0;
			for (int i = 0; i < menuItems.size(); i++) {
				menuItemId = menuItems.get(i).getId();
				s += menuItemId + ",";
			}
			s = s.substring(0, s.length() - 1); // remove last char ','
		}
		return s;
	}

	// getter and setters
	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(ArrayList<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

}