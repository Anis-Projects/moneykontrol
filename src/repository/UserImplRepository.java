package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
import util.DatabaseUtil;

public class UserImplRepository implements UserRepository{




	@Override
	public void addUser(String username, String password) {
		var sqlCommand = "INSERT INTO user (`username`,`password`) VALUES (?,?)";

		try (Connection connection = DatabaseUtil.connectToDb();
				PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

			statement.setString(1, username);
			statement.setString(2, password);

			var rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Successfully created user.");
			} else {
				System.out.println("Failed to add expense.");
			}

		} catch (SQLException e) {
			System.out.print("Please try again later...");
			e.printStackTrace();
		}


	}

	@Override
	public User findUserByUsername(String username) {
		var sqlCommand = "SELECT * FROM user WHERE username = ? AND deletedAt IS NULL";

		try (Connection connection = DatabaseUtil.connectToDb();
				PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

			statement.setString(1, username);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return new User(
							resultSet.getInt("id"),
							resultSet.getString("username"),
							resultSet.getString("password")
					);
				}
			}
		} catch (SQLException e) {
			System.out.print("Please try again later...");
			e.printStackTrace();
		}

		return null;
	}
}
