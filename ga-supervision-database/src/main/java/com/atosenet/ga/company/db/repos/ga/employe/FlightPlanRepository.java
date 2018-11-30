package com.atosenet.ga.company.db.repos.ga.employe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.atosenet.ga.company.db.model.ga.employe.FlightPlan;

@RepositoryRestResource(collectionResourceRel = "fp", path = "fp")
public interface FlightPlanRepository extends JpaRepository<FlightPlan, Long> {

}
