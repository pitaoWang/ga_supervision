package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.AircraftPartsRepairLicense;
import com.atosenet.ga.company.db.model.ga.company.AircraftRepairLicense;

@RepositoryRestResource(collectionResourceRel = "aircraftrepairlicense", path = "aircraftrepairlicense")
public interface AircraftRepairLicenseRepository extends PagingAndSortingRepository<AircraftRepairLicense, Long> {
	@Query("select t from AircraftRepairLicense t")
	List<AircraftRepairLicense> findList();
}
