package com.atosenet.ga.company.db.repos.ga.employe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.atosenet.ga.company.db.model.ga.employe.GroundInstructorLicense;

@RepositoryRestResource(collectionResourceRel = "gi", path = "gi")
public interface GroundInstructorLicenseRepository extends JpaRepository<GroundInstructorLicense, Long> {
	
//	GroundInstructorLicense findByLicenseId(String licenseId);
	
	GroundInstructorLicense findById(int licenseId);

}
