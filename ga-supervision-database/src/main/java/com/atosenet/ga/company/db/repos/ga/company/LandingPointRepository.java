package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.ga.company.LandingPoint;
import com.atosenet.ga.company.db.projection.company.LandingPointSimple;

@RepositoryRestResource(collectionResourceRel = "LandingPointrepos", path = "LandingPointrepos")
public interface LandingPointRepository extends PagingAndSortingRepository<LandingPoint, Long> {
	//List<LandingPoint> findByName(@Param("name") String name);
	
	
//	public interface UserRepository extends JpaRepository<Test, Long> {
//
//		  @Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
//		  Test findByEmailAddress(String emailAddress);
//		}
	
	

//	  @Modifying
//	  @Query("delete from User u where user.role.id = ?1")
//	  void deleteInBulkByRoleId(long roleId);
	  
	@Query("select t from LandingPoint t")
	List<LandingPoint> findList();
	
	@Query("select t from LandingPoint t where t.name = ?1")
	List<LandingPoint> findListByName(String name);
	/**
	 * 根据主权公司id获取起降点
	 * @param id
	 * @return
	 */
	@Query(value="select * from landing_point where landing_point.company_id=?1",
			   nativeQuery=true)
	List<LandingPoint> getlistbymaincompanyid(long id);
	
	@Query(value="select * from landing_point LEFT JOIN company_landing_point ON landing_point.id=company_landing_point.landing_point_id where company_landing_point.company_id=?1 \n-- #pageable\n",
			   nativeQuery=true)
	List<LandingPoint>getlistbycompanyid(long companyid);
	
	@Query(value=" select distinct landing_point.id,landing_point.name,landing_point.company_id,landing_point.isavailable,landing_point.landing_grade "
			+ "from landing_point LEFT JOIN company_landing_point ON landing_point.id=company_landing_point.landing_point_id "
			+ "where company_landing_point.company_id=?1 or landing_point.company_id=?1 ",
			   nativeQuery=true)
	List<Object[]>selectlistbycompanyid(long companyid);
	
	@Modifying
	@Query(value="insert into company_landing_point(company_id,landing_point_id) values(?1,?2)",
			   nativeQuery=true)
	void savecompanylandingpoint(long companyid,long landingpointid);
	
	/**
	 * 删除起降点以及相关表数据
	 * @param companyid
	 * @param landingpointid
	 */
	@Modifying
	@Query(value=" DELETE FROM company_landing_point WHERE landing_point_id=?1 ",
			   nativeQuery=true)
	void deletecompanylandingpoint(long landingpointid);
	
	//获取所有的起降点 部分字段
	List<LandingPointSimple> findByIdNotNull();
			
	//根据公司ID获取起降点 部分字段
	List<LandingPointSimple> findByCompanyId(@Param("companyId") GaCompany companyId);
	
}