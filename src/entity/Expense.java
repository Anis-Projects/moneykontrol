package entity;

public class Expense {
	private int id;
	private int userId;
	private double amount;
	private ExpenseType type;

	public Expense() {}

	public Expense(int userId, double amount, ExpenseType type) {
		this.amount = amount;
		this.type = type;
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
}

