package com.ukg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ukg.entity.LeaveEntity;

public interface LeaveRepository extends JpaRepository<LeaveEntity, Long> {

}
