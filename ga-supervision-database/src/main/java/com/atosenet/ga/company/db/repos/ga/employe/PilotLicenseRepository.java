package com.atosenet.ga.company.db.repos.ga.employe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.employe.PilotLicense;

@RepositoryRestResource(collectionResourceRel = "pl", path = "pl")
public interface PilotLicenseRepository extends JpaRepository<PilotLicense, Long> {


	
	PilotLicense findById(Long licenseId);
}
