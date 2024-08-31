package entity;

import java.time.LocalDateTime;

public class Income {
	private int id;
	private int userId;
	private double amount;
	private String source;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;

	public Income() {

	}

	public Income(int id, int userId, double amount, String source,
			LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime deletedAt) {
		this.id = id;
		this.userId = userId;
		this.amount = amount;
		this.source = source;
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

	public String getSource() {
		return source;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
