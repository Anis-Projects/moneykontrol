package pages;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import entity.Income;
import service.IncomeService;
import util.InputValidation;
import util.LoadingAnimation;

public class IncomePage {
	private final Scanner scanner;
	private final IncomeService incomeService;
	private final int userId;
	private final InputValidation inputValidation;

	public IncomePage(IncomeService incomeService,Scanner scanner, int userId, InputValidation inputValidation) {
		this.incomeService = incomeService;
		this.scanner = scanner;
		this.userId = userId;
		this.inputValidation = inputValidation;
	}

	public boolean display() {
		boolean exitIncomePage = false;

		while(!exitIncomePage) {
			listIncome();

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
					createIncome();
					break;
				case 2:
					updateIncome();
					break;
				case 3:
					deleteIncome();
					break;
				case 5:
					exitIncomePage = true;
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}

		}
		return exitIncomePage;
	}

	private void listIncome() {
		List<Income> incomeList = incomeService.findAllIncomeByUserId(userId);
		System.out.println("\n============================");
		System.out.println("      MANAGE INCOME      ");
		System.out.println("============================");

		System.out.printf("\n%-10s%-20s %-30s %-20s%n", "ID", "AMOUNT OF INCOME", "SOURCE", "CREATED AT");
		System.out.println("-----------------------------------------------------------------------------");

		var formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

		for (var income : incomeList) {
			var formattedDate = income.getCreatedAt().format(formatter);
			System.out.printf("%-10d %-20.2f %-30s %-20s%n",
					income.getId() ,income.getAmount(), income.getSource(), formattedDate);
		}
	}

	private void createIncome() {
		System.out.println("\n============================");
		System.out.println("      ADD NEW INCOME      ");
		System.out.println("============================");
		var amount = inputValidation.validateInputAmount();
		var source = inputValidation.validateInputSource();

		LoadingAnimation.showLoading("Creating income record", 2);
		incomeService.createIncome(amount, userId, source);
		LoadingAnimation.pause();
	}

	private void updateIncome() {
		System.out.println("\n============================");
		System.out.println("     UPDATE INCOME      ");
		System.out.println("============================");
		System.out.print("Enter the ID of the income to update: ");
		var incomeId = scanner.nextInt();
		scanner.nextLine();

		var updateAmount = inputValidation.validateInputAmount();
		var updateSource = inputValidation.validateInputSource();

		var resultMessage = incomeService.updateIncome(incomeId, updateAmount, updateSource);
		System.out.println(resultMessage);
		LoadingAnimation.pause();
	}

	private void deleteIncome() {
		System.out.println("\n============================");
		System.out.println("     DELETE INCOME      ");
		System.out.println("============================");
		System.out.print("Enter the ID of the income to delete: ");
		var incomeId = scanner.nextInt();

		var isExist = incomeService.existsById(incomeId);

		if(isExist) {
			System.out.println("Are you sure you want to delete this record");
			System.out.println("1. Yes");
			System.out.println("2. No");
			System.out.println("============================");
			System.out.print("Enter your choice: ");
			var choice = scanner.nextInt();
			switch (choice) {
				case 1:
					incomeService.deleteIncome(incomeId);
					break;
				case 2:
					System.out.println("Deletion canceled.");
					break;
				default:
					System.out.println("Invalid choice. Please try again");
					deleteIncome();
			}
		}else {
			System.out.println("Income ID not found. Please try again.");
		}
		LoadingAnimation.pause();
		scanner.nextLine();
	}
}
