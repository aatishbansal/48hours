package com.ukg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name ="empwellness")
public class EmpWellnessEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empwellnessid;
	
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
	

	public Long getEmpid() {
		return empid;
	}

	public EmpWellnessEntity setEmpid(Long empid) {
		this.empid = empid;
		return this;
	}

	public Long getManagerid() {
		return managerid;
	}

	public EmpWellnessEntity setManagerid(Long managerid) {
		this.managerid = managerid;
		return this;
	}

	public Long getScale() {
		return scale;
	}

	public EmpWellnessEntity setScale(Long scale) {
		this.scale = scale;
		return this;
	}

	public String getGrade() {
		return grade;
	}

	public EmpWellnessEntity setGrade(String grade) {
		this.grade = grade;
		return this;
	}

	public String getRemarks() {
		return remarks;
	}

	public EmpWellnessEntity setRemarks(String remarks) {
		this.remarks = remarks;
		return this;
	}

	public String getType() {
		return type;
	}

	public EmpWellnessEntity setType(String type) {
		this.type = type;
		return this;
	}

	public Long getLeaveAmt() {
		return leaveAmt;
	}

	public EmpWellnessEntity setLeaveAmt(Long leaveAmt) {
		this.leaveAmt = leaveAmt;
		return this;
	}

	public Long getOvertimeAmt() {
		return overtimeAmt;
	}

	public EmpWellnessEntity setOvertimeAmt(Long overtimeAmt) {
		this.overtimeAmt = overtimeAmt;
		return this;
	}

	public Long getShiftAmt() {
		return shiftAmt;
	}

	public EmpWellnessEntity setShiftAmt(Long shiftAmt) {
		this.shiftAmt = shiftAmt;
		return this;
	}
}
