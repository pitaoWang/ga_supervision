/**  
 * 
 * @Title: ControllerLicenseService.java 
 * @Package com.atosenet.ga.company.mgt.service.ga.employe  
 * @author dgw   
 * @date 2018年8月10日 上午10:04:59 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.service.ga.employe;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.ControllerLicense;
import com.atosenet.ga.company.db.repos.ga.employe.ControllerLicenseRepository;



/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: ControllerLicenseService 
 * @author dgw 
 * @date 2018年8月10日 上午10:04:59 
 *  
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class ControllerLicenseService {
	
	@Autowired
	ControllerLicenseRepository controllerLicenseRep;
	
	/**
	 * 
	 * TODO(获取管制人员执照分页信息) 
	 *
	 * @Title: findPageAll 
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = true)
	public Page<ControllerLicense> findPageAll(Pageable pageable){
		return controllerLicenseRep.findAll(pageable);
	}
	
	/**
	 * 
	 * TODO(保存管理人员执照信息) 
	 *
	 * @Title: save 
	 * @param controllerLicense
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public ControllerLicense save(ControllerLicense controllerLicense){
		return controllerLicenseRep.save(controllerLicense);
	}
	
	/**
	 * 
	 * TODO(根据id删除管制人员执照信息) 
	 *
	 * @Title: delete 
	 * @param licenseId  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public void delete(long licenseId){
		 controllerLicenseRep.delete(licenseId);
	}
}
