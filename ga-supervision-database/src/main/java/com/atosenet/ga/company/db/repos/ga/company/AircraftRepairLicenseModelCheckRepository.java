package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.AircraftPartsRepairLicenseSpecialCheck;
import com.atosenet.ga.company.db.model.ga.company.AircraftRepairLicenseModelCheck;

@RepositoryRestResource(collectionResourceRel = "aircraftpartsrepairlicensespecialcheck", path = "aircraftpartsrepairlicensespecialcheck")
public interface AircraftRepairLicenseModelCheckRepository extends PagingAndSortingRepository<AircraftRepairLicenseModelCheck, Long> {
	@Query("select t from AircraftRepairLicenseModelCheck t")
	List<AircraftRepairLicenseModelCheck> findList();
	
	@Query("select t from AircraftRepairLicenseModelCheck t where t.licenseId = ?1")
	List<AircraftRepairLicenseModelCheck> findListBylicenseId(long id);
	@Modifying
	@Query("delete from AircraftRepairLicenseModelCheck t where t.licenseId =?1")
	void deleteByLicenseId(long id);
}
