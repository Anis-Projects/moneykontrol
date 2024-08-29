package pages;

import java.util.Scanner;

public class Index {
	private final Scanner scanner;

	public Index(Scanner scanner) {
		this.scanner = scanner;
	}

	public int display() {
		System.out.println("----WELCOME TO MONEY KONTROL----");
		System.out.print("Enter 1 for login and Enter 2 for register:");
		var option = scanner.nextInt();
		scanner.nextLine();
		return option;
	}
}
