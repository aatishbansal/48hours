package com.ukg.entity;

public class StressCount {

	private String type;
	private Double percentage;
	private Long count;
	
	public String getType() {
		return type;
	}
	public StressCount setType(String type) {
		this.type = type;
		return this;
	}
	public Double getPercentage() {
		return percentage;
	}
	public StressCount setPercentage(Double percentage) {
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
