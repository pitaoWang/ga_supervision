package com.atosenet.ga.company.db.model.ga.company;

import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;

import lombok.Data;

@Data
public class AirportPartRepairDto {
	private AircraftPartsRepairLicense aircraftpart;
	private PersonQulificationInfo personQulificationInfo;

}
