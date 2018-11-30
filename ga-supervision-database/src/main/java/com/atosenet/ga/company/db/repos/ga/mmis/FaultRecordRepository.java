package com.atosenet.ga.company.db.repos.ga.mmis;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.mmis.FaultRecord;

@RepositoryRestResource(collectionResourceRel = "faultRecord", path = "faultRecord")
public interface FaultRecordRepository extends PagingAndSortingRepository<FaultRecord, Serializable> {
	
	Page<FaultRecord> findByParentId(String parentId, Pageable pageable);
	
}
