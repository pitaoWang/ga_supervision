/**
 * 
 */
package com.atosenet.ga.company.mgt.service.ga.employe.dto;

import java.util.List;

import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;
import com.atosenet.ga.company.db.model.ga.employe.PilotLicenseCheckRecord;

import lombok.Data;

/**
 * @author lvhao
 *
 */
@Data
public class ControlLicenseDTO {
	private PersonQulificationInfo personQulificationInfo;
	private List<PilotLicenseCheckRecord> licenseChecks;

}
