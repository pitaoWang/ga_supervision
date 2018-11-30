package com.atosenet.ga.company.db.repos.ga.mmis;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.mmis.BaseEngine;


@RepositoryRestResource(collectionResourceRel = "baseEngine", path = "baseEngine")
public interface BaseEngineRepository extends PagingAndSortingRepository<BaseEngine, Serializable> {
	
	@Query("select t from BaseEngine t where aircraftRegNo =?1 and isDelete = 0")
	List<BaseEngine> findEngineListByRegNumber(String regNumber);
	
}
