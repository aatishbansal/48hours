package com.ukg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ukg.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{

	@Query(value = "select count(*) from employeewellbeing.employee", nativeQuery = true)
	int findTotalEmployees();
}
