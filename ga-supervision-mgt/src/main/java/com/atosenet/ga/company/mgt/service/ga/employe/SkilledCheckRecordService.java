/**  
 * 
 * @Title: SkilledCheckRecordService.java 
 * @Package com.atosenet.ga.company.mgt.service.ga.employe  
 * @author xyx   
 * @date 2018年8月8日 下午3:15:53 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.service.ga.employe;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.SkilledCheckRecord;
import com.atosenet.ga.company.db.repos.ga.employe.SkilledCheckRecordRepository;


/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: SkilledCheckRecordService 
 * @author xyx 
 * @date 2018年8月8日 下午3:15:53 
 *  
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class SkilledCheckRecordService {
	@Autowired
	SkilledCheckRecordRepository skilledCheckRecordRepository;
	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findBypilotId 
	 * @param parseInt
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly=false)
	public Page<SkilledCheckRecord> findBypilotId(long pilotId, Pageable pageable) {
		// TODO Auto-generated method stub
		return skilledCheckRecordRepository.findBypilotId(pilotId, pageable);
	}
	
	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: save 
	 * @param skilledCheckRecord
	 * @param request  
	 * @throws
	 *
	 */
	@Transactional(readOnly=false)
	public void save(SkilledCheckRecord skilledCheckRecord, HttpServletRequest request) {
		skilledCheckRecordRepository.save(skilledCheckRecord);
		
	}
	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: delete 
	 * @param id  
	 * @throws
	 *
	 */
	public void delete(Long id) {
		// TODO Auto-generated method stub
		skilledCheckRecordRepository.delete(id);
	}
	
	/**
	 * 
	 * @param ids
	 */
	public void batchDelete(List<Long> ids) {
		List<SkilledCheckRecord> recordList = new ArrayList<SkilledCheckRecord>();
		if(ids != null && ids.size() > 0) {
			for (Long id : ids) {
				SkilledCheckRecord record = new SkilledCheckRecord();
				record.setId(id);
				recordList.add(record);
			}
			skilledCheckRecordRepository.delete(recordList);
		}
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
	public SkilledCheckRecord findById(long id) {
		// TODO Auto-generated method stub
		return skilledCheckRecordRepository.findOne(id);
	}

}
