package com.atosenet.ga.company.db.repos.ga.document;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


import com.atosenet.ga.company.db.model.ga.document.DocList;
import com.atosenet.ga.company.db.model.ga.document.DocListDto;

@RepositoryRestResource(collectionResourceRel = "doclist", path = "doclist")
public interface DocListRepository extends JpaRepository<DocList, Long> {
	
	@Query(value="select doc_list.* FROM doc_list,doc_reader WHERE doc_list.id = doc_reader.doc_id AND doc_reader.reader LIKE CONCAT('%',:companyIds,'%') AND doc_reader.reader_type LIKE CONCAT('%',:jobTitle,'%') \n-- #pageable\n",
			   countQuery=" select count(*) FROM doc_list,doc_reader WHERE doc_list.id = doc_reader.doc_id AND doc_reader.reader LIKE CONCAT('%',:companyIds,'%') AND doc_reader.reader_type LIKE CONCAT('%',:jobTitle,'%') ",
			   nativeQuery=true)
	Page<DocList> finddoclist(@Param("jobTitle")String jobTitle,@Param("companyIds")String companyIds,Pageable pageable);
	
	@Query(value="select ga_company.`name` as companyname,employee.username as username from employee,ga_company where employee.employee_id=:employeeid and employee.company_id=ga_company.company_id  ",
			   nativeQuery=true)
	List<Object> findnamebyemployeeid(@Param("employeeid")long employeeid);
	
	@Query(value="select doc_list.* FROM doc_list,doc_reader WHERE doc_list.id = doc_reader.doc_id AND doc_reader.reader LIKE CONCAT('%',:companyIds,'%') AND doc_reader.reader_type LIKE CONCAT('%',:jobTitle,'%') \n-- #pageable\n",
			   countQuery=" select count(*) FROM doc_list,doc_reader WHERE doc_list.id = doc_reader.doc_id AND doc_reader.reader LIKE CONCAT('%',:companyIds,'%') AND doc_reader.reader_type LIKE CONCAT('%',:jobTitle,'%') ",
			   nativeQuery=true)
	Page<DocList> finddoclistcompany(@Param("jobTitle")String jobTitle,@Param("companyIds")String companyIds,Pageable pageable);
	
	@Query(value="SELECT doc_list.* FROM doc_list WHERE doc_list.superviseId=:superviseId \n-- #pageable\n",
			   countQuery=" SELECT count(*) FROM doc_list WHERE doc_list.superviseId=:superviseId ",
			   nativeQuery=true)
	Page<DocList> geilistbysuperviseId(@Param("superviseId")long superviseId,Pageable pageable);
	
	
}