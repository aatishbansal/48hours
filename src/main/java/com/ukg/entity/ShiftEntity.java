package com.ukg.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name ="shift")
public class ShiftEntity {

	@Id
	private Long shiftid;
	
	@Column(name ="empid")
	private Long empid;
	
	@Column(name ="shiftname")
	private String shiftname;
	
	@Column(name ="datesubmit")
	private LocalDate datesubmit;

	public Long getShiftid() {
		return shiftid;
	}

	public void setShiftid(Long shiftid) {
		this.shiftid = shiftid;
	}

	public Long getEmpid() {
		return empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
	}

	public String getShiftname() {
		return shiftname;
	}

	public void setShiftname(String shiftname) {
		this.shiftname = shiftname;
	}

	public LocalDate getDatesubmit() {
		return datesubmit;
	}

	public void setDatesubmit(LocalDate datesubmit) {
		this.datesubmit = datesubmit;
	}

}
