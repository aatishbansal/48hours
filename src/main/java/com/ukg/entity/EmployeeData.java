package com.ukg.entity;

import java.time.LocalDate;

public class EmployeeData {
	
	private String name;
	
	private String currentRole;
	
	private LocalDate hiredate;
		
	private Long tenure;
		
	private String benefits;
	
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

	public EmployeeData setEmpid(Long empid) {
		this.empid = empid;
		return this;
	}

	public Long getManagerid() {
		return managerid;
	}

	public EmployeeData setManagerid(Long managerid) {
		this.managerid = managerid;
		return this;
	}

	public Long getScale() {
		return scale;
	}

	public EmployeeData setScale(Long scale) {
		this.scale = scale;
		return this;
	}

	public String getGrade() {
		return grade;
	}

	public EmployeeData setGrade(String grade) {
		this.grade = grade;
		return this;
	}

	public String getRemarks() {
		return remarks;
	}

	public EmployeeData setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}

	public String getType() {
		return type;
	}

	public EmployeeData setType(String type) {
		this.type = type;
		return this;
	}

	public Long getLeaveAmt() {
		return leaveAmt;
	}

	public EmployeeData setLeaveAmt(Long leaveAmt) {
		this.leaveAmt = leaveAmt;
		return this;
	}

	public Long getOvertimeAmt() {
		return overtimeAmt;
	}

	public EmployeeData setOvertimeAmt(Long overtimeAmt) {
		this.overtimeAmt = overtimeAmt;
		return this;
	}

	public Long getShiftAmt() {
		return shiftAmt;
	}

	public EmployeeData setShiftAmt(Long shiftAmt) {
		this.shiftAmt = shiftAmt;
		return this;
	}

	public String getRecommend() {
		return recommend;
	}

	public EmployeeData setRecommend(String recommend) {
		this.recommend = recommend;
		return this;
	}

	public String getName() {
		return name;
	}

	public EmployeeData setName(String name) {
		this.name = name;
		return this;
	}

	public String getCurrentRole() {
		return currentRole;
	}

	public EmployeeData setCurrentRole(String currentRole) {
		this.currentRole = currentRole;
		return this;
	}

	public LocalDate getHiredate() {
		return hiredate;
	}

	public EmployeeData setHiredate(LocalDate hiredate) {
		this.hiredate = hiredate;
		return this;
	}

	public Long getTenure() {
		return tenure;
	}

	public EmployeeData setTenure(Long tenure) {
		this.tenure = tenure;
		return this;
	}

	public String getBenefits() {
		return benefits;
	}

	public EmployeeData setBenefits(String benefits) {
		this.benefits = benefits;
		return this;
	}

	@Override
	public String toString() {
		return "EmployeeData [name=" + name + ", currentRole=" + currentRole + ", hiredate=" + hiredate + ", tenure="
				+ tenure + ", benefits=" + benefits + ", empid=" + empid + ", managerid=" + managerid + ", scale="
				+ scale + ", grade=" + grade + ", remarks=" + remarks + ", type=" + type + ", leaveAmt=" + leaveAmt
				+ ", overtimeAmt=" + overtimeAmt + ", shiftAmt=" + shiftAmt + ", recommend=" + recommend + "]";
	}

}
