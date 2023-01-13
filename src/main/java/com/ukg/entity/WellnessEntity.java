package com.ukg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name ="empwellness")
public class WellnessEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empwellnessid;
	
	@Column(name="name")
	private String name;
	
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

	public WellnessEntity setEmpid(Long empid) {
		this.empid = empid;
		return this;
	}

	public Long getManagerid() {
		return managerid;
	}

	public WellnessEntity setManagerid(Long managerid) {
		this.managerid = managerid;
		return this;
	}

	public Long getScale() {
		return scale;
	}

	public WellnessEntity setScale(Long scale) {
		this.scale = scale;
		return this;
	}

	public String getGrade() {
		return grade;
	}

	public WellnessEntity setGrade(String grade) {
		this.grade = grade;
		return this;
	}

	public String getRemarks() {
		return remarks;
	}

	public WellnessEntity setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}

	public String getType() {
		return type;
	}

	public WellnessEntity setType(String type) {
		this.type = type;
		return this;
	}

	public Long getLeaveAmt() {
		return leaveAmt;
	}

	public WellnessEntity setLeaveAmt(Long leaveAmt) {
		this.leaveAmt = leaveAmt;
		return this;
	}

	public Long getOvertimeAmt() {
		return overtimeAmt;
	}

	public WellnessEntity setOvertimeAmt(Long overtimeAmt) {
		this.overtimeAmt = overtimeAmt;
		return this;
	}

	public Long getShiftAmt() {
		return shiftAmt;
	}

	public WellnessEntity setShiftAmt(Long shiftAmt) {
		this.shiftAmt = shiftAmt;
		return this;
	}

	public Long getEmpwellnessid() {
		return empwellnessid;
	}

	public WellnessEntity setEmpwellnessid(Long empwellnessid) {
		this.empwellnessid = empwellnessid;
		return this;
	}

	public String getRecommend() {
		return recommend;
	}

	public WellnessEntity setRecommend(String recommend) {
		this.recommend = recommend;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
