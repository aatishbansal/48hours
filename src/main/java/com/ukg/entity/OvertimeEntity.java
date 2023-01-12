package com.ukg.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "overtime")
public class OvertimeEntity {

	@Id
	private Long overtimeid;
	
	@Column(name ="empid")
	private Long empid;
	
	@Column(name ="punchin")
	private LocalDateTime punchin;
	
	@Column(name ="punchout")
	private LocalDateTime punchout;
	
	@Column(name ="durationhrs")
	private Long durationhrs;
}
