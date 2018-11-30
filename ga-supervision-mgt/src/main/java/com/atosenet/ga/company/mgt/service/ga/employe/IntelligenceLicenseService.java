package com.atosenet.ga.company.mgt.service.ga.employe;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.IntelligenceLicense;
import com.atosenet.ga.company.db.model.ga.employe.IntelligenceLicenseDTO;

import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;

import com.atosenet.ga.company.db.repos.ga.employe.IntelligenceLicenseRepository;
import com.atosenet.ga.company.db.repos.ga.employe.PersonQulificationInfoRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class IntelligenceLicenseService {
	@Autowired
	IntelligenceLicenseRepository intelligenceLicenseRepository;
	@Autowired
	PersonQulificationInfoRepository personQulificationInfoRepository;
	
	/**
	 * 增加
	 */
	@Transactional(readOnly=false)
    public void save(IntelligenceLicense record){
		intelligenceLicenseRepository.save(record);
    }
	
	/**
	 *删除
	 */
	@Transactional(readOnly=false)
    public void delete(Long id){
		intelligenceLicenseRepository.delete(id);
    }
	
	/**
	 * 查询
	 */
	@Transactional(readOnly=true)
    public IntelligenceLicense findById(long id){	
		return  intelligenceLicenseRepository.findOne(id);
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
	public Page<IntelligenceLicense> selectPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.intelligenceLicenseRepository.findAll(pageable);
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
	public IntelligenceLicenseDTO findAllById(Long id) {
		IntelligenceLicenseDTO intelligenceLicenseDTO = new IntelligenceLicenseDTO();
		intelligenceLicenseDTO.setIntelligenceLicense(intelligenceLicenseRepository.findOne(id));
		PersonQulificationInfo info = personQulificationInfoRepository.findById(id);
		intelligenceLicenseDTO.setPersonQulificationInfo(info);
		return intelligenceLicenseDTO;
	}
	
}
