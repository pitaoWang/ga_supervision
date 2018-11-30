package com.atosenet.ga.sys.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.FocusCompany;

@RepositoryRestResource(collectionResourceRel = "focusCompany", path = "focusCompany")
public interface FocusCompanyDao extends PagingAndSortingRepository<FocusCompany, Long> {

	@Query("select t.companyIds from FocusCompany t where t.superId = ?1")
	String findCompanyIdsBySuperId(Long superId);
	
	FocusCompany findBySuperId(Long superId);
	
	@Query("delete from FocusCompany t where t.superId = ?1")
	@Modifying
	void deleteBySupserId(Long superId);
}
