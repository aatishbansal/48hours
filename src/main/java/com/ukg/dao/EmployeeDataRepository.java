package com.ukg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ukg.entity.EmployeeDataEntity;

public interface EmployeeDataRepository extends JpaRepository<EmployeeDataEntity, Long>{

	@Query(value ="select e4.name,e4.currentrole ,e4.hiredate , e4.tenure ,e4.benefits ,e4.shift,e4.managername ,e4.avatar,e.*,e2.recommend  from employeewellbeing.empwellness e , employeewellbeing.emprecommend e2, employeewellbeing.employee e4  where e.grade =e2.grade and e.remarks =e2.remarks \r\n"
			+ "and e4.empid =e.empid and e.type=?1 and e4.empid=?2", nativeQuery = true)
	EmployeeDataEntity getEmployeeData(String type, Long empid);
}
