package repository;

import java.util.List;

import entity.Income;

public interface IncomeRepository {
	void addIncome(double amount, int userId, String source);
	List<Income> getAllIncomeByUserId(int userId);
	void updateIncome(int id, double amount, String source);
	boolean existsById(int id);
	void deleteIncome(int id);
	Double calculateUserTotalIncome(int userId);
}
