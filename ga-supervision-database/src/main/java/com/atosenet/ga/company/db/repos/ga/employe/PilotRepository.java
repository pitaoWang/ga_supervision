package com.atosenet.ga.company.db.repos.ga.employe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.atosenet.ga.company.db.model.ga.employe.Pilot;

@RepositoryRestResource(collectionResourceRel = "pt", path = "pt")
public interface PilotRepository extends JpaRepository<Pilot, Long> {

	Pilot  findByEmployeeId(@Param("employee_id") long employeeId);
	
	@Modifying
	@Query(value=" DELETE FROM pilot WHERE employee_id = :id ",nativeQuery=true)
	void deletebyemployeeId(@Param("id") Long id);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: getByEmployeeId 
	 * @param employeeId
	 * @return  
	 * @throws
	 *
	 */
	@Query(value ="select p from Pilot p where employeeId = ?1")
	Pilot getByEmployeeId(long employeeId);
}
