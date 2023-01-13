package com.ukg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "overtime")
public class OvertimeEntity {
	
	@Id
	@Column(name ="empid")
	private Long empid;
		
	@Column(name ="durationhrs")
	private Long durationhrs;
	
	@Column
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getEmpid() {
		return empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
	}

	public Long getDurationhrs() {
		return durationhrs;
	}

	public void setDurationhrs(Long durationhrs) {
		this.durationhrs = durationhrs;
	}
}
