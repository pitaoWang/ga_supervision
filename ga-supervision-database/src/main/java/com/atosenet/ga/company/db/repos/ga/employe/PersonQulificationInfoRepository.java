package com.atosenet.ga.company.db.repos.ga.employe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;

@RepositoryRestResource(collectionResourceRel = "pq", path = "pq")
public interface PersonQulificationInfoRepository extends JpaRepository<PersonQulificationInfo, Long> {

	PersonQulificationInfo findById(@Param("id") Long id);
	
//	PersonQulificationInfo findByEmployeeIdAndLicenseType(@Param("employeeId")long employeeId,@Param("licenseType")String licenseType );
	
	
	List<PersonQulificationInfo> findByEmployeeId(@Param("employeeId")long employeeId);
	
	PersonQulificationInfo findByIdAndLicensetype(@Param("id") Long id,@Param("licensetype") String licensetype);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: deleteByemployeeId 
	 * @param id  
	 * @throws
	 *
	 */
	void deleteByEmployeeId(@Param("id")Long id);

	
}
