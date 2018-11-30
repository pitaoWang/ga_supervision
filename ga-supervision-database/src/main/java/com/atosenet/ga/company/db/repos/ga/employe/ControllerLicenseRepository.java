/**  
 * 
 * @Title: ControllerLicenseRepository.java 
 * @Package com.atosenet.ga.company.db.repos.ga.employe  
 * @author dgw   
 * @date 2018年8月10日 上午9:44:44 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.repos.ga.employe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atosenet.ga.company.db.model.ga.employe.ControllerLicense;

/** 
 * TODO(管理人员执照)
 * 
 * @ClassName: ControllerLicenseRepository 
 * @author dgw 
 * @date 2018年8月10日 上午9:44:44 
 *  
 */
public interface ControllerLicenseRepository extends PagingAndSortingRepository<ControllerLicense,Long> {
    
	/**
	 * 
	 * (non-Javadoc)
	 * <p>Title: findAll</p> 
	 * <p>Description:获取管制人员执照分页信息 </p> 
	 * @param pageable
	 * @return 
	 * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Pageable)
	 *
	 */
	Page<ControllerLicense> findAll(Pageable pageable);
	
	/**
	 * 
	 * (non-Javadoc)
	 * <p>Title: save</p> 
	 * <p>Description:保存管制人员执照信息 </p> 
	 * @param controllerLicense
	 * @return 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 *
	 */
	ControllerLicense save(ControllerLicense controllerLicense);
	
	/**
	 * 
	 * TODO(根据管制人员执照id删除信息) 
	 *
	 * @Title: delete 
	 * @param licenseId  
	 * @throws
	 *
	 */
	void delete(long licenseId);
}
