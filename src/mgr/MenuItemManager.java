package mgr;

import java.util.ArrayList;
import java.util.Scanner;

import classes.MenuItem;
import classes.MenuItem.MenuType;

/**
 * Handles manipulation of MenuItem
 * @author Kailing
 *
 */
public class MenuItemManager {

	/**
	 * prints all available MenuItem, from parameter menuItems <br>
	 * print "No Menu Item available." if menuItems size == 0 or menuItems == null
	 * 
	 * @param menuItems available ArrayList<MenuItem> menuItems
	 */
	public void viewAllMenuItem(ArrayList<MenuItem> menuItems) {
		if (menuItems.size() == 0 || menuItems == null) {
			System.out.println("No Menu Item available.");
			return;
		}
		for (int i = 0; i < menuItems.size(); i++) {
			MenuItem item = menuItems.get(i);
			System.out.print((i + 1) + ") ");
			item.print();
		}
	}

	/**
	 * Create new Menu Item from user's inputs
	 * 
	 * @param menuItems available ArrayList<MenuItem> menuItems
	 * @return new Menu Item created from user's inputs
	 */
	public MenuItem createMenuItem(ArrayList<MenuItem> menuItems) {
		Scanner sc = new Scanner(System.in);
		int id = 0;
		String name = "";
		String desc = "";
		double price = 0;
		MenuType menuType = null;

		System.out.println("Enter Menu Item Id: ");
		do {
			while (!sc.hasNextInt()) { // check if user entered int
				sc.next(); // move buffer
				System.out.println("Invalid Input, please enter valid input.");
			}
			// user entered int
			id = sc.nextInt();
			sc.nextLine(); // get rid of \n
			if (id <= 0 || !MenuItem.isValidId(menuItems, id)) { // invalid value
				System.out.println("Invalid Input, please enter valid input.");
			} else { // valid value
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		System.out.println("Enter Menu Item name: ");
		do {
			name = sc.nextLine();
			if (name.isEmpty()) { // invalid value
				System.out.println("Invalid Input, please enter valid input.");
			} else { // valid value
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		System.out.println("Enter Menu Item description: ");
		do {
			desc = sc.nextLine();
			if (desc.isEmpty()) { // invalid value
				System.out.println("Invalid Input, please enter valid input.");
			} else { // valid value
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		System.out.println("Enter Menu Item price: ");
		do {
			while (!sc.hasNextDouble()) { // check if user entered double
				sc.next(); // move buffer
				System.out.println("Invalid Input, please enter valid input.");
			}
			// user entered double
			price = sc.nextDouble();
			sc.nextLine(); // get rid of \n
			if (price < 0) { // invalid value
				System.out.println("Invalid Input, please enter valid input.");
			} else { // valid value
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		menuType = chooseMenuType();

		// add to ArrayList<MenuItem> menuItems
		MenuItem newItem = new MenuItem(id, name, desc, price, menuType);

		// print success msg
		System.out.println("\nSuccess! New Menu Item:");
		newItem.print();

		return newItem;
	}

	/**
	 * Update a MenuItem from parameter, ArrayList<MenuItem> menuItems, <br>
	 * user choose a Menu Item from menuItems, <br>
	 * user enter new values for chosen Menu Item
	 * 
	 * @param menuItems available ArrayList<MenuItem> menuItems
	 * @return updated ArrayList<MenuItem> menuItems, where one of the MenuItem is
	 *         updated
	 */
	public ArrayList<MenuItem> updateMenuItem(ArrayList<MenuItem> menuItems) {
		Scanner sc = new Scanner(System.in);

		int id = 0;
		String name = "";
		String desc = "";
		double price = 0;
		MenuType menuType = null;
		MenuItem menuItem = null;

		viewAllMenuItem(menuItems);
		System.out.println("Select a Menu Item by Id:");

		do { // choose a Menu Item to edit
			while (!sc.hasNextInt()) { // check if user entered int
				sc.next(); // move buffer
				System.out.println("Invalid Input, please enter valid input.");
			}
			// user entered int
			id = sc.nextInt();
			sc.nextLine(); // get rid of \n
			menuItem = getMenuItemById(menuItems, id);
			if (menuItem == null) { // invalid value
				System.out.println("Invalid Input, please enter valid input.");
			} else { // valid value
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		System.out.println("\nCurrent id: " + menuItem.getId());
		System.out.println("Enter new Menu Item Id: ");
		do {
			while (!sc.hasNextInt()) { // check if user entered int
				sc.next(); // move buffer
				System.out.println("Invalid Input, please enter valid input.");
			}
			// user entered int
			id = sc.nextInt();
			sc.nextLine(); // get rid of \n
			if (id == menuItem.getId()) { // same value
				System.out.println("Input value is same as current value: " + menuItem.getId());
				break; // same value
			} else if (id <= 0 || !MenuItem.isValidId(menuItems, id)) { // invalid value
				System.out.println("Invalid Input, please enter valid input.");
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
				System.out.println("Invalid Input, please enter valid input.");
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
				System.out.println("Invalid Input, please enter valid input.");
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
				System.out.println("Invalid Input, please enter valid input.");
			}
			// user entered double
			price = sc.nextDouble();
			sc.nextLine(); // get rid of \n
			if (price < 0) { // invalid value
				System.out.println("Invalid Input, please enter valid input.");
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
		menuType = chooseMenuType();
		if (menuType == menuItem.getMenuType()) {
			System.out.println("Input value is same as current value: " + menuItem.getMenuType().toString());
		} else {
			menuItem.setMenuType(menuType);
			System.out.println("New value: " + menuItem.getMenuType().toString());
		}

		// print success msg
		System.out.println("\nSuccess! Edited Menu Item:");
		menuItem.print();

		return menuItems;
	}

	/**
	 * Delete a MenuItem from parameter, ArrayList<MenuItem> menuItems, <br>
	 * user choose a Menu Item from menuItems that will be deleted, <br>
	 * user can enter 0 to terminate delete process
	 * 
	 * @param menuItems available ArrayList<MenuItem> menuItems
	 * @return updated ArrayList <MenuItem> menuItems, where one of the MenuItem is
	 *         deleted <br>
	 *         null, if menuItems size == 0 or menuItems == null
	 */
	public ArrayList<MenuItem> deleteMenuItem(ArrayList<MenuItem> menuItems) {
		Scanner sc = new Scanner(System.in);

		int id = 0;
		MenuItem menuItem = null;

		if (menuItems.size() == 0 || menuItems == null) {
			System.out.println("No Menu item available.");
			return null;
		}

		viewAllMenuItem(menuItems);
		System.out.println("Select a Menu Item by Id:");

		System.out.println("Enter 0 to cancel delete.");
		do { // choose a Menu Item to delete
			while (!sc.hasNextInt()) { // check if user entered int
				sc.next(); // move buffer
				System.out.println("Invalid Input, please enter valid input.");
			}
			// user entered int
			id = sc.nextInt();
			sc.nextLine(); // get rid of \n
			if (id == 0) {
				System.out.println("Cancelled delete.");
				break; // exit/cancel delete
			}
			menuItem = getMenuItemById(menuItems, id);
			if (menuItem == null) { // invalid value
				System.out.println("Invalid Input, please enter valid input.");
			} else { // valid value
				menuItems.remove(menuItem);
				System.out.println("Menu Item Id: " + id + " has been deleted.");
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		return menuItems;
	}

	/**
	 * return a MenuItem by its Id
	 * 
	 * @param menuItems
	 * @param id
	 * @return null if MenuItem not found;
	 */
	public MenuItem getMenuItemById(ArrayList<MenuItem> menuItems, int id) {
		for (int i = 0; i < menuItems.size(); i++) {
			if (menuItems.get(i).getId() == id) {
				return menuItems.get(i);
			}
		}
		return null;
	}

	/**
	 * prints all MenuType <br>
	 * user choose a MenuType
	 * @return MenuType chosen by user
	 */
	public MenuType chooseMenuType() {
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
				System.out.println("Invalid Input, please enter valid input.");
			}
			// user entered int
			option = sc.nextInt();
			sc.nextLine(); // get rid of \n
			if (option <= 0 || option > MenuType.values().length) { // invalid value
				System.out.println("Invalid Input, please enter valid input.");
			} else { // valid value
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		return MenuType.values()[option - 1];
	}

}
