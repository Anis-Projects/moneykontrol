package pages;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import entity.Savings;
import service.SavingsService;
import util.InputValidation;
import util.LoadingAnimation;

public class SavingsPage {
	private final SavingsService savingsService;
	private final Scanner scanner;
	private final int userId;
	private final InputValidation inputValidation;

	public SavingsPage(SavingsService savingsService, Scanner scanner, int userId, InputValidation inputValidation) {
		this.savingsService = savingsService;
		this.scanner = scanner;
		this.userId = userId;
		this.inputValidation = inputValidation;
	}

	public boolean display() {
		boolean exitSavingsPage = false;

		while(!exitSavingsPage) {
			listSavings();

			System.out.println("\nPlease choose an option:");
			System.out.println("1. Add new record");
			System.out.println("2. Update a record");
			System.out.println("3. Delete a record");
			System.out.println("5. Exit");
			System.out.println("============================");
			System.out.print("Enter your choice: ");
			var choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
				case 1:
					createSavings();
					break;
				case 2:
					updateSavings();
					break;
				case 3:
					deleteSavings();
					break;
				case 5:
					exitSavingsPage = true;
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
		}
		return exitSavingsPage;
	}


	private void listSavings() {
		List<Savings> savingsList = savingsService.findAllSavingsByUserId(userId);
		System.out.println("\n============================");
		System.out.println("      MANAGE SAVINGS      ");
		System.out.println("============================");

		System.out.printf("\n%-10s%-20s %-30s %-20s%n", "ID", "AMOUNT", "DESCRIPTION", "CREATED AT");
		System.out.println("-----------------------------------------------------------------------------");

		var formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

		for (var savings : savingsList) {
			var formattedDate = savings.getCreatedAt().format(formatter);
			System.out.printf("%-10d %-20.2f %-30s %-20s%n",
					savings.getId() ,savings.getAmount(), savings.getDescription().toString(), formattedDate);
		}
	}

	private void createSavings() {
		System.out.println("\n============================");
		System.out.println("      ADD NEW SAVINGS      ");
		System.out.println("============================");
		var amount = inputValidation.validateInputAmount();
		var updateDescription = inputValidation.validateInputDescription();

		LoadingAnimation.showLoading("Creating savings record", 2);
		savingsService.createSavings(amount, userId, updateDescription);
		LoadingAnimation.pause();
	}

	private void updateSavings() {
		System.out.println("\n============================");
		System.out.println("     UPDATE SAVINGS      ");
		System.out.println("============================");
		System.out.print("Enter the ID of the savings to update: ");
		var savingsId = scanner.nextInt();
		scanner.nextLine();

		var updateAmount = inputValidation.validateInputAmount();
		var updateDescription = inputValidation.validateInputDescription();

		LoadingAnimation.showLoading("Updating savings record", 2);
		savingsService.updateSavings(savingsId, updateAmount, updateDescription);
		LoadingAnimation.pause();
	}

	private void deleteSavings() {
		System.out.println("\n============================");
		System.out.println("     DELETE SAVINGS      ");
		System.out.println("============================");
		System.out.print("Enter the ID of the savings to delete: ");
		var savingsId = scanner.nextInt();

		var isExist = savingsService.existsById(savingsId);

		if(isExist) {
			System.out.println("Are you sure you want to delete this record");
			System.out.println("1. Yes");
			System.out.println("2. No");
			System.out.println("============================");
			System.out.print("Enter your choice: ");
			var choice = scanner.nextInt();
			switch (choice) {
				case 1:
					LoadingAnimation.showLoading("Deleting savings record", 2);
					savingsService.deleteSavings(savingsId);
					break;
				case 2:
					System.out.println("Deletion canceled.");
					break;
				default:
					System.out.println("Invalid choice. Please try again");
					deleteSavings();
			}
		}else {
			System.out.println("Savings ID not found. Please try again.");
		}
		LoadingAnimation.pause();
		scanner.nextLine();
	}
}
