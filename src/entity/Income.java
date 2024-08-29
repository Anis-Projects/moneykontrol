package entity;

import java.time.LocalDateTime;

public class Income {
	private int id;
	private int userId;
	private String source;
	private LocalDateTime createdAt;

	public Income() {

	}

	public Income(int userId,String source, LocalDateTime createdAt) {
		this.userId = userId;
		this.source = source;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
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
