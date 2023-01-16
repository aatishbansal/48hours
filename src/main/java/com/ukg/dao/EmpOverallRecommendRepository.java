package com.ukg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ukg.entity.EmpOverallRecommendEntity;

public interface EmpOverallRecommendRepository extends JpaRepository<EmpOverallRecommendEntity, Long> {

	@Query(value = "select * from employeewellbeing.empoverallrecommend where empid=?", nativeQuery = true)
	EmpOverallRecommendEntity fetchEmployeeData(Long empid);
}
