/**  
 * 
 * @Title: WorkRecordRepository.java 
 * @Package com.atosenet.ga.company.db.repos.ga.employe  
 * @author xyx   
 * @date 2018年7月31日 下午5:21:52 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.repos.ga.employe;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.employe.WorkRecord;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: WorkRecordRepository 
 * @author xyx 
 * @date 2018年7月31日 下午5:21:52 
 *  
 */
@RepositoryRestResource(collectionResourceRel = "wr", path = "wr")
public interface WorkRecordRepository extends JpaRepository<WorkRecord, Long>{
	List<WorkRecord> findByEmployeeId(@Param("employee_id") long employeeId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: deleteByEmployeeId 
	 * @param id  
	 * @throws
	 *
	 */
	void deleteByEmployeeId(@Param("id")Long id);

	Page<WorkRecord> findByEmployeeId(@Param("employeeId")long employeeId, Pageable pageable);

	
}
