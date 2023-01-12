package com.ukg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukg.entity.OvertimeEntity;

public interface OvertimeRepository extends JpaRepository<OvertimeEntity, Long> {

}
