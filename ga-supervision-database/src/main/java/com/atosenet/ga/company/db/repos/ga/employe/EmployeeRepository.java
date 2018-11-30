package com.atosenet.ga.company.db.repos.ga.employe;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.atosenet.ga.company.db.model.ga.employe.Employee;

@RepositoryRestResource(collectionResourceRel = "ee", path = "ee")
public interface EmployeeRepository extends JpaRepository<Employee, Long>,JpaSpecificationExecutor<Employee>{
	
	Employee findByEmployeeId(@Param("employeeId") Long employeeId);
	
	Employee findByUserId(@Param("userId") Long userId);
	
	@Query(value="SELECT * FROM employee e WHERE e.userId = :userId",nativeQuery = true)
	List<Employee> findByUserIds(@Param("userId") long userId);
	
	
	@Query(value="SELECT * "
			+ "FROM employee e "
			+ "WHERE e.jobtitle = :jobTitle "
			+ "AND e.company_id = :companyId "
			+ "LIMIT :page, :page_size",  nativeQuery = true)
	List<Employee> findByCompanyIdAndJobtitle(@Param("companyId") long companyId, @Param("page") int offset, @Param("page_size") int pageSize,@Param("jobTitle") String jobTitle);
	
	@Query(value="SELECT count(*) "
			+ "FROM employee e "
			+ "WHERE e.jobtitle = :jobTitle "
			+ "AND e.company_id = :companyId ",  nativeQuery = true)
    int getacountbyjobTitle(@Param("companyId") long companyId,@Param("jobTitle") String jobTitle);
	
	@Query(value="SELECT * "
			+ "FROM employee e "
			+ "WHERE e.jobtitle <> '飞行人员' "
			+ "AND e.jobtitle <> '航务人员'  "
			+ "AND e.jobtitle <> '机务人员'  "
			+ "AND e.jobtitle <> '飞行学员'  "
			+ "AND e.company_id = :companyId "
			+ "LIMIT :page, :page_size",  nativeQuery = true)
	List<Employee> findByCompanyIdAndJobtitleBysomeoneelse(@Param("companyId") long companyId, @Param("page") int offset, @Param("page_size") int pageSize);
	
	
	@Query(value="SELECT * "
			+ "FROM employee e "
			+ "WHERE e.jobtitle = :jobTitle "
			+ "AND e.company_id = :companyId "
			+ "AND e.username LIKE CONCAT('%',:keyword,'%') "
			+ "LIMIT :page, :page_size",  nativeQuery = true)
	List<Employee> findByCompanyIdAndJobtitle1(@Param("companyId") long companyId, @Param("page") int offset, @Param("page_size") int pageSize,@Param("jobTitle") String jobTitle,@Param("keyword") String keyword);
	
	@Query(value="SELECT count(*) "
			+ "FROM employee e "
			+ "WHERE e.jobtitle = :jobTitle "
			+ "AND e.company_id = :companyId "
			+ "AND e.username LIKE CONCAT('%',:keyword,'%') ",  nativeQuery = true)
	 int getacountbyjobTitle1(@Param("companyId") long companyId, @Param("jobTitle") String jobTitle,@Param("keyword") String keyword);
	
	@Query("select t from Employee t where cardid = ?1")
	Employee selectEmployeeByCardId(String cardId);
	

	@Query(value="SELECT * "
			+ "FROM employee e "
			+ "WHERE e.jobtitle <> '飞行人员' "
			+ "AND e.jobtitle <> '航务人员'  "
			+ "AND e.jobtitle <> '机务人员'  "
			+ "AND e.jobtitle <> '飞行学员'  "
			+ "AND e.company_id = :companyId "
			+ "AND e.username LIKE CONCAT('%',:keyword,'%') "
			+ "LIMIT :page, :page_size",  nativeQuery = true)
	List<Employee> findByCompanyIdAndJobtitleBysomeoneelse1(@Param("companyId") long companyId, @Param("page") int offset, @Param("page_size") int pageSize,@Param("keyword") String keyword);

	
	@Query(value="select count(e.employee_id) ,e.jobtitle  from employee e left join ga_company ga on e.company_id =ga.company_id where ga.company_id = ?1 group by e.jobtitle  ORDER BY count(e.employee_id) desc",nativeQuery=true)
	List<Object> getcountbytype1(long companyId);
	
//	@Query(value="SELECT count(employee_id) from employee where company_id=?1 and jobtitle='地服人员'",nativeQuery=true)
//	int getcountbytype2(long companyId);
//	
//	@Query(value="SELECT count(employee_id) from employee where company_id=?1 and jobtitle='航务人员'",nativeQuery=true)
//	int getcountbytype3(long companyId);
//	
//	@Query(value="SELECT count(employee_id) from employee where company_id=?1 and jobtitle='机务人员'",nativeQuery=true)
//	int getcountbytype4(long companyId);
//	
//	@Query(value="SELECT count(employee_id) from employee where company_id=?1 and jobtitle='飞行学员'",nativeQuery=true)
//	int getcountbytype5(long companyId);
//	
//	@Query(value="SELECT count(employee_id) from employee where company_id=?1 and jobtitle <> '飞行学员' and jobtitle <> '机务人员' and jobtitle <> '航务人员' and jobtitle <> '飞行人员'",nativeQuery=true)
//	int getcountbytype6(long companyId);
	
	@Query(value="select count(e.employee_id) ,e.education  from employee e left join ga_company ga on e.company_id =ga.company_id where ga.company_id = ?1 group by e.education  ORDER BY count(e.employee_id) desc",nativeQuery=true)
	List<Object> getcountbyedu1(long companyId);
	
//	@Query(value="SELECT count(employee_id) from employee where company_id=?1 and education='2'",nativeQuery=true)
//	int getcountbyedu2(long companyId);
//	
//	@Query(value="SELECT count(employee_id) from employee where company_id=?1 and education='3'",nativeQuery=true)
//	int getcountbyedu3(long companyId);
//	
//	@Query(value="SELECT count(employee_id) from employee where company_id=?1 and education='4'",nativeQuery=true)
//	int getcountbyedu4(long companyId);
//	
//	@Query(value="SELECT count(employee_id) from employee where company_id=?1 and education='5'",nativeQuery=true)
//	int getcountbyedu5(long companyId);
	
	@Query(value="select * from employee where cardid like CONCAT('%',:username,'%')",  nativeQuery = true)
	List<Employee> searchEmployeeByUsername(@Param("username")String username);
	

	/*@Query(value="select (CASE a.jobtitle WHEN '飞行人员' then '飞行人员' WHEN '飞行学员' then '飞行学员' WHEN '机务人员' then '机务人员' WHEN '航务人员' then '航务人员' ELSE '其他人员'  END) as type,COUNT(a.employee_id) FROM (SELECT * FROM employee WHERE company_id IN (SELECT company_id FROM ga_company WHERE supervision_id = :supervisionId)) AS a "
			+ "GROUP BY CASE a.jobtitle WHEN '飞行人员' then '飞行人员' "
			+ "WHEN '飞行学员' then '飞行学员' WHEN '机务人员' then '机务人员' WHEN '航务人员' then '航务人员' ELSE '其他人员'  END ORDER BY COUNT(a.employee_id) desc",  nativeQuery = true)*/
	@Query(value="select count(e.employee_id) ,e.jobtitle  from employee e left join ga_company ga on e.company_id =ga.company_id where ga.supervision_id = ?1 group by e.jobtitle  ORDER BY count(e.employee_id) desc",nativeQuery=true)
	List<Object>  findEmployeeAndcountBysupId(@Param("supervisionId")Long supervisionId);
	
	/*@Query(value="select (CASE a.jobtitle WHEN '飞行人员' then '飞行人员' WHEN '飞行学员' then '飞行学员' WHEN '机务人员' then '机务人员' WHEN '航务人员' then '航务人员' ELSE '其他人员'  END) as type,COUNT(a.employee_id) FROM (SELECT * FROM employee WHERE company_id IN (SELECT company_id FROM ga_company WHERE authority_id = :authorityId)) AS a "
			+ "GROUP BY CASE a.jobtitle WHEN '飞行人员' then '飞行人员' "
			+ "WHEN '飞行学员' then '飞行学员' WHEN '机务人员' then '机务人员' WHEN '航务人员' then '航务人员' ELSE '其他人员'  END ORDER BY COUNT(a.employee_id) desc",  nativeQuery = true)*/
	@Query(value="select count(e.employee_id) ,e.jobtitle  from employee e left join ga_company ga on e.company_id =ga.company_id where ga.authority_id = ?1 group by e.jobtitle  ORDER BY count(e.employee_id) desc",nativeQuery=true)
	List<Object>  findEmployeeAndcountByautid(@Param("authorityId")Long authorityId);

	@Query("select t from Employee t where t.companyId=?1 and t.jobTitle='飞行人员'")
	List<Employee> findListByCompanyId(long companyId);
	
	@Query("select t.jobTitle from Employee t where t.userId=?1")
	String findjobtitlebyuserid(long useid);
	
	/**
	 * 
	 * @param supervisionId
	 * @return
	 * 根据监管局ID统计员工受教育情况
	 */
	
	@Query(value="select count(e.employee_id) ,e.education  from employee e left join ga_company ga on e.company_id =ga.company_id where ga.supervision_id = ?1 group by e.education  ORDER BY count(e.employee_id) desc",
			nativeQuery=true)
	List<Object> getGroupEmployeeEductionBysupervisionId(Long supervisionId);
	
	@Query(value="select count(e.employee_id) ,e.education  from employee e left join ga_company ga on e.company_id =ga.company_id where ga.authority_id = ?1 group by e.education  ORDER BY count(e.employee_id) desc",
			nativeQuery=true)
	List<Object> getGroupEmployeeEductionByauthorityId(Long authorityId);


	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: getEmployeeByCompanyId 
	 * @param companyid
	 * @return  
	 * @throws
	 *
	 */
	@Query("select e from Employee e where companyId = ?1")
	List<Employee> getEmployeeByCompanyId(long companyid);


	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findEmployeeListBybaseid 
	 * @param parseLong
	 * @return  
	 * @throws
	 *
	 */
	@Query("select t from Employee t where t.baseId=?1")
	List<Employee> findEmployeeListBybaseid(long baseid);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findAllinfo 
	 * @param info
	 * @param offset
	 * @param pageSize
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="select * from employee :info LIMIT :offset, :pageSize",nativeQuery=true)
	List<Employee> findAllinfo(@Param("info")String info, @Param("offset")Integer offset, @Param("pageSize")Integer pageSize);
	
	@Query(value ="select * from employee e where e.userId =:userId limit 1",nativeQuery=true)
	Employee findOneByUserId(@Param("userId")Long userId);

	
	@Query(value="SELECT COUNT(*),license_class  FROM ("
			+"SELECT * FROM pilot_license WHERE id IN  ("
			+"SELECT id FROM person_qulificationinfo WHERE employee_id IN "
			+"(SELECT employee_id  FROM employee WHERE company_id=?1)"
			+" ))AS temp GROUP BY license_class",nativeQuery=true)
	List<Object> getPilotLicenseTypeCount(long companyId);
	
	@Query(value="SELECT COUNT(*),license_class  FROM ("
			+"SELECT * FROM pilot_license WHERE id IN  ("
			+"SELECT id FROM person_qulificationinfo WHERE employee_id IN "
			+"(SELECT employee_id  FROM employee WHERE authority_id=?1)"
			+" ))AS temp GROUP BY license_class",nativeQuery=true)
	List<Object> getPilotLicenseTypeCountByauthorityId(Long authorityId);
	
	@Query(value="SELECT COUNT(*),license_class  FROM ("
			+"SELECT * FROM pilot_license WHERE id IN  ("
			+"SELECT id FROM person_qulificationinfo WHERE employee_id IN "
			+"(SELECT employee_id  FROM employee WHERE supervision_id=?1)"
			+" ))AS temp GROUP BY license_class",nativeQuery=true)
	List<Object> getPilotLicenseTypeCountBysupervisionId(Long supervisionId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findEmployeeLists 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="SELECT *  FROM employee where FIND_IN_SET(company_id,?1)",nativeQuery=true)
	List<Employee> findEmployeeLists(String companyId);

	
}
