package com.ukg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name ="emprecommend")
public class EmpRecommendEntity {

	@Id
	private Long emprecommendid;
	
	@Column
	private String grade;
	
	@Column
	private String remarks;
	
	@Column
	private String recommend;
	
	@Column
	private String type;

	public Long getEmprecommendid() {
		return emprecommendid;
	}

	public void setEmprecommendid(Long emprecommendid) {
		this.emprecommendid = emprecommendid;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
