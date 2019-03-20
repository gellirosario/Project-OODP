package RRPSS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import RRPSS.MenuItem.MenuType;

public class RRPSSApp {

	static Scanner sc = new Scanner(System.in);
	//don't put option here

	// using ArrayList as no fixed size
	// https://stackoverflow.com/questions/2279030/type-list-vs-type-arraylist-in-java
	static ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();

	public static void main(String[] args) {
		System.out.println("Starting RRPSS System...");

		// read/load data from text files
		loadData();

		int option = 0;
//		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("/n==============================");
			System.out.println("=== RRPSS System Main Menu ===");
			System.out.println("Choose an option:");
			System.out.println("1) View/Edit Menu Item");
			System.out.println("2) View/Edit Promotion Item");
			System.out.println("0) Exit");
			System.out.println("==============================");

			option = sc.nextInt();

			switch (option) {
			case 0:
				System.out.println("Ending RRPSS System...");
				break;
			case 1: // 1) View/Edit Menu Item
				viewMenuItem();
				break;
			case 2: // 2) View/Edit Promotion Item
				break;

			default:
				System.out.println("No such option");				
			}
		} while (option != 0);

	}// end of main

	public static void loadData() {
		// TODO got it from swensens menu (lol)
		menuItems.add(new MenuItem(1, "Ice Lemon Tea", "Homemade fresh ice lemon tea", 1.8, MenuType.DRINK));
		menuItems.add(new MenuItem(11, "Cheese Beef Burger",
				"Beef patties with melted cheddar cheese, tomatoes and lettuce", 20.45, MenuType.MAIN));
		menuItems.add(new MenuItem(21, "Caesar Salad",
				"Lettuce, eggs, parmesan cheese and cheese croutons with Caesar dressing.", 6.4,
				MenuType.SIDE));
		// TODO read/load data from text files
	}

	public static void viewMenuItem() {
		int option = 0;
		
		do {
			System.out.println("/n==============================");
			System.out.println("=== View/Edit Menu Item ===");
			System.out.println("Choose an option:");
			System.out.println("1) View all Menu Item");
			System.out.println("2) Create new Menu Item");
			System.out.println("3) Edit Menu Item");
			System.out.println("4) Delete Menu Item");
			System.out.println("0) Exit");
			System.out.println("==============================");

			option = sc.nextInt();

			switch (option) {
			case 0:
				System.out.println("Returning to RRPSS main menu...");
				break;
				
			case 1: // 1 View all Menu Item
				for (int i = 0; i < menuItems.size(); i++) {
					MenuItem item = menuItems.get(i);
					// eg Id: 1 | Name: Cheesecake | Description: Delicious fresh cheesecake |
					// Price: $5.25 | MenuType: Dessert
					System.out.println("Id: " + item.getMenuItemId() + " | Name: " + item.getName() + " | Description: "
							+ item.getDescription() + " | Price: " + item.getPrice() + " | MenuType: "
							+ item.getMenuType().getDesc());
				}
				break;

			case 2: // 2 Create Menu Item
				break;

			case 3: // 3 Edit Menu Item
				break;

			case 4: // 4 Delete Menu Item
				break;

			default:
				System.out.println("No such option");
			}
		} while (option != 0);
	}

}
