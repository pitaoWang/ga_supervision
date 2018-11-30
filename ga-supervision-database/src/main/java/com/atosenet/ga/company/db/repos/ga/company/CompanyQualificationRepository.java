package com.atosenet.ga.company.db.repos.ga.company;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.CompanyQualification;



@RepositoryRestResource(collectionResourceRel = "cq", path = "cq")
public interface CompanyQualificationRepository extends PagingAndSortingRepository<CompanyQualification, Long>{
	@Query("select t from CompanyQualification t ")
	List<CompanyQualification> findList();
	@Query("select t from CompanyQualification t where t.companyId=?")
	List<CompanyQualification> findListbycompanyid(Long companyid);
	
	@Query(value = "SELECT cq.operate_type,COUNT(cq.operate_type) FROM ga_company ga LEFT JOIN  company_qualification cq ON ga.company_id = cq.company_id WHERE ga.supervision_id = ?1 GROUP BY cq.operate_type",nativeQuery = true)
	List<Object> companyQualificationStatic(Long supervision);
	
	@Query(value = "SELECT cq.operate_type,COUNT(cq.operate_type) FROM ga_company ga LEFT JOIN  company_qualification cq ON ga.company_id = cq.company_id WHERE ga.supervision_id in (?1) GROUP BY cq.operate_type",nativeQuery = true)
	List<Object> companyQualificationStaticBySuperIds(String[] superIds);
	
	@Query(value = "SELECT DISTINCT(operate_type) FROM company_qualification WHERE company_id=?1",nativeQuery = true)
	List<Object> findTypeByCompanyId(Integer companyId);
	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: companyqualificationstaticbycompanyIds 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	@Query(value = "SELECT cq.operate_type,COUNT(cq.operate_type) FROM ga_company ga LEFT JOIN  company_qualification cq ON ga.company_id = cq.company_id WHERE FIND_IN_SET(ga.company_id,?1) GROUP BY cq.operate_type",nativeQuery = true)
	List<Object> companyqualificationstaticbycompanyIds(String companyId);
	
	//查询企业资质提醒过期时间
		@Query(value="SELECT c.* FROM company_qualification c LEFT JOIN qualification_configure q ON q.company_id = c.company_id WHERE c.company_id IN (?1) AND c.endtime>=CURDATE() AND c.endtime<=DATE_SUB(CURDATE(),INTERVAL-q.time_span1 DAY) UNION ALL (SELECT c.* FROM company_qualification c WHERE c.company_id IN (?2) AND c.endtime>=CURDATE() AND c.endtime<=DATE_SUB(CURDATE(),INTERVAL-7 DAY)) \n-- #pageable\n ",
				countQuery="select count(*) from (SELECT c.* FROM company_qualification c LEFT JOIN qualification_configure q ON q.company_id = c.company_id WHERE c.company_id IN (?1) AND c.endtime>=CURDATE() AND c.endtime<=DATE_SUB(CURDATE(),INTERVAL-q.time_span1 DAY) UNION ALL SELECT c.* FROM company_qualification c WHERE c.company_id IN (?2) AND c.endtime>=CURDATE() AND c.endtime<=DATE_SUB(CURDATE(),INTERVAL-7 DAY))a",nativeQuery=true)
		Page<CompanyQualification> findRecordByCompanyId(List<Long> companyId,List<Long> companyIds1, Pageable pageable);
		
		//查询企业资质提醒过期时间(如果qualification_configure表中存在所有企业)
		@Query(value="SELECT c.* FROM company_qualification c LEFT JOIN qualification_configure q ON q.company_id = c.company_id WHERE c.company_id IN (?1) AND c.endtime>=CURDATE() AND c.endtime<=DATE_SUB(CURDATE(),INTERVAL-q.time_span1 DAY)  \n-- #pageable\n ",
				countQuery="select count(*) from (SELECT c.* FROM company_qualification c LEFT JOIN qualification_configure q ON q.company_id = c.company_id WHERE c.company_id IN (?1) AND c.endtime>=CURDATE() AND c.endtime<=DATE_SUB(CURDATE(),INTERVAL-q.time_span1 DAY) )a",nativeQuery=true)
		Page<CompanyQualification> findRecordByCompanyId1(List<Long> companyId, Pageable pageable);
	
	
}
