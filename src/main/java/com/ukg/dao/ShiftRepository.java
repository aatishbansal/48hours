package com.ukg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ukg.entity.ShiftEntity;

public interface ShiftRepository extends JpaRepository<ShiftEntity, Long>{

	@Query(value = "select empid, count(shiftid) as shiftid,'MONTHLY' as TYPE  from employeewellbeing.shift where datesubmit > (CURRENT_DATE - INTERVAL '30 days') and shiftname ='NON_REGULAR' group by empid", nativeQuery = true)
	List<ShiftEntity> findAllEmployeeFromLastMonth();
	
	@Query(value = "select empid, count(shiftid) as shiftid ,'QUARTERLY' as TYPE  from employeewellbeing.shift where datesubmit > (CURRENT_DATE - INTERVAL '90 days') and shiftname ='NON_REGULAR' group by empid", nativeQuery = true)
	List<ShiftEntity> findAllEmployeeFromLastQuarter();
	
	@Query(value = "select empid, count(shiftid) as shiftid,'MONTHLY' as TYPE  from employeewellbeing.shift where datesubmit > (CURRENT_DATE - INTERVAL '30 days') and shiftname ='NON_REGULAR' group by empid", nativeQuery = true)
	List<ShiftEntity> findAllEmployeeFromMonthly();
	
	@Query(value = "select empid, count(shiftid) as shiftid,'BIMONTHLY' as TYPE  from employeewellbeing.shift where datesubmit >= (CURRENT_DATE - INTERVAL '60 days') and datesubmit < (CURRENT_DATE - INTERVAL '30 days') and shiftname ='NON_REGULAR' group by empid", nativeQuery = true)
	List<ShiftEntity> findAllEmployeeFromBiMonthly();
	
	@Query(value = "select empid, count(shiftid) as shiftid ,'QUARTERLY' as TYPE  from employeewellbeing.shift where datesubmit >= (CURRENT_DATE - INTERVAL '90 days') and datesubmit < (CURRENT_DATE - INTERVAL '60 days') and shiftname ='NON_REGULAR' group by empid", nativeQuery = true)
	List<ShiftEntity> findAllEmployeeFromQuarterly();
}
