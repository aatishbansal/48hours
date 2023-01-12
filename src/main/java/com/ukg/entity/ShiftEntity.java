package com.ukg.entity;

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
	
	@Column(name = "shifttype")
	private String shifttype;
	
	@Column(name="shiftassignmnt")
	private String shiftassignment;
	
	@Column(name ="shiftprofile")
	private String shiftprofile;

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

	public String getShifttype() {
		return shifttype;
	}

	public void setShifttype(String shifttype) {
		this.shifttype = shifttype;
	}

	public String getShiftassignment() {
		return shiftassignment;
	}

	public void setShiftassignment(String shiftassignment) {
		this.shiftassignment = shiftassignment;
	}

	public String getShiftprofile() {
		return shiftprofile;
	}

	public void setShiftprofile(String shiftprofile) {
		this.shiftprofile = shiftprofile;
	}
}
