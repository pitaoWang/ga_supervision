package com.atosenet.ga.company.mgt.service.ga.employe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.atosenet.ga.company.db.model.ga.employe.GroundInstructorLicense;
import com.atosenet.ga.company.db.repos.ga.employe.GroundInstructorLicenseRepository;
/**
 * @Description:TODO
 * @time:2017年9月1日 上午8:00:48
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class GroundInstructorLicenseService {

	@Autowired
	GroundInstructorLicenseRepository groundInstructorLicenseRepository;
	
	/**
	 * 增加
	 */
	@Transactional(readOnly=false)
    public void sava(GroundInstructorLicense license){
		groundInstructorLicenseRepository.save(license);
    }
	
	/**
	 *删除
	 */
	@Transactional(readOnly=false)
    public void delete(Long id){
		groundInstructorLicenseRepository.delete(id);
    }
	
}
