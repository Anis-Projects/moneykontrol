package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entity.Savings;
import util.DatabaseUtil;

public class SavingsImplRepository implements SavingsRepository{

	@Override
	public void addSavings(double amount, int userId, String description) {
		var sqlCommand = "INSERT INTO savings (`amount`,`user_id`,`description`) VALUES (?,?,?)";

		try (Connection connection = DatabaseUtil.connectToDb();
				PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

			statement.setDouble(1, amount);
			statement.setInt(2, userId);
			statement.setString(3, description);

			var rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Savings added successfully.");
			} else {
				System.out.println("Failed to add expense.");
			}
		} catch (SQLException e) {
			System.out.println("An error occurred while adding savings.");
		}
	}

	@Override
	public List<Savings> getAllSavingsByUserId(int userId) {
		var sqlCommand = "SELECT * FROM savings WHERE user_id = ? AND deletedAt IS NULL";
		List<Savings> savingsList = new ArrayList<>();

		try (Connection connection = DatabaseUtil.connectToDb();
				PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

			statement.setInt(1, userId);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					var savings = new Savings(
							resultSet.getInt("id"),
							resultSet.getInt("user_id"),
							resultSet.getDouble("amount"),
							resultSet.getString("description"),
							resultSet.getTimestamp("createdAt").toLocalDateTime(),
							resultSet.getTimestamp("updatedAt").toLocalDateTime(),
							resultSet.getTimestamp("deletedAt") != null ?
									resultSet.getTimestamp("deletedAt").toLocalDateTime() : null
					);
					savingsList.add(savings);
				}
			}
		} catch (SQLException e) {
			System.out.print("Please try again later...");
			e.printStackTrace();
		}

		return savingsList;
	}

	@Override
	public void updateSavings(int id,double amount, String description) {
		var sqlCommand = "UPDATE savings SET amount=?, description=? WHERE id=? ";

		try (Connection connection = DatabaseUtil.connectToDb();
				PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

			statement.setDouble(1, amount);
			statement.setString(2, description);
			statement.setInt(3, id);

			var rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Savings updated successfully.");
			} else {
				System.out.println("Failed to updated savings.");
			}
		} catch (SQLException e) {
			System.out.println("An error occurred while adding savings.");
		}
	}

	@Override
	public boolean existsById(int id) {
		var sqlCommand = "SELECT COUNT(*) FROM savings WHERE id = ? AND deletedAt IS NULL";
		try (Connection connection = DatabaseUtil.connectToDb();
				PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

			statement.setInt(1, id);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					int count = resultSet.getInt(1);
					if (count > 0) {
						return true;
					} else {
						System.out.println("ID doesn't exist.");
						return false;
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("An error occurred while checking existence.");
		}
		return false;
	}

	@Override
	public void deleteSavings(int id) {
		var sqlCommand = "UPDATE savings SET  deletedAt=? WHERE id=? ";

		try (Connection connection = DatabaseUtil.connectToDb();
				PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

			statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			statement.setInt(2, id);

			var rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Savings deleted successfully.");
			} else {
				System.out.println("Failed to delete savings.");
			}
		} catch (SQLException e) {
			System.out.println("An error occurred while deleting savings.");
		}
	}

	@Override
	public Double calculateUserTotalSavings(int userId) {
		var sqlCommand = "SELECT SUM(amount) FROM savings " +
				"WHERE user_id = ? AND deletedAt IS NULL " +
				"AND YEAR(createdAt) = YEAR(CURDATE()) " +
				"AND MONTH(createdAt) = MONTH(CURDATE())";
		double totalAmount = 0.0;

		try (Connection connection = DatabaseUtil.connectToDb();
				PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

			statement.setInt(1, userId);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					totalAmount = resultSet.getDouble(1);
				}
			}
		} catch (SQLException e) {
			System.out.println("An error occurred while checking existence.");
		}
		return totalAmount;
	}
}
