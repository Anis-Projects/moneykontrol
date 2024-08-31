package util;

import java.util.Scanner;

public class LoadingAnimation {
	public static void showLoading(String title, int duration) {
		final String[] loading = {".", "..", "...", "...."};
		var endTime = System.currentTimeMillis() + duration * 1000;
		var index = 0;

		while (System.currentTimeMillis() < endTime) {
			System.out.print("\r" + title + loading[index]);
			index = (index + 1) % loading.length;

			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}

		System.out.println("\r" + title);
	}

	public static void pause() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Press Enter to continue...");
		scanner.nextLine();
	}
}
