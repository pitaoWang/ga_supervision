package com.atosenet.ga.company.db.repos.ga.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.GaOfficial;

@RepositoryRestResource(collectionResourceRel = "official", path = "official")
public interface GaOfficialRepository extends JpaRepository<GaOfficial, Integer> {
	@Query("select t from GaOfficial t where orgId = :orgId")
	GaOfficial findByOrgId(@Param("orgId") Long orgId);
}