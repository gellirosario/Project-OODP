package RRPSS;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuItem {
	private int menuItemId;
	private String name;
	private String description;
	private double price;

	private MenuType menuType;

	public enum MenuType {
		MAIN("Main Dish"), SIDE("Side Dish"), DRINK("Drink"), DESSERT("Dessert");

		private final String desc;

		MenuType(String desc) {
			this.desc = desc;
		}

		public String toString() {
			return desc;
		}
	}

	public MenuItem(int menuItemId, String name, String description, double price, MenuType menuType) {
//		super(); //TODO remove?
		this.menuItemId = menuItemId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.menuType = menuType;
	}

	public MenuItem() {

	}

	/**
	 * check if input id is valid; invalid if id already exist
	 * 
	 * @param menuItems
	 * @param id
	 * @return
	 */
	public static boolean isValidId(ArrayList<MenuItem> menuItems, int id) {
		for (int i = 0; i < menuItems.size(); i++) {
			if (menuItems.get(i).getMenuItemId() == id) {
				return false;
			}
		}
		return true;
	}

	/**
	 * return MenuItem by its Id
	 * 
	 * @param menuItems
	 * @param id
	 * @return null if MenuItem not found;
	 */
	public static MenuItem getMenuItemById(ArrayList<MenuItem> menuItems, int id) {
		for (int i = 0; i < menuItems.size(); i++) {
			if (menuItems.get(i).getMenuItemId() == id) {
				return menuItems.get(i);
			}
		}
		return null;
	}

	public static MenuType chooseMenuType() {
		Scanner sc = new Scanner(System.in);
		int option = 0;

		System.out.println("Choose a Menu Type");

		// print all MenuType
		for (int i = 0; i < MenuType.values().length; i++) {
			System.out.println(i + 1 + ") " + MenuType.values()[i].toString());
		}

		do {
			while (!sc.hasNextInt()) { // check if user entered int
				sc.next(); // move buffer
				RRPSSApp.printInvalidInputMsg();
			}
			// user entered int
			option = sc.nextInt();
			sc.nextLine(); // get rid of \n
			if (option <= 0 || option > MenuType.values().length) { // invalid value
				RRPSSApp.printInvalidInputMsg();
			} else { // valid value
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		return MenuType.values()[option - 1];
	}

	public void print() {
		// eg Id: 1 | Name: Cheesecake | Description: Delicious fresh cheesecake |
		// Price: $5.25 | Menu Type: Dessert
		System.out.println("Id: " + menuItemId + " | Name: " + name + " | Description: " + description + " | Price: "
				+ price + " | Menu Type: " + menuType.toString());
	}

	public static void printAllMenuItem(ArrayList<MenuItem> menuItems) {
		for (int i = 0; i < menuItems.size(); i++) {
			MenuItem item = menuItems.get(i);
			System.out.print((i + 1) + ") ");
			item.print();
		}
	}

	public String toString() {
		return "MenuItem|" + menuItemId + "|" + name + "|" + description + "|" + price + "|" + menuType.name();
	}

	// getter and setters

	public int getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(int menuItemId) {
		this.menuItemId = menuItemId;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public MenuType getMenuType() {
		return menuType;
	}

	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}

}
