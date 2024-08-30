import java.util.Scanner;

import pages.Index;
import pages.Login;
import pages.Menu;
import repository.UserImplRepository;
import service.UserService;

public class Main {
	public static void main(String[] args) {
		var userRepository = new UserImplRepository();
		var userService = new UserService(userRepository);
		var scanner = new Scanner(System.in);

		var indexPage = new Index(scanner);


		var loginPage = new Login(userService, scanner);
		var menuPage = new Menu(userService, scanner);

		boolean isRunning = true;
		while(isRunning) {
			var inputOption = indexPage.display();

			switch (inputOption) {
				case 1:
					var isUserAuthenticated = loginPage.display();
					if (isUserAuthenticated) {
						boolean exitMenu = false;
						while (!exitMenu) {
							exitMenu = menuPage.display();
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
