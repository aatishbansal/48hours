package com.ukg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ukg.entity.WellnessEntity;

public interface WellnessRepository extends JpaRepository<WellnessEntity, Long>{

	@Query(value ="select e4.name,e4.avatar, e.empid, e.manageid,e.type,e.empwellnessid,e.grade,e.scale,e.overtimeamt,e.leaveamt, e.shiftamt, e2.remarks,e2.recommend  from employeewellbeing.empwellness e , employeewellbeing.emprecommend e2, employeewellbeing.employee e4  where e.grade =e2.grade and e.remarks =e2.emprecommendid \r\n"
			+ "and e4.empid =e.empid and e.manageid=?1 and e.type=?2 and e.grade=?3", nativeQuery = true)
	List<WellnessEntity> getAllData(Long manageid, String type, String grade);
}
