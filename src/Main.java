import java.util.Scanner;

import pages.Index;
import pages.Login;
import repository.UserImplRepository;
import service.UserService;

public class Main {
	public static void main(String[] args) {
		var userRepository = new UserImplRepository();
		var userService = new UserService(userRepository);
		var scanner = new Scanner(System.in);

		var indexPage = new Index(scanner);
		var inputOption = indexPage.display();

		var loginPage = new Login(userService, scanner);

		switch (inputOption) {
			case 1:
				loginPage.display();
				break;
			case 2:
				System.out.println("You're in register");
				break;
			default:
				System.out.println("Invalid choice");
				break;
		}
	}

}
