/**  
 * 
 * @Title: SkilledCheckRecordRepository.java 
 * @Package com.atosenet.ga.company.db.repos.ga.employe  
 * @author xyx   
 * @date 2018年8月8日 下午3:26:23 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.repos.ga.employe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.atosenet.ga.company.db.model.ga.employe.PeriodicalCheckRecord;
import com.atosenet.ga.company.db.model.ga.employe.WorkRecord;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: SkilledCheckRecordRepository 
 * @author xyx 
 * @date 2018年8月8日 下午3:26:23 
 *  
 */
public interface PeriodicalCheckRecordRepository extends JpaRepository<PeriodicalCheckRecord, Long>{

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findBypilotId 
	 * @param parseInt
	 * @return  
	 * @throws
	 *
	 */
	List<PeriodicalCheckRecord> findBypilotId(@Param("pilot_id")long pilotId);

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
