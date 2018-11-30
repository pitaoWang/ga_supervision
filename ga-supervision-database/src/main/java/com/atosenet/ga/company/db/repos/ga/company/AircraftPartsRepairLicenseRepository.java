package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.AircraftPartsRepairLicense;

@RepositoryRestResource(collectionResourceRel = "aircraftpartsrepairlicense", path = "aircraftpartsrepairlicense")
public interface AircraftPartsRepairLicenseRepository extends PagingAndSortingRepository<AircraftPartsRepairLicense, Long> {
	@Query("select t from AircraftPartsRepairLicense t")
	List<AircraftPartsRepairLicense> findList();
}
