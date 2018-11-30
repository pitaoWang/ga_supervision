package com.atosenet.ga.company.db.repos.ga.company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.atosenet.ga.company.db.model.ga.company.CompanyLegalperson;

@RepositoryRestResource(collectionResourceRel = "CompanyLegalperson", path = "CompanyLegalperson")
public interface CompanyLegalpersonRepository extends PagingAndSortingRepository<CompanyLegalperson, Long>{
	@Query("select t from CompanyLegalperson t where t.cardid=?1")
	CompanyLegalperson findByCardid(String cardid);

	@Query("select t from CompanyLegalperson t where t.companyId=?1")
	CompanyLegalperson findByCompanyId(Integer companyId);
}
