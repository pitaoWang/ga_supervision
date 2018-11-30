package com.atosenet.ga.company.mgt.service.ga.mmis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.mmis.RepairModified;
import com.atosenet.ga.company.db.repos.ga.mmis.RepairModifiedRepository;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Transactional
public class RepairModifiedService {

	@Autowired
	private RepairModifiedRepository repairModifiedRepository;
	
	/**
	 * 
	 * 
	 * @param no
	 * @param aircraftModel
	 * @param type
	 * @param project
	 * @param regNumber
	 * @param pageable
	 * @return
	 */
	public Page<RepairModified> findListByPage(String no, String aircraftModel, String type, String project, String regNumber, Pageable pageable) {
		
		return repairModifiedRepository.findRepairModifiedListByPage(no, aircraftModel, type, project, regNumber, pageable);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public RepairModified findById(String id) {
		return repairModifiedRepository.findOne(id);
	}
}
