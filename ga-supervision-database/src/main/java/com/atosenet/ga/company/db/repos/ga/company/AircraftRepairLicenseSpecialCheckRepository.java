package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.AircraftRepairLicenseSpecialCheck;

@RepositoryRestResource(collectionResourceRel = "aircraftrepairlicensespecialcheck", path = "aircraftrepairlicensespecialcheck")
public interface AircraftRepairLicenseSpecialCheckRepository extends PagingAndSortingRepository<AircraftRepairLicenseSpecialCheck, Long> {
	@Query("select t from AircraftRepairLicenseSpecialCheck t")
	List<AircraftRepairLicenseSpecialCheck> findList();
	
	@Query("select t from AircraftRepairLicenseSpecialCheck t where t.licenseId = ?1")
	List<AircraftRepairLicenseSpecialCheck> findListBylicenseId(long id);
	@Modifying
	@Query("delete from AircraftRepairLicenseSpecialCheck t where t.licenseId =?1")
	void deleteByLicenseId(long id);
}
