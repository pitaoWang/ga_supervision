package com.atosenet.ga.company.db.repos.ga.employe;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.atosenet.ga.company.db.model.ga.employe.PilotLicenseCheckRecord;

@RepositoryRestResource(collectionResourceRel = "plc", path = "plc")
public interface PilotLicenseCheckRepository extends JpaRepository<PilotLicenseCheckRecord, Long> {

//	List<PilotLicenseCheckRecord> findByLicenseIdAndLicenseType(@Param("licenseId") String licenseId,@Param("licenseType") String licenseType);
	List<PilotLicenseCheckRecord> findByLicenseId(@Param("licenseId") Long licenseId);

	Page<PilotLicenseCheckRecord> findByLicenseId(@Param("licenseId")Long licenseId, Pageable pageable);
}
