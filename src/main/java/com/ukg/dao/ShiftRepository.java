package com.ukg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ukg.entity.LeaveEntity;
import com.ukg.entity.ShiftEntity;

public interface ShiftRepository extends JpaRepository<ShiftEntity, Long>{

	@Query(value = "select empid, sum(durationhrs) as durationhrs,'MONTHLY' as TYPE  from overtime o where datesubmit > (CURRENT_DATE - INTERVAL '30 days')  group by empid")
	List<LeaveEntity> findAllEmployeeFromLastMonth();
	
	@Query(value = "select empid, sum(durationhrs) as durationhrs,'QUARTERLY' as TYPE  from overtime o where datesubmit > (CURRENT_DATE - INTERVAL '90 days')  group by empid")
	List<LeaveEntity> findAllEmployeeFromLastQuarter();
}
