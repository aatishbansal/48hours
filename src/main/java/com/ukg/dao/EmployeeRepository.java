package com.ukg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukg.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{

}
