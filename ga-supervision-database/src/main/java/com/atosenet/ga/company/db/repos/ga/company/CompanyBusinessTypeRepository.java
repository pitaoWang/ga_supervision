package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.CompanyBusinessType;

@RepositoryRestResource(collectionResourceRel = "CompanyBusinessType", path = "CompanyBusinessType")
public interface CompanyBusinessTypeRepository extends PagingAndSortingRepository<CompanyBusinessType, Long> {
	@Modifying
	@Query("delete from CompanyBusinessType t where t.companyId=?1")
	void deleteByCompanyId(int companyId);
	@Query("select t from CompanyBusinessType t where t.companyId=?1")
    List<CompanyBusinessType> findListbyCompanyId(int companyId);
	
	@Query("select t from CompanyBusinessType t where t.businessType=?1")
    List<CompanyBusinessType> findListbybusinessType(short businessType);
}
