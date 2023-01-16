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

import com.ukg.dao.EmpOverallRecommendRepository;
import com.ukg.dao.EmpRecommendRepository;
import com.ukg.dao.EmpWellnessRepository;
import com.ukg.dao.EmployeeDataRepository;
import com.ukg.dao.EmployeeRepository;
import com.ukg.dao.LeaveRepository;
import com.ukg.dao.WellnessRepository;
import com.ukg.entity.DataPlot;
import com.ukg.entity.EmpOverallRecommend;
import com.ukg.entity.EmpOverallRecommendEntity;
import com.ukg.entity.EmployeeData;
import com.ukg.entity.EmployeeDataEntity;
import com.ukg.entity.Leave;
import com.ukg.entity.LeaveEntity;
import com.ukg.entity.StressCount;
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
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeDataRepository employeeDataRepository; 
	
	@Autowired
	private EmpOverallRecommendRepository empOverallRecommendRepository;

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
    
    @GetMapping(path = "/empwellness")
    public List<Wellness> getAllData(@RequestParam(defaultValue = "Last 30 days")  String type, @RequestParam(defaultValue = "High Stress") String grade) {
    	System.out.println("type ::: " + type);
    	System.out.println("grade ::: " + grade);
    	if(type != null) {    		
    		if("Last 30 days".equalsIgnoreCase(type)) {
    			List<WellnessEntity> wellnessEntity =  wellnessRepository.getAllData(10l, "MONTHLY", grade);
    			return createWellnessList(wellnessEntity);
    		} else if("Last 60 days".equalsIgnoreCase(type)) {
    			List<WellnessEntity> wellnessEntity =  wellnessRepository.getAllData(10l, "BIMONTHLY", grade);
    			return createWellnessList(wellnessEntity);
    		} else {
    			List<WellnessEntity> wellnessEntity =  wellnessRepository.getAllData(10l, "QUARTERLY", grade);
    			return createWellnessList(wellnessEntity);
    		} 
    	} else {
    		List<WellnessEntity> wellnessEntity =  wellnessRepository.getAllData(10l, "QUARTERLY", grade);
			return createWellnessList(wellnessEntity);
    	}
    }
    
    private List<Wellness> createWellnessList(List<WellnessEntity> wellnessEntity) {
    	List<Wellness> wellnessList = new ArrayList<>();
    	for(WellnessEntity entity : wellnessEntity) {
    		Wellness wellness = new Wellness();
    		wellness.setEmpid(entity.getEmpid()).setGrade(entity.getGrade()).setLeaveAmt(entity.getLeaveAmt()).setShiftAmt(entity.getShiftAmt()).setOvertimeAmt(entity.getOvertimeAmt())
    		.setRemarks(entity.getRemarks()).setRecommend(entity.getRecommend()).setScale(entity.getScale()).setType(entity.getType()).setManagerid(entity.getManagerid()).setName(entity.getName()).setAvatar(entity.getAvatar());
    		wellnessList.add(wellness);
    	}
    	
    	wellnessList.forEach(System.out::println);
    	return wellnessList;
    }
    
    @GetMapping(path="/empwellness/stress")
    private StressCount getStressCount(@RequestParam(defaultValue = "Last 30 days")  String type, @RequestParam(defaultValue = "High Stress") String grade) {
    	if("Last 30 days".equalsIgnoreCase(type)) {
    		int employeeCount = employeeRepository.findTotalEmployees(10l);
			long count = empWellnessRepository.findcountpergrade(grade, "MONTHLY", 10l);
			Double percentage = (double) ((count * 100) /employeeCount );
			return new StressCount().setCount(count).setPercentage(percentage + "%").setType(grade);
		} else if("Last 60 days".equalsIgnoreCase(type)) {
    		int employeeCount = employeeRepository.findTotalEmployees(10l);
			long count = empWellnessRepository.findcountpergrade(grade, "BIMONTHLY", 10l);
			Double percentage = (double) ((count * 100) /employeeCount );
			return new StressCount().setCount(count).setPercentage(percentage + "%").setType(grade);
		} else {
			int employeeCount = employeeRepository.findTotalEmployees(10l);
			long count = empWellnessRepository.findcountpergrade(grade, "QUARTERLY", 10l);
			Double percentage = (double) ((count * 100) /employeeCount );
			return new StressCount().setCount(count).setPercentage(percentage+ "%").setType(grade);
		} 
    }
    
    @GetMapping(path="/empwellness/data")
    private EmployeeData getEmployeeData(@RequestParam(defaultValue = "Last 30 days")  String type) {
    	if("Last 30 days".equalsIgnoreCase(type)) {
    		EmployeeDataEntity entity = employeeDataRepository.getEmployeeData("MONTHLY", 10l);
    		return new EmployeeData().setEmpid(entity.getEmpid()).setGrade(entity.getGrade()).setLeaveAmt(entity.getLeaveAmt()).setShiftAmt(entity.getShiftAmt()).setOvertimeAmt(entity.getOvertimeAmt())
    	    		.setRemarks(entity.getRemarks()).setRecommend(entity.getRecommend()).setScale(entity.getScale()).setType(entity.getType()).setManagerid(entity.getManagerid()).setName(entity.getName())
    	    		.setCurrentRole(entity.getCurrentRole()).setBenefits(entity.getBenefits()).setHiredate(entity.getHiredate()).setTenure(entity.getTenure()).setShift(entity.getShift()).setManagername(entity.getManagername()).setAvatar(entity.getAvatar());
		} else if("Last 60 days".equalsIgnoreCase(type)) {
    		EmployeeDataEntity entity = employeeDataRepository.getEmployeeData("BIMONTHLY", 10l);
    		return new EmployeeData().setEmpid(entity.getEmpid()).setGrade(entity.getGrade()).setLeaveAmt(entity.getLeaveAmt()).setShiftAmt(entity.getShiftAmt()).setOvertimeAmt(entity.getOvertimeAmt())
    	    		.setRemarks(entity.getRemarks()).setRecommend(entity.getRecommend()).setScale(entity.getScale()).setType(entity.getType()).setManagerid(entity.getManagerid()).setName(entity.getName())
    	    		.setCurrentRole(entity.getCurrentRole()).setBenefits(entity.getBenefits()).setHiredate(entity.getHiredate()).setTenure(entity.getTenure()).setShift(entity.getShift()).setManagername(entity.getManagername()).setAvatar(entity.getAvatar());
		} else {
			EmployeeDataEntity entity = employeeDataRepository.getEmployeeData("QUARTERLY", 10l);
			return new EmployeeData().setEmpid(entity.getEmpid()).setGrade(entity.getGrade()).setLeaveAmt(entity.getLeaveAmt()).setShiftAmt(entity.getShiftAmt()).setOvertimeAmt(entity.getOvertimeAmt())
    	    		.setRemarks(entity.getRemarks()).setRecommend(entity.getRecommend()).setScale(entity.getScale()).setType(entity.getType()).setManagerid(entity.getManagerid()).setName(entity.getName())
    	    		.setCurrentRole(entity.getCurrentRole()).setBenefits(entity.getBenefits()).setHiredate(entity.getHiredate()).setTenure(entity.getTenure()).setShift(entity.getShift()).setManagername(entity.getManagername()).setAvatar(entity.getAvatar());
		} 
    }
    
    @GetMapping(path="/empwellness/empoverallrecommend")
    private EmpOverallRecommend getEmpOverallRecommend(@RequestParam(defaultValue = "1") Long empid) {
    	EmpOverallRecommendEntity entity = empOverallRecommendRepository.fetchEmployeeData(empid);
    	List<DataPlot> dataPlot = new ArrayList<DataPlot>();
    	DataPlot p1 = new DataPlot().setDuration("Last 30 days").setScale(entity.getLast30days());
    	DataPlot p2 = new DataPlot().setDuration("Last 60 days").setScale(entity.getLast60days());
    	DataPlot p3 = new DataPlot().setDuration("Last 90 days").setScale(entity.getLast90days());
    	
    	dataPlot.add(p1);
    	dataPlot.add(p2);
    	dataPlot.add(p3);
    	
    	return new EmpOverallRecommend().setEmpid(entity.getEmpid()).setOverallrecommend(entity.getOverallrecommend()).setPlot(dataPlot);
    }
    
}
