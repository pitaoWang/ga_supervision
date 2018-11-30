package com.atosenet.ga.company.mgt.service.ga.employe.dto;

import java.util.List;

import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;
import com.atosenet.ga.company.db.model.ga.employe.PilotLicense;
import com.atosenet.ga.company.db.model.ga.employe.PilotLicenseCheckRecord;

import lombok.Data;

@Data
public class PersonQulificationInfoDTO {
	
	private PersonQulificationInfo personQulificationInfo;
	
	private PilotLicense pilotLicense;
	private List<PilotLicenseCheckRecord> licenseChecks;
}
