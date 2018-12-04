/**
 * 
 */
package com.atosenet.ga.company.db.repos.ga.employe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


import com.atosenet.ga.company.db.model.ga.employe.UpdateCheckRecord;

/**
 * @author lvhao
 *
 */
public interface UpdateCheckRecordRepository extends JpaRepository<UpdateCheckRecord, Long> {
	
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
	List<UpdateCheckRecord> findBypilotId(@Param("pilot_id")long pilotId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: deleteByPilotId 
	 * @param pilotId  
	 * @throws
	 *
	 */
	void deleteByPilotId(@Param("pilotId")long pilotId);

}