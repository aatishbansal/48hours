package com.ukg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name ="shift")
public class ShiftEntity {

	@Column(name="shiftid")
	private Long shiftid;
	
	@Id
	@Column(name ="empid")
	private Long empid;
	
	@Column(name="type")
	private String type;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
