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
	 * if inString != NULL, prints: "Invalid Input, please enter valid input.
	 * Exiting inString ..." ; if inString == "" , prints: "Invalid Input, please
	 * enter valid input."
	 * 
	 * @param inString
	 */
	public static void printInvalidInputMsg(String inString) {
		// eg s = "create Menu Item" ,
		// Please enter valid input. Exiting create Menu Item...
		if (inString.isEmpty()) {
			System.out.println("Invalid Input, please enter valid input.");
		} else {
			System.out.println("Invalid Input, please enter valid input. Exiting " + inString + "...");
		}
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
					// MenuItem(int menuItemId, String name, String description, double price,
					// MenuType menuType)
					menuItems.add(new MenuItem(Integer.parseInt(tokens[1]), tokens[2], tokens[3],
							Double.parseDouble(tokens[4]), MenuType.valueOf(tokens[5])));
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
				try {
					int id = 0;
					System.out.println("Enter Menu Item Id: ");
					id = sc.nextInt();
					sc.nextLine(); // get rid of \n
					if (id < 0 || !MenuItem.isValidId(menuItems, id)) { // invalid value
						printInvalidInputMsg("Create Menu Item");
						break; // exit to displayMenuItem() do while loop
					}

					String name = "";
					System.out.println("Enter Menu Item name: ");
					name = sc.nextLine();
					if (name.isEmpty()) { // invalid value
						printInvalidInputMsg("Create Menu Item");
						break; // exit to displayMenuItem() do while loop
					}

					String desc = "";
					System.out.println("Enter Menu Item description: ");
					desc = sc.nextLine();
					if (desc.isEmpty()) { // invalid value
						printInvalidInputMsg("Create Menu Item");
						break; // exit to displayMenuItem() do while loop
					}

					double price = 0;
					System.out.println("Enter Menu Item price: ");
					price = sc.nextDouble();
					if (price < 0) { // invalid value
						printInvalidInputMsg("Create Menu Item");
						break; // exit to displayMenuItem() do while loop
					}

					MenuType menuType = MenuItem.chooseMenuType();

					// add to ArrayList<MenuItem> menuItems
					MenuItem newItem = new MenuItem(id, name, desc, price, menuType);
					menuItems.add(newItem);
					// print success msg
					System.out.println("Success! New Menu Item:");
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
