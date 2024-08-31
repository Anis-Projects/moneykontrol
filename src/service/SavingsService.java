package service;

import java.util.List;

import entity.Savings;
import repository.SavingsImplRepository;

public class SavingsService {
	private final SavingsImplRepository savingsRepository;

	public SavingsService(SavingsImplRepository savingsRepository) {
		this.savingsRepository = savingsRepository;
	}

	public void createSavings(double amount, int userId, String source) {
		savingsRepository.addSavings(amount, userId, source);
	}

	public List<Savings> findAllSavingsByUserId(int userId) {
		return savingsRepository.getAllSavingsByUserId(userId);
	}

	public void updateSavings(int savingsId, double amount, String description) {
		if (savingsRepository.existsById(savingsId)) {
			savingsRepository.updateSavings(savingsId, amount, description);
		}
	}

	public boolean existsById(int id) {
		return savingsRepository.existsById(id);
	}

	public void deleteSavings(int savingsId) {
		savingsRepository.deleteSavings(savingsId);
	}

	public Double getTotalExpectedSavings(int userId) {
		return savingsRepository.calculateUserTotalSavings(userId);
	}
}
