package com.ukg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ukg.dao.EmpWellnessRepository;
import com.ukg.dao.EmployeeRepository;
import com.ukg.dao.LeaveRepository;
import com.ukg.dao.OvertimeRepository;
import com.ukg.dao.ShiftRepository;

@Component
public class Schedular {

	@Autowired
	private LeaveRepository leaveRepository;
	
	@Autowired
	private OvertimeRepository overtimeRepository;
	
	@Autowired
	private ShiftRepository shiftRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmpWellnessRepository empWellnessRepository;
	
	@Scheduled(fixedRate = 1000)
	public void fixedRateSchedular() {
		
	}
	
}
