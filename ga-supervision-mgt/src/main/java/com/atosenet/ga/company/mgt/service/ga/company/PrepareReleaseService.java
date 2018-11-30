/**  
 * 
 * @Title: PrepareReleaseService.java 
 * @Package com.atosenet.ga.company.mgt.service.ga.company  
 * @author xyx   
 * @date 2018年9月27日 下午2:14:17 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.Aircraft;
import com.atosenet.ga.company.db.model.ga.company.PrepareRelease;
import com.atosenet.ga.company.db.repos.ga.company.AircraftRepository;
import com.atosenet.ga.company.db.repos.ga.company.PrepareReleaseRepository;
import com.atosenet.ga.company.db.repos.ga.company.entity.DaoSpec;
import com.atosenet.ga.company.db.repos.ga.company.entity.DaoSpecs;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: PrepareReleaseService 
 * @author xyx 
 * @date 2018年9月27日 下午2:14:17 
 *  
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class PrepareReleaseService {
	@Autowired
	PrepareReleaseRepository prepareReleaseRepository;
	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findAircraftByInfo 
	 * @param organizationId
	 * @param aircraftNo
	 * @param releaseTime
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	public Page<PrepareRelease> findAircraftByInfo(String organizationId, String aircraftNo, String releaseTime,
			Pageable pageable) {
		Specification<PrepareRelease> spec = DaoSpecs.getSpec(organizationId,aircraftNo,releaseTime);
		Page<PrepareRelease> list = prepareReleaseRepository.findAll(spec,pageable);
		return list;
	}

}
