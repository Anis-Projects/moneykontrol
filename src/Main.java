import java.util.Scanner;

import pages.ExpensePage;
import pages.GenerateReportPage;
import pages.IncomePage;
import pages.IndexPage;
import pages.LoginPage;
import pages.MenuPage;
import pages.SavingsPage;
import pages.SignUpPage;
import repository.ExpenseImplRepository;
import repository.IncomeImplRepository;
import repository.SavingsImplRepository;
import repository.UserImplRepository;
import service.ExpenseService;
import service.IncomeService;
import service.SavingsService;
import service.UserService;
import util.InputValidation;

public class Main {
	public static void main(String[] args) {
		var userRepository = new UserImplRepository();
		var userService = new UserService(userRepository);
		var incomeRepository = new IncomeImplRepository();
		var incomeService = new IncomeService(incomeRepository);
		var expenseRepository = new ExpenseImplRepository();
		var expenseService = new ExpenseService(expenseRepository);
		var savingsRepository = new SavingsImplRepository();
		var savingsService = new SavingsService(savingsRepository);
		var scanner = new Scanner(System.in);

		var indexPage = new IndexPage(scanner);
		var loginPage = new LoginPage(userService, scanner);
		var signUpPage = new SignUpPage(userService, scanner);
		var inputValidation = new InputValidation(scanner);

		boolean isRunning = true;
		while(isRunning) {
			var inputOption = indexPage.display();

			switch (inputOption) {
				case 1:
					var authenticationResult = loginPage.display();
					if (authenticationResult.isAuthenticated()) {
						var userId = authenticationResult.getUserId();
						var menuPage = new MenuPage(scanner, userId);
						var incomePage = new IncomePage(incomeService, scanner, userId, inputValidation);
						var expensePage = new ExpensePage(expenseService, scanner, userId, inputValidation);
						var savingsPage = new SavingsPage(savingsService, scanner, userId, inputValidation);
						var generateReportPage = new GenerateReportPage(expenseService, savingsService, incomeService,
								scanner, userId, inputValidation);
						boolean exitMenu = false;

						while (!exitMenu) {
							var menuChoice = menuPage.display();
							switch (menuChoice) {
								case 1:
									boolean exitIncomePage = false;
									while (!exitIncomePage) {
										exitIncomePage = incomePage.display();
									}
									break;
								case 2:
									boolean exitExpensePage = false;
									while (!exitExpensePage) {
										exitExpensePage = expensePage.display();
									}
									break;
								case 3:
									boolean exitSavingsPage = false;
									while (!exitSavingsPage) {
										exitSavingsPage = savingsPage.display();
									}
									break;
								case 4:
									generateReportPage.display();
									break;
								case 5:
									exitMenu = true;
									break;
								default:
									System.out.println("Invalid choice. Please try again.");
									break;
							}
						}
					}
					break;
				case 2:
					signUpPage.display();
					break;
				case 5:
					isRunning = false;
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
		}
	}
}
