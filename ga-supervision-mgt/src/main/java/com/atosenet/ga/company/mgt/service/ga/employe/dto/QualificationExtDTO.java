package com.atosenet.ga.company.mgt.service.ga.employe.dto;

import java.util.List;

import com.atosenet.ga.company.db.model.ga.employe.FlightDispatcherLicense;
import com.atosenet.ga.company.db.model.ga.employe.IntelligenceLicense;
import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;
import com.atosenet.ga.company.db.model.ga.employe.PilotLicense;
import com.atosenet.ga.company.db.model.ga.employe.PilotLicenseCheckRecord;
import com.atosenet.ga.company.db.model.ga.employe.WeatherLicense;

import lombok.Data;

@Data
public class QualificationExtDTO {
	
	private PersonQulificationInfo personQulificationInfo;
	
	private WeatherLicense weatherLicense;
	
	private FlightDispatcherLicense flightDispatcherLicense;
	
	private IntelligenceLicense intelligenceLicense;
	
	private PilotLicense pilotLicense;
	
	private List<PilotLicenseCheckRecord> licenseChecks;
	
}
