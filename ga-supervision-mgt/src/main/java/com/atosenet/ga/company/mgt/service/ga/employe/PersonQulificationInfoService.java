package com.atosenet.ga.company.mgt.service.ga.employe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.FlightDispatcherLicense;
import com.atosenet.ga.company.db.model.ga.employe.IntelligenceLicense;
import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;
import com.atosenet.ga.company.db.model.ga.employe.PilotLicense;
import com.atosenet.ga.company.db.model.ga.employe.PilotLicenseCheckRecord;
import com.atosenet.ga.company.db.model.ga.employe.WeatherLicense;
import com.atosenet.ga.company.db.repos.ga.employe.FlightDispatcherLicenseRepository;


import com.atosenet.ga.company.db.repos.ga.employe.GroundInstructorLicenseRepository;
import com.atosenet.ga.company.db.repos.ga.employe.IntelligenceLicenseRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PersonQulificationInfoRepository;
import com.atosenet.ga.company.db.repos.ga.employe.WeatherLicenseRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PilotLicenseCheckRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PilotLicenseRepository;
import com.atosenet.ga.company.mgt.service.ga.employe.dto.ControlLicenseDTO;
import com.atosenet.ga.company.mgt.service.ga.employe.dto.PersonQulificationInfoDTO;
import com.atosenet.ga.company.mgt.service.ga.employe.dto.QualificationExtDTO;
/**
 * @Description:TODO
 * @time:2017年9月1日 上午8:00:48
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class PersonQulificationInfoService {

	@Autowired
	PersonQulificationInfoRepository personQulificationInfoRepository;
	@Autowired
	PilotLicenseRepository pilotLicenseRepository;
	@Autowired
	PilotLicenseCheckRepository pilotLicenseCheckRepository;

	@Autowired
	GroundInstructorLicenseRepository groundInstructorLicenseRepository;
	@Autowired
	WeatherLicenseRepository weatherLicenseRepository;
	@Autowired
	FlightDispatcherLicenseRepository flightDispatcherLicenseRepository;
	@Autowired
	IntelligenceLicenseRepository intelligenceLicenseRepository;

	/**
	 * 增加
	 * @return 
	 */
	@Transactional(readOnly=false)
    public  PersonQulificationInfo  save(PersonQulificationInfo info){
		return personQulificationInfoRepository.save(info);
    }
	
	@Transactional(readOnly=false)
    public PersonQulificationInfo save(PersonQulificationInfo info,PilotLicense PilotLicense,List<PilotLicenseCheckRecord> licenseChecks){
		 personQulificationInfoRepository.save(info);
		 PilotLicense.setId(info.getId());
		 pilotLicenseRepository.save(PilotLicense);
		 for(int i=0;i<licenseChecks.size();i++) {
			 licenseChecks.get(i).setLicenseId(info.getId());
		 }
		return  personQulificationInfoRepository.save(info);
	
		 
    }
	
	
	/**
	 *删除
	 */
	@Transactional(readOnly=false)
    public List<PersonQulificationInfo> findByEmployeeId(Long employeeId){
		return personQulificationInfoRepository.findByEmployeeId(employeeId);
    }
	
	/**
	 *删除
	 */
	@Transactional(readOnly=false)
    public void delete(Long id){
		personQulificationInfoRepository.delete(id);
    }
	
	
	@Transactional(readOnly=false)
	public PersonQulificationInfoDTO getPersonQulificationInfoById1(String licenseId){
		PersonQulificationInfoDTO dto = new PersonQulificationInfoDTO();
		PersonQulificationInfo info = personQulificationInfoRepository.findById(Long.parseLong(licenseId));
		dto.setPersonQulificationInfo(info);
		dto.setPilotLicense(pilotLicenseRepository.findById(Long.parseLong(licenseId)));
		//check record
		dto.setLicenseChecks(pilotLicenseCheckRepository.findByLicenseId(Long.parseLong(licenseId)));
			
		
		return dto;
	}
	
	@Transactional(readOnly=false)
	public PersonQulificationInfoDTO getPersonQulificationInfoById(long employeeId){
		PersonQulificationInfoDTO dto = new PersonQulificationInfoDTO();
		List<PersonQulificationInfo> list = personQulificationInfoRepository.findByEmployeeId(employeeId);
		for(int i=0;i<list.size();i++) {
			if(pilotLicenseRepository.findById(list.get(i).getId())!=null) {
				dto.setPilotLicense(pilotLicenseRepository.findById(list.get(i).getId()));
				dto.setPersonQulificationInfo(list.get(i));
				//check record
				dto.setLicenseChecks(pilotLicenseCheckRepository.findByLicenseId(list.get(i).getId()));
				
				return dto;
			}
		}
		
			
		
		return dto;
	}
	
	/**
	 * 增加人员资质与飞行员签派资质信息
	 * @return 
	 */
	@Transactional(readOnly=false)
    public  void  saveLicense(QualificationExtDTO info){
		PersonQulificationInfo pqInfo = personQulificationInfoRepository.save(info.getPersonQulificationInfo());
		if("气象执照".equals(info.getPersonQulificationInfo().getLicensetype()))
		{
			info.getWeatherLicense().setId(pqInfo.getId());
			weatherLicenseRepository.save(info.getWeatherLicense());
			for(int i=0;i<info.getLicenseChecks().size();i++) {
				info.getLicenseChecks().get(i).setLicenseId(pqInfo.getId());
				pilotLicenseCheckRepository.save(info.getLicenseChecks().get(i));
			 }
		}
		else if("飞行签派员执照".equals(info.getPersonQulificationInfo().getLicensetype()))
		{
			info.getFlightDispatcherLicense().setId(pqInfo.getId());
			flightDispatcherLicenseRepository.save(info.getFlightDispatcherLicense());
			for(int i=0;i<info.getLicenseChecks().size();i++) {
				info.getLicenseChecks().get(i).setLicenseId(pqInfo.getId());
				pilotLicenseCheckRepository.save(info.getLicenseChecks().get(i));
			 }
		}
		else if("情报执照".equals(info.getPersonQulificationInfo().getLicensetype()))
		{
			info.getIntelligenceLicense().setId(pqInfo.getId());
			intelligenceLicenseRepository.save(info.getIntelligenceLicense());
			for(int i=0;i<info.getLicenseChecks().size();i++) {
				info.getLicenseChecks().get(i).setLicenseId(pqInfo.getId());
				pilotLicenseCheckRepository.save(info.getLicenseChecks().get(i));
			 }
		}
		else if("飞行执照".equals(info.getPersonQulificationInfo().getLicensetype()))
		{
			info.getPilotLicense().setId(pqInfo.getId());
			pilotLicenseRepository.save(info.getPilotLicense());
			for(int i=0;i<info.getLicenseChecks().size();i++) {
				info.getLicenseChecks().get(i).setLicenseId(pqInfo.getId());
				pilotLicenseCheckRepository.save(info.getLicenseChecks().get(i));
			 }
		}
    }
	
	/**
	 *删除
	 */
	@Transactional(readOnly=false)
    public void deleteLicense(PersonQulificationInfo info){
		personQulificationInfoRepository.delete(info.getId());
		if("气象执照".equals(info.getLicensetype()))
		{
			weatherLicenseRepository.delete(info.getId());
		}
		else if("飞行签派员执照".equals(info.getLicensetype()))
		{
			flightDispatcherLicenseRepository.delete(info.getId());
		}
		else if("情报执照".equals(info.getLicensetype()))
		{
			intelligenceLicenseRepository.delete(info.getId());
		}
    }
	
	/**
	 * 查询人员资质与执照信息
	 * @return 
	 */
	@Transactional(readOnly=false)
	public QualificationExtDTO searchLicenseByIdAndType(String id, String licensetype) {
		
		QualificationExtDTO qualificationExtDTO=new QualificationExtDTO();
		PersonQulificationInfo personQulificationInfo1= personQulificationInfoRepository.findByIdAndLicensetype(Long.parseLong(id),licensetype);
		qualificationExtDTO.setPersonQulificationInfo(personQulificationInfo1);
		qualificationExtDTO.setLicenseChecks(pilotLicenseCheckRepository.findByLicenseId(Long.parseLong(id)));
		if("气象执照".equals(licensetype))
		{
			WeatherLicense weatherLicense= weatherLicenseRepository.findOne(Long.parseLong(id));
			
			qualificationExtDTO.setWeatherLicense(weatherLicense);
			
			qualificationExtDTO.setLicenseChecks(pilotLicenseCheckRepository.findByLicenseId(Long.parseLong(id)));
			
		}
		else if("飞行签派员执照".equals(licensetype))
		{
			FlightDispatcherLicense flightDispatcherLicense= flightDispatcherLicenseRepository.findOne(Long.parseLong(id));
			
			qualificationExtDTO.setFlightDispatcherLicense(flightDispatcherLicense);
			
			qualificationExtDTO.setLicenseChecks(pilotLicenseCheckRepository.findByLicenseId(Long.parseLong(id)));
		}
		else if("情报执照".equals(licensetype))
		{
			IntelligenceLicense intelligenceLicense= intelligenceLicenseRepository.findOne(Long.parseLong(id));
			
			qualificationExtDTO.setIntelligenceLicense(intelligenceLicense);
			
			qualificationExtDTO.setLicenseChecks(pilotLicenseCheckRepository.findByLicenseId(Long.parseLong(id)));
		}
		return qualificationExtDTO;
	}

	
	public PersonQulificationInfo getId(Long id) {
		return personQulificationInfoRepository.findById(id);
	}
	
	@Transactional(readOnly=false)
	public ControlLicenseDTO getControlLicenseInfo(String licenseId) {
		// TODO Auto-generated method stub
		ControlLicenseDTO dto = new ControlLicenseDTO();
		PersonQulificationInfo info = personQulificationInfoRepository.findById(Long.parseLong(licenseId));
		dto.setPersonQulificationInfo(info);
		dto.setLicenseChecks(pilotLicenseCheckRepository.findByLicenseId(Long.parseLong(licenseId)));
		return dto;
	}
}
