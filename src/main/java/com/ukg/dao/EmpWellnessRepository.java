package com.ukg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ukg.entity.CountPerStress;
import com.ukg.entity.EmpWellnessEntity;

public interface EmpWellnessRepository extends JpaRepository<EmpWellnessEntity, Long> {

	@Query(value = "select * from employeewellbeing.empwellness where empid=? and type=?", nativeQuery = true)
	EmpWellnessEntity findByEmpIdAndType(Long empId, String type);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "update employeewellbeing.empwellness set scale= ?1, leaveamt= ?2, shiftamt= ?3, overtimeamt= ?4, remarks= ?5, grade= ?6 where empid= ?7 and type= ?8", nativeQuery = true)
	int update(Long scale, Long leaveAmt, Long shiftAmt, Long overtimeAmt, String remarks, String grade, Long empid, String type);
	
	@Query(value = "select count(*) from employeewellbeing.empwellness e where grade=?1 and type=?2 and manageid=?3", nativeQuery = true)
	int findcountpergrade(String grade, String type, Long managerid);
	
	@Query(value = "select count(*), grade from employeewellbeing.empwellness e where type=?1 and manageid=?2 group by grade", nativeQuery = true)
	CountPerStress findcount(String type, Long managerid);
}
