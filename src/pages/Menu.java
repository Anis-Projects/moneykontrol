package pages;

import java.util.Scanner;

import service.UserService;

public class Menu {
	private final Scanner scanner;
	private final UserService userService;

	public Menu(UserService userService, Scanner scanner) {
		this.scanner = scanner;
		this.userService = userService;
	}

	public boolean display() {
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

		switch (choice) {
			case 1:
				System.out.println("Manage Income");
				break;
			case 2:
				System.out.println("Manage Expenses");
				break;
			case 3:
				System.out.println("Manage Savings");
				break;
			case 4:
				System.out.println("Generate Monthly Report");
				break;
			case 5:
				return true;
			default:
				System.out.println("Invalid choice");
				break;
		}
		return false;
	}



}
