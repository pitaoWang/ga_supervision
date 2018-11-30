package com.atosenet.ga.company.mgt.service.ga.company.dto;

import java.util.List;

import com.atosenet.ga.company.db.model.ga.company.Course;
import com.atosenet.ga.company.db.model.ga.company.Engine;
import com.atosenet.ga.company.db.model.ga.company.Simulator;
import com.atosenet.ga.company.db.model.ga.company.SimulatorRecord;

import lombok.Data;
@Data
public class SimulatorDTO {
	
	private Simulator simulator;
	
	private List<Engine> engine;
	
	private List<Course> course;
	
	private List<SimulatorRecord> simulatorRecord;
}