package ui;

import java.util.ArrayList;
import java.util.Scanner;

import classes.MenuItem;
import classes.Restaurant;
import mgr.MenuItemManager;

/**
 * Handles UI related to MenuItem
 * 
 * @author Kailing
 *
 */
public class MenuItemUI {
	private static MenuItemManager menuItemManager = new MenuItemManager();

	/**
	 * Display UI related to MenuItem
	 * 
	 * @param restaurant
	 */
	public static void showMenuItemUI(Restaurant restaurant) {
		Scanner sc = new Scanner(System.in);
		int option = 0;

		ArrayList<MenuItem> menuItems = restaurant.getMenuItems();

		do {
			System.out.println("\n==============================");
			System.out.println("=== [Menu Item] ===");
			System.out.println("Choose an option:");
			System.out.println("[1] View all Menu Item");
			System.out.println("[2] Create Menu Item");
			System.out.println("[3] Update Menu Item");
			System.out.println("[4] Remove Menu Item");
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
			case 1: // 1 View all Menu Item
				menuItemManager.viewAllMenuItem(menuItems);
				break;
			case 2: // 2 Create Menu Item
				MenuItem newMenuItem = null;
				newMenuItem = menuItemManager.createMenuItem(menuItems);
				if (newMenuItem != null) {
					menuItems.add(newMenuItem);
					restaurant.setMenuItems(menuItems);
				}
				break;
			case 3: // 3 Update Menu Item
				ArrayList<MenuItem> updateMenuItems = null;
				updateMenuItems = menuItemManager.updateMenuItem(menuItems);
				if (updateMenuItems != null) {
					restaurant.setMenuItems(updateMenuItems);
				}
				break;
			case 4: // 4 Delete Menu Item
				ArrayList<MenuItem> delMenuItems = null;
				delMenuItems = menuItemManager.deleteMenuItem(menuItems);
				if (delMenuItems != null) {
					restaurant.setMenuItems(delMenuItems);
				}
				break;
			default:
				System.out.println("No such option.");
			}

		} while (option != 0);
	}
}
