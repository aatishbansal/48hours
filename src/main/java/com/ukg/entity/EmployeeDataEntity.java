package com.ukg.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name ="empwellness")
public class EmployeeDataEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empwellnessid;
	
	@Column(name="name")
	private String name;
	
	@Column(name="currentrole")
	private String currentRole;
	
	@Column(name="hiredate")
	private LocalDate hiredate;
	
	@Column(name="tenure")
	private Long tenure;
	
	@Column(name="benefits")
	private String benefits;
	
	@Column(name="shift")
	private String shift;
	
	@Column(name ="empid")
	private Long empid;
	
	@Column(name="manageid")
	private Long managerid;
	
	@Column(name="scale")
	private Long scale;
	
	@Column(name="grade")
	private String grade;
	
	@Column(name="remarks")
	private String remarks;
	
	@Column(name="type")
	private String type;
	
	@Column(name="leaveamt")
	private Long leaveAmt;
	
	@Column(name="overtimeamt")
	private Long overtimeAmt;
	
	@Column(name="shiftamt")
	private Long shiftAmt;
	
	@Column(name="recommend")
	private String recommend;
	

	public Long getEmpid() {
		return empid;
	}

	public EmployeeDataEntity setEmpid(Long empid) {
		this.empid = empid;
		return this;
	}

	public Long getManagerid() {
		return managerid;
	}

	public EmployeeDataEntity setManagerid(Long managerid) {
		this.managerid = managerid;
		return this;
	}

	public Long getScale() {
		return scale;
	}

	public EmployeeDataEntity setScale(Long scale) {
		this.scale = scale;
		return this;
	}

	public String getGrade() {
		return grade;
	}

	public EmployeeDataEntity setGrade(String grade) {
		this.grade = grade;
		return this;
	}

	public String getRemarks() {
		return remarks;
	}

	public EmployeeDataEntity setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}

	public String getType() {
		return type;
	}

	public EmployeeDataEntity setType(String type) {
		this.type = type;
		return this;
	}

	public Long getLeaveAmt() {
		return leaveAmt;
	}

	public EmployeeDataEntity setLeaveAmt(Long leaveAmt) {
		this.leaveAmt = leaveAmt;
		return this;
	}

	public Long getOvertimeAmt() {
		return overtimeAmt;
	}

	public EmployeeDataEntity setOvertimeAmt(Long overtimeAmt) {
		this.overtimeAmt = overtimeAmt;
		return this;
	}

	public Long getShiftAmt() {
		return shiftAmt;
	}

	public EmployeeDataEntity setShiftAmt(Long shiftAmt) {
		this.shiftAmt = shiftAmt;
		return this;
	}

	public Long getEmpwellnessid() {
		return empwellnessid;
	}

	public EmployeeDataEntity setEmpwellnessid(Long empwellnessid) {
		this.empwellnessid = empwellnessid;
		return this;
	}

	public String getRecommend() {
		return recommend;
	}

	public EmployeeDataEntity setRecommend(String recommend) {
		this.recommend = recommend;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCurrentRole() {
		return currentRole;
	}

	public void setCurrentRole(String currentRole) {
		this.currentRole = currentRole;
	}

	public LocalDate getHiredate() {
		return hiredate;
	}

	public void setHiredate(LocalDate hiredate) {
		this.hiredate = hiredate;
	}

	public Long getTenure() {
		return tenure;
	}

	public void setTenure(Long tenure) {
		this.tenure = tenure;
	}

	public String getBenefits() {
		return benefits;
	}

	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}
}
