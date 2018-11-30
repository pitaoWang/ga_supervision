package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.AircraftPartsRepairLicenseSpecialCheck;

@RepositoryRestResource(collectionResourceRel = "aircraftpartsrepairlicensespecialcheck", path = "aircraftpartsrepairlicensespecialcheck")
public interface AircraftPartsRepairLicenseSpecialCheckRepository extends PagingAndSortingRepository<AircraftPartsRepairLicenseSpecialCheck, Long> {
	@Query("select t from AircraftPartsRepairLicenseSpecialCheck t")
	List<AircraftPartsRepairLicenseSpecialCheck> findList();
	
	@Query("select t from AircraftPartsRepairLicenseSpecialCheck t where t.licenseId = ?1")
	List<AircraftPartsRepairLicenseSpecialCheck> findListBylicenseId(long id);
	@Modifying
	@Query("delete from AircraftPartsRepairLicenseSpecialCheck t where t.licenseId =?1")
	void deleteByLicenseId(long id);
}
