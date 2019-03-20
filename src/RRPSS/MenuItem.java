package RRPSS;

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

	public static MenuType chooseMenuType() {		
		Scanner sc = new Scanner(System.in);
		int option = 0;
		
		//print all MenuType
		for (int i = 0; i < MenuType.values().length; i++) {
			System.out.println(i + 1 + ") " + MenuType.values()[i].toString());
		}
				
		do {
			System.out.println("Enter number of Menu Type");
			
//			try {				
				option = sc.nextInt(); //TODO input int error checking
//			} catch (Exception e) {
//				System.out.println("Error! Try again");
//			}
			
			if (option <= 0 || option > MenuType.values().length) { // invalid input
				System.out.println("Invalid input, enter again");
			}
		} while (option <= 0 || option > MenuType.values().length);
		
		return MenuType.values()[option-1];
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
