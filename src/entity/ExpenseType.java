package entity;

public enum ExpenseType {
	FOOD,
	GROCERY,
	RENT,
	ELECTRICITY,
	OTHERS,
	INTERNET;

	public static ExpenseType fromString(String type) {
		for (ExpenseType expenseType : ExpenseType.values()) {
			if (expenseType.name().equalsIgnoreCase(type)) {
				return expenseType;
			}
		}
		throw new IllegalArgumentException("Unknown expense type: " + type);
	}
}
