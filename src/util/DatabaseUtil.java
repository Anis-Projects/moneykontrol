package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

	public static Connection connectToDb() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/budget_db";
		String user = "budget_user";
		String password = "budget_pass";

		//		System.out.println("Connected to the database!");

		return DriverManager.getConnection(url, user, password);
	}
}
