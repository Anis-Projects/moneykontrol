package pages;

import java.util.Scanner;

public class Index {
	private final Scanner scanner;

	public Index(Scanner scanner) {
		this.scanner = scanner;
	}

	public int display() {
		System.out.println("\n============================");
		System.out.println("      MONEY KONTROL      ");
		System.out.println("============================");
		System.out.println("1. Login");
		System.out.println("2. Sign Up");
		System.out.println("5. Exit");
		System.out.println("============================");
		System.out.print("Enter your choice: ");
		var option = scanner.nextInt();
		scanner.nextLine();
		return option;
	}
}
