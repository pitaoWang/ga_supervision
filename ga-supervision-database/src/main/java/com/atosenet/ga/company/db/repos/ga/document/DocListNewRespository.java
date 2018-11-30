package com.atosenet.ga.company.db.repos.ga.document;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.atosenet.ga.company.db.model.ga.document.DocListDto;
import com.atosenet.ga.company.db.model.ga.document.DocListNewDto;

public interface DocListNewRespository extends JpaRepository<DocListNewDto, Long> {
	
	@Query(value="select doc_list.* ,doc_list.`supervisecompanyname` as companyname,employee.username as username FROM doc_list,ga_company,employee WHERE doc_list.creator = :employeeid AND employee.employee_id =:employeeid AND employee.company_id = ga_company.company_id \n-- #pageable\n",
			   countQuery=" select count(*) FROM doc_list,ga_company,employee WHERE doc_list.creator = :employeeid AND employee.employee_id =:employeeid AND employee.company_id = ga_company.company_id ",
			   nativeQuery=true)
	Page<DocListNewDto> geilistbycreator(@Param("employeeid")long employeeid,Pageable pageable);
	
	//根据所属管理(查询)
			@Query(value="SELECT doc_list.*,doc_list.supervisecompanyname as companyname,employee.username FROM doc_list LEFT JOIN employee ON employee.employee_id = doc_list.creator WHERE superviseId = ?1 \n-- #pageable\n",
					countQuery="SELECT count(*) FROM doc_list LEFT JOIN employee ON employee.employee_id = doc_list.creator WHERE superviseId = ?1",
					nativeQuery=true
					)
			Page<DocListNewDto> getListBySupervisionId(long superviseId,Pageable pageable);
}
