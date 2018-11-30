package com.atosenet.ga.company.db.repos.ga.message;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atosenet.ga.company.db.model.ga.message.vo.MessageDto;
import com.atosenet.ga.company.db.model.ga.message.vo.MessageVO;


public interface MessageDtoRepository extends JpaRepository<MessageDto, Long> {
	
	@Query(value="SELECT p.id,p.employee_id employeeId,p.license_no licenseno,p.license_type licensetype,p.expire_date expiredate,e.username userName FROM person_qulificationinfo p LEFT JOIN employee e ON p.`employee_id` = e.`employee_id` LEFT JOIN qualification_configure q ON q.`company_id` = e.`company_id` WHERE e.`company_id` IN (?1) AND p.`expire_date` >= CURDATE() AND p.`expire_date` <= DATE_SUB(CURDATE(),INTERVAL - q.`time_span2` DAY)UNION ALL SELECT p.id,p.employee_id employeeId,p.license_no licenseno,p.license_type licensetype,p.expire_date expiredate,e.username userName FROM person_qulificationinfo p LEFT JOIN employee e  ON p.`employee_id` = e.`employee_id` WHERE e.`company_id` IN (?2) AND p.`expire_date` >= CURDATE()  AND p.`expire_date` <= DATE_SUB(CURDATE(), INTERVAL - 7 DAY) \n-- #pageable\n",
			countQuery = "select count(*) from (SELECT p.id,p.employee_id employeeId,p.license_no licenseno,p.license_type licensetype,p.expire_date expiredate,e.username userName FROM person_qulificationinfo p LEFT JOIN employee e ON p.`employee_id` = e.`employee_id` LEFT JOIN qualification_configure q ON q.`company_id` = e.`company_id` WHERE e.`company_id` IN (?1) AND p.`expire_date` >= CURDATE() AND p.`expire_date` <= DATE_SUB(CURDATE(),INTERVAL - q.`time_span2` DAY)UNION ALL SELECT p.id,p.employee_id employeeId,p.license_no licenseno,p.license_type licensetype,p.expire_date expiredate,e.username userName FROM person_qulificationinfo p LEFT JOIN employee e  ON p.`employee_id` = e.`employee_id` WHERE e.`company_id` IN (?2) AND p.`expire_date` >= CURDATE()  AND p.`expire_date` <= DATE_SUB(CURDATE(), INTERVAL - 7 DAY))a",nativeQuery=true
			)
	Page<MessageDto> findRecordByCompanyIds1(List<Long> companyIds,List<Long> companyIds1, Pageable pageable);
	
	@Query(value="SELECT p.id,p.employee_id employeeId,p.license_no licenseno,p.license_type licensetype,p.expire_date expiredate,e.username userName FROM person_qulificationinfo p LEFT JOIN employee e ON p.`employee_id` = e.`employee_id` LEFT JOIN qualification_configure q ON q.`company_id` = e.`company_id` WHERE e.`company_id` IN (?1) AND p.`expire_date` >= CURDATE() AND p.`expire_date` <= DATE_SUB(CURDATE(),INTERVAL - q.`time_span2` DAY) \n-- #pageable\n",
			countQuery = "select count(*) from (SELECT p.id,p.employee_id employeeId,p.license_no licenseno,p.license_type licensetype,p.expire_date expiredate,e.username userName FROM person_qulificationinfo p LEFT JOIN employee e ON p.`employee_id` = e.`employee_id` LEFT JOIN qualification_configure q ON q.`company_id` = e.`company_id` WHERE e.`company_id` IN (?1) AND p.`expire_date` >= CURDATE() AND p.`expire_date` <= DATE_SUB(CURDATE(),INTERVAL - q.`time_span2` DAY))a",nativeQuery=true
			)
	Page<MessageDto> findRecordByCompanyIds2(List<Long> companyIds, Pageable pageable);
}
