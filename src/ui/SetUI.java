package ui;

import java.util.ArrayList;
import java.util.Scanner;

import classes.MenuItem;
import classes.Restaurant;
import classes.Set;
import mgr.SetManager;

public class SetUI {
	private static SetManager setManager = new SetManager();

	public static void showSetUI(Restaurant restaurant) {
		Scanner sc = new Scanner(System.in);
		int option = 0;

		ArrayList<Set> sets = restaurant.getSets();
		ArrayList<MenuItem> menuItems = restaurant.getMenuItems();

		do {
			System.out.println("\n==============================");
			System.out.println("=== [Set] ===");
			System.out.println("Choose an option:");
			System.out.println("[1] View all Set");
			System.out.println("[2] Create Set");
			System.out.println("[3] Update Set");
			System.out.println("[4] Remove Set");
			System.out.println("[0] Return to Sale Item");
			System.out.println("==============================");

			while (!sc.hasNextInt()) { // check if user entered int
				sc.next(); // move buffer
				System.out.println("Invalid Input, please enter valid input.");
			}

			// user entered int
			option = sc.nextInt();

			switch (option) {
			case 0:
				System.out.println("Returning to Sale Item...");
				break;
			case 1: // 1 View all Set
				setManager.viewAllSet(sets);
				break;
			case 2: // 2 Create Set
				Set newSet = null;
				newSet = setManager.createSet(menuItems, sets);
				sets.add(newSet);
				restaurant.setSets(sets);
				break;
			case 3: // 3 Update Set
				ArrayList<Set> updateSets = null;
				updateSets = setManager.updateSet(menuItems, sets);
				restaurant.setSets(updateSets);
				break;
			case 4: // 4 Delete Set
				ArrayList<Set> delSets = null;
				delSets = setManager.deleteSet(sets);
				restaurant.setSets(delSets);
				break;
			default:
				System.out.println("No such option.");
			}

		} while (option != 0);

	}
}
