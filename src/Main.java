import java.util.Scanner;

import pages.ExpensePage;
import pages.IncomePage;
import pages.IndexPage;
import pages.LoginPage;
import pages.MenuPage;
import repository.ExpenseImplRepository;
import repository.IncomeImplRepository;
import repository.UserImplRepository;
import service.ExpenseService;
import service.IncomeService;
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
		var scanner = new Scanner(System.in);

		var indexPage = new IndexPage(scanner);
		var loginPage = new LoginPage(userService, scanner);
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
									System.out.println("Manage Savings");
									break;
								case 4:
									System.out.println("Generate Monthly Report");
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
					System.out.println("You're in register");
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
