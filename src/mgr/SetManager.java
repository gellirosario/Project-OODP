package mgr;

import java.util.ArrayList;
import java.util.Scanner;

import classes.MenuItem;
import classes.Set;

/**
 * Handles manipulation of Set
 * 
 * @author Kailing
 *
 */
public class SetManager {

	private static MenuItemManager menuItemManager = new MenuItemManager();

	/**
	 * prints all available Set, from parameter sets <br>
	 * print "No Set available." if sets size == 0 or sets == null
	 * 
	 * @param sets available ArrayList<Set> sets
	 */
	public void viewAllSet(ArrayList<Set> sets) {
		if (sets.size() == 0 || sets == null) {
			System.out.println("No Set available.");
			return;
		}
		for (int i = 0; i < sets.size(); i++) {
			Set set = sets.get(i);
			System.out.print((i + 1) + ") ");
			set.print();
		}
	}

	/**
	 * Create new Set from user's inputs
	 * 
	 * @param menuItems available ArrayList<MenuItem> menuItems
	 * @param sets      available ArrayList<Set> sets
	 * @return null if menuItems size == 0 or menuItems == null or user exit<br>
	 *         new Set created from user's inputs
	 */
	public Set createSet(ArrayList<MenuItem> menuItems, ArrayList<Set> sets) {
		System.out.println("Enter -1 at any time to exit current action.");
		Scanner sc = new Scanner(System.in);

		int id = 0;
		String name = "";
		String desc = "";
		double price = 0;
		ArrayList<MenuItem> setItems = null;

		if (menuItems.size() == 0 || menuItems == null) {
			System.out.println("No Menu Item available. Cannot create Set.");
			return null;
		}

		System.out.println("Enter Set Id: ");
		do {
			while (!sc.hasNextInt()) { // check if user entered int
				sc.next(); // move buffer
				System.out.println("***Invalid Input, please enter valid input.");
			}
			// user entered int
			id = sc.nextInt();
			sc.nextLine(); // get rid of \n
			if (id == -1) { // check user exit
				return null; // exit
			}
			if (id <= 0 || !Set.isValidId(sets, id)) { // invalid value
				System.out.println("***Invalid Input, please enter valid input.");
			} else { // valid value
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		System.out.println("Enter Set name: ");
		do {
			name = sc.nextLine();
			if (name.equals("-1")) { // check for user exit
				return null; // exit
			}
			if (name.isEmpty()) { // invalid value
				System.out.println("***Invalid Input, please enter valid input.");
			} else { // valid value
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		System.out.println("Enter Set description: ");
		do {
			desc = sc.nextLine();
			if (desc.equals("-1")) { // check for user exit
				return null; // exit
			}
			if (desc.isEmpty()) { // invalid value
				System.out.println("***Invalid Input, please enter valid input.");
			} else { // valid value
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		System.out.println("Enter Set price: ");
		do {
			while (!sc.hasNextDouble()) { // check if user entered double
				sc.next(); // move buffer
				System.out.println("***Invalid Input, please enter valid input.");
			}
			// user entered double
			price = sc.nextDouble();
			sc.nextLine(); // get rid of \n
			if (price == -1) { // check user exit
				return null; // exit
			}
			if (price < 0) { // invalid value
				System.out.println("***Invalid Input, please enter valid input.");
			} else { // valid value
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		setItems = createSetItems(menuItems);
		if(setItems == null) {
			return null;
		}

		Set newSet = new Set(id, name, desc, price, setItems);
		// print success msg
		System.out.println("\nSuccess! New Set:");
		newSet.print();

		return newSet;
	}

	/**
	 * Update a Set from parameter, ArrayList<Set> sets
	 * 
	 * @param menuItems available ArrayList<MenuItem> menuItems
	 * @param sets      available ArrayList<Set> sets
	 * @return null if menuItems size == 0 or menuItems == null or sets size == 0 or
	 *         sets == null or user exit; <br>
	 *         updated ArrayList<Set> sets, where one of the Set is updated
	 */
	public ArrayList<Set> updateSet(ArrayList<MenuItem> menuItems, ArrayList<Set> sets) {
		System.out.println("Enter -1 at any time to exit current action.");
		System.out.println("Note that any changes will be kept.");
		Scanner sc = new Scanner(System.in);

		int id = 0;
		String name = "";
		String desc = "";
		double price = 0;
		ArrayList<MenuItem> setItems = null;
		Set set = null;

		if (menuItems.size() == 0 || menuItems == null) {
			System.out.println("No Menu Item available. Cannot edit Set.");
			return null;
		}
		if (sets.size() == 0 || sets == null) {
			System.out.println("No Set available.");
			return null;
		}

		viewAllSet(sets);
		System.out.println("Select a Set by Id:");
		do { // choose a Set to edit
			while (!sc.hasNextInt()) { // check if user entered int
				sc.next(); // move buffer
				System.out.println("***Invalid Input, please enter valid input.");
			}
			// user entered int
			id = sc.nextInt();
			sc.nextLine(); // get rid of \n
			if (id == -1) { // check user exit
				return null; // exit
			}
			set = getSetById(sets, id);
			if (set == null) { // invalid value
				System.out.println("***Invalid Input, please enter valid input.");
			} else { // valid value
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		System.out.println("\nCurrent id: " + set.getId());
		System.out.println("Enter new Set Id: ");
		do {
			while (!sc.hasNextInt()) { // check if user entered int
				sc.next(); // move buffer
				System.out.println("***Invalid Input, please enter valid input.");
			}
			// user entered int
			id = sc.nextInt();
			sc.nextLine(); // get rid of \n
			if (id == -1) { // check user exit
				return null; // exit
			}
			if (id == set.getId()) { // same value
				System.out.println("Input value is same as current value: " + set.getId());
				break; // same value
			} else if (id <= 0 || !Set.isValidId(sets, id)) { // invalid value
				System.out.println("***Invalid Input, please enter valid input.");
			} else { // valid value
				set.setId(id);
				System.out.println("New value: " + set.getId());
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		System.out.println("\nCurrent name: " + set.getName());
		System.out.println("Enter new Set name: ");
		do {
			name = sc.nextLine();
			if (name.equals("-1")) { // check for user exit
				return null; // exit
			}
			if (name.isEmpty()) { // invalid value
				System.out.println("***Invalid Input, please enter valid input.");
			} else { // valid value
				if (name.equals(set.getName())) {
					System.out.println("Input value is same as current value: " + set.getName());
				} else {
					set.setName(name);
					System.out.println("New value: " + set.getName());
				}
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		System.out.println("\nCurrent description: " + set.getDescription());
		System.out.println("Enter new Set description: ");
		do {
			desc = sc.nextLine();
			if (desc.equals("-1")) { // check for user exit
				return null; // exit
			}
			if (desc.isEmpty()) { // invalid value
				System.out.println("***Invalid Input, please enter valid input.");
			} else { // valid value
				if (desc.equals(set.getDescription())) {
					System.out.println("Input value is same as current value: " + set.getDescription());
				} else {
					set.setDescription(desc);
					System.out.println("New value: " + set.getDescription());
				}
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		System.out.println("\nCurrent price: " + set.getPrice());
		System.out.println("Enter new Set price: ");
		do {
			while (!sc.hasNextDouble()) { // check if user entered double
				sc.next(); // move buffer
				System.out.println("***Invalid Input, please enter valid input.");
			}
			// user entered double
			price = sc.nextDouble();
			sc.nextLine(); // get rid of \n
			if (price == -1) { // check user exit
				return null; // exit
			}
			if (price < 0) { // invalid value
				System.out.println("***Invalid Input, please enter valid input.");
			} else { // valid value
				if (price == set.getPrice()) {
					System.out.println("Input value is same as current value: " + set.getPrice());
				} else {
					set.setPrice(price);
					System.out.println("New value: " + set.getPrice());
				}
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		System.out.println("\nCurrent Set's Menu Items(s): ");
		setItems = set.getMenuItems();
		menuItemManager.viewAllMenuItem(setItems);
		setItems = createSetItems(menuItems); // enter new set items
		if(setItems == null) {
			return null;
		}
		set.setMenuItems(setItems);
		System.out.println("New Set's Menu Items(s):");
		menuItemManager.viewAllMenuItem(setItems);

		// print success msg
		System.out.println("\nSuccess! Edited Set:");
		set.print();

		return sets;
	}

	/**
	 * Delete a Set from parameter, ArrayList<MenuItem> sets, <br>
	 * user choose a Set from sets that will be deleted
	 * 
	 * @param sets available ArrayList<Set> sets
	 * @return updated ArrayList <Set> sets where one of the Set is deleted;<br>
	 *         null if sets size == 0 or sets == null or user exit;
	 */
	public ArrayList<Set> deleteSet(ArrayList<Set> sets) {
		System.out.println("Enter -1 at any time to exit current action.");
		Scanner sc = new Scanner(System.in);

		int id = 0;
		Set set = null;

		if (sets.size() == 0 || sets == null) {
			System.out.println("No Set available.");
			return null;
		}

		viewAllSet(sets);
		System.out.println("Select a Set by Id:");
		do { // choose a Set to delete
			while (!sc.hasNextInt()) { // check if user entered int
				sc.next(); // move buffer
				System.out.println("***Invalid Input, please enter valid input.");
			}
			// user entered int
			id = sc.nextInt();
			sc.nextLine(); // get rid of \n
			if (id == -1) { // check for user exit
				return null; // exit
			}
			set = getSetById(sets, id);
			if (set == null) { // invalid value
				System.out.println("***Invalid Input, please enter valid input.");
			} else { // valid value
				sets.remove(set);
				System.out.println("Set Id: " + id + " has been deleted.");
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		return sets;
	}

	/**
	 * return a Set by its Id
	 * 
	 * @param sets available ArrayList<Set> sets
	 * @param id   id of Set
	 * @return null if Set not found; <br>
	 *         Set that has id same as parameter id;
	 */
	public Set getSetById(ArrayList<Set> sets, int id) {
		for (int i = 0; i < sets.size(); i++) {
			if (sets.get(i).getId() == id) {
				return sets.get(i);
			}
		}
		return null;
	}

	/**
	 * Creates new ArrayList<MenuItem> setItems, of a Set <br>
	 * Each Set must have at least 1 MenuItem <br>
	 * Each Set can have duplicate MenuItems
	 * 
	 * @param menuItems available ArrayList<MenuItem> menuItems
	 * @return null if input menuItems size == 0 or menuItems == null or user exit;
	 *         <br>
	 *         valid ArrayList<MenuItem> setItems
	 */
	public ArrayList<MenuItem> createSetItems(ArrayList<MenuItem> menuItems) {
		System.out.println("Enter -1 at any time to exit current action.");
		Scanner sc = new Scanner(System.in);
		int menuItemId = 0;
		int setItemsSize = 0;
		MenuItem setItem = null;
		ArrayList<MenuItem> setItems = new ArrayList<MenuItem>();

		if (menuItems.size() == 0 || menuItems == null) {
			System.out.println("No Menu Item available. Cannot create Set.");
			return null;
		}

		System.out.println("List of all Menu Item(s):");
		menuItemManager.viewAllMenuItem(menuItems);

		System.out.println("Enter number of Menu Item(s) to be added into Set:");
		do {
			while (!sc.hasNextInt()) { // check if user entered int
				sc.next(); // move buffer
				System.out.println("***Invalid Input, please enter valid input.");
			}
			// user entered int
			setItemsSize = sc.nextInt();
			sc.nextLine(); // get rid of \n
			if (setItemsSize == -1) { // check for user exit
				return null; // exit
			}
			if (setItemsSize < 1) { // invalid value
				System.out.println("***Invalid Input, please enter valid input.");
			} else { // valid value
				break; // exit do while loop
			}
		} while (true); // only exit do while loop when user input is valid

		for (int i = 0; i < setItemsSize; i++) {
			System.out.println("Enter Menu Item Id that will be added to Set:");
			do {
				while (!sc.hasNextInt()) { // check if user entered int
					sc.next(); // move buffer
					System.out.println("***Invalid Input, please enter valid input.");
				}
				// user entered int
				menuItemId = sc.nextInt();
				sc.nextLine(); // get rid of \n
				if (menuItemId == -1) { // check for user exit
					return null; // exit
				}
				setItem = menuItemManager.getMenuItemById(menuItems, menuItemId);
				if (setItem == null) { // invalid value
					System.out.println("***Invalid Input, please enter valid input.");
				} else { // valid value
					setItems.add(setItem);
					break; // exit do while loop
				}
			} while (true); // only exit do while loop when user input is valid
		}

		return setItems;
	}

}
