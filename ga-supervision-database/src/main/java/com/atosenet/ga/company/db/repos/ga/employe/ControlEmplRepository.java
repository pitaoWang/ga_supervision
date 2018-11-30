/**
 * 
 */
package com.atosenet.ga.company.db.repos.ga.employe;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.atosenet.ga.company.db.model.ga.employe.ControlEmpl;

/**
 * @author lvhao
 *
 */
public interface ControlEmplRepository extends PagingAndSortingRepository<ControlEmpl, Long> {

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByEmployeeId 
	 * @param employeeId
	 * @return  
	 * @throws
	 *
	 */
	@Query(value ="select c from ControlEmpl c where employeeId = ?1")
	ControlEmpl findByEmployeeId(long employeeId);
	
}
