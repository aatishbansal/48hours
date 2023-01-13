package com.ukg.entity;

public class Wellness {
	
	private String name;
	
	private Long empid;
	
	private Long managerid;
	
	private Long scale;
	
	private String grade;
	
	private String remarks;
	
	private String type;
	
	private Long leaveAmt;
	
	private Long overtimeAmt;
	
	private Long shiftAmt;
	
	private String recommend;
	

	public Long getEmpid() {
		return empid;
	}

	public Wellness setEmpid(Long empid) {
		this.empid = empid;
		return this;
	}

	public Long getManagerid() {
		return managerid;
	}

	public Wellness setManagerid(Long managerid) {
		this.managerid = managerid;
		return this;
	}

	public Long getScale() {
		return scale;
	}

	public Wellness setScale(Long scale) {
		this.scale = scale;
		return this;
	}

	public String getGrade() {
		return grade;
	}

	public Wellness setGrade(String grade) {
		this.grade = grade;
		return this;
	}

	public String getRemarks() {
		return remarks;
	}

	public Wellness setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}

	public String getType() {
		return type;
	}

	public Wellness setType(String type) {
		this.type = type;
		return this;
	}

	public Long getLeaveAmt() {
		return leaveAmt;
	}

	public Wellness setLeaveAmt(Long leaveAmt) {
		this.leaveAmt = leaveAmt;
		return this;
	}

	public Long getOvertimeAmt() {
		return overtimeAmt;
	}

	public Wellness setOvertimeAmt(Long overtimeAmt) {
		this.overtimeAmt = overtimeAmt;
		return this;
	}

	public Long getShiftAmt() {
		return shiftAmt;
	}

	public Wellness setShiftAmt(Long shiftAmt) {
		this.shiftAmt = shiftAmt;
		return this;
	}

	public String getRecommend() {
		return recommend;
	}

	public Wellness setRecommend(String recommend) {
		this.recommend = recommend;
		return this;
	}

	public String getName() {
		return name;
	}

	public Wellness setName(String name) {
		this.name = name;
		return this;
	}
}
