package classes;

import java.util.ArrayList;

public class MenuItem extends SaleItem {
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

	public void print() {
		System.out.println("Id: " + getId() + " | Name: " + getName() + " | Description: " + getDescription()
				+ " | Price: " + getPrice() + " | Menu Type: " + menuType.toString());
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
