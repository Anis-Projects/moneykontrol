package entity;

import java.time.LocalDateTime;

public class Report {
	private int id;
	private int month;
	private int year;
	private LocalDateTime createdAt;

	public Report(){}

	public Report(int month, int year, LocalDateTime createdAt) {
		this.month = month;
		this.year = year;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
