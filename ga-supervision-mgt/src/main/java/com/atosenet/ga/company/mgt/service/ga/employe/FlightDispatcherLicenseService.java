package com.atosenet.ga.company.mgt.service.ga.employe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.FlightDispatcherLicense;
import com.atosenet.ga.company.db.model.ga.employe.FlightDispatcherLicenseDTO;
import com.atosenet.ga.company.db.model.ga.employe.IntelligenceLicense;
import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;
import com.atosenet.ga.company.db.model.ga.employe.WeatherLicenseDTO;
import com.atosenet.ga.company.db.repos.ga.employe.FlightDispatcherLicenseRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PersonQulificationInfoRepository;



@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class FlightDispatcherLicenseService {
	@Autowired
	FlightDispatcherLicenseRepository flightDispatcherLicenseRepository;
	@Autowired
	PersonQulificationInfoRepository personQulificationInfoRepository;
	
	/**
	 * 增加
	 */
	@Transactional(readOnly=false)
    public void save(FlightDispatcherLicense record){
		flightDispatcherLicenseRepository.save(record);
    }
	
	/**
	 *删除
	 */
	@Transactional(readOnly=false)
    public void delete(Long id){
		flightDispatcherLicenseRepository.delete(id);
    }
	
	/**
	 * 查询
	 */
	@Transactional(readOnly=true)
    public FlightDispatcherLicense findById(long id){	
		return  flightDispatcherLicenseRepository.findOne(id);
    }

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: selectPage 
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly=true)
	public Page<FlightDispatcherLicense> selectPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.flightDispatcherLicenseRepository.findAll(pageable);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findAllById 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	public FlightDispatcherLicenseDTO findAllById(Long id) {
		FlightDispatcherLicenseDTO flightDispatcherLicenseDTO = new FlightDispatcherLicenseDTO();
		flightDispatcherLicenseDTO.setFlightDispatcherLicense(flightDispatcherLicenseRepository.findOne(id));
		PersonQulificationInfo info = personQulificationInfoRepository.findById(id);
		flightDispatcherLicenseDTO.setPersonQulificationInfo(info);
		return flightDispatcherLicenseDTO;
	}
}
