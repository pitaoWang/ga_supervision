package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.AircraftPartsRepairLicense;
import com.atosenet.ga.company.db.model.ga.company.AircraftPartsRepairLicenseProjectCheck;
import com.atosenet.ga.company.db.model.ga.company.AircraftPartsRepairLicenseSpecialCheck;
import com.atosenet.ga.company.db.model.ga.company.vo.AircraftPartsRepairLicenseVO;
import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;
import com.atosenet.ga.company.db.repos.ga.company.AircraftPartsRepairLicenseRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PersonQulificationInfoRepository;
import com.atosenet.ga.company.mgt.service.ga.employe.PersonQulificationInfoService;
import com.atosenet.ga.company.mgt.service.ga.company.AircraftPartsRepairLicenseSpecialCheckService;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class AircraftPartsRepairLicenseService {

	@Autowired
	private AircraftPartsRepairLicenseRepository apr;
	@Autowired
	PersonQulificationInfoRepository personQulificationInfoRepository;
	@Autowired
	PersonQulificationInfoService personQulificationInfoService;

	@Autowired
	AircraftPartsRepairLicenseProjectCheckService aircraftPartsRepairLicenseProjectCheckService;

	@Autowired
	AircraftPartsRepairLicenseSpecialCheckService aircraftPartsRepairLicenseSpecialService;

	/**
	 * 航空器部件维修执照
	 */
	@Transactional(readOnly = false)
	public AircraftPartsRepairLicense save(AircraftPartsRepairLicense a, PersonQulificationInfo info) {
		PersonQulificationInfo saveinfo = personQulificationInfoService.save(info);
		a.setId(saveinfo.getId());
		AircraftPartsRepairLicense apart = apr.save(a);
		System.out.println(a.getAprlpc());
		if (a != null && a.getAprlpc() != null && a.getAprlpc().size() > 0) {
			aircraftPartsRepairLicenseProjectCheckService.deleteByLicense(a.getId());
			for (AircraftPartsRepairLicenseProjectCheck aircraftPartsRepairLicenseProjectCheck : a.getAprlpc()) {
				aircraftPartsRepairLicenseProjectCheck.setLicenseId(a.getId());
				aircraftPartsRepairLicenseProjectCheckService.save(aircraftPartsRepairLicenseProjectCheck);
			}
		}
		if(a.getAprlpc().size() == 0){
			aircraftPartsRepairLicenseProjectCheckService.deleteByLicense(a.getId());
		}
		
		
		
		System.out.println(a.getAprlsc());
		if (a != null && a.getAprlsc() != null && a.getAprlsc().size() > 0) {
			aircraftPartsRepairLicenseSpecialService.deleteByLicense(a.getId());
			for (AircraftPartsRepairLicenseSpecialCheck Special : a.getAprlsc()) {
				Special.setLicenseId(a.getId());
				aircraftPartsRepairLicenseSpecialService.save(Special);
			}
		}
		if(a.getAprlsc().size() == 0){
			aircraftPartsRepairLicenseSpecialService.deleteByLicense(a.getId());
		}
		
		return a;
	}

	public AircraftPartsRepairLicense getId(long id) {
		AircraftPartsRepairLicense aircraftPartsRepairLicense = apr.findOne(id);
		if (aircraftPartsRepairLicense != null) {
			aircraftPartsRepairLicense.setAprlsc(aircraftPartsRepairLicenseSpecialService.findByLicenseId(id));
			aircraftPartsRepairLicense.setAprlpc(aircraftPartsRepairLicenseProjectCheckService.findByLicenseId(id));
		}
		return aircraftPartsRepairLicense;
	}

	public List<AircraftPartsRepairLicense> findAll() {
		return apr.findList();
	}

	@Transactional(readOnly = false)
	public void delete(long id) {
		personQulificationInfoService.delete(id);
		aircraftPartsRepairLicenseSpecialService.deleteByLicense(id);
		aircraftPartsRepairLicenseProjectCheckService.deleteByLicense(id);
		apr.delete(id);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: geaAllById 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	public AircraftPartsRepairLicenseVO geaAllById(Long id) {
		AircraftPartsRepairLicenseVO avo = new AircraftPartsRepairLicenseVO();
		AircraftPartsRepairLicense aircraftPartsRepairLicense = apr.findOne(id);
		if (aircraftPartsRepairLicense != null) {
			aircraftPartsRepairLicense.setAprlsc(aircraftPartsRepairLicenseSpecialService.findByLicenseId(id));
			aircraftPartsRepairLicense.setAprlpc(aircraftPartsRepairLicenseProjectCheckService.findByLicenseId(id));
		}
		PersonQulificationInfo info = personQulificationInfoRepository.findById(id);
		avo.setAircraftPartsRepairLicense(aircraftPartsRepairLicense);
		avo.setPersonQulificationInfo(info);
		return avo;
	}
}
