package com.atosenet.ga.company.db.repos.ga.company;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.atosenet.ga.company.db.model.ga.company.CompanyTechGrade;
@RepositoryRestResource(collectionResourceRel = "CompanyTechGrade", path = "CompanyTechGrade")
public interface CompanyTechGradeRepository extends PagingAndSortingRepository<CompanyTechGrade, Long> {
	@Query("select t from CompanyTechGrade t ")
	List<CompanyTechGrade> findList();

}





