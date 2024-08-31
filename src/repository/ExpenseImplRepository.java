package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entity.Expense;
import entity.ExpenseType;
import util.DatabaseUtil;

public class ExpenseImplRepository implements ExpenseRepository{

	@Override
	public void addExpense(double amount, int userId, ExpenseType type) {
		var sqlCommand = "INSERT INTO expense (`amount`,`user_id`,`type`) VALUES (?,?,?)";

		try (Connection connection = DatabaseUtil.connectToDb();
				PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

			statement.setDouble(1, amount);
			statement.setInt(2, userId);
			statement.setString(3, type.toString());

			var rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Expense added successfully.");
			} else {
				System.out.println("Failed to add expense.");
			}
		} catch (SQLException e) {
			System.out.println("An error occurred while adding expense.");
		}
	}

	@Override
	public List<Expense> getAllExpenseByUserId(int userId) {
		var sqlCommand = "SELECT * FROM expense "
				+ "WHERE user_id = ? AND deletedAt IS NULL "
				+ "ORDER BY createdAt DESC";
		List<Expense> expenseList = new ArrayList<>();

		try (Connection connection = DatabaseUtil.connectToDb();
				PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

			statement.setInt(1, userId);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					var expense = new Expense(
							resultSet.getInt("id"),
							resultSet.getInt("user_id"),
							resultSet.getDouble("amount"),
							ExpenseType.fromString(resultSet.getString("type")),
							resultSet.getTimestamp("createdAt").toLocalDateTime(),
							resultSet.getTimestamp("updatedAt").toLocalDateTime(),
							resultSet.getTimestamp("deletedAt") != null ?
									resultSet.getTimestamp("deletedAt").toLocalDateTime() : null
					);
					expenseList.add(expense);
				}
			}
		} catch (SQLException e) {
			System.out.print("Please try again later...");
			e.printStackTrace();
		}

		return expenseList;
	}

	@Override
	public void updateExpense(int id,double amount, ExpenseType type) {
		var sqlCommand = "UPDATE expense SET amount=?, type=? WHERE id=? ";

		try (Connection connection = DatabaseUtil.connectToDb();
				PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

			statement.setDouble(1, amount);
			statement.setString(2, type.toString());
			statement.setInt(3, id);

			var rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Expense updated successfully.");
			} else {
				System.out.println("Failed to updated expense.");
			}
		} catch (SQLException e) {
			System.out.println("An error occurred while updating expense.");
		}
	}

	@Override
	public boolean existsById(int id) {
		var sqlCommand = "SELECT COUNT(*) FROM expense WHERE id = ? AND deletedAt IS NULL";
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
	public void deleteExpense(int id) {
		var sqlCommand = "UPDATE expense SET  deletedAt=? WHERE id=? ";

		try (Connection connection = DatabaseUtil.connectToDb();
				PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

			statement.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
			statement.setInt(2, id);

			var rowsAffected = statement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Expense deleted successfully.");
			} else {
				System.out.println("Failed to delete expense.");
			}
		} catch (SQLException e) {
			System.out.println("An error occurred while deleting expense.");
		}
	}

	@Override
	public List<Expense> getAllExpenseByUserIdThisMonth(int userId) {
		var sqlCommand = "SELECT * FROM expense " +
					"WHERE user_id = ? " +
					"AND YEAR(createdAt) = YEAR(CURDATE()) " +
					"AND MONTH(createdAt) = MONTH(CURDATE())";
		List<Expense> expenseList = new ArrayList<>();

		try (Connection connection = DatabaseUtil.connectToDb();
				PreparedStatement statement = connection.prepareStatement(sqlCommand)) {

			statement.setInt(1, userId);

			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					var expense = new Expense(
							resultSet.getInt("id"),
							resultSet.getInt("user_id"),
							resultSet.getDouble("amount"),
							ExpenseType.fromString(resultSet.getString("type")),
							resultSet.getTimestamp("createdAt").toLocalDateTime(),
							resultSet.getTimestamp("updatedAt").toLocalDateTime(),
							resultSet.getTimestamp("deletedAt") != null ?
									resultSet.getTimestamp("deletedAt").toLocalDateTime() : null
					);
					expenseList.add(expense);
				}
			}
		} catch (SQLException e) {
			System.out.print("Please try again later...");
			e.printStackTrace();
		}

		return expenseList;
	}
}
