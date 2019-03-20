package RRPSS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import RRPSS.MenuItem.MenuType;

public class RRPSSApp {

//	 DON'T add int option = 0 AND Scanner here, will affect all menu choice //TODO remove b4 submit

	// using ArrayList as it has no fixed size
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

		// TODO save data when program ends

	}// end of main

	public static void loadData() {
		// ========== PLACEHOLDER VALUES ========== //TODO remove when not needed
//		menuItems.add(new MenuItem(31, "Ice Lemon Tea", "Homemade fresh ice lemon tea", 1.8, MenuType.DRINK));

		// read/load data from text file, data.txt
		try {
			String line = "";
			BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
			while ((line = reader.readLine()) != null) { //check and read next line
				System.out.println("Line: " + line); //TODO remove b4 submit
				
				// used '|' as char to separate values, as ',' is used in description
				//NOTE: used "\\|" as "|" is interpret as logical operator OR
				String[] tokens = line.split("\\|");
				
				if (tokens[0].equals("MenuItem")) { //MenuItem
					//MenuItem(int menuItemId, String name, String description, double price, MenuType menuType)
					menuItems.add(new MenuItem(Integer.parseInt(tokens[1]), tokens[2], tokens[3],
							Double.parseDouble(tokens[4]), MenuType.valueOf(tokens[5])));
					System.out.println("Added MenuItem " + tokens[2]);
				}
				
				// ############ ADD YOUR OWN READ DATA HERE ################# //TODO remove b4 submit
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
					if (id < 0 || !MenuItem.isValidId(menuItems, id)) { // invalid value
						System.out.println("Please enter valid input. Exiting create menu item...");
						break; // exit to displayMenuItem() do while loop
					}

					String name = "";
					System.out.println("Enter menu item name: ");
					name = sc.nextLine();
					if (name.isEmpty()) { // invalid value
						System.out.println("Please enter valid input. Exiting create menu item...");
						break; // exit to displayMenuItem() do while loop
					}

					String desc = "";
					System.out.println("Enter menu item description: ");
					desc = sc.nextLine();
					if (desc.isEmpty()) { // invalid value
						System.out.println("Please enter valid input. Exiting create menu item...");
						break; // exit to displayMenuItem() do while loop
					}

					double price = 0;
					System.out.println("Enter menu item price: ");
					price = sc.nextDouble();
					if (price < 0) { // invalid value
						System.out.println("Please enter valid input. Exiting create menu item...");
						break; // exit to displayMenuItem() do while loop
					}

					MenuType menuType = MenuItem.chooseMenuType();

					// add to ArrayList<MenuItem> menuItems
					MenuItem newItem = new MenuItem(id, name, desc, price, menuType);
					menuItems.add(newItem);
					// print success msg
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
