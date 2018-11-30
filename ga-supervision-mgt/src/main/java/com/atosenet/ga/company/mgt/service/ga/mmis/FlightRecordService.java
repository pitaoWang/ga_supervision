package com.atosenet.ga.company.mgt.service.ga.mmis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.mmis.ADRecord;
import com.atosenet.ga.company.db.model.ga.mmis.FaultRecord;
import com.atosenet.ga.company.db.model.ga.mmis.FlightRecord;
import com.atosenet.ga.company.db.model.ga.mmis.ReplaceRecord;
import com.atosenet.ga.company.db.repos.ga.mmis.ADRecordRepository;
import com.atosenet.ga.company.db.repos.ga.mmis.FaultRecordRepository;
import com.atosenet.ga.company.db.repos.ga.mmis.FlightRecordRepository;
import com.atosenet.ga.company.db.repos.ga.mmis.ReplaceRecordRepository;

@Service
public class FlightRecordService {

	@Autowired
	private FlightRecordRepository flightRecordRepository;
	
	@Autowired
	private FaultRecordRepository faultRecordRepository;
	
	@Autowired
	private ReplaceRecordRepository replaceRecordRepository;
	
	@Autowired
	private ADRecordRepository adRecordRepository;
	
	/**
	 * 按条件分页查询飞行记录
	 * 
	 * @param no
	 * @param aircraftModel
	 * @param aircraftRegNo
	 * @param releaser
	 * @param releaseTime
	 * @param companyIds
	 * @param pageable
	 * @return
	 */
	public Page<FlightRecord> findFlightRecordByPage(String no, String aircraftModel, String aircraftRegNo, String releaser, String releaseTime, Pageable pageable) {
		return flightRecordRepository.findFlightRecordListByPage(no, aircraftModel, aircraftRegNo, releaser, releaseTime, pageable);
	}
	
	/**
	 * 根据ID获取飞行记录详细信息
	 * 
	 * @param id
	 * @return
	 */
	public FlightRecord findByFlightRecordId(String id) {
		return flightRecordRepository.findOne(id);
	}
	
	/**
	 * 根据飞行记录ID分页获取排故记录
	 * 
	 * @param parentId
	 * @param pageable
	 * @return
	 */
	public Page<FaultRecord> findFaultRecordByPage(String parentId, Pageable pageable) {
		return faultRecordRepository.findByParentId(parentId, pageable);
	}
	
	/**
	 * 根据飞行记录ID分页分页获取换件记录
	 * 
	 * @param parentId
	 * @param pageable
	 * @return
	 */
	public Page<ReplaceRecord> findReplaceRecordByPage(String parentId, Pageable pageable) {
		return replaceRecordRepository.findByParentId(parentId, pageable);
	}
	
	/**
	 * 根据飞行记录ID分页分页获取AD/SB执行记录
	 * 
	 * @param parentId
	 * @param pageable
	 * @return
	 */
	public Page<ADRecord> findADRecordByPage(String parentId, Pageable pageable) {
		return adRecordRepository.findByParentId(parentId, pageable);
	}
}
