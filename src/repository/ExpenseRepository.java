package repository;

import java.util.List;

import entity.Expense;
import entity.ExpenseType;

public interface ExpenseRepository {
	void addExpense(double amount, int userId, ExpenseType type);
	List<Expense> getAllExpenseByUserId(int userId);
	void updateExpense(int id, double amount, ExpenseType type);
	boolean existsById(int id);
	void deleteExpense(int id);
	List<Expense> getAllExpenseByUserIdThisMonth(int userId);
}
