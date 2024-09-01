package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;

import entity.ExpenseType;

public class ExportCSVUtil {
	private static final String HEADER = "Expense Type,Expected,Actual,Difference\n";


	public static void exportToCSV(Map<ExpenseType, Double> expectedExpenses,
			Map<ExpenseType, Double> actualExpenses, double totalExpectedExpenses,
			double totalActualExpenses, double totalExpensesDifference, double expectedSavings,
			double actualSavings, double totalIncome) {
		var fileName = generateFilename();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write(HEADER);

			for (ExpenseType type : ExpenseType.values()) {
				double expected = expectedExpenses.getOrDefault(type, 0.0);
				double actual = actualExpenses.getOrDefault(type, 0.0);
				double difference = expected - actual;
				writer.write(String.format("%s,%.2f,%.2f,%.2f\n", type.name(), expected, actual, difference));
			}
			writer.write(String.format("TOTAL,%.2f,%.2f,%.2f\n", totalExpectedExpenses, totalActualExpenses, totalExpensesDifference));
			writer.write(String.format("Total Income:,%.2f\n", totalIncome));
			writer.write(String.format("Savings:,%.2f,%.2f\n", expectedSavings, actualSavings));

			System.out.println("Successfully exported to:" + fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String generateFilename() {
		LocalDate now = LocalDate.now();
		String month = now.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH).toLowerCase();
		int year = now.getYear();
		return String.format("budget_report_%s_%d.csv", month, year);
	}
}
