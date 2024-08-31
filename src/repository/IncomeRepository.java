package repository;

import java.util.List;

import entity.Income;

public interface IncomeRepository {
	public void addIncome(double amount, int userId, String source);
	public List<Income> getAllIncomeByUserId(int userId);
	public void updateIncome(int id, double amount, String source);
	public boolean existsById(int id);
	public void deleteIncome(int id);
}
