package service;

import java.util.List;

import entity.Income;
import entity.User;
import repository.IncomeImplRepository;
import repository.UserImplRepository;

public class IncomeService {
	private final IncomeImplRepository incomeRepository;

	public IncomeService(IncomeImplRepository incomeRepository) {
		this.incomeRepository = incomeRepository;
	}

	public void createIncome(double amount, int userId, String source) {
		incomeRepository.addIncome(amount, userId, source);
	}

	public List<Income> findAllIncomeByUserId(int userId) {
		return incomeRepository.getAllIncomeByUserId(userId);
	}

	public String updateIncome(int incomeId, double amount, String source) {
		if (incomeRepository.existsById(incomeId)) {
			incomeRepository.updateIncome(incomeId, amount, source);
			return "Income updated successfully.";
		} else {
			return "Error: ID does not exist.";
		}
	}

	public boolean existsById(int id) {
		return incomeRepository.existsById(id);
	}

	public void deleteIncome(int incomeId) {
		incomeRepository.deleteIncome(incomeId);
	}
}
