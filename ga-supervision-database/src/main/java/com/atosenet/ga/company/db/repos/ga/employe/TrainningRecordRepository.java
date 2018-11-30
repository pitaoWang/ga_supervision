package com.atosenet.ga.company.db.repos.ga.employe;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import com.atosenet.ga.company.db.model.ga.employe.TrainningRecord;


@RepositoryRestResource(collectionResourceRel = "tr", path = "tr")
public interface TrainningRecordRepository extends JpaRepository<TrainningRecord, Long> {
	List<TrainningRecord> findByEmployeeId(@Param("employee_id") long employeeId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: deleteByEmployeeId 
	 * @param id  
	 * @throws
	 *
	 */
	void deleteByEmployeeId(@Param("id")Long id);

	Page<TrainningRecord> findByEmployeeId(@Param("employeeId")long employeeId, Pageable pageable);

	

	
}
