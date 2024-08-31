package pages;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import entity.Expense;
import service.ExpenseService;
import util.InputValidation;
import util.LoadingAnimation;

public class ExpensePage {
	private final ExpenseService expenseService;
	private final Scanner scanner;
	private final int userId;
	private final InputValidation inputValidation;

	public ExpensePage(ExpenseService expenseService, Scanner scanner, int userId, InputValidation inputValidation) {
		this.expenseService = expenseService;
		this.scanner = scanner;
		this.userId = userId;
		this.inputValidation = inputValidation;
	}

	public boolean display() {
		boolean exitExpensePage = false;

		while(!exitExpensePage) {
			listExpense();

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
					createExpense();
					break;
				case 2:
					updateExpense();
					break;
				case 3:
					deleteExpense();
					break;
				case 5:
					exitExpensePage = true;
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}

		}
		return false;
	}


	private void listExpense() {
		List<Expense> expenseList = expenseService.findAllExpenseByUserId(userId);
		System.out.println("\n============================");
		System.out.println("      MANAGE EXPENSE      ");
		System.out.println("============================");

		System.out.printf("\n%-10s%-20s %-30s %-20s%n", "ID", "AMOUNT", "TYPE", "CREATED AT");
		System.out.println("-----------------------------------------------------------------------------");

		var formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

		for (var expense : expenseList) {
			var formattedDate = expense.getCreatedAt().format(formatter);
			System.out.printf("%-10d %-20.2f %-30s %-20s%n",
					expense.getId() ,expense.getAmount(), expense.getType().toString(), formattedDate);
		}
	}

	private void createExpense() {
		System.out.println("\n============================");
		System.out.println("      ADD NEW EXPENSE      ");
		System.out.println("============================");
		var amount = inputValidation.validateInputAmount();
		var type = inputValidation.validateInputExpenseType();

		LoadingAnimation.showLoading("Creating expense record", 2);
		expenseService.createExpense(amount, userId, type);
		LoadingAnimation.pause();
	}

	private void updateExpense() {
		System.out.println("\n============================");
		System.out.println("     UPDATE EXPENSE      ");
		System.out.println("============================");
		System.out.print("Enter the ID of the expense to update: ");
		var expenseId = scanner.nextInt();
		scanner.nextLine();

		var updateAmount = inputValidation.validateInputAmount();
		var updateType = inputValidation.validateInputExpenseType();

		LoadingAnimation.showLoading("Updating expense record", 2);
		expenseService.updateExpense(expenseId, updateAmount, updateType);
		LoadingAnimation.pause();
	}

	private void deleteExpense() {
		System.out.println("\n============================");
		System.out.println("     DELETE EXPENSE      ");
		System.out.println("============================");
		System.out.print("Enter the ID of the expense to delete: ");
		var expenseId = scanner.nextInt();

		var isExist = expenseService.existsById(expenseId);

		if(isExist) {
			System.out.println("Are you sure you want to delete this record");
			System.out.println("1. Yes");
			System.out.println("2. No");
			System.out.println("============================");
			System.out.print("Enter your choice: ");
			var choice = scanner.nextInt();
			switch (choice) {
				case 1:
					LoadingAnimation.showLoading("Deleting expense record", 2);
					expenseService.deleteExpense(expenseId);
					break;
				case 2:
					System.out.println("Deletion canceled.");
					break;
				default:
					System.out.println("Invalid choice. Please try again");
					deleteExpense();
			}
		}else {
			System.out.println("Expense ID not found. Please try again.");
		}
		LoadingAnimation.pause();
		scanner.nextLine();
	}
}
