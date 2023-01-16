package com.ukg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ukg.entity.OvertimeEntity;

public interface OvertimeRepository extends JpaRepository<OvertimeEntity, Long> {

	@Query(value = "select empid, sum(durationhrs) as durationhrs,'MONTHLY' as TYPE  from employeewellbeing.overtime o where datesubmit > (CURRENT_DATE - INTERVAL '30 days')  group by empid", nativeQuery = true)
	List<OvertimeEntity> findAllEmployeeFromLastMonth();
	
	@Query(value = "select empid, sum(durationhrs) as durationhrs,'QUARTERLY' as TYPE  from employeewellbeing.overtime o where datesubmit > (CURRENT_DATE - INTERVAL '90 days')  group by empid", nativeQuery = true)
	List<OvertimeEntity> findAllEmployeeFromLastQuarter();
		
	@Query(value = "select empid, sum(durationhrs) as durationhrs,'BIMONTHLY' as TYPE  from employeewellbeing.overtime o where datesubmit >= (CURRENT_DATE - INTERVAL '60 days') group by empid", nativeQuery = true)
	List<OvertimeEntity> findAllEmployeeFromBiMonthly();
	
}
