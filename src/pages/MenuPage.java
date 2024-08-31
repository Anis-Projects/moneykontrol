package pages;

import java.util.Scanner;

public class MenuPage {
	private final Scanner scanner;
	private final int userId;

	public MenuPage(Scanner scanner, int userId) {
		this.scanner = scanner;
		this.userId = userId;
	}

	public int display() {
		System.out.println("\n============================");
		System.out.println("      MONEY KONTROL MENU      ");
		System.out.println("============================");
		System.out.println("Please choose an option:");
		System.out.println("1. Manage Income");
		System.out.println("2. Manage Expenses");
		System.out.println("3. Manage Savings");
		System.out.println("4. Generate Monthly Report");
		System.out.println("5. Exit");
		System.out.println("============================");
		System.out.print("Enter your choice: ");
		var choice = scanner.nextInt();

		return choice;
	}



}
