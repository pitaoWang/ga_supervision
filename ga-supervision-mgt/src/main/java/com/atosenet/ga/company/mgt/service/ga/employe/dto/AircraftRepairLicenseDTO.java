/**
 * 
 */
package com.atosenet.ga.company.mgt.service.ga.employe.dto;

import com.atosenet.ga.company.db.model.ga.company.AircraftRepairLicense;
import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;

import lombok.Data;

/**
 * @author lvhao
 *
 */
@Data
public class AircraftRepairLicenseDTO {
	private AircraftRepairLicense aircraftrepair;
	private PersonQulificationInfo personQulificationInfo;
	
}
