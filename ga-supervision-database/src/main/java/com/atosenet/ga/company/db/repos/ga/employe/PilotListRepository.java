package com.atosenet.ga.company.db.repos.ga.employe;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.employe.vo.PilotListVO;

@RepositoryRestResource(collectionResourceRel = "ptv", path = "ptv")
public interface PilotListRepository extends JpaRepository<PilotListVO, Long> {

	/**
	 * 查询飞行员的基本信息
	 * @param companyId
	 * @param offset
	 * @param pageSize
	 * @return
	 *///, c.name as company_name
	@Query(value="SELECT "
			+ "e.employee_id, e.company_id, e.username, e.givenname, e.join_date, e.cardid, e.home_mobile, e.business_tel,e.flag, p.pilot_id, p.tech_class, p.fly_sum_time, p.fly_sum_illegal, p.flight_team "
			+ "FROM employee e LEFT JOIN pilot p "
			+ "on e.employee_id = p.employee_id "
			+ "where e.company_id = :company_id "
			+ "AND e.jobtitle = :jobTitle "
			+ "ORDER BY p.fly_sum_illegal DESC, p.fly_sum_time DESC LIMIT :page, :page_size",  nativeQuery = true)
	List<PilotListVO> findPilot4Company(@Param("company_id") long companyId, @Param("page") int offset, @Param("page_size") int pageSize,@Param("jobTitle") String jobTitle);
	
	
	@Query(value="SELECT count(*) "
			+ "FROM employee e LEFT JOIN pilot p "
			+ "on e.employee_id = p.employee_id "
			+ "where e.company_id = :company_id "
			+ "AND e.jobtitle = :jobTitle "
			+ "ORDER BY p.fly_sum_illegal DESC, p.fly_sum_time DESC",  nativeQuery = true)
	int getacountpliot2(@Param("company_id") long companyId, @Param("jobTitle") String jobTitle);
	/**
	 * 通过飞行员名称查询飞行员的基本信息
	 * @param companyId
	 * @param offset
	 * @param pageSize
	 * @return
	 *///, c.name as company_name
	@Query(value="SELECT "
			+ "e.employee_id, e.company_id, e.username, e.givenname, e.join_date, e.cardid, e.home_mobile, e.business_tel,e.flag, p.pilot_id, p.tech_class, p.fly_sum_time, p.fly_sum_illegal, p.flight_team "
			+ "FROM employee e LEFT JOIN pilot p "
			+ "on e.employee_id = p.employee_id "
			+ "where e.company_id = :company_id "
			+ "AND e.jobtitle = :jobTitle "
			+ "AND e.username LIKE CONCAT('%',:keyword,'%')"
			+ "ORDER BY p.fly_sum_illegal DESC, p.fly_sum_time DESC LIMIT :page, :page_size",  nativeQuery = true)
	List<PilotListVO> querySerchPilot4Company(@Param("company_id") long companyId,@Param("keyword") String keyword,@Param("page") int offset, @Param("page_size") int pageSize,@Param("jobTitle") String jobTitle);
	
	
	/**
	 * 通过飞行员名称查询飞行员的基本信息
	 * @param companyId
	 * @param offset
	 * @param pageSize
	 * @return
	 *///, c.name as company_name
	@Query(value="SELECT count(*) "
			+ "FROM employee e LEFT JOIN pilot p "
			+ "on e.employee_id = p.employee_id "
			+ "where e.company_id = :company_id "
			+ "AND e.jobtitle = :jobTitle "
			+ "AND e.username LIKE CONCAT('%',:keyword,'%')"
			+ "ORDER BY p.fly_sum_illegal DESC, p.fly_sum_time DESC",  nativeQuery = true)
	int getacountpliot3(@Param("company_id") long companyId,@Param("keyword") String keyword,@Param("jobTitle") String jobTitle);
	/**
	 * 查询多个企业飞行员的基本信息
	 * @param companyId
	 * @param offset
	 * @param pageSize
	 * @return
	 *///, c.name as company_name
	@Query(value="SELECT "
			+ "e.employee_id, e.company_id, e.username, e.givenname, e.join_date, e.cardid, e.home_mobile, e.business_tel,e.flag, p.pilot_id, p.tech_class, p.fly_sum_time, p.fly_sum_illegal, p.flight_team "
			+ "FROM employee e LEFT JOIN pilot p "
			+ "on e.employee_id = p.employee_id "
			+ "where e.jobtitle = :jobTitle "
			+ "AND e.company_id in :companyIdList "	
			+ "ORDER BY p.fly_sum_illegal DESC, p.fly_sum_time DESC  LIMIT :page, :page_size",  nativeQuery = true)
	List<PilotListVO> queryPilotList(@Param("companyIdList") List<Integer> companyIdList ,@Param("page") int offset, @Param("page_size") int pageSize,@Param("jobTitle") String jobTitle);
	
	@Query(value="SELECT count(*) "
			+ "FROM employee e LEFT JOIN pilot p "
			+ "on e.employee_id = p.employee_id "
			+ "where e.jobtitle = :jobTitle "
			+ "AND e.company_id in :companyIdList "	
			+ "ORDER BY p.fly_sum_illegal DESC, p.fly_sum_time DESC",  nativeQuery = true)
	int getacountpliot(@Param("companyIdList") List<Integer> companyIdList ,@Param("jobTitle") String jobTitle);
	
	/**
	 * 查询多个企业飞行员的基本信息
	 * @param companyId
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	@Query(value="SELECT "
			+ "e.employee_id, e.company_id, e.username, e.givenname, e.join_date, e.cardid, e.home_mobile, e.business_tel,e.flag, p.pilot_id, p.tech_class, p.fly_sum_time, p.fly_sum_illegal, p.flight_team "
			+ "FROM employee e LEFT JOIN pilot p "
			+ "on e.employee_id = p.employee_id "
			+ "where e.jobtitle = :jobTitle "
			+ "AND e.company_id in :companyIdList "		
			+ "AND CONCAT(e.username, e.workid) like CONCAT('%',:keyword,'%')"
			+ "ORDER BY p.fly_sum_illegal DESC, p.fly_sum_time DESC  LIMIT :page, :page_size"		
			,  nativeQuery = true)//+ "ORDER BY p.fly_sum_illegal DESC, p.fly_sum_time DESC  LIMIT :page, :page_size"
	List<PilotListVO> querySerchPilotList(@Param("companyIdList") List<Integer> companyIdList ,@Param("keyword") String keyword,@Param("jobTitle") String jobTitle,@Param("page") int offset, @Param("page_size") int pageSize);//,@Param("page") int offset, @Param("page_size") int pageSize
	
	@Query(value="SELECT count(*) "
			+ "FROM employee e LEFT JOIN pilot p "
			+ "on e.employee_id = p.employee_id "
			+ "where e.jobtitle = :jobTitle "
			+ "AND e.company_id in :companyIdList "		
			+ "AND CONCAT(e.username, e.workid) like CONCAT('%',:keyword,'%')"		
			,  nativeQuery = true)//+ "ORDER BY p.fly_sum_illegal DESC, p.fly_sum_time DESC  LIMIT :page, :page_size"
int getacountpliot3(@Param("companyIdList") List<Integer> companyIdList ,@Param("keyword") String keyword,@Param("jobTitle") String jobTitle);//,@Param("page") int offset, @Param("page_size") int pageSize
	/**
	 * 查詢企業員工的执照信息、企业信息、员工信息等
	 * @param companyId
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	@Query(value="SELECT "
			+ "p.pilot_id,temp3.*, p.flight_team,p.fly_sum_illegal FROM (SELECT e.company_id,e.username,e.givenname,e.join_date,e.cardid,e.home_mobile,e.business_tel,e.flag,e.tech_grade AS tech_class,temp1.* "
			+ "FROM(SELECT temp.employee_id,temp.fly_date,sum(temp.sum_time) AS fly_sum_time "
			+ "FROM(SELECT cap.* FROM v_flight_time_captain cap WHERE cap.company_id = :company_id "
			+ "AND cap.fly_date > :begaindata AND cap.fly_date < :enddata UNION ALL SELECT cop.* "
			+ "FROM v_flight_time_copilot cop WHERE cop.company_id = :company_id AND cop.fly_date > :begaindata AND cop.fly_date < :enddata) AS temp GROUP BY temp.employee_id) AS temp1 "
			+ "LEFT JOIN employee e ON temp1.employee_id = e.employee_id) AS temp3 "
			+ "LEFT JOIN pilot p ON temp3.employee_id = p.employee_id "
			+ "ORDER BY temp3.fly_sum_time desc  LIMIT :page, :page_size ",  nativeQuery = true)
	List<PilotListVO> findPilot4CompanyWithLicense(@Param("company_id") long companyId, @Param("page") int offset, @Param("page_size") int pageSize,@Param("begaindata") String begaindata,@Param("enddata") String enddata);
	
	
	
	//飞行统计
	@Query(value="SELECT C_FLIGHT_PERSON_ID,username ,total,tech_class, flight_team FROM"
			+"(select * from ("
	+"SELECT SUM(diff) AS total , C_FLIGHT_PERSON_ID FROM "
	+"("
	+"SELECT C_FLIGHT_PERSON_ID, C_FLIGHT_DATE,TIMESTAMPDIFF(SECOND,C_FLIGHT_TIME_BEGIN,C_FLIGHT_TIME_END) AS diff FROM (SELECT * FROM flight_record WHERE C_FLIGHT_PERSON_ID IN"
	+"(SELECT employee_id FROM employee WHERE company_id = :company_id AND employee_id IN "
	+"(SELECT employee_id FROM person_qulificationinfo WHERE id IN (SELECT id FROM pilot_license WHERE license_class= :type))))AS cc WHERE C_FLIGHT_DATE > :begaindata AND C_FLIGHT_DATE < :enddata"
	+") AS ta"
	+" GROUP BY C_FLIGHT_PERSON_ID"
	+") AS temp1, pilot  WHERE temp1.C_FLIGHT_PERSON_ID = pilot.employee_id) AS temp3 , employee  WHERE temp3.employee_id = employee.employee_id LIMIT :page , :page_size",nativeQuery = true)
	List<PilotListVO> statistic(@Param("company_id")long companyId, @Param("page")int offset,@Param("page_size")int pageSize,@Param("begaindata")String begaindata, @Param("enddata")String enddate,@Param("type")String type);
	
	
	//分页  统计数量
	@Query(value="SELECT COUNT(*) FROM"
			+"(select * from ("
	+"SELECT SUM(diff) AS total , C_FLIGHT_PERSON_ID FROM "
	+"("
	+"SELECT C_FLIGHT_PERSON_ID, C_FLIGHT_DATE,TIMESTAMPDIFF(SECOND,C_FLIGHT_TIME_BEGIN,C_FLIGHT_TIME_END) AS diff FROM (SELECT * FROM flight_record WHERE C_FLIGHT_PERSON_ID IN"
	+"(SELECT employee_id FROM employee WHERE company_id = :company_id AND employee_id IN "
	+"(SELECT employee_id FROM person_qulificationinfo WHERE id IN (SELECT id FROM pilot_license WHERE license_class= :type))))AS cc WHERE C_FLIGHT_DATE > :begaindata AND C_FLIGHT_DATE < :enddata"
	+") AS ta"
	+" GROUP BY C_FLIGHT_PERSON_ID"
	+") AS temp1, pilot  WHERE temp1.C_FLIGHT_PERSON_ID = pilot.employee_id) AS temp3 , employee  WHERE temp3.employee_id = employee.employee_id",nativeQuery = true)
	int getCount(@Param("company_id")long companyId, @Param("begaindata")String begaindata, @Param("enddata")String enddate, @Param("type")String type);
	
	
	
	@Query(value="SELECT "
			+ "count(*) FROM (SELECT e.company_id,e.username,e.givenname,e.join_date,e.cardid,e.home_mobile,e.business_tel,e.flag,e.tech_grade AS tech_class,temp1.* "
			+ "FROM(SELECT temp.employee_id,temp.fly_date,sum(temp.sum_time) AS fly_sum_time "
			+ "FROM(SELECT cap.* FROM v_flight_time_captain cap WHERE cap.company_id = :company_id "
			+ "AND cap.fly_date > :begaindata AND cap.fly_date < :enddata UNION ALL SELECT cop.* "
			+ "FROM v_flight_time_copilot cop WHERE cop.company_id = :company_id AND cop.fly_date > :begaindata AND cop.fly_date < :enddata) AS temp GROUP BY temp.employee_id) AS temp1 "
			+ "LEFT JOIN employee e ON temp1.employee_id = e.employee_id) AS temp3 "
			+ "LEFT JOIN pilot p ON temp3.employee_id = p.employee_id "
			+ "ORDER BY temp3.fly_sum_time desc",  nativeQuery = true)
	int findPilot4CompanyWithLicensecount(@Param("company_id") long companyId,@Param("begaindata") String begaindata,@Param("enddata") String enddata);
	/**
	 * 查詢企業員工的执照信息、企业信息、员工信息等
	 * @param companyId
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	 
		@Query(value="SELECT "
				+ "p.pilot_id,temp3.*, p.flight_team,p.fly_sum_illegal FROM (SELECT e.company_id,e.username,e.givenname,e.join_date,e.cardid,e.home_mobile,e.business_tel,e.flag,e.tech_grade AS tech_class,temp1.* "
				+ "FROM(SELECT temp.employee_id,temp.fly_date,sum(temp.sum_time) AS fly_sum_time "
				+ "FROM(SELECT cap.* FROM v_flight_time_captain cap WHERE cap.company_id in :companyIdList "
				+ "AND cap.fly_date > :begaindata AND cap.fly_date < :enddata UNION ALL SELECT cop.* "
				+ "FROM v_flight_time_copilot cop WHERE cop.company_id in :companyIdList AND cop.fly_date > :begaindata AND cop.fly_date < :enddata) AS temp GROUP BY temp.employee_id) AS temp1 "
				+ "LEFT JOIN employee e ON temp1.employee_id = e.employee_id) AS temp3 "
				+ "LEFT JOIN pilot p ON temp3.employee_id = p.employee_id "
				+ "ORDER BY temp3.fly_sum_time desc  LIMIT :page, :page_size ",  nativeQuery = true)
	List<PilotListVO> findPilot4CompanyWithLicenseBysupId(@Param("companyIdList") List<Integer> companyIdList, @Param("page") int offset, @Param("page_size") int pageSize,@Param("begaindata") String begaindata,@Param("enddata") String enddata);
	/**
	 * 查詢企業員工的执照信息、企业信息、员工信息等
	 * @param companyId
	 * @param offset
	 * @param pageSize
	 * @return
	 */
		@Query(value="SELECT "
				+ "count(*) FROM (SELECT e.company_id,e.username,e.givenname,e.join_date,e.cardid,e.home_mobile,e.business_tel,e.flag,e.tech_grade AS tech_class,temp1.* "
				+ "FROM(SELECT temp.employee_id,temp.fly_date,sum(temp.sum_time) AS fly_sum_time "
				+ "FROM(SELECT cap.* FROM v_flight_time_captain cap WHERE cap.company_id in :companyIdList "
				+ "AND cap.fly_date > :begaindata AND cap.fly_date < :enddata UNION ALL SELECT cop.* "
				+ "FROM v_flight_time_copilot cop WHERE cop.company_id in :companyIdList AND cop.fly_date > :begaindata AND cop.fly_date < :enddata) AS temp GROUP BY temp.employee_id) AS temp1 "
				+ "LEFT JOIN employee e ON temp1.employee_id = e.employee_id) AS temp3 "
				+ "LEFT JOIN pilot p ON temp3.employee_id = p.employee_id "
				+ "ORDER BY temp3.fly_sum_time desc",  nativeQuery = true)
	int findPilot4CompanyWithLicensecountBysupId(@Param("companyIdList") List<Integer> companyIdList,@Param("begaindata") String begaindata,@Param("enddata") String enddata);

	
		
		@Query(value="SELECT C_FLIGHT_PERSON_ID,username ,total,tech_class, flight_team FROM"
				+"(select * from ("
		+"SELECT SUM(diff) AS total , C_FLIGHT_PERSON_ID FROM "
		+"("
		+"SELECT C_FLIGHT_PERSON_ID, C_FLIGHT_DATE,TIMESTAMPDIFF(SECOND,C_FLIGHT_TIME_BEGIN,C_FLIGHT_TIME_END) AS diff FROM (SELECT * FROM flight_record WHERE C_FLIGHT_PERSON_ID IN"
		+"(SELECT employee_id FROM employee WHERE authority_id = :authority_id AND employee_id IN "
		+"(SELECT employee_id FROM person_qulificationinfo WHERE id IN (SELECT id FROM pilot_license WHERE license_class= :type))))AS cc WHERE C_FLIGHT_DATE > :begaindata AND C_FLIGHT_DATE < :enddata"
		+") AS ta"
		+" GROUP BY C_FLIGHT_PERSON_ID"
		+") AS temp1, pilot  WHERE temp1.C_FLIGHT_PERSON_ID = pilot.employee_id) AS temp3 , employee  WHERE temp3.employee_id = employee.employee_id LIMIT :page , :page_size",nativeQuery = true)
	List<PilotListVO> statisticByauthorityId(@Param("authority_id")long authorityId, @Param("page")int offset,@Param("page_size")int pageSize,@Param("begaindata")String begaindata, @Param("enddata")String enddate,@Param("type")String type);
		
		//分页统计数量  按照管理局id
		@Query(value="SELECT COUNT(*) FROM"
				+"(select * from ("
		+"SELECT SUM(diff) AS total , C_FLIGHT_PERSON_ID FROM "
		+"("
		+"SELECT C_FLIGHT_PERSON_ID, C_FLIGHT_DATE,TIMESTAMPDIFF(SECOND,C_FLIGHT_TIME_BEGIN,C_FLIGHT_TIME_END) AS diff FROM (SELECT * FROM flight_record WHERE C_FLIGHT_PERSON_ID IN"
		+"(SELECT employee_id FROM employee WHERE authority_id = :authority_id AND employee_id IN "
		+"(SELECT employee_id FROM person_qulificationinfo WHERE id IN (SELECT id FROM pilot_license WHERE license_class= :type))))AS cc WHERE C_FLIGHT_DATE > :begaindata AND C_FLIGHT_DATE < :enddata"
		+") AS ta"
		+" GROUP BY C_FLIGHT_PERSON_ID"
		+") AS temp1, pilot  WHERE temp1.C_FLIGHT_PERSON_ID = pilot.employee_id) AS temp3 , employee  WHERE temp3.employee_id = employee.employee_id",nativeQuery = true)
		Integer getCountByauthorityId(@Param("authority_id")long authorityId, @Param("begaindata")String begaindata, @Param("enddata")String enddate, @Param("type")String type);

		
		@Query(value="SELECT C_FLIGHT_PERSON_ID,username ,total,tech_class, flight_team FROM"
				+"(select * from ("
		+"SELECT SUM(diff) AS total , C_FLIGHT_PERSON_ID FROM "
		+"("
		+"SELECT C_FLIGHT_PERSON_ID, C_FLIGHT_DATE,TIMESTAMPDIFF(SECOND,C_FLIGHT_TIME_BEGIN,C_FLIGHT_TIME_END) AS diff FROM (SELECT * FROM flight_record WHERE C_FLIGHT_PERSON_ID IN"
		+"(SELECT employee_id FROM employee WHERE supervision_id = :supervision_id AND employee_id IN "
		+"(SELECT employee_id FROM person_qulificationinfo WHERE id IN (SELECT id FROM pilot_license WHERE license_class= :type))))AS cc WHERE C_FLIGHT_DATE > :begaindata AND C_FLIGHT_DATE < :enddata"
		+") AS ta"
		+" GROUP BY C_FLIGHT_PERSON_ID"
		+") AS temp1, pilot  WHERE temp1.C_FLIGHT_PERSON_ID = pilot.employee_id) AS temp3 , employee  WHERE temp3.employee_id = employee.employee_id LIMIT :page , :page_size",nativeQuery = true)
		List<PilotListVO> statisticBysupervisionId(@Param("supervision_id")long supervisionId, @Param("page")int offset,@Param("page_size")int pageSize,@Param("begaindata")String begaindata, @Param("enddata")String enddate,@Param("type")String type);

		@Query(value="SELECT COUNT(*) FROM"
				+"(select * from ("
		+"SELECT SUM(diff) AS total , C_FLIGHT_PERSON_ID FROM "
		+"("
		+"SELECT C_FLIGHT_PERSON_ID, C_FLIGHT_DATE,TIMESTAMPDIFF(SECOND,C_FLIGHT_TIME_BEGIN,C_FLIGHT_TIME_END) AS diff FROM (SELECT * FROM flight_record WHERE C_FLIGHT_PERSON_ID IN"
		+"(SELECT employee_id FROM employee WHERE supervision_id = :supervision_id AND employee_id IN "
		+"(SELECT employee_id FROM person_qulificationinfo WHERE id IN (SELECT id FROM pilot_license WHERE license_class= :type))))AS cc WHERE C_FLIGHT_DATE > :begaindata AND C_FLIGHT_DATE < :enddata"
		+") AS ta"
		+" GROUP BY C_FLIGHT_PERSON_ID"
		+") AS temp1, pilot  WHERE temp1.C_FLIGHT_PERSON_ID = pilot.employee_id) AS temp3 , employee  WHERE temp3.employee_id = employee.employee_id",nativeQuery = true)
		Integer getCountBysupervisionId(@Param("supervision_id")long supervisionId, @Param("begaindata")String begaindata, @Param("enddata")String enddate, @Param("type")String type);


		/** 
		 * TODO(这里用一句话描述这个方法的作用) 
		 *
		 * @Title: statisticBycompanyId 
		 * @param companyId
		 * @param beginDate
		 * @param endDate
		 * @return  
		 * @throws
		 *
		 */
		@Query(value="SELECT C_FLIGHT_PERSON_ID,username ,total,tech_class, flight_team FROM"
				+"(select * from ("
		+"SELECT SUM(diff) AS total , C_FLIGHT_PERSON_ID FROM "
		+"("
		+"SELECT C_FLIGHT_PERSON_ID, C_FLIGHT_DATE,TIMESTAMPDIFF(SECOND,C_FLIGHT_TIME_BEGIN,C_FLIGHT_TIME_END) AS diff FROM (SELECT * FROM flight_record WHERE C_FLIGHT_PERSON_ID IN"
		+"(SELECT employee_id FROM employee WHERE FIND_IN_SET(company_id,?1) "
		+"))AS cc WHERE C_FLIGHT_DATE > ?2 AND C_FLIGHT_DATE < ?3"
		+") AS ta"
		+" GROUP BY C_FLIGHT_PERSON_ID"
		+") AS temp1, pilot  WHERE temp1.C_FLIGHT_PERSON_ID = pilot.employee_id) AS temp3 , employee  WHERE temp3.employee_id = employee.employee_id ORDER BY total DESC LIMIT 0,10",nativeQuery = true)
		List<PilotListVO> statisticBycompanyId(String companyId, String beginDate, String endDate);


		/** 
		 * TODO(这里用一句话描述这个方法的作用) 
		 *
		 * @Title: statisticBycompanyIds 
		 * @param companyId
		 * @param beginDate
		 * @param endDate
		 * @return  
		 * @throws
		 *
		 */
		@Query(value="SELECT C_FLIGHT_PERSON_ID,username ,total,tech_class, flight_team FROM"
				+"(select * from ("
		+"SELECT SUM(diff) AS total , C_FLIGHT_PERSON_ID FROM "
		+"("
		+"SELECT C_FLIGHT_PERSON_ID, C_FLIGHT_DATE,TIMESTAMPDIFF(SECOND,C_FLIGHT_TIME_BEGIN,C_FLIGHT_TIME_END) AS diff FROM (SELECT * FROM flight_record WHERE C_FLIGHT_PERSON_ID IN"
		+"(SELECT employee_id FROM employee WHERE FIND_IN_SET(company_id,?1) "
		+"))AS cc WHERE C_FLIGHT_DATE > ?2 AND C_FLIGHT_DATE < ?3"
		+") AS ta"
		+" GROUP BY C_FLIGHT_PERSON_ID"
		+") AS temp1, pilot  WHERE temp1.C_FLIGHT_PERSON_ID = pilot.employee_id) AS temp3 , employee  WHERE temp3.employee_id = employee.employee_id ORDER BY total DESC",nativeQuery = true)
		List<PilotListVO> statisticBycompanyIds(String companyId, String beginDate, String endDate);
}
