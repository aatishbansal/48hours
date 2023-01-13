package com.ukg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ukg.dao.EmpRecommendRepository;
import com.ukg.dao.EmpWellnessRepository;
import com.ukg.dao.LeaveRepository;
import com.ukg.dao.WellnessRepository;
import com.ukg.entity.Leave;
import com.ukg.entity.LeaveEntity;
import com.ukg.entity.Wellness;
import com.ukg.entity.WellnessEntity;

@RestController
@RequestMapping("/v1/data")
public class DataController {
	
	@Autowired
	private LeaveRepository leaveRepository;
	
	@Autowired
	private EmpWellnessRepository empWellnessRepository;
	
	@Autowired
	private EmpRecommendRepository empRecommendRepository;
	
	@Autowired
	private WellnessRepository wellnessRepository;

	@GetMapping(path="/leavedetails")
	public List<Leave> getLeaveDetails() {
		List<LeaveEntity> leaveEntity = leaveRepository.findAll();
		List<Leave> leaveLst = new ArrayList<Leave>();
		for(LeaveEntity entity : leaveEntity) {
			Leave l = new Leave();
			l.setEmpid(entity.getEmpid());
			l.setAmount(entity.getAmount());
			//l.setDatesubmit(entity.getDatesubmit());
			l.setType(entity.getType());
			leaveLst.add(l);
		}
		
		return leaveLst;
	}
	
	@GetMapping(path = {"/user"})
    public String user(@RequestParam(required=false) Map<String,String> qparams) {
        qparams.forEach((a,b) -> {
            System.out.println(String.format("%s -> %s",a,b));
        });

        return "[{\"empid\":1,\"datesubmit\":\"2023-01-13\",\"amount\":1,\"type\":\"SICK_LEAVE\"},{\"empid\":6,\"datesubmit\":\"2023-01-12\",\"amount\":1,\"type\":\"CASUAL_LEAVE\"}]";
    }

    @GetMapping(path = {"/empwellness/monthly"})
    public String employeeWellnessMonthly() {
        empWellnessRepository.findAll();
        return "[{\"empid\":1,\"datesubmit\":\"2023-01-13\",\"amount\":1,\"type\":\"SICK_LEAVE\"},{\"empid\":6,\"datesubmit\":\"2023-01-12\",\"amount\":1,\"type\":\"CASUAL_LEAVE\"}]";
    }

    @GetMapping(path = {"/empwellness/quaterly"})
    public String employeeWellnessQuaterly() {
        empWellnessRepository.findAll();
        return "[{\"empid\":1,\"datesubmit\":\"2023-01-13\",\"amount\":1,\"type\":\"SICK_LEAVE\"},{\"empid\":6,\"datesubmit\":\"2023-01-12\",\"amount\":1,\"type\":\"CASUAL_LEAVE\"}]";
    }

    @GetMapping(path = {"/emprecommend"})
    public String employeeRecommended() {
        empRecommendRepository.findAll();
        return "[{\"empid\":1,\"datesubmit\":\"2023-01-13\",\"amount\":1,\"type\":\"SICK_LEAVE\"},{\"empid\":6,\"datesubmit\":\"2023-01-12\",\"amount\":1,\"type\":\"CASUAL_LEAVE\"}]";
    }
    
    @GetMapping(path = "/empwellness/{type}")
    public List<Wellness> getAllData(@PathVariable("type") String type) {
    	if(type != null) {    		
    		if("Last 30 days".equalsIgnoreCase(type)) {
    			List<WellnessEntity> wellnessEntity =  wellnessRepository.getAllData(10l, "MONTHLY");
    			return createWellnessList(wellnessEntity);
    		} else {
    			List<WellnessEntity> wellnessEntity =  wellnessRepository.getAllData(10l, "QUARTERLY");
    			return createWellnessList(wellnessEntity);
    		} 
    	} else {
    		List<WellnessEntity> wellnessEntity =  wellnessRepository.getAllData(10l, "QUARTERLY");
			return createWellnessList(wellnessEntity);
    	}
    }
    
    private List<Wellness> createWellnessList(List<WellnessEntity> wellnessEntity) {
    	List<Wellness> wellnessList = new ArrayList<Wellness>();
    	for(WellnessEntity entity : wellnessEntity) {
    		Wellness wellness = new Wellness();
    		wellness.setEmpid(entity.getEmpid()).setGrade(entity.getGrade()).setLeaveAmt(entity.getLeaveAmt()).setShiftAmt(entity.getShiftAmt()).setOvertimeAmt(entity.getOvertimeAmt())
    		.setRemarks(entity.getRemarks()).setRecommend(entity.getRecommend()).setScale(entity.getScale()).setType(entity.getType()).setManagerid(entity.getManagerid());
    		wellnessList.add(wellness);
    	}
    	return wellnessList;
    }
}
