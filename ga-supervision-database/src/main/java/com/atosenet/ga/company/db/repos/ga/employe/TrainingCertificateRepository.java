package com.atosenet.ga.company.db.repos.ga.employe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.employe.TrainingCertificate;
@RepositoryRestResource(collectionResourceRel = "ttr", path = "ttr")
public interface TrainingCertificateRepository extends JpaRepository<TrainingCertificate, Long>{
	
	List<TrainingCertificate> findByEmployeeId(@Param("employee_id") long employeeId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: deleteByEmployeeId 
	 * @param id  
	 * @throws
	 *
	 */
	void deleteByEmployeeId(@Param("id")Long id);

	
}
