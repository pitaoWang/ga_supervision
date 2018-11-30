/**  
 * 
 * @Title: WorkRecordService.java 
 * @Package com.atosenet.ga.company.mgt.service.ga.employe  
 * @author xyx   
 * @date 2018年7月31日 下午5:18:55 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.service.ga.employe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.PersonExamRecord;
import com.atosenet.ga.company.db.model.ga.employe.WorkRecord;
import com.atosenet.ga.company.db.repos.ga.employe.WorkRecordRepository;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: WorkRecordService 
 * @author xyx 
 * @date 2018年7月31日 下午5:18:55 
 *  
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class WorkRecordService {
	@Autowired
	WorkRecordRepository workRecordRepository;
	
	@Transactional(readOnly=false)
    public List<WorkRecord> findByEmployeeId(long employeeId){
		
		return workRecordRepository.findByEmployeeId(employeeId);
    }

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: save 
	 * @param workRecord
	 * @param request  
	 * @throws
	 *
	 */
	@Transactional(readOnly=false)
	public void save(WorkRecord workRecord, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		workRecordRepository.save(workRecord);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: delete 
	 * @param id  
	 * @throws
	 *
	 */
	@Transactional(readOnly=false)
	public void delete(Long id) {
		// TODO Auto-generated method stub
		workRecordRepository.delete(id);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findById 
	 * @param parseLong
	 * @return  
	 * @throws
	 *
	 */
	public WorkRecord findById(long id) {
		// TODO Auto-generated method stub
		return workRecordRepository.findOne(id);
	}

	public Page<WorkRecord> getPage(long employeeId, Pageable pageable) {
		// TODO Auto-generated method stub
		return workRecordRepository.findByEmployeeId(employeeId,pageable);
	}

	public void deleteBatch(List<Long> ids) {
		for(Long id : ids){
			this.workRecordRepository.delete(id);
		}
		
	}
}
