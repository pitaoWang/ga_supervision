package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.AircraftPartsRepairLicenseProjectCheck;

@RepositoryRestResource(collectionResourceRel = "aircraftpartsrepairlicenseprojectcheck", path = "aircraftpartsrepairlicenseprojectcheck")
public interface AircraftPartsRepairLicenseProjectCheckRepository extends PagingAndSortingRepository<AircraftPartsRepairLicenseProjectCheck, Long> {
	@Query("select t from AircraftPartsRepairLicenseProjectCheck t")
	List<AircraftPartsRepairLicenseProjectCheck> findList();
	
	@Query("select t from AircraftPartsRepairLicenseProjectCheck t where t.licenseId = ?1")
	List<AircraftPartsRepairLicenseProjectCheck> findListBylicenseId(long id);
	
	@Modifying
	@Query("delete from AircraftPartsRepairLicenseProjectCheck t where t.licenseId =?1")
	void deleteByLicenseId(long id);
}
