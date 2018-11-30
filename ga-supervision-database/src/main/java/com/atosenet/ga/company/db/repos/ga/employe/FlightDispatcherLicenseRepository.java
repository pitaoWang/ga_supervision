package com.atosenet.ga.company.db.repos.ga.employe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.employe.FlightDispatcherLicense;

@RepositoryRestResource(collectionResourceRel = "fdl", path = "fdl")
public interface FlightDispatcherLicenseRepository extends JpaRepository<FlightDispatcherLicense, Long>{

}
