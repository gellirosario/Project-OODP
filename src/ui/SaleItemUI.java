package ui;

import java.util.Scanner;

import classes.Restaurant;

public class SaleItemUI {

	public static void showSaleItemUI(Restaurant restaurant) {
		Scanner sc = new Scanner(System.in);
		int option = 0;

		do {
			System.out.println("\n==============================");
			System.out.println("=== [Sale Item] ===");
			System.out.println("Choose an option:");
			System.out.println("[1] Menu Item");
			System.out.println("[2] Set");
			System.out.println("[0] Return to Main Menu");
			System.out.println("==============================");

			while (!sc.hasNextInt()) { // check if user entered int
				sc.next(); // move buffer
				System.out.println("Invalid Input, please enter valid input.");
			}

			// user entered int
			option = sc.nextInt();

			switch (option) {
			case 0:
				System.out.println("Returning to Main Menu...");
				break;
			case 1:
				MenuItemUI.showMenuItemUI(restaurant);
				break;
			case 2:
				SetUI.showSetUI(restaurant);
				break;
			default:
				System.out.println("No such option.");
			}
		} while (option != 0);
	}
}
