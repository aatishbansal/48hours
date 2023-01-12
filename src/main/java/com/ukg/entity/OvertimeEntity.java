package com.ukg.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "overtime")
public class OvertimeEntity {

	@Id
	private Long overtimeid;
	
	@Column(name ="empid")
	private Long empid;
	
	@Column(name ="datesubmit")
	private LocalDate datesubmit;
		
	@Column(name ="durationhrs")
	private Long durationhrs;

	public Long getOvertimeid() {
		return overtimeid;
	}

	public void setOvertimeid(Long overtimeid) {
		this.overtimeid = overtimeid;
	}

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

	public Long getDurationhrs() {
		return durationhrs;
	}

	public void setDurationhrs(Long durationhrs) {
		this.durationhrs = durationhrs;
	}
}
