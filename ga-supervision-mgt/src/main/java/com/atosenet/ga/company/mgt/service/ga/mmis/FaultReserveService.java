package com.atosenet.ga.company.mgt.service.ga.mmis;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.mmis.FaultReserve;
import com.atosenet.ga.company.db.repos.ga.mmis.FaultReserveRepository;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Transactional
public class FaultReserveService {
	
	@Autowired
	private FaultReserveRepository faultReserveRepository;
	
	/**
	 * 
	 * @param no
	 * @param aircraftModel
	 * @param reserveDate
	 * @param status
	 * @param regNumber
	 * @param pageable
	 * @return
	 */
	public Page<FaultReserve> findListByPage(String no, String aircraftModel, String reserveDate, String status, String regNumber, Pageable pageable) {
		return faultReserveRepository.findFaultListByPage(no, aircraftModel, reserveDate, status, regNumber, pageable);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public FaultReserve findById(String id) {
		return faultReserveRepository.findOne(id);
	}
}
