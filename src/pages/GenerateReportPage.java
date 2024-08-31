package pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import entity.Expense;
import entity.ExpenseType;
import service.ExpenseService;
import service.IncomeService;
import service.SavingsService;
import util.InputValidation;
import util.LoadingAnimation;

public class GenerateReportPage {
	private final Scanner scanner;
	private final ExpenseService expenseService;
	private final SavingsService savingsService;
	private final IncomeService incomeService;
	private final int userId;
	private final InputValidation inputValidation;

	public GenerateReportPage(ExpenseService expenseService, SavingsService savingsService,
			IncomeService incomeService,Scanner scanner, int userId, InputValidation inputValidation) {
		this.expenseService = expenseService;
		this.savingsService = savingsService;
		this.incomeService = incomeService;
		this.scanner = scanner;
		this.userId = userId;
		this.inputValidation = inputValidation;
	}

	public void display() {
		System.out.println("\n============================");
		System.out.println("      GENERATE MONTHLY REPORT      ");
		System.out.println("============================");

		Map<ExpenseType, Double> expectedExpenses = new HashMap<>();
		for (ExpenseType expenseType : ExpenseType.values()) {
			System.out.print("Enter expected total amount for " + expenseType.name().toLowerCase() + ": ");
			double expectedAmount = scanner.nextDouble();
			expectedExpenses.put(expenseType, expectedAmount);
		}

		var expectedSavings = savingsService.getTotalExpectedSavings(userId);
		System.out.print("Your expected saving goals this month is: " + expectedSavings);

		Map<ExpenseType, Double> actualExpenses = getActualExpensesByUserId(userId);

		System.out.println("\n============================");
		System.out.println("      MONTHLY REPORT      ");
		System.out.println("============================");

		System.out.printf("%-20s%-20s%-20s%-20s%n", "Expense Type", "Expected", "Actual", "Difference");
		System.out.println("-----------------------------------------------------------------------");

		double totalExpectedExpenses = 0.0;
		double totalActualExpenses = 0.0;
		double totalExpensesDifference = 0.0;
		for (ExpenseType type : ExpenseType.values()) {
			double expected = expectedExpenses.getOrDefault(type, 0.0);
			double actual = actualExpenses.getOrDefault(type, 0.0);
			double difference = expected - actual;

			totalExpectedExpenses += expected;
			totalActualExpenses += actual;
			totalExpensesDifference += difference;

			System.out.printf("%-20s%-20.2f%-20.2f%-20.2f%n", type.name(), expected, actual, difference);
		}

		System.out.println("-----------------------------------------------------------------------");
		System.out.printf("%-20s%-20.2f%-20.2f%-20.2f%n", "TOTAL", totalExpectedExpenses, totalActualExpenses, totalExpensesDifference);

		var actualSavings = calculateActualSavings(totalActualExpenses);
		System.out.printf("%-20s%-20.2f%-20.2f%-20.2f%n", "Savings:", expectedSavings, actualSavings);

		LoadingAnimation.pause();
		scanner.nextLine();
	}

	private Map<ExpenseType, Double> getActualExpensesByUserId(int userId) {
		Map<ExpenseType, Double> actualExpenses = new HashMap<>();
		List<Expense> expenseList = expenseService.findAllExpenseByUserIdThisMonth(userId);

		for (Expense expense : expenseList) {
			double currentAmount = actualExpenses.getOrDefault(expense.getType(), 0.0);
			actualExpenses.put(expense.getType(), currentAmount + expense.getAmount());
		}
		return actualExpenses;
	}

	private Double calculateActualSavings(double totalExpenses) {
		var totalIncome = incomeService.getTotalIncomeThisMonth(userId);
		System.out.println();
		System.out.printf("Total Income:%n%.2f\n", totalIncome);
		return totalIncome - totalExpenses;
	}


}
