package service;

import java.util.List;

import entity.Expense;
import entity.ExpenseType;
import repository.ExpenseImplRepository;

public class ExpenseService {
	private final ExpenseImplRepository incomeRepository;

	public ExpenseService(ExpenseImplRepository incomeRepository) {
		this.incomeRepository = incomeRepository;
	}

	public void createExpense(double amount, int userId, ExpenseType type) {
		incomeRepository.addExpense(amount, userId, type);
	}

	public List<Expense> findAllExpenseByUserId(int userId) {
		return incomeRepository.getAllExpenseByUserId(userId);
	}

	public void updateExpense(int incomeId, double amount,ExpenseType type) {
		if (incomeRepository.existsById(incomeId)) {
			incomeRepository.updateExpense(incomeId, amount, type);
		}
	}

	public boolean existsById(int id) {
		return incomeRepository.existsById(id);
	}

	public void deleteExpense(int incomeId) {
		incomeRepository.deleteExpense(incomeId);
	}
}
