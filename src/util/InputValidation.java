package util;

import java.util.Scanner;

import entity.ExpenseType;

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

	public ExpenseType validateInputExpenseType() {
		int choice;
		ExpenseType selectedType = null;
		while (true) {
			System.out.println("EXPENSE TYPE LIST");
			for (ExpenseType type : ExpenseType.values()) {
				System.out.println((type.ordinal() + 1) + ". " + type.name());
			}
			System.out.print("Please select an expense type:");
			if (scanner.hasNextInt()) {
				choice = scanner.nextInt();
				scanner.nextLine();

				if (choice >= 1 && choice <= ExpenseType.values().length) {
					selectedType = ExpenseType.values()[choice - 1];
					System.out.println("You selected: " + selectedType.name());
					break;
				} else {
					System.out.println("Invalid choice. Please try again.");
				}
			} else {
				System.out.println("Invalid input. Please enter a number.");
				scanner.nextLine();
			}
		}
		return selectedType;
	}


}
