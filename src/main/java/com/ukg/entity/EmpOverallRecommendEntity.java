package com.ukg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="empoverallrecommend")
public class EmpOverallRecommendEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Long empid;
	
	@Column
	private String overallrecommend;
	
	@Column
	private Long last30days;
	
	@Column
	private Long last60days;
	
	@Column
	private Long last90days;

	public Long getId() {
		return id;
	}

	public EmpOverallRecommendEntity setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getEmpid() {
		return empid;
	}

	public EmpOverallRecommendEntity setEmpid(Long empid) {
		this.empid = empid;
		return this;
	}

	public String getOverallrecommend() {
		return overallrecommend;
	}

	public EmpOverallRecommendEntity setOverallrecommend(String overallrecommend) {
		this.overallrecommend = overallrecommend;
		return this;
	}

	public Long getLast30days() {
		return last30days;
	}

	public EmpOverallRecommendEntity setLast30days(Long last30days) {
		this.last30days = last30days;
		return this;
	}

	public Long getLast60days() {
		return last60days;
	}

	public EmpOverallRecommendEntity setLast60days(Long last60days) {
		this.last60days = last60days;
		return this;
	}

	public Long getLast90days() {
		return last90days;
	}

	public EmpOverallRecommendEntity setLast90days(Long last90days) {
		this.last90days = last90days;
		return this;
	}
	
	
}
