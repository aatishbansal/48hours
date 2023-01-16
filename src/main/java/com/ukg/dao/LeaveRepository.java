package com.ukg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ukg.entity.LeaveEntity;

public interface LeaveRepository extends JpaRepository<LeaveEntity, Long> {

	@Query(value = "select empid, sum(amount) as amount, 'MONTHLY' as TYPE from employeewellbeing.leave where datesubmit > (CURRENT_DATE - INTERVAL '30 days') and  type != 'SICK_LEAVE' group by empid", nativeQuery = true)
	List<LeaveEntity> findAllEmployeeFromLastMonth();
	
	@Query(value = "select empid, sum(amount) as amount, 'QUARTERLY' as TYPE from employeewellbeing.leave where datesubmit > (CURRENT_DATE - INTERVAL '90 days') and  type != 'SICK_LEAVE' group by empid", nativeQuery = true)
	List<LeaveEntity> findAllEmployeeFromLastQuarter();
	
	@Query(value = "select empid, sum(amount) as amount, 'MONTHLY' as TYPE from employeewellbeing.leave where datesubmit >= (CURRENT_DATE - INTERVAL '30 days') and  type != 'SICK_LEAVE' group by empid", nativeQuery = true)
	List<LeaveEntity> findAllEmployeeFromMonthly();
	
	@Query(value = "select empid, sum(amount) as amount, 'BIMONTHLY' as TYPE from employeewellbeing.leave where datesubmit >= (CURRENT_DATE - INTERVAL '60 days') and datesubmit < (CURRENT_DATE - interval '30 days')  and  type != 'SICK_LEAVE' group by empid", nativeQuery = true)
	List<LeaveEntity> findAllEmployeeFromBiMonthly();
	
	@Query(value = "select empid, sum(amount) as amount, 'QUARTERLY' as TYPE from employeewellbeing.leave where datesubmit >= (CURRENT_DATE - INTERVAL '90 days') and datesubmit < (CURRENT_DATE - interval '60 days')  and  type != 'SICK_LEAVE' group by empid", nativeQuery = true)
	List<LeaveEntity> findAllEmployeeFromQuarterly();

}
