package com.atosenet.ga.company.mgt.service.ga.mmis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.mmis.WorkReserve;
import com.atosenet.ga.company.db.repos.ga.mmis.WorkReserveRepository;

/**
 * 
 * @author Administrator
 *
 */

@Transactional
@Service
public class WorkReserveService {
	
	@Autowired
	private WorkReserveRepository workReserveRepository;
	
	/**
	 * 
	 * @param no
	 * @param aircraftModel
	 * @param wocNo
	 * @param status
	 * @param delayDate
	 * @param regNumber
	 * @param pageable
	 * @return
	 */
	public Page<WorkReserve> findListByPage(String no, String aircraftModel, String wocNo, String delayDate, String status, String regNumber, Pageable pageable) {
		
		return workReserveRepository.findWorkListByPage(no, aircraftModel, wocNo, delayDate, status, regNumber, pageable);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public WorkReserve findWorkReserveById(String id) {
		return workReserveRepository.findOne(id);
	}
	
}
