package com.ukg.entity;

public class StressCount {

	private String type;
	private String percentage;
	private Long count;
	
	public String getType() {
		return type;
	}
	public StressCount setType(String type) {
		this.type = type;
		return this;
	}
	public String getPercentage() {
		return percentage;
	}
	public StressCount setPercentage(String percentage) {
		this.percentage = percentage;
		return this;
	}
	public Long getCount() {
		return count;
	}
	public StressCount setCount(Long count) {
		this.count = count;
		return this;
	}
	
}
