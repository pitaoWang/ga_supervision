package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.FlightRunway;

@RepositoryRestResource(collectionResourceRel = "flightrunway", path = "flightrunway")
public interface FlightRunwayRepository extends PagingAndSortingRepository<FlightRunway, Long> {

	@Query("select t from FlightRunway t")
	List<FlightRunway> findList();
	
	@Query("select t from FlightRunway t where airdromeId.airportId=?1")
	List<FlightRunway> findListByAirportId(long airportId);
	@Modifying
	@Query("delete from FlightRunway t where t.airdromeId.airportId=?1")
	void deleteByAirportId(long airportId);
}

