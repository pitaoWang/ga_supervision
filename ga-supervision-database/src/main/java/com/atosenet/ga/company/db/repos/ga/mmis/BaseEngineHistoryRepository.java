package com.atosenet.ga.company.db.repos.ga.mmis;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.mmis.BaseEngineHistory;


@RepositoryRestResource(collectionResourceRel = "baseEngineHistory", path = "baseEngineHistory")
public interface BaseEngineHistoryRepository extends PagingAndSortingRepository<BaseEngineHistory, Serializable> {
	
	@Query("select t from BaseEngineHistory t where engineId =?1 and isDelete = 0")
	List<BaseEngineHistory> findHistoryListByEngineId(String engineId);
	
}
