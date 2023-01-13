package com.ukg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ukg.entity.WellnessEntity;

public interface WellnessRepository extends JpaRepository<WellnessEntity, Long>{

	@Query(value ="select e.*,e2.recommend  from employeewellbeing.empwellness e , employeewellbeing.emprecommend e2 where e.grade =e2.grade and e.remarks =e2.remarks and e.manageid=?1 and e.type=?2", nativeQuery = true)
	List<WellnessEntity> getAllData(Long manageid, String type);
}
