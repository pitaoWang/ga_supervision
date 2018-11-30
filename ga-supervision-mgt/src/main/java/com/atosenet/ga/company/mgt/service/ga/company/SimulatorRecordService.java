/**
 * 
 */
package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.SimulatorRecord;
import com.atosenet.ga.company.db.repos.ga.company.SimulatorRecordRepository;

/**
 * @author lvhao
 *
 */
@Service
@Transactional
public class SimulatorRecordService {
	
	@Autowired
	private SimulatorRecordRepository srRrpository;

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: selectPage 
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly=true)
	public Page<SimulatorRecord> selectPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.srRrpository.findAll(pageable);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: save 
	 * @param simulatorRecord  
	 * @throws
	 *
	 */
	public SimulatorRecord save(SimulatorRecord simulatorRecord) {
		// TODO Auto-generated method stub
		return this.srRrpository.save(simulatorRecord);
		
	}
	
	/**
	 * 
	 * TODO(根据训练器id查询鉴定记录) 
	 *
	 * @Title: findBySimulatorId 
	 * @param simulatorId
	 * @return  
	 * @throws
	 *
	 */
	public List<SimulatorRecord> findBySimulatorId(Long simulatorId){
		return srRrpository.findBySimulatorId(simulatorId);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: delete 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	public Long delete(Long id) {
		srRrpository.delete(id);
		return id;
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: deletes 
	 * @param list
	 * @return  
	 * @throws
	 *
	 */
	public void deletes(List<SimulatorRecord> list) {
		// TODO Auto-generated method stub
		srRrpository.delete(list);
		
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findBySimulatorIds 
	 * @param simulatorId
	 * @return  
	 * @throws
	 *
	 */
	public List<SimulatorRecord> findBySimulatorIds(List<Long> simulatorId) {
		
		return srRrpository.findBySimulatorIdIn(simulatorId);
	}
	
	
}
