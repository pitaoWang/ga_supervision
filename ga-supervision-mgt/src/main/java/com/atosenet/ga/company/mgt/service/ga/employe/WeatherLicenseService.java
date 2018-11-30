package com.atosenet.ga.company.mgt.service.ga.employe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;
import com.atosenet.ga.company.db.model.ga.employe.WeatherLicense;
import com.atosenet.ga.company.db.model.ga.employe.WeatherLicenseDTO;
import com.atosenet.ga.company.db.repos.ga.employe.PersonQulificationInfoRepository;
import com.atosenet.ga.company.db.repos.ga.employe.WeatherLicenseRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class WeatherLicenseService {
	@Autowired
	WeatherLicenseRepository weatherLicenseRepository;
	
	@Autowired
	PersonQulificationInfoRepository personQulificationInfoRepository;
	/**根据id来删除
	 * 
	 * @param id
	 */
	@Transactional(readOnly=false)
	public void deleteById(long id) {
		weatherLicenseRepository.delete(id);
	}
	
	/**
	 * 保存
	 * 
	 */
	@Transactional(readOnly=false)
	public void save(WeatherLicense weatherlicense) {
		weatherLicenseRepository.save(weatherlicense);
	}
	
	/**
	 * 查询所有的信息
	 * 
	 */
	@Transactional(readOnly=false)
	public List<WeatherLicense> selectAll() {
		
		return weatherLicenseRepository.findAll();
	}
	
	/**
	 * 根据id来查询信息
	 * 
	 */
	@Transactional(readOnly=false)
	public WeatherLicense selectById(long id) {
		
		return weatherLicenseRepository.findOne(id);
	}

	@Transactional(readOnly=false)
	public WeatherLicenseDTO selectAllById(long id) {
		WeatherLicenseDTO weatherLicenseDTO = new WeatherLicenseDTO();
		weatherLicenseDTO.setWeatherLicense(weatherLicenseRepository.findOne(id));
		PersonQulificationInfo info = personQulificationInfoRepository.findById(id);
		weatherLicenseDTO.setPersonQulificationInfo(info);
		return weatherLicenseDTO;
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
	public Page<WeatherLicense> selectPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.weatherLicenseRepository.findAll(pageable);
	}
	
}
