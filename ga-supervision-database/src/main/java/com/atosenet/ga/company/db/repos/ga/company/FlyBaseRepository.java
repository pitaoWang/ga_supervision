package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.FlyBase;
import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.projection.company.FlyBaseSimple;



@RepositoryRestResource(collectionResourceRel = "flybaserepos", path = "flybaserepos")
public interface FlyBaseRepository extends PagingAndSortingRepository<FlyBase, Long> {

	  
	@Query("select t from FlyBase t")
	List<FlyBase> findList();
	
	@Query("select t from FlyBase t where authorityId = ?1")
	List<FlyBase> findByAuthorityIdList(long id);

	@Query("select t from FlyBase t where supervisionId = ?1")
	List<FlyBase> findBySupervisionIdList(long id);
	
	@Query("select t from FlyBase t where flybasename = ?1")
	List<FlyBase> findByName(String flybasename);
	
	//获取所有的飞行基地的MaintainBaseSimple
	List<FlyBaseSimple> findByFlybaseidNotNull();
		
	//根据公司ID获取飞行基地信息
	List<FlyBaseSimple> findByCompanyId(@Param("companyId") GaCompany companyId);
	
	@Query(value="select * from flybase LEFT JOIN company_flybase ON flybase.flybaseid=company_flybase.flybaseid where company_flybase.company_id=?1 \n-- #pageable\n",
			   nativeQuery=true)
	List<FlyBase> findflybasebycompanyid(Long id);
	
	
	@Query(value="select * from flybase where flybase.company_id=?1 \n-- #pageable\n",
			   nativeQuery=true)
	List<FlyBase> findflybasebymaincompanyid(Long id);
	
	/**
	 * 
	 * 获取该公司所有的飞行基地以及租的飞行基地
	 * @param id
	 * @return
	 */
	@Query(value=" select distinct flybase.flybaseid,flybase.flybasename,flybase.company_id "
			+ " from flybase LEFT JOIN company_flybase ON flybase.flybaseid=company_flybase.flybaseid "
			+ "where company_flybase.company_id=?1 or flybase.company_id=?1",
			   nativeQuery=true)
	List<Object[]> selectflybasebycompanyid(Long id);
	

	/**
	 * 
	 * 删除指定基地关联表的信息
	 * @param id
	 * @return
	 */
	@Modifying
	@Query(value=" DELETE FROM `company_flybase` WHERE `flybaseid`=?1 ",
			   nativeQuery=true)
	void deleteflybasebycompanyid(long flybaseid);
	
	@Modifying
	@Query(value="insert into company_flybase(company_id,flybaseid) values(?1,?2)",
			   nativeQuery=true)
	void savecompanyflybase(long companyid,long flybaseid);
}