package classes;

import java.util.ArrayList;

/**
 * Represents individual (Food) Menu Item <br>
 * A MenuItem is a SaleItem, with MenuType
 * 
 * @author Kailing
 *
 */
public class MenuItem extends SaleItem {
	private MenuType menuType;

	/**
	 * Represent type of (Food) Menu Item <br>
	 * Can be a Main Dish, Side Dish, Drink, Dessert etc
	 * 
	 * @author Kailing
	 *
	 */
	public enum MenuType {
		MAIN("Main Dish"), SIDE("Side Dish"), DRINK("Drink"), DESSERT("Dessert");

		private final String desc;

		MenuType(String desc) {
			this.desc = desc;
		}

		/**
		 * Get description of MenuType
		 * 
		 * @return description of MenuType
		 */
		public String toString() {
			return desc;
		}
	}

	/**
	 * Constructor with all attributes of MenuItem
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param price
	 * @param menuType
	 */
	public MenuItem(int id, String name, String description, double price, MenuType menuType) {
		super(id, name, description, price);
		this.menuType = menuType;
	}

	/**
	 * Default constructor of MenuItem
	 */
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
	 * prints all attributes of MenuItem;
	 * 
	 */
	public void print() {
		System.out.println("Id: " + getId() + " | Name: " + getName() + " | Description: " + getDescription()
				+ " | Price: " + getPrice() + " | Menu Type: " + menuType.toString());
	}

	/**
	 * returns a String of a MenuItem, including all of its attributes
	 */
	public String toString() {
		return "MenuItem|" + getId() + "|" + getName() + "|" + getDescription() + "|" + getPrice() + "|"
				+ menuType.name();
	}

	/**
	 * Get menuType of MenuItem
	 * 
	 * @return menuType of MenuItem
	 */
	public MenuType getMenuType() {
		return menuType;
	}

	/**
	 * Change menuType of MenuItem
	 * 
	 * @param menuType new menuType of MenuItem
	 */
	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}

}
