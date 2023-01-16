package com.ukg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ukg.dao.EmpOverallRecommendRepository;
import com.ukg.dao.EmpRecommendRepository;
import com.ukg.dao.EmpWellnessRepository;
import com.ukg.dao.EmployeeRepository;
import com.ukg.dao.LeaveRepository;
import com.ukg.dao.OvertimeRepository;
import com.ukg.dao.ShiftRepository;
import com.ukg.entity.EmpOverallRecommendEntity;
import com.ukg.entity.EmpRecommendEntity;
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
	
	@Autowired
	private EmpRecommendRepository empRecommendRepository;
	
	@Autowired
	private EmpOverallRecommendRepository empOverallRecommendRepository;
	
	private Map<Long, EmpWellnessEntity> employeeWellnessMap;
	
	private Map<Long, EmpOverallRecommendEntity> employeeOverallRecommendMap;
	
	private List<EmpRecommendEntity> empRecommendEntity;
	
	@Scheduled(fixedRate = 10000, initialDelay = 1000)
	public void fixedRateSchedular() {
		employeeWellnessMap = new HashMap<>();
		employeeOverallRecommendMap = new HashMap<>();
		empRecommendEntity = empRecommendRepository.findAll();
		List<EmployeeEntity> employeeList = employeeRepository.findAll();
		for(EmployeeEntity e : employeeList) {
			EmpWellnessEntity empWellnessEntity = new EmpWellnessEntity().setEmpid(e.getEmpid()).setLeaveAmt(0l).setShiftAmt(1l).setOvertimeAmt(1l).setType("QUARTERLY").setManagerid(e.getManagerid());
			employeeWellnessMap.put(e.getEmpid(), empWellnessEntity);
			EmpOverallRecommendEntity empOverallRecommendEntity = new EmpOverallRecommendEntity().setEmpid(e.getEmpid());
			employeeOverallRecommendMap.put(e.getEmpid(), empOverallRecommendEntity);
		}
				
		calculateQuarterData(leaveRepository.findAllEmployeeFromLastQuarter(), overtimeRepository.findAllEmployeeFromLastQuarter(), shiftRepository.findAllEmployeeFromLastQuarter());

		employeeWellnessMap.clear();
		
		for(EmployeeEntity e : employeeList) {
			EmpWellnessEntity empWellnessEntity = new EmpWellnessEntity().setEmpid(e.getEmpid()).setLeaveAmt(0l).setShiftAmt(1l).setOvertimeAmt(1l).setType("BIMONTHLY").setManagerid(e.getManagerid());
			employeeWellnessMap.put(e.getEmpid(), empWellnessEntity);
		}
		
		calculateBiMonthlyData(leaveRepository.findAllEmployeeFromBiMonthly(), overtimeRepository.findAllEmployeeFromBiMonthly(), shiftRepository.findAllEmployeeFromBiMonthly());
		
		employeeWellnessMap.clear();
		
		for(EmployeeEntity e : employeeList) {
			EmpWellnessEntity empWellnessEntity = new EmpWellnessEntity().setEmpid(e.getEmpid()).setLeaveAmt(0l).setShiftAmt(1l).setOvertimeAmt(1l).setType("MONTHLY").setManagerid(e.getManagerid());
			employeeWellnessMap.put(e.getEmpid(), empWellnessEntity);
		}
		
		calculateMonthlyData(leaveRepository.findAllEmployeeFromLastMonth(), overtimeRepository.findAllEmployeeFromLastMonth(), shiftRepository.findAllEmployeeFromLastMonth());
		
		employeeWellnessMap.clear();
		
		for(Map.Entry<Long, EmpOverallRecommendEntity> entry : employeeOverallRecommendMap.entrySet()) {
			EmpOverallRecommendEntity overallRecommendEntity = empOverallRecommendRepository.fetchEmployeeData(entry.getKey());
			EmpOverallRecommendEntity recomendentity = entry.getValue();
			if(overallRecommendEntity != null) {
				overallRecommendEntity.setLast30days(recomendentity.getLast30days()).setLast60days(recomendentity.getLast60days()).setLast90days(recomendentity.getLast90days());
				empOverallRecommendRepository.save(overallRecommendEntity);
			} else {				
				empOverallRecommendRepository.save(recomendentity);
			}
		}
		
		employeeOverallRecommendMap.clear();
	}

	private void calculateMonthlyData(List<LeaveEntity> leaveList,
			List<OvertimeEntity> overtimeList, List<ShiftEntity> shiftList) {
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
			long leavescale = (e.getLeaveAmt() != null) ? calculateLeavesForMonth(e.getLeaveAmt()) : 5;
			long overtimescale = ((e.getOvertimeAmt() != null) ? calculateOvertimeForMonth(e.getOvertimeAmt()) : 1);
			long shiftscale = ((e.getShiftAmt() != null) ? calculateShiftForMonth(e.getShiftAmt()) : 1);
			
			long scale = leavescale + overtimescale + shiftscale;			
			e.setScale(scale);
			
			if(scale<=3) {
				e.setGrade("Congratulations you have managed stress");
			} else if(scale>=4 && scale <=5) {
				e.setGrade("Moderate Stress");
			} else if(scale>=6 && scale <=8) {
				e.setGrade("Stressed");
			} else {
				e.setGrade("High Stress");
			}
			
			if("Stressed".equalsIgnoreCase(e.getGrade()) || "HIGH STRESS".equalsIgnoreCase(e.getGrade())) {
				if(leavescale > overtimescale && leavescale > shiftscale) {
					e.setRemarks("Less Leaves");
				} else if(overtimescale > leavescale && overtimescale > shiftscale) {
					e.setRemarks("Overtime Hours");
				} else if(shiftscale > leavescale && shiftscale > overtimescale) {
					e.setRemarks("Night Shifts");
				} else if(leavescale == overtimescale) {
					e.setRemarks("Less Leaves and More Night Shifts");
				} else if(leavescale == shiftscale) {
					e.setRemarks("Less Leaves and Overtime Hours");
				} else if(overtimescale == shiftscale) {
					e.setRemarks("Night Shifts and Overtime Hours");
				} else if(leavescale == overtimescale && leavescale == shiftscale && overtimescale == shiftscale) {
					e.setRemarks("Night Shifts, Overtime Hours and Less Leaves");
				}
			} else if("Moderate Stress".equalsIgnoreCase(e.getGrade())) {
				if(leavescale >= overtimescale && leavescale >= shiftscale) {
					e.setRemarks("Less Leaves");
				} else if(overtimescale >= leavescale && overtimescale >= shiftscale) {
					e.setRemarks("Overtime Hours");
				} else if(shiftscale >= leavescale && shiftscale >= overtimescale) {
					e.setRemarks("Night Shifts");
				}
			} else {
				e.setRemarks(CONGRATULATIONS_YOH_HAVE_MANAGED_STRESS);
			}
			
			EmpWellnessEntity ew = empWellnessRepository.findByEmpIdAndType(e.getEmpid(), e.getType());
			if(ew != null)
				update(ew, e);
			else
				empWellnessRepository.save(e);
			
			EmpOverallRecommendEntity empOverallRecommendEntity = employeeOverallRecommendMap.get(e.getEmpid());
			empOverallRecommendEntity.setLast30days(e.getScale());
			employeeOverallRecommendMap.put(e.getEmpid(), empOverallRecommendEntity);
			
		}
	}
	
	private static final String CONGRATULATIONS_YOH_HAVE_MANAGED_STRESS = "Doing Great !!! Your energy is currency, Spend it well.";
	
	private void calculateBiMonthlyData(List<LeaveEntity> leaveList,
			List<OvertimeEntity> overtimeList, List<ShiftEntity> shiftList) {
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
			long leavescale = (e.getLeaveAmt() != null) ? calculateLeavesForBiMonth(e.getLeaveAmt()) : 7;
			long overtimescale = ((e.getOvertimeAmt() != null) ? calculateOvertimeForBiMonth(e.getOvertimeAmt()) : 1);
			long shiftscale = ((e.getShiftAmt() != null) ? calculateShiftForBiMonth(e.getShiftAmt()) : 1);
			
			long scale = leavescale + overtimescale + shiftscale;			
			e.setScale(scale);
			
			if(scale<=3) {
				e.setGrade("Congratulations you have managed stress");
			} else if(scale>=4 && scale <=5) {
				e.setGrade("Moderate Stress");
			} else if(scale>=6 && scale <=8) {
				e.setGrade("Stressed");
			} else {
				e.setGrade("High Stress");
			}
			
			if("Stressed".equalsIgnoreCase(e.getGrade()) || "HIGH STRESS".equalsIgnoreCase(e.getGrade())) {
				if(leavescale > overtimescale && leavescale > shiftscale) {
					e.setRemarks("Less Leaves");
				} else if(overtimescale > leavescale && overtimescale > shiftscale) {
					e.setRemarks("Overtime Hours");
				} else if(shiftscale > leavescale && shiftscale > overtimescale) {
					e.setRemarks("Night Shifts");
				} else if(leavescale == overtimescale) {
					e.setRemarks("Less Leaves and More Night Shifts");
				} else if(leavescale == shiftscale) {
					e.setRemarks("Less Leaves and Overtime Hours");
				} else if(overtimescale == shiftscale) {
					e.setRemarks("Night Shifts and Overtime Hours");
				} else if(leavescale == overtimescale && leavescale == shiftscale && overtimescale == shiftscale) {
					e.setRemarks("Night Shifts, Overtime Hours and Less Leaves");
				}
			} else if("Moderate STRESS".equalsIgnoreCase(e.getGrade())) {
				if(leavescale >= overtimescale && leavescale >= shiftscale) {
					e.setRemarks("Less Leaves");
				} else if(overtimescale >= leavescale && overtimescale >= shiftscale) {
					e.setRemarks("Overtime Hours");
				} else if(shiftscale >= leavescale && shiftscale >= overtimescale) {
					e.setRemarks("Night Shifts");
				}
			} else {
				e.setRemarks(CONGRATULATIONS_YOH_HAVE_MANAGED_STRESS);
			}
			
			EmpWellnessEntity ew = empWellnessRepository.findByEmpIdAndType(e.getEmpid(), e.getType());
			if(ew != null)
				update(ew, e);
			else
				empWellnessRepository.save(e);
			
			EmpOverallRecommendEntity empOverallRecommendEntity = employeeOverallRecommendMap.get(e.getEmpid());
			empOverallRecommendEntity.setLast60days(e.getScale());
			employeeOverallRecommendMap.put(e.getEmpid(), empOverallRecommendEntity);
		}
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
			long leavescale = (e.getLeaveAmt() != null) ? calculateLeaves(e.getLeaveAmt()) : 10;
			long overtimescale = ((e.getOvertimeAmt() != null) ? calculateOvertime(e.getOvertimeAmt()) : 1);
			long shiftscale = ((e.getShiftAmt() != null) ? calculateShift(e.getShiftAmt()) : 1);
			
			long scale = leavescale + overtimescale + shiftscale;			
			e.setScale(scale);
			
			if(scale<=3) {
				e.setGrade("Congratulations you have managed stress");
			} else if(scale>=4 && scale <=5) {
				e.setGrade("Moderate Stress");
			} else if(scale>=6 && scale <=8) {
				e.setGrade("Stressed");
			} else {
				e.setGrade("High Stress");
			}
			
			if("Stressed".equalsIgnoreCase(e.getGrade()) || "HIGH STRESS".equalsIgnoreCase(e.getGrade())) {
				if(leavescale > overtimescale && leavescale > shiftscale) {
					e.setRemarks("Less Leaves");
				} else if(overtimescale > leavescale && overtimescale > shiftscale) {
					e.setRemarks("Overtime Hours");
				} else if(shiftscale > leavescale && shiftscale > overtimescale) {
					e.setRemarks("Night Shifts");
				} else if(leavescale == overtimescale) {
					e.setRemarks("Less Leaves and More Night Shifts");
				} else if(leavescale == shiftscale) {
					e.setRemarks("Less Leaves and Overtime Hours");
				} else if(overtimescale == shiftscale) {
					e.setRemarks("Night Shifts and Overtime Hours");
				} else if(leavescale == overtimescale && leavescale == shiftscale && overtimescale == shiftscale) {
					e.setRemarks("Night Shifts, Overtime Hours and Less Leaves");
				}
			} else if("Moderate STRESS".equalsIgnoreCase(e.getGrade())) {
				if(leavescale >= overtimescale && leavescale >= shiftscale) {
					e.setRemarks("Less Leaves");
				} else if(overtimescale >= leavescale && overtimescale >= shiftscale) {
					e.setRemarks("Overtime Hours");
				} else if(shiftscale >= leavescale && shiftscale >= overtimescale) {
					e.setRemarks("Night Shifts");
				}
			} else {
				e.setRemarks(CONGRATULATIONS_YOH_HAVE_MANAGED_STRESS);
			}
			
			EmpWellnessEntity ew = empWellnessRepository.findByEmpIdAndType(e.getEmpid(), e.getType());
			if(ew != null)
				update(ew, e);
			else
				empWellnessRepository.save(e);
			
			EmpRecommendEntity entity = empRecommendEntity.stream().filter(er -> er.getGrade().equalsIgnoreCase(e.getGrade()) && er.getRemarks().equalsIgnoreCase(e.getRemarks())).findAny().get();
			EmpOverallRecommendEntity empOverallRecommendEntity = employeeOverallRecommendMap.get(e.getEmpid());
			empOverallRecommendEntity.setLast90days(e.getScale()).setOverallrecommend(entity.getRecommend());
			employeeOverallRecommendMap.put(e.getEmpid(), empOverallRecommendEntity);
		}
	}

	@Transactional
	public void update(EmpWellnessEntity ew, EmpWellnessEntity e ) {
		ew.setScale(e.getScale()).setLeaveAmt(e.getLeaveAmt()).setShiftAmt(e.getShiftAmt()).setOvertimeAmt(e.getOvertimeAmt()).setRemarks(e.getRemarks()).setGrade(e.getGrade());
		empWellnessRepository.save(ew);
	}
	
	private long calculateLeaves(Long leavesAmt) {
		if(leavesAmt > 15) {
			return 1;
		} else if(leavesAmt >=14 && leavesAmt <=15) {
			return 2;
		} else if(leavesAmt >=12 && leavesAmt <=13) {
			return 3;
		} else if(leavesAmt ==10 || leavesAmt == 11) {
			return 4;
		} else if(leavesAmt ==8 || leavesAmt == 9) {
			return 5;
		} else if(leavesAmt ==6 || leavesAmt == 7) {
			return 6;
		} else if(leavesAmt ==4 || leavesAmt == 5) {
			return 7;
		} else if(leavesAmt ==2 || leavesAmt == 3) {
			return 8;
		} else if(leavesAmt ==1) {
			return 9;
		} else {
			return 10;
		}
	}
	
	private long calculateLeavesForMonth(Long leavesAmt) {
		if(leavesAmt > 4) {
			return 1;
		} else if(leavesAmt >=3 && leavesAmt <=4) {
			return 2;
		} else if(leavesAmt ==2) {
			return 3;
		} else if(leavesAmt ==1) {
			return 4;
		} else {
			return 5;
		}
	}
	
	private long calculateLeavesForBiMonth(Long leavesAmt) {
		if(leavesAmt > 7) {
			return 1;
		} else if(leavesAmt == 6) {
			return 2;
		} else if(leavesAmt ==5) {
			return 3;
		} else if(leavesAmt ==4) {
			return 4;
		}    else if(leavesAmt ==2) {
			return 5;
		} else if(leavesAmt ==1) {
			return 6;
		} else {
			return 7;
		}
	}
	
	private long calculateOvertime(Long overtimeAmt) {
		if(overtimeAmt>=21 && overtimeAmt<=40) {
			return 2;
		} else if(overtimeAmt>=41 && overtimeAmt<=65) {
			return 3;
		} else if(overtimeAmt>=66 && overtimeAmt<=90) {
			return 4;
		} else if(overtimeAmt>=91 && overtimeAmt<=110) {
			return 5;
		} else if(overtimeAmt>=111 && overtimeAmt<=130) {
			return 6;
		} else if(overtimeAmt>=131 && overtimeAmt<=150) {
			return 7;
		} else if(overtimeAmt>=151 && overtimeAmt<=175) {
			return 8;
		} else if(overtimeAmt>=176 && overtimeAmt<=210) {
			return 9;
		}  else if(overtimeAmt>210) {
			return 10;
		} else {
			return 1;
		}
	}
	
	private long calculateOvertimeForMonth(Long overtimeAmt) {
		if(overtimeAmt>=5 && overtimeAmt<=15) {
			return 2;
		} else if(overtimeAmt>=16 && overtimeAmt<=25) {
			return 3;
		} else if(overtimeAmt>=26 && overtimeAmt<=35) {
			return 4;
		} else if(overtimeAmt>35) {
			return 5;
		} else {
			return 1;
		}
	}
	
	private long calculateOvertimeForBiMonth(Long overtimeAmt) {
		if(overtimeAmt>=10 && overtimeAmt<=25) {
			return 2;
		} else if(overtimeAmt>=26 && overtimeAmt<=35) {
			return 3;
		} else if(overtimeAmt>=36 && overtimeAmt<=45) {
			return 4;
		} else if(overtimeAmt>=46 && overtimeAmt<=55) {
			return 5;
		}  else if(overtimeAmt>=56 && overtimeAmt<=70) {
			return 6;
		}  else if(overtimeAmt>70) {
			return 7;
		} else {
			return 1;
		}
	}
	
	private long calculateShift(Long shiftAmt) {
		if(shiftAmt>=5 && shiftAmt <=7) {
			return 2;
		} else if(shiftAmt >=8 && shiftAmt <=12) {
			return 3;
		} else if(shiftAmt >=13 && shiftAmt <=15) {
			return 4;
		} else if(shiftAmt >=16 && shiftAmt <=18) {
			return 5;
		}  else if(shiftAmt >=19 && shiftAmt <=21) {
			return 6;
		}  else if(shiftAmt >=22 && shiftAmt <=24) {
			return 7;
		}  else if(shiftAmt >=25 && shiftAmt <=27) {
			return 8;
		}  else if(shiftAmt >=28 && shiftAmt <=30) {
			return 9;
		}  else if(shiftAmt > 31) {
			return 10;
		} else {
			return 1;
		}
	}
	
	private long calculateShiftForMonth(Long shiftAmt) {
		if(shiftAmt>=2 && shiftAmt <=3) {
			return 2;
		} else if(shiftAmt >=4 && shiftAmt <=6) {
			return 3;
		} else if(shiftAmt >=7 && shiftAmt <=9) {
			return 4;
		} else if(shiftAmt > 9) {
			return 5;
		} else {
			return 1;
		}
	}
	
	private long calculateShiftForBiMonth(Long shiftAmt) {
		if(shiftAmt>=4 && shiftAmt <=6) {
			return 2;
		} else if(shiftAmt >=7 && shiftAmt <=9) {
			return 3;
		} else if(shiftAmt >=10 && shiftAmt <=11) {
			return 4;
		} else if(shiftAmt >=12 && shiftAmt<=14) {
			return 5;
		} else if(shiftAmt >=15 && shiftAmt<=17) {
			return 6;
		} else if(shiftAmt > 17) {
			return 7;
		}    else {
			return 1;
		}
	}
	
}
