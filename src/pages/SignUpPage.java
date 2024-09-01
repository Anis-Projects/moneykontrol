package pages;

import java.util.Scanner;

import service.UserService;
import util.LoadingAnimation;

public class SignUpPage {
	private final Scanner scanner;
	private final UserService userService;

	public SignUpPage(UserService userService, Scanner scanner) {
		this.userService = userService;
		this.scanner = scanner;
	}

	public void display() {
		System.out.println("\n============================");
		System.out.println("      MONEY KONTROL SIGN UP   ");
		System.out.println("============================");
		System.out.println("Enter your username:");
		var username = scanner.nextLine();
		System.out.println("Enter your password:");
		var password = scanner.nextLine();

		LoadingAnimation.showLoading("Registering",2);
		userService.addUser(username, password);
		LoadingAnimation.pause();
	}
}
