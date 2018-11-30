package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.Aircraft;
import com.atosenet.ga.company.db.model.ga.employe.Employee;

@RepositoryRestResource(collectionResourceRel = "aircraft", path = "aircraft")
public interface AircraftRepository extends PagingAndSortingRepository<Aircraft, Long>,JpaSpecificationExecutor<Aircraft> {

	@Query("select t from Aircraft t")
	List<Aircraft> findList();
	
	@Query(value="select * from aircraft t where t.company_name = ?1",
			nativeQuery=true)
	List<Aircraft> findAircraftList(String companyName);
	
	@Query(value="select * from aircraft as air left join ga_company  as com on com.company_id=air.company_id where FIND_IN_SET(air.company_id,:companyIds) \n-- #pageable\n",
			   countQuery=" select count(*) from aircraft where FIND_IN_SET(company_id,:companyIds) ",
			   nativeQuery=true)
	Page<Aircraft> selectBycompanyId(@Param("companyIds")String companyIds,Pageable pageable);

	@Query(value="select * from aircraft as air left join ga_company  as com on com.company_id=air.company_id where com.name like %?1% or air.airworthiness_certificate_number= ?1 \n-- #pageable\n",
			   countQuery=" select count(*) from aircraft as air left join ga_company  as com on com.company_id=air.company_id where com.name like %?1% or air.airworthiness_certificate_number= ?1 ",
			   nativeQuery=true)
	Page<Aircraft> selectBycompanyICNumber(String compnameorICN,Pageable pageable);
	
	@Query(value="select * from aircraft as air left join ga_company  as com on com.company_id=air.company_id where FIND_IN_SET(air.company_id,:compids) and (air.aircraft_model like %:info% or air.registernumber like %:info%)  \n-- #pageable\n",
			   countQuery=" select count(*) from aircraft as air left join ga_company  as com on com.company_id=air.company_id where (air.aircraft_model like %:info% or air.registernumber like %:info% ) and FIND_IN_SET(air.company_id,:compids) ",
			   nativeQuery=true)
	Page<Aircraft> selectBycompanyIds(@Param("info")String info,@Param("compids") String compids,Pageable pageable);
	
	@Query(value="select * from aircraft as air left join ga_company  as com on com.company_id=air.company_id where FIND_IN_SET(air.company_id,:companyids)  and (air.aircraft_model like %:info% or air.registernumber like %:info% ) \n-- #pageable\n",
			   countQuery=" select count(*) from aircraft as air left join ga_company  as com on com.company_id=air.company_id where (air.aircraft_model like %:info% or air.registernumber like %:info% ) and FIND_IN_SET(air.company_id,:companyids)",
			   nativeQuery=true)
	Page<Aircraft> selectByCompanyAndinfo(@Param("companyids")String companyids,@Param("info") String info,Pageable pageable);
	
	
	@Query(value="select * from aircraft as air left join ga_company  as com on com.company_id=air.company_id where FIND_IN_SET(air.company_id,:companyidds) order by air.fly_sum_time desc \n-- #pageable\n",
			   countQuery=" select count(*) from aircraft as air left join ga_company  as com on com.company_id=air.company_id where FIND_IN_SET(air.company_id,:companyidds) ",
			   nativeQuery=true)
	Page<Aircraft> selectBycompanyIdsAll(@Param("companyidds") String companyidds,Pageable pageable);
	
	@Query(value="select t.aircraft_model as aircraftModel,count(t.aircraft_id) as num from v_flight_time_as_aircraft as t where FIND_IN_SET(t.company_id,:companyidds) and t.aircraft_model is not null group by t.aircraft_model order by num desc",
			nativeQuery=true)
	List<Object> findModelByCompanyids(@Param("companyidds") String companyidds);
	
	@Query(value="select t.aircraft_class,count(t.aircraft_id) from aircraft t where FIND_IN_SET(t.company_id,?1) group by t.aircraft_class",
			nativeQuery=true)
	List<Object> getAircraftClassNum(String ids);
	
	@Query(value="select * from aircraft t where t.registernumber = ?1",
			nativeQuery=true)
	List<Aircraft> isExistAircraft(String registernumber);
	
	@Query(value="select DISTINCT company_name from aircraft where company_name is not null",
			nativeQuery=true)
	List<Object> airbelongcompanyname();
	
	/**
	 * 
	 * TODO(根据企业ID获取该企业所有的航空器列表) 
	 *
	 * @Title: findByCompanyId 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	List<Aircraft> findByCompanyId(@Param("companyId") Integer companyId);
	
	/**
	 * 
	 * TODO(根据航空器注册号获取航空器基本信息) 
	 *
	 * @Title: findOne 
	 * @param regNumber
	 * @return  
	 * @throws
	 *
	 */
	Aircraft findByRegNumber(@Param("regNumber") String regNumber);
	
	/**
	 * 
	 * TODO(根据监管局或管理局ID返回航空器列表) 
	 *
	 * @Title: findBySuperIdOrManagerId 
	 * @param superId
	 * @param managerId
	 * @return  
	 * @throws
	 *
	 */
	List<Aircraft> findBySuperIdOrManagerId(@Param("superId")long superId,@Param("managerId")long managerId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findAircraftByCompany 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="select * from aircraft t FIND_IN_SET(t.company_id,?1)",
			nativeQuery=true)
	List<Aircraft> findAircraftByCompany(String companyId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByRegNumberAndAircaftId 
	 * @param regnumber
	 * @param aircaftId
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="select * from aircraft t where t.reg_number=?1 and t.company_id = ?3 and  t.aircraft_id != ?2",
			nativeQuery=true)
	Aircraft findByRegNumberAndAircaftId(String regNumber,Long aircaftId,Integer companyId);
	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByCompanyId 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="select * from aircraft  WHERE  company_id =?1",
			   nativeQuery=true)
	List<Aircraft> findByCompanyId(String companyId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByRegNumberAndCompanyId 
	 * @param regNumber
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	Aircraft findByRegNumberAndCompanyId(@Param("regNumber")String regNumber, @Param("companyId")Integer companyId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findBySuperId 
	 * @param superid
	 * @return  
	 * @throws
	 *
	 */
	List<Aircraft> findBySuperId(@Param("superid")long superid);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByManagerId 
	 * @param managerid
	 * @return  
	 * @throws
	 *
	 */
	List<Aircraft> findByManagerId(@Param("managerid")long managerid);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: mut 
	 * @param name
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="SELECT t1.title as one,t2.title as two FROM t_permission t1,t_permission t2 where t1.parent_id = t2.id and t1.name=:name",
			nativeQuery=true)
	List<Object> mut(@Param("name")String name);

	/**
	 * @return  
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findallLists 
	 * @param companyId  
	 * @throws
	 *
	 */
	@Query(value="select count(*) from aircraft  WHERE  FIND_IN_SET(company_id,?1)",
			nativeQuery=true)
	String findallLists(String companyId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findflyLists 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="SELECT SUM(timestampdiff(SECOND,C_FLIGHT_TIME_BEGIN,C_FLIGHT_TIME_END)) AS sum_time FROM flight_record WHERE DATE_FORMAT(C_FLIGHT_DATE, '%Y%m') = DATE_FORMAT(CURDATE(), '%Y%m') and FIND_IN_SET(C_ORGANIZATION_ID,?1) union ALL SELECT SUM(timestampdiff(SECOND,C_FLIGHT_TIME_BEGIN,C_FLIGHT_TIME_END)) AS sum_time FROM flight_record WHERE FIND_IN_SET(C_ORGANIZATION_ID,?1)",
			nativeQuery=true)
	List<String> findflyLists(String companyId);
	
}
