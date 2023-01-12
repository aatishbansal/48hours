package com.ukg.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ukg.dao.LeaveRepository;
import com.ukg.entity.Leave;
import com.ukg.entity.LeaveEntity;

@RestController
@RequestMapping("/v1/data")
public class DataController {
	
	@Autowired
	private LeaveRepository leaveRepository;

	@GetMapping(path="/leavedetails")
	public List<Leave> getLeaveDetails() {
		List<LeaveEntity> leaveEntity = leaveRepository.findAll();
		List<Leave> leaveLst = new ArrayList<Leave>();
		for(LeaveEntity entity : leaveEntity) {
			Leave l = new Leave();
			l.setEmpid(entity.getEmpid());
			l.setAmount(entity.getAmount());
			l.setDatesubmit(entity.getDatesubmit());
			l.setType(entity.getType());
			leaveLst.add(l);
		}
		
		return leaveLst;
	}
}
