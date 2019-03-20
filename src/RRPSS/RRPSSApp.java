package RRPSS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import RRPSS.MenuItem.MenuType;

public class RRPSSApp {

//	 DON'T add int option = 0 AND Scanner here, will affect all menu choice //TODO remove b4 submit

	// using ArrayList as no fixed size
	// https://stackoverflow.com/questions/2279030/type-list-vs-type-arraylist-in-java
	static ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();

	public static void main(String[] args) {
		System.out.println("Starting RRPSS System...");

		// read/load data from text files
		loadData();

		int option = 0;
		Scanner sc = new Scanner(System.in); 
		
		do {
			System.out.println("\n==============================");
			System.out.println("=== RRPSS System Main Menu ===");
			System.out.println("Choose an option:");
			System.out.println("1) View/Edit Menu Item");
			System.out.println("2) View/Edit Promotion Item");
			System.out.println("0) Exit");
			System.out.println("==============================");

			option = sc.nextInt();

			switch (option) {
//			case 55: // for testing lul TODO remove b4 submit
//				break;
			case 0:
				System.out.println("Ending RRPSS System...");
				break;
			case 1: // 1) View/Edit Menu Item
				displayMenuItem();
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
				"Lettuce, eggs, parmesan cheese and cheese croutons with Caesar dressing.", 6.4, MenuType.SIDE));
		// TODO read/load data from text files
	}

	public static void displayMenuItem() {
		Scanner sc = new Scanner(System.in); 
		int option = 0;

		do {
			System.out.println("\n==============================");
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
					item.print();
				}
				break;

			case 2: // 2 Create Menu Item
				try {
					int id = 0;
					System.out.println("Enter menu item Id: ");
					id = sc.nextInt();
					sc.nextLine(); // get rid of \n
					if (id < 0 || !MenuItem.isValidId(menuItems,id)) { // invalid value
						System.out.println("Please enter valid input. Exiting create menu item...");
						break; //exit to displayMenuItem() do while loop
					}

					String name = "";
					System.out.println("Enter menu item name: ");
					name = sc.nextLine();
					if (name.isEmpty()) { // invalid value
						System.out.println("Please enter valid input. Exiting create menu item...");
						break; //exit to displayMenuItem() do while loop
					}

					String desc = "";
					System.out.println("Enter menu item description: ");
					desc = sc.nextLine();
					if (desc.isEmpty()) { // invalid value
						System.out.println("Please enter valid input. Exiting create menu item...");
						break; //exit to displayMenuItem() do while loop
					}

					double price = 0;
					System.out.println("Enter menu item price: ");
					price = sc.nextDouble();
					if (price < 0) { // invalid value
						System.out.println("Please enter valid input. Exiting create menu item...");
						break; //exit to displayMenuItem() do while loop
					}

					MenuType menuType = MenuItem.chooseMenuType();					

					//add to ArrayList<MenuItem> menuItems
					MenuItem newItem = new MenuItem(id, name, desc, price, menuType);
					menuItems.add(newItem);
					//print success msg
					System.out.println("Success! New menu item:");
					newItem.print();
				} catch (Exception e) {
					System.out.println("Error! Try again.");
				}
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
