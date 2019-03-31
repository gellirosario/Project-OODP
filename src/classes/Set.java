package classes;

import java.util.ArrayList;

public class Set extends SaleItem {
	private ArrayList<MenuItem> menuItems;

	public Set() {

	}

	public Set(int id, String name, String description, double price, ArrayList<MenuItem> menuItems) {
		super(id, name, description, price);
		this.menuItems = menuItems;
	}

	/**
	 * check if input id is valid; invalid if id already exist
	 * 
	 * @param sets
	 * @param id
	 * @return
	 */
	public static boolean isValidId(ArrayList<Set> sets, int id) {
		for (int i = 0; i < sets.size(); i++) {
			if (sets.get(i).getId() == id) {
				return false;
			}
		}
		return true;
	}

	/**
	 * prints Set id, name, description, price, and list of its Menu Items' names
	 */
	public void print() {
		System.out.println("Id: " + getId() + " | Name: " + getName() + " | Description: " + getDescription()
				+ " | Price: " + getPrice() + " | Set's Menu Item(s): ");
		String s = "";
		for (int i = 0; i < menuItems.size(); i++) {
			if (menuItems.get(i) == null) { // set may still have deleted menu item
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

	public String toString() {
		String s = "";
		s = "Set|" + getId() + "|" + getName() + "|" + getDescription() + "|" + getPrice();
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