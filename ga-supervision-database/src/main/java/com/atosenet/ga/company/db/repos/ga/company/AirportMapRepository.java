package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.AirportMap;

@RepositoryRestResource(collectionResourceRel = "airport", path = "airport")
public interface AirportMapRepository extends PagingAndSortingRepository<AirportMap, Long> {

	@Query("select t from AirportMap t")
	List<AirportMap> findList();
	
	@Query("select t from AirportMap t where t.airportId=?1")
	AirportMap findByAirportId(long id);
	
}
