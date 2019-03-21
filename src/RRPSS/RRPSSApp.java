package RRPSS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
			System.out.println("0) Exit RRPSS");
			System.out.println("==============================");

			option = sc.nextInt();

			switch (option) {
//			case 55: // for testing lul TODO remove b4 submit
//				break;
			case 0:
				System.out.println("Exiting RRPSS System...");
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

		// save data when program ends
		saveData();

	}// end of main

	/**
	 * prints "Invalid Input, please enter valid input."
	 */
	public static void printInvalidInputMsg() {
		System.out.println("Invalid Input, please enter valid input.");
	}

	public static void loadData() {
		System.out.println("Loading data...");
		// ========== PLACEHOLDER VALUES ========== //TODO remove when not needed
//		menuItems.add(new MenuItem(31, "Ice Lemon Tea", "Homemade fresh ice lemon tea", 1.8, MenuType.DRINK));

		// read/load data from text file, data.txt
		try {
			String line = "";
			BufferedReader reader = new BufferedReader(new FileReader("data.txt"));
			while ((line = reader.readLine()) != null) { // check and read next line
//				System.out.println("Line: " + line); //TODO remove b4 submit

				// used '|' as char to separate values, as ',' is used in description
				// NOTE: used "\\|" as "|" is interpret as logical operator OR
				String[] tokens = line.split("\\|");

				if (tokens[0].equals("MenuItem")) { // MenuItem
					menuItems.add(new MenuItem(Integer.parseInt(tokens[1]), tokens[2], tokens[3],
							Double.parseDouble(tokens[4]), MenuType.valueOf(tokens[5])));
				} else {
					System.out.println("Error reading data.");
				}

				// ####### ADD YOUR OWN READ DATA HERE ####### //TODO remove b4 submit
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Loading data done.");
	}

	public static void saveData() {
		System.out.println("Saving data...");
		// output to text
		try {
			PrintWriter out = new PrintWriter("data.txt");

			// save all MenuItem
			for (int i = 0; i < menuItems.size(); i++) {
				String line = menuItems.get(i).toString(); // generate line
				out.println(line); // add a line to text file
			}

			out.close(); // close before exit
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Saving data done.");
	}

	public static void displayMenuItem() {
		Scanner sc = new Scanner(System.in);
		int option = 0;

		do {
			System.out.println("\n==============================");
			System.out.println("=== View/Edit Menu Item ===");
			System.out.println("Choose an option:");
			System.out.println("1) View all Menu Item");
			System.out.println("2) Create Menu Item");
			System.out.println("3) Edit Menu Item");
			System.out.println("4) Delete Menu Item");
			System.out.println("0) Return to RRPSS Main Menu");
			System.out.println("==============================");

			option = sc.nextInt();

			switch (option) {
			case 0:
				System.out.println("Returning to RRPSS Main Menu...");
				break;

			case 1: // 1 View all Menu Item
				for (int i = 0; i < menuItems.size(); i++) {
					MenuItem item = menuItems.get(i);
					item.print();
				}
				break;

			case 2: // 2 Create Menu Item
				int id = 0;
				System.out.println("Enter Menu Item Id: ");
				do {
					while (!sc.hasNextInt()) { // check if user entered int
						sc.next(); // move buffer
						printInvalidInputMsg();
					}
					// user entered int
					id = sc.nextInt();
					sc.nextLine(); // get rid of \n
					if (id <= 0 || !MenuItem.isValidId(menuItems, id)) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				String name = "";
				System.out.println("Enter Menu Item name: ");
				do {
					name = sc.nextLine();
					if (name.isEmpty()) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				String desc = "";
				System.out.println("Enter Menu Item description: ");
				do {
					desc = sc.nextLine();
					if (desc.isEmpty()) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				double price = 0;
				System.out.println("Enter Menu Item price: ");
				do {
					while (!sc.hasNextDouble()) { // check if user entered double
						sc.next(); // move buffer
						printInvalidInputMsg();
					}
					// user entered double
					price = sc.nextDouble();
					sc.nextLine(); // get rid of \n
					if (price < 0) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				MenuType menuType = MenuItem.chooseMenuType();

				// add to ArrayList<MenuItem> menuItems
				MenuItem newItem = new MenuItem(id, name, desc, price, menuType);
				menuItems.add(newItem);
				// print success msg
				System.out.println("Success! New Menu Item:");
				newItem.print();
				break;

			case 3: // 3 Edit Menu Item
//				try {
//					// choose a Menu Item to edit
//					// print all Menu Item
//					for (int i = 0; i < menuItems.size(); i++) {
//						MenuItem item = menuItems.get(i);
//						System.out.print((i+1) + "): ");
//						item.print();
//					}
//
//					do { // loop till valid input
//						option = sc.nextInt();
//						if (option <= 0 || option > menuItems.size()) { // invalid input
//							System.out.println("Invalid input, enter again");
//						}
//					} while (option <= 0 || option > menuItems.size());
//
//					MenuItem menuItem = menuItems.get(option - 1);
//
//					// user input changes
//					int id = 0;
//					System.out.println("Press Enter to keep original value; Enter new Menu Item Id: ");
//					id = sc.nextInt();
//					sc.nextLine(); // get rid of \n
//					if (menuItem.getMenuItemId() != id) { // different from original id
//						if (id < 0 || !MenuItem.isValidId(menuItems, id)) { // invalid value
//							printInvalidInputMsg("Edit Menu Item");
//							break; // exit to displayMenuItem() do while loop
//						} else {
//							menuItem.setMenuItemId(id);
//						}
//					}
//
//					String name = "";
//					System.out.println("Press Enter to keep original value; Enter new Menu Item name: ");
//					name = sc.nextLine();
//					if (name.isEmpty()) { // invalid value
//						printInvalidInputMsg("Edit Menu Item");
//						break; // exit to displayMenuItem() do while loop
//					}
//
//					String desc = "";
//					System.out.println("Press Enter to keep original value; Enter new Menu Item description: ");
//					desc = sc.nextLine();
//					if (desc.isEmpty()) { // invalid value
//						printInvalidInputMsg("Edit Menu Item");
//						break; // exit to displayMenuItem() do while loop
//					}
//
//					double price = 0;
//					System.out.println("Press Enter to keep original value; Enter new Menu Item price: ");
//					price = sc.nextDouble();
//					if (price < 0) { // invalid value
//						printInvalidInputMsg("Edit Menu Item");
//						break; // exit to displayMenuItem() do while loop
//					}
//
//					MenuType menuType = MenuItem.chooseMenuType();
//
//					// add to ArrayList<MenuItem> menuItems
//					MenuItem newItem = new MenuItem(id, name, desc, price, menuType);
//					menuItems.add(newItem);
//					// print success msg
//					System.out.println("Success! Edited Menu Item:");
//					newItem.print();
//				} catch (Exception e) {
//					System.out.println("Error! Try again.");
//				}
				break;

			case 4: // 4 Delete Menu Item
				break;

			default:
				printInvalidInputMsg();
			}
		} while (option != 0);
	}

}
