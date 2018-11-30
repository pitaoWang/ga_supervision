package com.atosenet.ga.company.db.repos.ga.employe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.atosenet.ga.company.db.model.ga.employe.PilotExperience;

@RepositoryRestResource(collectionResourceRel = "pe", path = "pe")
public interface PilotExperienceRepository extends JpaRepository<PilotExperience, Long> {
	
	List<PilotExperience> findByEmployeeId(@Param("employee_id") long employeeId);
}
