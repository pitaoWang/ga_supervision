package com.atosenet.ga.company.mgt.service.ga.employe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.PilotLicense;
import com.atosenet.ga.company.db.repos.ga.employe.PilotLicenseRepository;
/**
 * @Description:TODO
 * @time:2017年9月1日 上午8:00:48
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class PilotLicenseService {

	@Autowired
	PilotLicenseRepository pilotLicenseRepository;
	
	/**
	 * 增加
	 */
	@Transactional(readOnly=false)
    public PilotLicense sava(PilotLicense license){
		return pilotLicenseRepository.save(license);
    }
	
	/**
	 *删除
	 */
	@Transactional(readOnly=false)
    public void delete(Long id){
		pilotLicenseRepository.delete(id);
    }
	
	/**
	 * 
	 * @param id
	 * @return
	 * 根据id查询
	 */
	@Transactional(readOnly=true)
	public PilotLicense getById(Long id){
		return this.pilotLicenseRepository.getOne(id);
	}
	
	@Transactional(readOnly=true)
	public List<PilotLicense> getAll() {
		// TODO Auto-generated method stub
		return this.pilotLicenseRepository.findAll();
	}

	public void deleteById(String id) {
		// TODO Auto-generated method stub
		 this.pilotLicenseRepository.delete(Long.parseLong(id));
	}
	
}
