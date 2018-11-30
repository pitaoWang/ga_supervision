package com.atosenet.ga.company.db.repos.ga.employe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.employe.IntelligenceLicense;
@RepositoryRestResource(collectionResourceRel = "ilr", path = "ilr")
public interface IntelligenceLicenseRepository extends JpaRepository<IntelligenceLicense, Long>{
	
}
