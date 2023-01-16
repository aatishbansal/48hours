package com.ukg.entity;

import java.util.List;

public class EmpOverallRecommend {

	private Long empid;
	
	private String overallrecommend;
	
	private List<DataPlot> plot;

	public Long getEmpid() {
		return empid;
	}

	public EmpOverallRecommend setEmpid(Long empid) {
		this.empid = empid;
		return this;
	}

	public String getOverallrecommend() {
		return overallrecommend;
	}

	public EmpOverallRecommend setOverallrecommend(String overallrecommend) {
		this.overallrecommend = overallrecommend;
		return this;
	}

	public List<DataPlot> getPlot() {
		return plot;
	}

	public EmpOverallRecommend setPlot(List<DataPlot> plot) {
		this.plot = plot;
		return this;
	}
	
}
