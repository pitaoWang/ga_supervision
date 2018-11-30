package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.Airport;

@RepositoryRestResource(collectionResourceRel = "airport", path = "airport")
public interface AirportRepository extends PagingAndSortingRepository<Airport, Long> {

	@Query("select t from Airport t")
	List<Airport> findList();
	@Query("select t from Airport t where t.province=?1 and t.city=?2")
    List<Airport> findListbypc(String province,String city);
	
	
//	@Query("select t from Airport t where t.airportName like %?1%")
	@Query("select t from Airport t where t.airportName like ?1")
    List<Airport> getByairportName(String airportName);
	
	@Query(value="select * from airport LEFT JOIN company_airport ON airport.airport_id=company_airport.airport_id where company_airport.company_id=?1 \n-- #pageable\n",
			   nativeQuery=true)
	List<Airport> getlistbycompanyid(long companyid);
	
	@Modifying
	@Query(value="insert into company_airport(company_id,airport_id) values(?1,?2)",
			   nativeQuery=true)
	void savecompanyairport(long companyid,long airportid);
	
	@Modifying
	@Query(value="delete from company_airport where company_id=?1 and airport_id=?2 ",
			   nativeQuery=true)
	void deletecompanyairport(long companyid,long airportid);
	/**
	 * 
	 * 删除指定基地关联表的信息
	 * @param id
	 * @return
	 */
	@Modifying
	@Query(value=" DELETE FROM company_airport WHERE company_id=:id ",
			   nativeQuery=true)
	void deleteairportbycompanyid(@Param("id") long id);
	/**
	 * ;
	 * @param airportName
	 * @param companyid
	 * @param pageable
	 * @return
	 * 根据监管局查询通航机场
	 */
	@Query(value="select air.airport_id,air.airport_name,ga.flag from airport as air,ga_company ga where air.company_id=ga.company_id and air.airport_name like %?1% and air.supervise_orgid=?2 \n-- #pageable\n",
			   countQuery=" select count(*) from airport as air where air.airport_name like %?1% and air.supervise_orgid=?2",
			   nativeQuery=true)
	Page<Object[]> selectByAirportNameAndSupervise(String airportName,long companyid,Pageable pageable);
	/**
	 * 
	 * @param airportName
	 * @param companyid
	 * @param pageable
	 * @return
	 * 根据管理局查询通航机场
	 */
	@Query(value="select air.airport_id,air.airport_name,ga.flag from airport as air,ga_company ga where air.company_id=ga.company_id and air.airport_name like %?1% and air.authority_id=?2 \n-- #pageable\n",
			   countQuery=" select count(*) from airport as air where air.airport_name like %?1% and air.authority_id=?2",
			   nativeQuery=true)
	Page<Object[]> selectByAirportNameAuthority(String airportName,long companyid,Pageable pageable);
	
	//获取所有的机场的MaintainBaseSimple
	//List<AirportSimple> findByAirportIdNotNull();
		
	//根据公司ID获取机场信息
	//List<AirportSimple> findByCompanyId(@Param("companyId") GaCompany companyId);

	//根据公司ID获取本企业的以及租用的机场信息
	@Query(value="SELECT ap.airport_id as airportId,ap.airport_name as airportName,gc.company_id AS companyId,gc.name AS name FROM airport as ap left join "
			    + " ga_company as gc on ap.company_id=gc.company_id ",
			      nativeQuery=true)
	List<Object[]>  findAllAiport();
	
	//根据公司ID获取本企业的以及租用的机场信息
	@Query(value=" select airport.airport_id,airport.airport_name,airport.company_id,airport.grade,airport.isavailable "
			+ " from airport LEFT JOIN company_airport ON airport.airport_id=company_airport.airport_id "
			+ " where company_airport.company_id= :id or airport.company_id= :id ",
			   nativeQuery=true)
	List<Object[]> selectAirportbycompanyid(@Param("id") Long id);
	
	//根据主权公司ID获取本企业的机场
	@Query(value=" select * from airport  where airport.company_id= :id ",
			   nativeQuery=true)
	List<Airport> selectAirportbymaincompanyid(@Param("id") Long id);
	
	List<Airport> findByProvinceAndCity(@Param("province") String province,@Param("city") String city);
}
