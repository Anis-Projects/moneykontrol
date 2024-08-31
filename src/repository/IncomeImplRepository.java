package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entity.Income;
import entity.User;
import util.DatabaseUtil;

public class IncomeImplRepository implements IncomeRepository{

	@Override
	public void addIncome(double amount, int userId, String source) {
		var sqlCommand = "INSERT INTO income (`amount`,`user_id`,`source`) VALUES (?,?,?)";

		try (Connection connection = DatabaseUtil.connectToDb();
				PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

			statement.setDouble(1, amount);
			statement.setInt(2, userId);
			statement.setString(3, source);

			var rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Income added successfully.");
			} else {
				System.out.println("Failed to add income.");
			}

		} catch (SQLException e) {
			System.out.println("An error occurred while adding income.");
		}
	}

	@Override
	public List<Income> getAllIncomeByUserId(int userId) {
		var sqlCommand = "SELECT * FROM income WHERE user_id = ? AND deletedAt IS NULL";
		List<Income> incomeList = new ArrayList<>();

		try (Connection connection = DatabaseUtil.connectToDb();
				PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

			statement.setInt(1, userId);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					var income = new Income(
							resultSet.getInt("id"),
							resultSet.getInt("user_id"),
							resultSet.getDouble("amount"),
							resultSet.getString("source"),
							resultSet.getTimestamp("createdAt").toLocalDateTime(),
							resultSet.getTimestamp("updatedAt").toLocalDateTime(),
							resultSet.getTimestamp("deletedAt") != null ?
									resultSet.getTimestamp("deletedAt").toLocalDateTime() : null
					);
					incomeList.add(income);
				}
			}
		} catch (SQLException e) {
			System.out.print("Please try again later...");
			e.printStackTrace();
		}

		return incomeList;
	}

	@Override
	public void updateIncome(int id,double amount, String source) {
		var sqlCommand = "UPDATE income SET amount=?, source=? WHERE id=? ";

		try (Connection connection = DatabaseUtil.connectToDb();
				PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

			statement.setDouble(1, amount);
			statement.setString(2, source);
			statement.setInt(3, id);

			var rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Savings updated successfully.");
			} else {
				System.out.println("Failed to updated savings.");
			}
		} catch (SQLException e) {
			System.out.println("An error occurred while adding income.");
		}
	}

	@Override
	public boolean existsById(int id) {
		var sqlCommand = "SELECT COUNT(*) FROM income WHERE id = ? AND deletedAt IS NULL";
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
	public void deleteIncome(int id) {
		var sqlCommand = "UPDATE income SET  deletedAt=? WHERE id=? ";

		try (Connection connection = DatabaseUtil.connectToDb();
				PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

			statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			statement.setInt(2, id);

			var rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Income deleted successfully.");
			} else {
				System.out.println("Failed to delete income.");
			}
		} catch (SQLException e) {
			System.out.println("An error occurred while deleting income.");
		}
	}

	@Override
	public Double calculateUserTotalIncome(int userId) {
		var sqlCommand = "SELECT SUM(amount) FROM income " +
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
