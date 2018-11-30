package com.atosenet.ga.company.db.repos.ga.employe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.atosenet.ga.company.db.model.ga.employe.WeatherLicense;
import com.atosenet.ga.company.db.model.sys.Org;

@RepositoryRestResource(collectionResourceRel = "wlr", path = "wlr")
public interface WeatherLicenseRepository extends JpaRepository<WeatherLicense, Long>{
	
	@Query("select t from WeatherLicense t ")
	List<Org> selectAll();
}
