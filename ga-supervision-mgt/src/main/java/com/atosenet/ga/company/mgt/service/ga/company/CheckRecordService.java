/**  
 * 
 * @Title: CheckRecordService.java 
 * @Package com.atosenet.ga.company.mgt.service.ga.company  
 * @author xyx   
 * @date 2018年8月8日 下午5:57:27 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.CheckRecord;
import com.atosenet.ga.company.db.model.ga.employe.WorkRecord;
import com.atosenet.ga.company.db.repos.ga.company.CheckRecordRepository;
import com.atosenet.ga.company.db.repos.ga.employe.WorkRecordRepository;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: CheckRecordService 
 * @author xyx 
 * @date 2018年8月8日 下午5:57:27 
 *  
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class CheckRecordService {

	@Autowired
	CheckRecordRepository checkRecordRepository;
	
	@Transactional(readOnly=false)
	public List<CheckRecord> findByAircaftId(long aircaftId) {
		// TODO Auto-generated method stub
		return checkRecordRepository.findByAircaftId(aircaftId);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: save 
	 * @param checkRecord
	 * @param request  
	 * @throws
	 *
	 */
	@Transactional(readOnly=false)
	public CheckRecord save(CheckRecord checkRecord, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return checkRecordRepository.save(checkRecord);
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
	public Long delete(Long id) {
		
		checkRecordRepository.delete(id);
		return id;
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
	public CheckRecord findById(long id) {
		// TODO Auto-generated method stub
		return checkRecordRepository.findOne(id);
	}

}
