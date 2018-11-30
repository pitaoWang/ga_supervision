package com.atosenet.ga.company.mgt.service.ga.employe.dto;

import com.atosenet.ga.company.db.model.ga.employe.ControlEmpl;
import com.atosenet.ga.company.db.model.ga.employe.Employee;
import com.atosenet.ga.company.db.model.ga.employe.Pilot;

import lombok.Data;

@Data
public class EmployeePilotDTO {
	
	private Employee employee;
	
	private Pilot pilot;
	
	private ControlEmpl control;

}
