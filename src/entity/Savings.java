package entity;

public class Savings {
	private int id;
	private int userId;
	private double amount;
	private String description;

	public Savings() {

	}

	public Savings(int userId,double amount, String description) {
		this.userId = userId;
		this.amount = amount;
		this.description = description;
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

	public String getDescription() {
		return description;
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

	public void setDescription(String description) {
		this.description = description;
	}
}
