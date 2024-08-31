package pages;

import java.util.Scanner;

import service.UserService;
import util.AuthenticationResult;
import util.LoadingAnimation;

public class LoginPage {
	private final Scanner scanner;
	private final UserService userService;

	public LoginPage(UserService userService, Scanner scanner) {
		this.scanner = scanner;
		this.userService = userService;
	}

	public AuthenticationResult display() {
		System.out.println("\n============================");
		System.out.println("      MONEY KONTROL LOGIN      ");
		System.out.println("============================");
		System.out.println("Enter your username:");
		var username = scanner.nextLine();
		System.out.println("Enter your password:");
		var password = scanner.nextLine();
		LoadingAnimation.showLoading("Authenticating",2);

		return authenticateUser(username, password);
	}

	public AuthenticationResult authenticateUser(String username, String password) {
		var user = userService.findUserByUsername(username);
		if (user == null) {
			System.out.println("User not found");
			return new AuthenticationResult(false, -1);
		} else {
			if(password.equals(user.getPassword())) {
				System.out.println("Successfully Login! Hello: " + username);
				return new AuthenticationResult(true, user.getId());
			} else {
				System.out.println("Invalid Username/Password!");
				return new AuthenticationResult(false, -1);
			}
		}
	}



}
