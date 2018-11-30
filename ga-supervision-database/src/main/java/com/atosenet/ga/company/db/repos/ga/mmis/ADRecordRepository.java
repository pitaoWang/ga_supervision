package com.atosenet.ga.company.db.repos.ga.mmis;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.mmis.ADRecord;

@RepositoryRestResource(collectionResourceRel = "adRecord", path = "adRecord")
public interface ADRecordRepository extends PagingAndSortingRepository<ADRecord, Serializable> {
	
	Page<ADRecord> findByParentId(String parentId, Pageable pageable);
	
}
