package classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuItem extends SaleItem implements Serializable{
	
	private static final long serialVersionUID = 4L;
	
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

	public MenuItem(int id, String name, String description, double price, MenuType menuType) {
		super(id, name, description, price);
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
			if (menuItems.get(i).getId() == id) {
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
			if (menuItems.get(i).getId() == id) {
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
				//RRPSSApp.printInvalidInputMsg();
			}
			// user entered int
			option = sc.nextInt();
			sc.nextLine(); // get rid of \n
			if (option <= 0 || option > MenuType.values().length) { // invalid value
				//RRPSSApp.printInvalidInputMsg();
			} else { // valid value
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		return MenuType.values()[option - 1];
	}

	public void print() {
		System.out.println("Id: " + getId() + " | Name: " + getName() + " | Description: " + getDescription()
				+ " | Price: " + getPrice() + " | Menu Type: " + menuType.toString());
	}

	public static void printAll(ArrayList<MenuItem> menuItems) {
		for (int i = 0; i < menuItems.size(); i++) {
			MenuItem item = menuItems.get(i);
			System.out.print((i + 1) + ") ");
			item.print();
		}
	}

	public String toString() {
		return "MenuItem|" + getId() + "|" + getName() + "|" + getDescription() + "|" + getPrice() + "|"
				+ menuType.name();
	}

	// getter and setters
	public MenuType getMenuType() {
		return menuType;
	}

	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}

}
