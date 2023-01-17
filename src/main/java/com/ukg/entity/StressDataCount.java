package com.ukg.entity;

public class StressDataCount {

	private String grade;
	private String percent;
	private Long count;
	
	public String getGrade() {
		return grade;
	}
	public StressDataCount setGrade(String grade) {
		this.grade = grade;
		return this;
	}
	public String getPercent() {
		return percent;
	}
	public StressDataCount setPercent(String percent) {
		this.percent = percent;
		return this;
	}
	public Long getCount() {
		return count;
	}
	public StressDataCount setCount(Long count) {
		this.count = count;
		return this;
	}
	
}
