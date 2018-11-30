package com.atosenet.ga.company.db.repos.ga.mmis;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.mmis.ReplaceRecord;

@RepositoryRestResource(collectionResourceRel = "replaceRecord", path = "replaceRecord")
public interface ReplaceRecordRepository extends PagingAndSortingRepository<ReplaceRecord, Serializable> {
	
	Page<ReplaceRecord> findByParentId(String parentId, Pageable pageable);
	
}
