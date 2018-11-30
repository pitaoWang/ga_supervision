package com.atosenet.ga.company.db.repos.ga.message;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atosenet.ga.company.db.model.ga.employe.PersonQulificationInfo;
import com.atosenet.ga.company.db.model.ga.message.vo.MessageVO;

public interface MessageRepository extends PagingAndSortingRepository<PersonQulificationInfo, Serializable> {
	
	@Query(value="select new com.atosenet.ga.company.db.model.ga.message.vo.MessageVO(p.id, p.employeeId, p.licenseno, p.licensetype, p.expiredate, e.userName) from PersonQulificationInfo p, Employee e "
			+ "where p.employeeId = e.employeeId and e.companyId in (?1) and p.expiredate is not null and p.expiredate <  ?2 and p.expiredate > ?3")
	Page<MessageVO> findRecordByCompanyIds(List<Long> companyIds,Date preMondays,Date preMonday, Pageable pageable);
	
	//查询个人资质到期提醒
	/*@Query(value="select new com.atosenet.ga.company.db.model.ga.message.vo.MessageVO(p.id, p.employeeId, p.licenseno, p.licensetype, p.expiredate, e.userName) from PersonQulificationInfo p,Employee e,QualificationConfigure q "
			+"where p.employeeId = e.employeeId and q.companyId = e.companyId and e.companyId in (?1) and p.expiredate is not null and p.expiredate >curdate() and  p.expiredate<date_sub(curdate(),interval -q.timeSpan2 day) "
			+"union all select new com.atosenet.ga.company.db.model.ga.message.vo.MessageVO(p.id, p.employeeId, p.licenseno, p.licensetype, p.expiredate, e.userName) from PersonQulificationInfo p,Employee e,QualificationConfigure q "
			+"where p.employeeId = e.employeeId and q.companyId = e.companyId and e.companyId in (?2) and p.expiredate is not null and p.expiredate >curdate() and  p.expiredate<date_sub(curdate(),interval -7 day)")
	Page<MessageVO> findRecordByCompanyIds1(List<Long> companyIds,List<Long> companyIds1, Pageable pageable);*/
	
	
	@Query(value="SELECT a.id,a.employeeId,a.licenseno,a.licensetype,a.expiredate,a.userName FROM (SELECT p.id,p.employee_id employeeId,p.license_no licenseno,p.license_type licensetype,p.expire_date expiredate,e.username userName FROM person_qulificationinfo p LEFT JOIN employee e ON p.`employee_id` = e.`employee_id` LEFT JOIN qualification_configure q ON q.`company_id` = e.`company_id` WHERE e.`company_id` IN (?1) AND p.`expire_date` > CURDATE() AND p.`expire_date` < DATE_SUB(CURDATE(),INTERVAL - q.`time_span2` DAY )) a LEFT JOIN(SELECT p.id,p.employee_id employeeId,p.license_no licenseno,p.license_type licensetype,p.expire_date expiredate,e.username userName FROM person_qulificationinfo p LEFT JOIN employee e ON p.`employee_id` = e.`employee_id` LEFT JOIN qualification_configure q ON q.`company_id` = e.`company_id` WHERE e.`company_id` IN (?2) AND p.`expire_date` > CURDATE() AND p.`expire_date` < DATE_SUB(CURDATE(), INTERVAL - 7 DAY)) b ON a.employeeId = b.employeeId \n-- #pageable\n",
			countQuery = "SELECT count(*) FROM (SELECT p.id,p.employee_id employeeId,p.license_no licenseno,p.license_type licensetype,p.expire_date expiredate,e.username userName FROM person_qulificationinfo p LEFT JOIN employee e ON p.`employee_id` = e.`employee_id` LEFT JOIN qualification_configure q ON q.`company_id` = e.`company_id` WHERE e.`company_id` IN (?1) AND p.`expire_date` > CURDATE() AND p.`expire_date` < DATE_SUB(CURDATE(),INTERVAL - q.`time_span2` DAY )) a LEFT JOIN(SELECT p.id,p.employee_id employeeId,p.license_no licenseno,p.license_type licensetype,p.expire_date expiredate,e.username userName FROM person_qulificationinfo p LEFT JOIN employee e ON p.`employee_id` = e.`employee_id` LEFT JOIN qualification_configure q ON q.`company_id` = e.`company_id` WHERE e.`company_id` IN (?2) AND p.`expire_date` > CURDATE() AND p.`expire_date` < DATE_SUB(CURDATE(), INTERVAL - 7 DAY)) b ON a.employeeId = b.employeeId",nativeQuery=true
			)
	Page<Object> findRecordByCompanyIds1(List<Long> companyIds,List<Long> companyIds1, Pageable pageable);
	
	
	
}
