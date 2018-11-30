/**
 * 
 */
package com.atosenet.ga.company.mgt.service.ga.employe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.SkilledCheckRecord;
import com.atosenet.ga.company.db.model.ga.employe.UpdateCheckRecord;
import com.atosenet.ga.company.db.repos.ga.employe.UpdateCheckRecordRepository;

/**
 * @author lvhao
 *
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class UpdateCheckRecordService {
	
	@Autowired
	private UpdateCheckRecordRepository updateCheckRecordRepository;
	
	/**
	 * 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findBypilotId 
	 * @param pilotId
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly=false)
	public List<UpdateCheckRecord> findBypilotId(long pilotId) {
		// TODO Auto-generated method stub
		return updateCheckRecordRepository.findBypilotId(pilotId);
	}
	
	/**
	 * 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: save 
	 * @param skilledCheckRecord
	 * @param request  
	 * @throws
	 *
	 */
	@Transactional(readOnly=false)
	public void save(UpdateCheckRecord updateCheckRecord, HttpServletRequest request) {
		updateCheckRecordRepository.save(updateCheckRecord);
		
	}
	
	/**
	 * 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: delete 
	 * @param id  
	 * @throws
	 *
	 */
	public void delete(Long id) {
		// TODO Auto-generated method stub
		updateCheckRecordRepository.delete(id);
	}
	
	/**
	 * 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findById 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	public UpdateCheckRecord findById(long id) {
		// TODO Auto-generated method stub
		return updateCheckRecordRepository.findOne(id);
	}

}
