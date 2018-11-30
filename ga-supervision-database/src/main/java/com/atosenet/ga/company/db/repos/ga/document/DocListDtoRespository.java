package com.atosenet.ga.company.db.repos.ga.document;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.atosenet.ga.company.db.model.ga.document.DocListDto;

public interface DocListDtoRespository extends JpaRepository<DocListDto, Long> {
	/*@Query(value="select doc_list.* ,ga_company.`name` as companyname,employee.username as username FROM doc_list,doc_reader,ga_company,employee WHERE doc_list.id = doc_reader.doc_id AND doc_reader.reader LIKE CONCAT('%',:companyIds,'%') AND doc_reader.reader_type LIKE CONCAT('%',:jobTitle,'%') AND doc_list.creator=employee.employee_id"
			+ " AND employee.company_id =ga_company.company_id \n-- #pageable\n",
			   countQuery=" select count(*) FROM doc_list,doc_reader WHERE doc_list.id = doc_reader.doc_id AND doc_reader.reader LIKE CONCAT('%',:companyIds,'%') AND doc_reader.reader_type LIKE CONCAT('%',:jobTitle,'%') ",
			   nativeQuery=true)
	Page<DocListDto> finddoclist(@Param("jobTitle")String jobTitle,@Param("companyIds")String companyIds,Pageable pageable);*/
	@Query(value="select doc_list.* ,doc_reader.readflag,doc_reader.reader_type as readertype,doc_reader.reader as reader,doc_reader.id as readerid,doc_list.`supervisecompanyname` as companyname,employee.username as username FROM doc_list,doc_reader,ga_company,employee WHERE doc_list.id = doc_reader.doc_id AND doc_reader.reader LIKE CONCAT('%',:companyIds,'%') AND  doc_list.creator=employee.employee_id"
			+ " AND employee.company_id =ga_company.company_id \n-- #pageable\n",
			   countQuery=" select count(*) FROM doc_list,doc_reader WHERE doc_list.id = doc_reader.doc_id AND doc_reader.reader LIKE CONCAT('%',:companyIds,'%')  ",
			   nativeQuery=true)
	Page<DocListDto> finddoclist(@Param("companyIds")String companyIds,Pageable pageable);
	
	@Query(value="select doc_list.* ,doc_list.`supervisecompanyname` as companyname,employee.username as username,doc_reader.reader as reader,doc_reader.id as readerid,doc_reader.reader_type as readertype,doc_reader.readflag as readflag FROM doc_list,ga_company,employee,doc_reader WHERE doc_list.creator = :employeeid AND employee.employee_id =:employeeid AND employee.company_id = ga_company.company_id \n-- #pageable\n",
			   countQuery=" select count(*) FROM doc_list,ga_company,employee WHERE doc_list.creator = :employeeid AND employee.employee_id =:employeeid AND employee.company_id = ga_company.company_id ",
			   nativeQuery=true)
	Page<DocListDto> geilistbycreator(@Param("employeeid")long employeeid,Pageable pageable);
	
	
	
	
}
