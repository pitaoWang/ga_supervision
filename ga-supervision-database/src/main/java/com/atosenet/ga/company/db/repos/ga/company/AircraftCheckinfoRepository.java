package com.atosenet.ga.company.db.repos.ga.company;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.AircraftCheckinfo;



@RepositoryRestResource(collectionResourceRel = "ac", path = "ac")
public interface AircraftCheckinfoRepository extends PagingAndSortingRepository<AircraftCheckinfo, Long> {
	@Query("select t from AircraftCheckinfo t ")
	List<AircraftCheckinfo> findList();
	
	@Query("select t from AircraftCheckinfo t where aircraftid =?1")
	List<AircraftCheckinfo> findByAircraftId(long aircraftId);
}
