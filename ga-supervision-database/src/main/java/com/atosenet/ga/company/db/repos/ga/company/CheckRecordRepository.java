/**  
 * 
 * @Title: CheckRecordRepository.java 
 * @Package com.atosenet.ga.company.db.repos.ga.company  
 * @author xyx   
 * @date 2018年8月8日 下午5:59:46 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.atosenet.ga.company.db.model.ga.company.CheckRecord;
import com.atosenet.ga.company.db.model.ga.employe.WorkRecord;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: CheckRecordRepository 
 * @author xyx 
 * @date 2018年8月8日 下午5:59:46 
 *  
 */
public interface CheckRecordRepository extends JpaRepository<CheckRecord, Long>{

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByAircaftId 
	 * @param aircaftId
	 * @return  
	 * @throws
	 *
	 */
	List<CheckRecord> findByAircaftId(@Param("aircaftId")long aircaftId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: deleteByAircaftId 
	 * @param id  
	 * @throws
	 *
	 */
	void deleteByAircaftId(@Param("id")Long id);
	
	
}
