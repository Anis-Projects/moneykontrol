package pages;

import java.util.Scanner;

import service.UserService;

public class Login {
	private final Scanner scanner;
	private final UserService userService;

	public Login(UserService userService, Scanner scanner) {
		this.scanner = scanner;
		this.userService = userService;
	}

	public boolean display() {
		System.out.println("\n============================");
		System.out.println("      MONEY KONTROL LOGIN      ");
		System.out.println("============================");
		System.out.println("Enter your username:");
		var username = scanner.nextLine();
		System.out.println("Enter your password:");
		var password = scanner.nextLine();
		showLoading(3);

		return authenticateUser(username, password);
	}

	public boolean authenticateUser(String username, String password) {
		var user = userService.findUserByUsername(username);
		if (user == null) {
			System.out.println("User not found");
			return false;
		} else {
			if(password.equals(user.getPassword())) {
				System.out.println("Successfully Login! Hello: " + username);
				return true;
			} else {
				System.out.println("Invalid Username/Password!");
				return false;
			}
		}
	}

	private void showLoading(int duration) {
		final String[] loading = {".", "..", "...", "...."};
		var endTime = System.currentTimeMillis() + duration * 1000;
		var index = 0;

		while (System.currentTimeMillis() < endTime) {
			System.out.print("\rAuthenticating" + loading[index]);
			index = (index + 1) % loading.length;

			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		System.out.println("\rAuthenticating    ");
	}

}
