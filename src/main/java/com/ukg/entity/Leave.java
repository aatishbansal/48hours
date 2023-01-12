package com.ukg.entity;

import java.time.LocalDate;

public class Leave {

	private Long empid;
	
	private LocalDate datesubmit;
		
	private Long amount;
		
	private LocalDate startdate;
		
	private LocalDate enddate;
		
	private String type;

	public Long getEmpid() {
		return empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
	}

	public LocalDate getDatesubmit() {
		return datesubmit;
	}

	public void setDatesubmit(LocalDate datesubmit) {
		this.datesubmit = datesubmit;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public LocalDate getEnddate() {
		return enddate;
	}

	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
