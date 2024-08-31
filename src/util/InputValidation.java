package util;

import java.util.Scanner;

public class InputValidation {
	private final Scanner scanner;

	public InputValidation(Scanner scanner) {
		this.scanner = scanner;
	}

	public double validateInputAmount() {
		double amount;
		while (true) {
			System.out.print("Enter your amount: ");
			if (scanner.hasNextDouble()) {
				amount = scanner.nextDouble();
				scanner.nextLine();
				if (amount >= 0) {
					break;
				} else {
					System.out.println("Amount cannot be negative. Please try again.");
				}
			} else {
				System.out.println("Invalid input. Please enter a valid number.");
				scanner.nextLine();
			}
		}
		return amount;
	}

	public String validateInputSource() {
		String source = "";
		while (true) {
			System.out.print("Enter your source: ");
			source = scanner.nextLine();
			if (!source.trim().isEmpty()) {
				break;
			} else {
				System.out.println("Source cannot be empty. Please try again.");
			}
		}
		return source;
	}


}
