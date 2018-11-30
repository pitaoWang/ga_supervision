package com.atosenet.ga.company.db.repos.ga.company;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.GaCompany;

@RepositoryRestResource(collectionResourceRel = "comapnyrepos", path = "comapnyrepos")
public interface GaCompanyRepository extends PagingAndSortingRepository<GaCompany, Integer> {

	@Query("select t from GaCompany t")
	List<GaCompany> findList();
	
	@Query("select t from GaCompany t where companyId = :companyId")
	GaCompany findById(@Param("companyId") int companyId);
	
	@Query("select t from GaCompany t where orgId = :orgId")
	GaCompany findByOrgId(@Param("orgId") Long orgId);

	@Query("select t from GaCompany t where authorityId = ?1")
	List<GaCompany> findByAuthorityIdList(Long id);

	@Query("select t from GaCompany t where supervisionId = ?1")
	List<GaCompany> findBySupervisionIdList(Long id);
	
	//by dgw
	/*@Query("select count(t.id) as num,t.operateType from GaCompany t where authorityId = ?1 group by operateType")
	List<Object> getGroupInfo(Long id);*/
	
	@Query(value="select * from ga_company where ga_company.supervision_id =?1 \n-- #pageable\n",
			countQuery=" select count(*) from ga_company where ga_company.supervision_id =?1",
			 nativeQuery=true)
	Page<GaCompany> selectBySupervisionId(Long supervision_id,Pageable pageable);
	
	@Query(value="select * from ga_company where ga_company.authority_id =?1 \n-- #pageable\n",
			countQuery=" select count(*) from ga_company where ga_company.authority_id =?1",
			 nativeQuery=true)
	Page<GaCompany> selectByAuthorityId(Long authorityId,Pageable pageable);
	
	@Query(value="select * from ga_company where ga_company.supervision_id =?1 AND CONCAT(ga_company.name, ga_company.company_id) like CONCAT('%',?2,'%') \n-- #pageable\n",
			countQuery=" select count(*) from ga_company where ga_company.supervision_id =?1 AND CONCAT(ga_company.name, ga_company.company_id) like CONCAT('%',?2,'%')",
			 nativeQuery=true)
	Page<GaCompany> selectBySupervisionIdAndname(Long supervision_id,Pageable pageable,String keyword);
	
	@Query(value="select * from ga_company where ga_company.authority_id =?1 AND CONCAT(ga_company.name, ga_company.company_id) like CONCAT('%',?2,'%') \n-- #pageable\n",
			countQuery=" select count(*) from ga_company where ga_company.authority_id =?1 AND CONCAT(ga_company.name, ga_company.company_id) like CONCAT('%',?2,'%')",
			 nativeQuery=true)
	Page<GaCompany> selectByAuthorityIdAndname(Long authorityId,Pageable pageable,String keyword);
	
	//根据监管局、法人代表查询公司信息
		@Query(value="select * from ga_company where ga_company.supervision_id =?1 AND ga_company.legal_person like CONCAT('%',?2,'%') \n-- #pageable\n",
				countQuery = "select count(*) from ga_company where ga_company.supervision_id=?1 AND ga_company.legal_person like CONCAT('%',?2,'%')",
				 nativeQuery=true)
		Page<GaCompany> selectBySupervisionIdAndLegalPerson(Long supervision_id,Pageable pageable,String legalPerson);
		
		//根据监管局、企业名称和法人代表查询信息
		@Query(value="select * from ga_company where ga_company.supervision_id =?1 AND CONCAT(ga_company.name, ga_company.company_id) like CONCAT('%',?2,'%') AND  ga_company.legal_person like CONCAT('%',?3,'%') \n-- #pageable\n",
				countQuery = "select count(*) from ga_company where ga_company.supervision_id=?1 CONCAT(ga_company.name, ga_company.company_id) like CONCAT('%',?2,'%') AND ga_company.legal_person like CONCAT('%',?3,'%')",
				 nativeQuery=true)
		Page<GaCompany> selectBySupervisionIdAndCompanyNameAndLegalPerson(Long supervision_id,Pageable pageable,String companyName,String legalPerson);
		
	@Query("select t from GaCompany t where name=?1")
	GaCompany getbyname(String name);
	
	@Query(value="SELECT count(id) from company_business_type where supervision_id=?1 and business_type='1'",nativeQuery=true)
	int getcountbytype1(long listbysupid);
	
	@Query(value="SELECT count(id) from company_business_type where supervision_id=?1 and business_type='2'",nativeQuery=true)
	int getcountbytype2(long listbysupid);
	
	@Query(value="SELECT count(id) from company_business_type where supervision_id=?1 and business_type='3'",nativeQuery=true)
	int getcountbytype3(long listbysupid);
	
	@Query(value="SELECT count(id) from company_business_type where supervision_id=?1 and business_type='4'",nativeQuery=true)
	int getcountbytype4(long listbysupid);
	
	@Query(value="SELECT count(id) from company_business_type where authority_id=?1 and business_type='1'",nativeQuery=true)
	int getcountbytype1AndauthorityId(long authorityId);
	
	@Query(value="SELECT count(id) from company_business_type where authority_id=?1 and business_type='2'",nativeQuery=true)
	int getcountbytype2AndauthorityId(long authorityId);
	
	@Query(value="SELECT count(id) from company_business_type where authority_id=?1 and business_type='3'",nativeQuery=true)
	int getcountbytype3AndauthorityId(long authorityId);
	
	@Query(value="SELECT count(id) from company_business_type where authority_id=?1 and business_type='4'",nativeQuery=true)
	int getcountbytype4AndauthorityId(long authorityId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: updateOne 
	 * @param title
	 * @param id  
	 * @throws
	 *
	 */
	@Modifying
	@Query(value = "update ga_company set name=?1 where orgid=?2",nativeQuery = true)	
	int updateOne(String title, Long id);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: deletes 
	 * @param idsp  
	 * @throws
	 *
	 */
	@Modifying
	@Query(value = "delete from ga_company where orgid in (?1)",nativeQuery = true)
	void deletes(String idsp);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: deleteByOrgIdIn 
	 * @param idsp  
	 * @throws
	 *
	 */
	void deleteByOrgIdIn(@Param("idsp")List<Long> idsp);


	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByIdIn 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="select * from ga_company where FIND_IN_SET(company_id,:id)",
			
			 nativeQuery=true)
	List<GaCompany> findByIdInss(@Param("id")String id);

	
	/**
	 * 根据企业ids查询企业信息
	 * @param companyIds
	 * @param pageable
	 * @return
	 */
	@Query(value="select * from ga_company   where ga_company.company_id in (?1) \n-- #pageable\n",
			countQuery=" select count(*) from ga_company where ga_company.company_id in (?1)",
			 nativeQuery=true)
	Page<GaCompany> selectByCompanyIds(String[] companyIds,Pageable pageable);
	
	
	
	/**
	 * 根据企业ids查询下拉框
	 * @param companyIds
	 * @return
	 */
	@Query(value = "select * from ga_company where ga_company.company_id in (?1)",nativeQuery = true)
	List<GaCompany> selectByCompanyIds1(String[] companyIds);
	
	/**
	 * 根据企业查询企业信息
	 * @param companyId
	 * @param pageable
	 * @return
	 */
	@Query(value="select * from ga_company where ga_company.company_id = ?1 \n-- #pageable\n",
			countQuery=" select count(*) from ga_company where ga_company.company_id = ?1",
			 nativeQuery=true)
	Page<GaCompany> selectByCompanyIdsAndId(Long companyId,Pageable pageable);
	
	/**
	 * 根据法人代表
	 * @param companyIds
	 * @param legalPerson
	 * @param pageable
	 * @return
	 */
	@Query(value="select * from ga_company where ga_company.company_id in (?1) and ga_company.legal_person like CONCAT('%',?2,'%') \n-- #pageable\n",
			countQuery=" select count(*) from ga_company where ga_company.company_id in (?1) and ga_company.legal_person like CONCAT('%',?2,'%')",
			 nativeQuery=true)
	Page<GaCompany> selectByCompanyIdsAndLegalPerson(String[] companyIds,String legalPerson,Pageable pageable);
	
	/**
	 * 根据企业和法人代表查询企业信息
	 * @param companyIds
	 * @param companyId
	 * @param legalPerson
	 * @param pageable
	 * @return
	 */
	@Query(value="select * from ga_company where ga_company.company_id in (?1) and ga_company.company_id=?2 and ga_company.legal_person like CONCAT('%',?3,'%') \n-- #pageable\n",
			countQuery=" select count(*) from ga_company where ga_company.company_id in (?1) and ga_company.lcompany_id=?2 and ga_company.legal_person like CONCAT('%',?3,'%')",
			 nativeQuery=true)
	Page<GaCompany> selectAllByCompanyIds(String[] companyIds,Long companyId,String legalPerson,Pageable pageable);
	
	/**
	 * 根据监管局ids统计企业飞行时间
	 * @param supervisionIds
	 * @return
	 */
	@Query(value = "SELECT ga.name,SUM(TIMESTAMPDIFF(SECOND,fr.C_FLIGHT_TIME_BEGIN,fr.C_FLIGHT_TIME_END)) FROM flight_record fr LEFT JOIN ga_company ga ON ga.company_id = fr.C_ORGANIZATION_ID WHERE ga.supervision_id IN (?1)"
          +"GROUP BY ga.company_id",nativeQuery = true)
	List<Object> staticFlightTimeBySupervisionIds(String[] supervisionIds);
	
	/**
	 * 根据监管局id统计企业飞行时间
	 * @param supervisionId
	 * @return
	 */
	@Query(value = "SELECT ga.name,SUM(TIMESTAMPDIFF(SECOND,fr.C_FLIGHT_TIME_BEGIN,fr.C_FLIGHT_TIME_END)) FROM flight_record fr LEFT JOIN ga_company ga ON ga.company_id = fr.C_ORGANIZATION_ID WHERE ga.supervision_id = ?1 GROUP BY ga.company_id",nativeQuery = true)
	List<Object> staticFlightTimeBySupervisionId(String supervisionId);
	
	/**
	 * 根据监管局ids和时间段统计企业飞行时间
	 * @param supervisionids
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@Query(value = "SELECT ga.name,SUM(TIMESTAMPDIFF(SECOND,fr.C_FLIGHT_TIME_BEGIN,fr.C_FLIGHT_TIME_END)) FROM flight_record fr LEFT JOIN ga_company ga ON ga.company_id = fr.C_ORGANIZATION_ID WHERE ga.supervision_id IN (?1)  and (fr.C_FLIGHT_DATE>=?2 and fr.C_FLIGHT_DATE<=?3)"
	          +"GROUP BY ga.company_id",nativeQuery = true)
	List<Object> staticFlightTimeBySupIdsAndDate(String[] supervisionids,String beginDate,String endDate);
	
	/**
	 * 根据作业类别查询企业飞行时间
	 * @param supervisionIds
	 * @param flightNature
	 * @return
	 */
	@Query(value = "SELECT ga.name,SUM(TIMESTAMPDIFF(SECOND,fr.C_FLIGHT_TIME_BEGIN,fr.C_FLIGHT_TIME_END)) FROM flight_record fr LEFT JOIN ga_company ga ON ga.company_id = fr.C_ORGANIZATION_ID WHERE ga.supervision_id IN (?1) and fr.C_FLIGHT_NATURE=?2 GROUP BY ga.company_id",nativeQuery = true)
	List<Object> staticFlightTimeBySupIdsAndNature(String[]supervisionIds,Integer flightNature);
	
	/**
	 * 根据日期和作业类别统计企业飞行时间
	 * @param supervisionIds
	 * @param beginDate
	 * @param endDate
	 * @param FlightNature
	 * @return
	 */
	@Query(value = "SELECT ga.name,SUM(TIMESTAMPDIFF(SECOND,fr.C_FLIGHT_TIME_BEGIN,fr.C_FLIGHT_TIME_END)) FROM flight_record fr LEFT JOIN ga_company ga ON ga.company_id = fr.C_ORGANIZATION_ID WHERE ga.supervision_id IN (?1) and (fr.C_FLIGHT_DATE>=?2 and fr.C_FLIGHT_DATE<=?3)and fr.C_FLIGHT_NATURE=?4 GROUP BY ga.company_id",nativeQuery = true)
	List<Object> staticFlightTimeBySupIdsAndDateAndNature(String[]supervisionIds,String beginDate,String endDate,Integer FlightNature);
	
	/**
	 * 根据日期和监管局id统计企业飞行时间
	 * @param supervisionId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@Query(value = "SELECT ga.name,SUM(TIMESTAMPDIFF(SECOND,fr.C_FLIGHT_TIME_BEGIN,fr.C_FLIGHT_TIME_END)) FROM flight_record fr LEFT JOIN ga_company ga ON ga.company_id = fr.C_ORGANIZATION_ID WHERE ga.supervision_id = ?1 and (fr.C_FLIGHT_DATE>=?2 and fr.C_FLIGHT_DATE<=?3) GROUP BY ga.company_id",nativeQuery = true)
	List<Object> staticFlightTimeByDateAndSupId(String supervisionId,String beginDate,String endDate);
	
	/**
	 * 根据工作类别和监管局统计企业飞行时间
	 * @param supervisionId
	 * @param flightNature
	 * @return
	 */
	@Query(value = "SELECT ga.name,SUM(TIMESTAMPDIFF(SECOND,fr.C_FLIGHT_TIME_BEGIN,fr.C_FLIGHT_TIME_END)) FROM flight_record fr LEFT JOIN ga_company ga ON ga.company_id = fr.C_ORGANIZATION_ID WHERE ga.supervision_id = ?1 and fr.C_FLIGHT_NATURE=?2 GROUP BY ga.company_id",nativeQuery = true)
	List<Object> staticFlightTimeByNatureAndSupId(String supervisionId,Integer flightNature);
	
	/**
	 * 根据所有条件统计企业飞行时间
	 * @param supervisionId
	 * @param beginDate
	 * @param endDate
	 * @param FlightNature
	 * @return
	 */
	@Query(value = "SELECT ga.name,SUM(TIMESTAMPDIFF(SECOND,fr.C_FLIGHT_TIME_BEGIN,fr.C_FLIGHT_TIME_END)) FROM flight_record fr LEFT JOIN ga_company ga ON ga.company_id = fr.C_ORGANIZATION_ID WHERE ga.supervision_id = ?1 and (fr.C_FLIGHT_DATE>=?2 and fr.C_FLIGHT_DATE<=?3)and fr.C_FLIGHT_NATURE=?4 GROUP BY ga.company_id",nativeQuery = true)
	List<Object> staticFlightTimeByAll(String supervisionId,String beginDate,String endDate,Integer FlightNature);
	
	/**
	 * 根据日期和企业id统计企业飞行时间
	 * @param beginDate
	 * @param endDate
	 * @param companyId
	 * @return
	 */
	@Query(value = "SELECT  dayTb.cday, IFNULL(fly_time,0) fly_time FROM (SELECT @cdate \\:= DATE_ADD(@cdate, INTERVAL +1 DAY) cday FROM( SELECT @cdate \\:= DATE_ADD(?1, INTERVAL -1 DAY) FROM t_static_tmp ) t0 WHERE DATE(@cdate) <= DATE_ADD(?2, INTERVAL -1 DAY) ) dayTb LEFT JOIN(SELECT C_ORGANIZATION_ID,C_FLIGHT_DATE,C_FLIGHT_NATURE, SUM(TIMESTAMPDIFF(SECOND,C_FLIGHT_TIME_BEGIN,C_FLIGHT_TIME_END)) fly_time  FROM flight_record  GROUP BY C_ORGANIZATION_ID,C_FLIGHT_DATE HAVING C_ORGANIZATION_ID = ?3 and C_FLIGHT_NATURE in (?4)) tt ON tt.C_FLIGHT_DATE = dayTb.cday ORDER BY  dayTb.cday",nativeQuery = true)
	List<Object> staticFlightCountsByDateAndCompanyId(Date beginDate,Date endDate,String companyId,String[] flightNatures);
	
	/**
	 * 根据监管局ids统计企业经营类别
	 * @param authId
	 * @return
	 */
	@Query(value="select business_type from ga_company where supervision_id in ?1",nativeQuery = true)
	List<String> staticBusinessTypeBySuperIds(String[] superIds);
	
	/**
	 * 根据监管局ids初始化统计飞行时间
	 * @param supervisionIds
	 * @param beginDate
	 * @param endDate
	 * @param FlightNatures
	 * @return
	 */
	@Query(value = "SELECT ga. NAME,SUM(TIMESTAMPDIFF(SECOND,fr.C_FLIGHT_TIME_BEGIN,fr.C_FLIGHT_TIME_END))FROM flight_record fr LEFT JOIN ga_company ga ON ga.company_id = fr.C_ORGANIZATION_ID WHERE ga.supervision_id IN (?1)and (fr.C_FLIGHT_DATE>=?2 and fr.C_FLIGHT_DATE<=?3)and fr.C_FLIGHT_NATURE in (?4) GROUP BY ga.company_id",nativeQuery=true)
	List<Object> staticFlight(String[] supervisionIds,String beginDate,String endDate,String[] FlightNatures);
	
	/**
	 * 根据监管局ids查询企业
	 * @param supervisionIds
	 * @return
	 */
	@Query(value = "select ga_company.company_id, ga_company.name from ga_company where supervision_id in ?1",nativeQuery = true)
	List<Object> selectCompanysBySupervisionIds(String[] supervisionIds);
	
	/**
	 * 根据企业id查询企业名称
	 * @param companyIds
	 * @return
	 */
	@Query(value = "select ga_company.name  from ga_company where ga_company.company_id = ?1",nativeQuery = true)
	List<Object> selectCompanyNamesByCompanyId(String companyId);
	
	/**
	 * 根据监管局id（管理局id）查询名字
	 * @param id
	 * @return
	 */
	@Query(value = "select title from t_department where id = ?1",nativeQuery = true)
	String selectSupvisionNameBySupId(String id);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: staticBusinessTypeByCompanyIds 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="select business_type from ga_company where FIND_IN_SET(company_id,?1)",nativeQuery = true)
	List<Object> staticBusinessTypeByCompanyIds(String companyId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: staticFlights 
	 * @param companyId
	 * @param beginDate
	 * @param endDate
	 * @param flightNatures
	 * @return  
	 * @throws
	 *
	 */
	@Query(value = "SELECT ga. NAME,SUM(TIMESTAMPDIFF(SECOND,fr.C_FLIGHT_TIME_BEGIN,fr.C_FLIGHT_TIME_END)) as sumtime FROM flight_record fr LEFT JOIN ga_company ga ON ga.company_id = fr.C_ORGANIZATION_ID WHERE FIND_IN_SET(ga.company_id,?1)and (fr.C_FLIGHT_DATE>=?2 and fr.C_FLIGHT_DATE<=?3) GROUP BY ga.company_id ORDER BY sumtime desc LIMIT 0,10",nativeQuery=true)
	List<Object> staticFlights(String companyId, String beginDate, String endDate);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: staticFlightsv 
	 * @param companyId
	 * @param beginDate
	 * @param endDate
	 * @return  
	 * @throws
	 *
	 */
	@Query(value = "SELECT ga. NAME,SUM(TIMESTAMPDIFF(SECOND,fr.C_FLIGHT_TIME_BEGIN,fr.C_FLIGHT_TIME_END)) as sumtime FROM flight_record fr LEFT JOIN ga_company ga ON ga.company_id = fr.C_ORGANIZATION_ID WHERE FIND_IN_SET(ga.company_id,?1)and (fr.C_FLIGHT_DATE>=?2 and fr.C_FLIGHT_DATE<=?3) GROUP BY ga.company_id ORDER BY sumtime desc ",nativeQuery=true)
	List<Object> staticFlightsv(String companyId, String beginDate, String endDate);
}