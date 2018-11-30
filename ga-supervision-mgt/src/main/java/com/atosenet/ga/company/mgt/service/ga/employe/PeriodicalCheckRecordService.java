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

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.PeriodicalCheckRecord;
import com.atosenet.ga.company.db.repos.ga.employe.PeriodicalCheckRecordRepository;



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
public class PeriodicalCheckRecordService {
	@Autowired
	PeriodicalCheckRecordRepository periodicalCheckRecordRepository;
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
	public List<PeriodicalCheckRecord> findBypilotId(long pilotId) {
		// TODO Auto-generated method stub
		return periodicalCheckRecordRepository.findBypilotId(pilotId);
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
	public void save(PeriodicalCheckRecord periodicalCheckRecord, HttpServletRequest request) {
		periodicalCheckRecordRepository.save(periodicalCheckRecord);
		
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
		periodicalCheckRecordRepository.delete(id);
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
	public PeriodicalCheckRecord findById(long id) {
		// TODO Auto-generated method stub
		return periodicalCheckRecordRepository.findOne(id);
	}

}
