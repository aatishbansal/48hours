package com.ukg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ukg.dao.EmpWellnessRepository;
import com.ukg.dao.EmployeeRepository;
import com.ukg.dao.LeaveRepository;
import com.ukg.dao.OvertimeRepository;
import com.ukg.dao.ShiftRepository;
import com.ukg.entity.EmpWellnessEntity;
import com.ukg.entity.EmployeeEntity;
import com.ukg.entity.LeaveEntity;
import com.ukg.entity.OvertimeEntity;
import com.ukg.entity.ShiftEntity;

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
	
	private Map<Long, EmpWellnessEntity> employeeWellnessMap;
	
	@Scheduled(fixedRate = 10000, initialDelay = 1000)
	public void fixedRateSchedular() {
		employeeWellnessMap = new HashMap<Long, EmpWellnessEntity>();
		List<EmployeeEntity> employeeList = employeeRepository.findAll();
		for(EmployeeEntity e : employeeList) {
			EmpWellnessEntity empWellnessEntity = new EmpWellnessEntity().setEmpid(e.getEmpid()).setLeaveAmt(0l).setShiftAmt(1l).setOvertimeAmt(1l).setType("QUARTERLY").setManagerid(e.getManagerid());
			employeeWellnessMap.put(e.getEmpid(), empWellnessEntity);
		}
				
		calculateQuarterData(leaveRepository.findAllEmployeeFromLastQuarter(), overtimeRepository.findAllEmployeeFromLastQuarter(), shiftRepository.findAllEmployeeFromLastQuarter());

	}

	private void calculateQuarterData(List<LeaveEntity> leaveList, List<OvertimeEntity> overtimeList,
			List<ShiftEntity> shiftList) {
		leaveList.stream().forEach(e -> {
			EmpWellnessEntity entity = employeeWellnessMap.get(e.getEmpid());
			entity.setEmpid(e.getEmpid()).setLeaveAmt(e.getAmount()).setType(e.getType());
			employeeWellnessMap.put(e.getEmpid(), entity);
		});
		
		overtimeList.stream().forEach(e -> {
			EmpWellnessEntity entity = employeeWellnessMap.get(e.getEmpid());
			entity.setOvertimeAmt(e.getDurationhrs()).setEmpid(e.getEmpid());
			employeeWellnessMap.put(e.getEmpid(), entity);
		});
		
		shiftList.stream().forEach(e -> {
			EmpWellnessEntity entity = employeeWellnessMap.get(e.getEmpid());
			entity.setShiftAmt(e.getShiftid()).setEmpid(e.getEmpid());
			employeeWellnessMap.put(e.getEmpid(), entity);
		});
		
		List<EmpWellnessEntity> empWellnessLst = employeeWellnessMap.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
		for(EmpWellnessEntity e : empWellnessLst) {
			long leavescale = (e.getLeaveAmt() != null) ? calculateLeaves(e.getLeaveAmt()) : 5;
			long overtimescale = ((e.getOvertimeAmt() != null) ? calculateOvertime(e.getOvertimeAmt()) : 1);
			long shiftscale = ((e.getShiftAmt() != null) ? calculateShift(e.getShiftAmt()) : 1);
			
			long scale = leavescale + overtimescale + shiftscale;			
			e.setScale(scale);
			
			if(scale<=3) {
				e.setGrade("Resting State");
			} else if(scale>=4 && scale <=6) {
				e.setGrade("Low Stress");
			} else if(scale>=7 && scale <=10) {
				e.setGrade("Medium Stress");
			} else {
				e.setGrade("High Stress");
			}
			
			if("MEDIUM STRESS".equalsIgnoreCase(e.getGrade()) || "HIGH STRESS".equalsIgnoreCase(e.getGrade())) {
				if(leavescale >= overtimescale && leavescale >= shiftscale) {
					e.setRemarks("Less Leaves");
				} else if(overtimescale >= leavescale && overtimescale >= shiftscale) {
					e.setRemarks("Overtime Hours");
				} else if(shiftscale >= leavescale && shiftscale >= overtimescale) {
					e.setRemarks("High Shift Hours");
				}
			} else if("LOW STRESS".equalsIgnoreCase(e.getGrade())) {
				e.setRemarks("Doing Good");
			} else {
				e.setRemarks("Doing Great");
			}
			
			EmpWellnessEntity ew = empWellnessRepository.findByEmpIdAndType(e.getEmpid(), e.getType());
			if(ew != null)
				update(ew, e);
			else
				empWellnessRepository.save(e);
		}
	}

	@Transactional
	public void update(EmpWellnessEntity ew, EmpWellnessEntity e ) {
		ew.setScale(e.getScale()).setLeaveAmt(e.getLeaveAmt()).setShiftAmt(e.getShiftAmt()).setOvertimeAmt(e.getOvertimeAmt()).setRemarks(e.getRemarks()).setGrade(e.getGrade());
		empWellnessRepository.save(ew);
	}
	
	private long calculateLeaves(Long leavesAmt) {
		if(leavesAmt > 10) {
			return 1;
		} else if(leavesAmt >=6 && leavesAmt <=10) {
			return 2;
		} else if(leavesAmt >2 && leavesAmt <=5) {
			return 3;
		} else if(leavesAmt ==1 || leavesAmt == 2) {
			return 4;
		} else {
			return 5;
		}
	}
	
	private long calculateOvertime(Long overtimeAmt) {
		if(overtimeAmt>=21 && overtimeAmt<=75) {
			return 2;
		} else if(overtimeAmt>=76 && overtimeAmt<=150) {
			return 3;
		} else if(overtimeAmt>=151 && overtimeAmt<=210) {
			return 4;
		} else if(overtimeAmt>210) {
			return 5;
		} else {
			return 1;
		}
	}
	
	private long calculateShift(Long shiftAmt) {
		if(shiftAmt>=2 && shiftAmt <=4) {
			return 2;
		} else if(shiftAmt >=5 && shiftAmt <=10) {
			return 3;
		} else if(shiftAmt >=11 && shiftAmt <=15) {
			return 4;
		} else if(shiftAmt > 15) {
			return 5;
		} else {
			return 1;
		}
	}
	
}
