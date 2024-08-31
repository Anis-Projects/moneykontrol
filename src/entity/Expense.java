package entity;

import java.time.LocalDateTime;

public class Expense {
	private int id;
	private int userId;
	private double amount;
	private ExpenseType type;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;

	public Expense() {}

	public Expense(int id, int userId, double amount, ExpenseType type,
			LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
		this.id = id;
		this.userId = userId;
		this.amount = amount;
		this.type = type;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public double getAmount() {
		return amount;
	}

	public ExpenseType getType() {
		return type;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setType(ExpenseType type) {
		this.type = type;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}

