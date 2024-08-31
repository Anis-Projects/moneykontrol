package repository;

import java.util.List;

import entity.Savings;

public interface SavingsRepository {
	void addSavings(double amount, int userId, String description);
	List<Savings> getAllSavingsByUserId(int userId);
	void updateSavings(int id, double amount, String description);
	boolean existsById(int id);
	void deleteSavings(int id);
	Double calculateUserTotalSavings(int userId);
}
