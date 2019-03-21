package RRPSS;

import java.util.ArrayList;

public class Promotion {
	private int id;
	private String name;
	private String description;
	private double price;
	private ArrayList<MenuItem> menuItems;

	public Promotion() {

	}

	public Promotion(int id, String name, String description, double price, ArrayList<MenuItem> menuItems) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.menuItems = menuItems;
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
	 * prints Promotion id, name, description, price, and list of its Menu Items'
	 * names
	 */
	public void print() {
		System.out.println("Id: " + id + " | Name: " + name + " | Description: " + description + " | Price: " + price
				+ " | Promotion's Menu Item(s): ");
		String s = "";
		for (int i = 0; i < menuItems.size(); i++) {
			s += menuItems.get(i).getName() + ", ";
		}
		s = s.substring(0, s.length() - 2); // remove last 2 char ', '
		System.out.println(s);
	}

	public static void printAllMenuItem(ArrayList<Promotion> promotions) {
		for (int i = 0; i < promotions.size(); i++) {
			Promotion item = promotions.get(i);
			System.out.print((i + 1) + ") ");
			item.print();
		}
	}

	public String toString() {
		String s = "";
		s = "Promotion|" + id + "|" + name + "|" + description + "|" + price;
		if (menuItems.size() != 0) {
			s += "|";
			int menuItemId = 0;
			for (int i = 0; i < menuItems.size(); i++) {
				menuItemId = menuItems.get(i).getMenuItemId();
				s += menuItemId + ",";
			}
			s = s.substring(0, s.length() - 1); // remove last char ','
		}
		return s;
	}

	// getter and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(ArrayList<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

}