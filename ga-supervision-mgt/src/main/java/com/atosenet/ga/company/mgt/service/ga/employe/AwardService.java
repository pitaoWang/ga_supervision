package com.atosenet.ga.company.mgt.service.ga.employe;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.AwardRecord;
import com.atosenet.ga.company.db.repos.ga.employe.AwardRepository;

@Service
@Transactional
public class AwardService {
	
	@Autowired
	private AwardRepository awardRepository;
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public AwardRecord save(AwardRecord entity) {
		return awardRepository.save(entity);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public AwardRecord findById(Long id) {
		return awardRepository.findOne(id);
	}
	
	/**
	 * 根据ID 批量删除
	 * @param ids
	 */
	public void delete(List<Long> ids) {
		List<AwardRecord> recordList = new ArrayList<AwardRecord>();
		if(ids != null && ids.size() > 0) {
			for (Long id : ids) {
				AwardRecord record = new AwardRecord();
				record.setId(id);
				recordList.add(record);
			}
			awardRepository.delete(recordList);
		}
	}
	
	/**
	 * 根据人员ID分页获取奖惩记录
	 * @param employeeId
	 * @param pageable
	 * @return
	 */
	public Page<AwardRecord> findByPage(Long employeeId, Pageable pageable) {
		return awardRepository.findByEmployeeId(employeeId, pageable);
	}
	
	@Transactional(readOnly=true)
	public List<AwardRecord> findByEmployeeId(Long employeeId) {
		// TODO Auto-generated method stub
		return this.awardRepository.findByEmployeeId(employeeId);
	}
	
}
