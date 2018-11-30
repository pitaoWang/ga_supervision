package com.atosenet.ga.company.db.repos.ga.employe;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.employe.PersonExamRecord;

@RepositoryRestResource(collectionResourceRel = "pe", path = "pe")
public interface PersonExamRecordRepository extends JpaRepository<PersonExamRecord, Long> {
	
	Page<PersonExamRecord> findByEmployeeId(@Param("employee_id") long employeeId, Pageable pageable);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: deleteByEmployeeId 
	 * @param id  
	 * @throws
	 *
	 */
	void deleteByEmployeeId(@Param("id")Long id);
	
	List<PersonExamRecord> findByEmployeeId(@Param("employee_id") long employeeId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByEmployeeId 
	 * @param employeeId
	 * @return  
	 * @throws
	 *
	 */
	List<PersonExamRecord> findByEmployeeId(@Param("employeeId")Long employeeId);

}
