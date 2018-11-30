package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.AircraftRepairLicense;
import com.atosenet.ga.company.db.model.ga.company.AircraftRepairLicenseModelCheck;
import com.atosenet.ga.company.db.model.ga.company.AircraftRepairLicenseSpecialCheck;
import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;
import com.atosenet.ga.company.db.repos.ga.company.AircraftRepairLicenseRepository;
import com.atosenet.ga.company.mgt.service.ga.employe.PersonQulificationInfoService;
import com.atosenet.ga.company.mgt.service.ga.company.AircraftRepairLicenseSpecialCheckService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AircraftRepairLicenseService {

	@Autowired
	private AircraftRepairLicenseRepository apr;
	@Autowired
	PersonQulificationInfoService personQulificationInfoService;

	@Autowired
	AircraftRepairLicenseModelCheckService aircraftRepairLicenseModelCheckService;

	@Autowired
	AircraftRepairLicenseSpecialCheckService aircraftRepairLicenseSpecialService;

	/**
	 * 航空器维修执照
	 */
	@Transactional(readOnly = false)
	public AircraftRepairLicense save(AircraftRepairLicense a, PersonQulificationInfo info) {
		PersonQulificationInfo saveinfo = personQulificationInfoService.save(info);
		a.setId(saveinfo.getId());
		apr.save(a);
		System.out.println(a.getArlmc());
		if (a != null && a.getArlmc() != null && a.getArlmc().size() > 0) {
			aircraftRepairLicenseModelCheckService.deleteByLicense(a.getId());
			for (AircraftRepairLicenseModelCheck aircraftRepairLicenseModelCheck : a.getArlmc()) {
				aircraftRepairLicenseModelCheck.setLicenseId(a.getId());
				aircraftRepairLicenseModelCheckService.save(aircraftRepairLicenseModelCheck);
			}
		}
		if(a.getArlmc().size()==0){
			aircraftRepairLicenseModelCheckService.deleteByLicense(a.getId());
		}
		
		System.out.println(a.getArlsc());
		if (a != null && a.getArlsc() != null && a.getArlsc().size() > 0) {
			aircraftRepairLicenseSpecialService.deleteByLicense(a.getId());
			for (AircraftRepairLicenseSpecialCheck Special : a.getArlsc()) {
				Special.setLicenseId(a.getId());
				aircraftRepairLicenseSpecialService.save(Special);
			}
		}
		if(a.getArlsc().size() == 0){
			aircraftRepairLicenseSpecialService.deleteByLicense(a.getId());
		}
		return a;
	}

	public AircraftRepairLicense getId(long id) {
		AircraftRepairLicense aircraftRepairLicense = apr.findOne(id);
		if (aircraftRepairLicense != null) {
			aircraftRepairLicense.setArlsc(aircraftRepairLicenseSpecialService.findByLicenseId(id));
			aircraftRepairLicense.setArlmc(aircraftRepairLicenseModelCheckService.findByLicenseId(id));
		}
		return aircraftRepairLicense;
	}

	public List<AircraftRepairLicense> findAll() {
		return apr.findList();
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		personQulificationInfoService.delete(id);
		aircraftRepairLicenseSpecialService.deleteByLicense(id);
		aircraftRepairLicenseModelCheckService.deleteByLicense(id);
		apr.delete(id);
	}
}
