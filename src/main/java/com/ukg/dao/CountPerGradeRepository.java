package com.ukg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ukg.entity.CountPerStress;

public interface CountPerGradeRepository extends JpaRepository<CountPerStress, Long> {

	@Query(value = "select count(*) from employeewellbeing.empwellness e where type=?1 and manageid=?2 and grade=?3", nativeQuery = true)
	List<CountPerStress> findcount(String type, Long managerid, String grade);
}
