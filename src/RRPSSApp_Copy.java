import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import classes.*;
import classes.MenuItem.MenuType;
import ui.*;

public class RRPSSApp_Copy {

//	 DON'T add int option = 0 AND Scanner here, will affect all menu choice //TODO remove b4 submit

	// using ArrayList as it has no fixed size
	static ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
	static ArrayList<Promotion> promotions = new ArrayList<Promotion>();

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

			while (!sc.hasNextInt()) { // check if user entered int
				sc.next(); // move buffer
				printInvalidInputMsg();
			}

			// user entered int
			option = sc.nextInt();
			sc.nextLine(); // get rid of \n

			switch (option) {
//			case 55: // for testing lul TODO remove b4 submit
//				break; // for testing lul TODO remove b4 submit
			case 0:
				System.out.println("Exiting RRPSS System...");
				break;
			case 1: // 1) View/Edit Menu Item
				displayMenuItem();
				break;
			case 2: // 2) View/Edit Promotion Item
				displayPromotion();
				break;

			default:
				System.out.println("No such option.");
			}
		} while (option != 0);

		// save data when program ends
		saveData();

		sc.close();
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
				} else if (tokens[0].equals("Promotion")) { // Promotion
					String[] stringIds = tokens[5].split(","); // menuItemIds are split by ','
					int[] menuItemIds = Arrays.asList(stringIds).stream().mapToInt(Integer::parseInt).toArray();
					MenuItem promotionItem = null;
					ArrayList<MenuItem> promotionItems = new ArrayList<MenuItem>();
					for (int i = 0; i < menuItemIds.length; i++) {
						promotionItem = MenuItem.getMenuItemById(menuItems, menuItemIds[i]);
						promotionItems.add(promotionItem);
					}
					promotions.add(new Promotion(Integer.parseInt(tokens[1]), tokens[2], tokens[3],
							Double.parseDouble(tokens[4]), promotionItems));
				}
				// ####### u can ADD YOUR OWN READ DATA HERE ####### //TODO remove b4 submit
				else {
					System.out.println("Error reading data.");
				}

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
			// save all Promotion
			for (int i = 0; i < promotions.size(); i++) {
				String line = promotions.get(i).toString(); // generate line
				out.println(line); // add a line to text file
			}
			// ####### u can ADD YOUR OWN SAVE DATA HERE ####### //TODO remove b4 submit

			out.close(); // close before exit
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Saving data done.");
	}

	public static void displayMenuItem() {
		Scanner sc = new Scanner(System.in);
		int option = 0;
		int id = 0;
		String name = "";
		String desc = "";
		double price = 0;
		MenuType menuType = null;

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

			while (!sc.hasNextInt()) { // check if user entered int
				sc.next(); // move buffer
				printInvalidInputMsg();
			}

			// user entered int
			option = sc.nextInt();

			switch (option) {
			case 0:
				System.out.println("Returning to RRPSS Main Menu...");
				break;

			case 1: // 1 View all Menu Item
				if (menuItems.size() == 0) {
					System.out.println("No Menu Item available.");
					break; // go back to === View/Edit Menu Item ===
				}
				MenuItem.printAll(menuItems);
				break;

			case 2: // 2 Create Menu Item
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

				System.out.println("Enter Menu Item name: ");
				do {
					name = sc.nextLine();
					if (name.isEmpty()) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				System.out.println("Enter Menu Item description: ");
				do {
					desc = sc.nextLine();
					if (desc.isEmpty()) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

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

				menuType = MenuItem.chooseMenuType();

				// add to ArrayList<MenuItem> menuItems
				MenuItem newItem = new MenuItem(id, name, desc, price, menuType);
				menuItems.add(newItem);
				// print success msg
				System.out.println("\nSuccess! New Menu Item:");
				newItem.print();
				break; // end of case 2 Create Menu Item

			case 3: // 3 Edit Menu Item
				MenuItem menuItem = null;

				if (menuItems.size() == 0) {
					System.out.println("No Menu Item available.");
					break; // go back to === View/Edit Menu Item ===
				}

				System.out.println("Select a Menu Item by Id:");
				MenuItem.printAll(menuItems);
				do { // choose a Menu Item to edit
					while (!sc.hasNextInt()) { // check if user entered int
						sc.next(); // move buffer
						printInvalidInputMsg();
					}
					// user entered int
					id = sc.nextInt();
					sc.nextLine(); // get rid of \n
					menuItem = MenuItem.getMenuItemById(menuItems, id);
					if (menuItem == null) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				System.out.println("\nCurrent id: " + menuItem.getId());
				System.out.println("Enter new Menu Item Id: ");
				do {
					while (!sc.hasNextInt()) { // check if user entered int
						sc.next(); // move buffer
						printInvalidInputMsg();
					}
					// user entered int
					id = sc.nextInt();
					sc.nextLine(); // get rid of \n
					if (id == menuItem.getId()) { // same value
						System.out.println("Input value is same as current value: " + menuItem.getId());
						break; // same value
					} else if (id <= 0 || !MenuItem.isValidId(menuItems, id)) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						menuItem.setId(id);
						System.out.println("New value: " + menuItem.getId());
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				System.out.println("\nCurrent name: " + menuItem.getName());
				System.out.println("Enter new Menu Item name: ");
				do {
					name = sc.nextLine();
					if (name.isEmpty()) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						if (name.equals(menuItem.getName())) {
							System.out.println("Input value is same as current value: " + menuItem.getName());
						} else {
							menuItem.setName(name);
							System.out.println("New value: " + menuItem.getName());
						}
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				System.out.println("\nCurrent description: " + menuItem.getDescription());
				System.out.println("Enter new Menu Item description: ");
				do {
					desc = sc.nextLine();
					if (desc.isEmpty()) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						if (desc.equals(menuItem.getDescription())) {
							System.out.println("Input value is same as current value: " + menuItem.getDescription());
						} else {
							menuItem.setDescription(desc);
							System.out.println("New value: " + menuItem.getDescription());
						}
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				System.out.println("\nCurrent price: " + menuItem.getPrice());
				System.out.println("Enter new Menu Item price: ");
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
						if (price == menuItem.getPrice()) {
							System.out.println("Input value is same as current value: " + menuItem.getPrice());
						} else {
							menuItem.setPrice(price);
							System.out.println("New value: " + menuItem.getPrice());
						}
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				System.out.println("\nCurrent menu type: " + menuItem.getMenuType().toString());
				menuType = MenuItem.chooseMenuType();
				if (menuType == menuItem.getMenuType()) {
					System.out.println("Input value is same as current value: " + menuItem.getMenuType().toString());
				} else {
					menuItem.setMenuType(menuType);
					System.out.println("New value: " + menuItem.getMenuType().toString());
				}

				// print success msg
				System.out.println("\nSuccess! Edited Menu Item:");
				menuItem.print();

				break; // end of case 3 Edit Menu Item

			case 4: // 4 Delete Menu Item
				if (menuItems.size() == 0) {
					System.out.println("No Menu Item available.");
					break; // go back to === View/Edit Menu Item ===
				}

				System.out.println("Select a Menu Item by Id:");
				MenuItem.printAll(menuItems);
				System.out.println("Enter 0 to cancel delete.");
				do { // choose a Menu Item to delete
					while (!sc.hasNextInt()) { // check if user entered int
						sc.next(); // move buffer
						printInvalidInputMsg();
					}
					// user entered int
					id = sc.nextInt();
					sc.nextLine(); // get rid of \n
					if (id == 0) {
						System.out.println("Cancelled delete.");
						break; // exit/cancel delete
					}
					menuItem = MenuItem.getMenuItemById(menuItems, id);
					if (menuItem == null) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						menuItems.remove(menuItem);
						System.out.println("Menu Item Id: " + id + " has been deleted.");
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid
				break; // end of case 4 Delete Menu Item

			default:
				System.out.println("No such option.");
			}
		} while (option != 0);
	}

	public static void displayPromotion() {
		Scanner sc = new Scanner(System.in);
		int option = 0;
		int id = 0;
		String name = "";
		String desc = "";
		double price = 0;
		ArrayList<MenuItem> promoItems = null;
		Promotion promotion = null;

		do {
			System.out.println("\n==============================");
			System.out.println("=== View/Edit Promotion ===");
			System.out.println("Choose an option:");
			System.out.println("1) View all Promotion");
			System.out.println("2) Create Promotion");
			System.out.println("3) Edit Promotion");
			System.out.println("4) Delete Promotion");
			System.out.println("0) Return to RRPSS Main Menu");
			System.out.println("==============================");

			while (!sc.hasNextInt()) { // check if user entered int
				sc.next(); // move buffer
				printInvalidInputMsg();
			}

			// user entered int
			option = sc.nextInt();

			switch (option) {
			case 0:
				System.out.println("Returning to RRPSS Main Menu...");
				break;

			case 1: // 1 View all Promotion
				if (promotions.size() == 0) {
					System.out.println("No Promotion available.");
					break; // go back to === View/Edit Promotion ===
				}
				Promotion.printAll(promotions);
				break;

			case 2: // 2 Create Promotion
				if (menuItems.size() == 0) {
					System.out.println("No Menu Item available. Cannot create Promotion.");
					break; // go back to === View/Edit Promotion ===
				}

				System.out.println("Enter Promotion Id: ");
				do {
					while (!sc.hasNextInt()) { // check if user entered int
						sc.next(); // move buffer
						printInvalidInputMsg();
					}
					// user entered int
					id = sc.nextInt();
					sc.nextLine(); // get rid of \n
					if (id <= 0 || !Promotion.isValidId(promotions, id)) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				System.out.println("Enter Promotion name: ");
				do {
					name = sc.nextLine();
					if (name.isEmpty()) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				System.out.println("Enter Promotion description: ");
				do {
					desc = sc.nextLine();
					if (desc.isEmpty()) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				System.out.println("Enter Promotion price: ");
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

				promoItems = Promotion.createPromotionItems(menuItems);

				// add to ArrayList<Promotion> promotions
				Promotion newPromotion = new Promotion(id, name, desc, price, promoItems);
				promotions.add(newPromotion);
				// print success msg
				System.out.println("\nSuccess! New Promotion:");
				newPromotion.print();
				break;

			case 3: // 3 Edit Promotion
				if (menuItems.size() == 0) {
					System.out.println("No Menu Item available. Cannot edit Promotion.");
					break; // go back to === View/Edit Promotion ===
				}
				if (promotions.size() == 0) {
					System.out.println("No Promotion available.");
					break; // go back to === View/Edit Promotion ===
				}

				System.out.println("Select a Promotion by Id:");
				Promotion.printAll(promotions);
				do { // choose a Promotion to edit
					while (!sc.hasNextInt()) { // check if user entered int
						sc.next(); // move buffer
						printInvalidInputMsg();
					}
					// user entered int
					id = sc.nextInt();
					sc.nextLine(); // get rid of \n
					promotion = Promotion.getPromotionById(promotions, id);
					if (promotion == null) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				System.out.println("\nCurrent id: " + promotion.getId());
				System.out.println("Enter new Promotion Id: ");
				do {
					while (!sc.hasNextInt()) { // check if user entered int
						sc.next(); // move buffer
						printInvalidInputMsg();
					}
					// user entered int
					id = sc.nextInt();
					sc.nextLine(); // get rid of \n
					if (id == promotion.getId()) { // same value
						System.out.println("Input value is same as current value: " + promotion.getId());
						break; // same value
					} else if (id <= 0 || !Promotion.isValidId(promotions, id)) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						promotion.setId(id);
						System.out.println("New value: " + promotion.getId());
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				System.out.println("\nCurrent name: " + promotion.getName());
				System.out.println("Enter new Promotion name: ");
				do {
					name = sc.nextLine();
					if (name.isEmpty()) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						if (name.equals(promotion.getName())) {
							System.out.println("Input value is same as current value: " + promotion.getName());
						} else {
							promotion.setName(name);
							System.out.println("New value: " + promotion.getName());
						}
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				System.out.println("\nCurrent description: " + promotion.getDescription());
				System.out.println("Enter new Promotion description: ");
				do {
					desc = sc.nextLine();
					if (desc.isEmpty()) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						if (desc.equals(promotion.getDescription())) {
							System.out.println("Input value is same as current value: " + promotion.getDescription());
						} else {
							promotion.setDescription(desc);
							System.out.println("New value: " + promotion.getDescription());
						}
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				System.out.println("\nCurrent price: " + promotion.getPrice());
				System.out.println("Enter new Promotion price: ");
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
						if (price == promotion.getPrice()) {
							System.out.println("Input value is same as current value: " + promotion.getPrice());
						} else {
							promotion.setPrice(price);
							System.out.println("New value: " + promotion.getPrice());
						}
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid

				System.out.println("\nCurrent Promotion's Menu Items(s): ");
				promoItems = promotion.getMenuItems();
				MenuItem.printAll(promoItems);
				promoItems = Promotion.createPromotionItems(menuItems); // enter new promotion items
				promotion.setMenuItems(promoItems);
				System.out.println("New Promotion's Menu Items(s):");
				MenuItem.printAll(promoItems);

				// print success msg
				System.out.println("\nSuccess! Edited Promotion:");
				promotion.print();

				break;

			case 4: // 4 Delete Promotion
				if (promotions.size() == 0) {
					System.out.println("No Promotion available.");
					break; // go back to === View/Edit Promotion ===
				}

				System.out.println("Select a Promotion by Id:");
				Promotion.printAll(promotions);
				System.out.println("Enter 0 to cancel delete.");
				do { // choose a Promotion to delete
					while (!sc.hasNextInt()) { // check if user entered int
						sc.next(); // move buffer
						printInvalidInputMsg();
					}
					// user entered int
					id = sc.nextInt();
					sc.nextLine(); // get rid of \n
					if (id == 0) {
						System.out.println("Cancelled delete.");
						break; // exit/cancel delete
					}
					promotion = Promotion.getPromotionById(promotions, id);
					if (promotion == null) { // invalid value
						printInvalidInputMsg();
					} else { // valid value
						promotions.remove(promotion);
						System.out.println("Promotion Id: " + id + " has been deleted.");
						break; // exit do while loop
					}
				} while (true); // only exit do while loop when user input is valid
				break;

			default:
				System.out.println("No such option.");
			}
		} while (option != 0);
	}

}