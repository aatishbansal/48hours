package com.ukg.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "leave")
public class LeaveEntity {

	@Id
	private Long leaveid;
	
	@Column(name = "empid")
	private Long empid;
	
	@Column(name = "datesubmit")
	private LocalDate datesubmit;
	
	@Column(name = "Amount")
	private Long amount;
	
	@Column(name = "type")
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
