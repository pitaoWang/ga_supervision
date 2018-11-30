package com.atosenet.ga.company.db.repos.ga.company;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.ga.company.MaintainBase;
import com.atosenet.ga.company.db.projection.company.MaintainBaseSimple;

@RepositoryRestResource(collectionResourceRel = "mb", path = "mb")
public interface MaintainBaseRepository extends JpaRepository<MaintainBase, Long>{
	@Query("select t from MaintainBase t ")
	List<MaintainBase> findList();
	
	@Query("select t from MaintainBase t where maintainbasename = ?1")
	List<MaintainBase> findListByName(String maintainbasename);
	
	@Query(value="select * from maintainbase LEFT JOIN company_maintainbase ON maintainbase.maintainbase_id=company_maintainbase.maintainbase_id where company_maintainbase.company_id=?1 ",
			   nativeQuery=true)
	List<MaintainBase>getlistbycompanyid(long companyid);
	/**
	 * 根据主权公司ID查询
	 * @param companyid
	 * @return
	 */
	@Query(value="select * from maintainbase where maintainbase.company_id=?1 ",
			   nativeQuery=true)
	List<MaintainBase> getlistbymainrightcompanyid(long companyid);
	
	@Query(value=" select distinct "
			+ "maintainbase.maintainbase_id as maintainbaseId ,"
			+ "maintainbase.maintainbase_name as maintainbaseName, "
			+ "maintainbase.company_id as companyId "
			+ "from maintainbase LEFT JOIN company_maintainbase ON maintainbase.maintainbase_id=company_maintainbase.maintainbase_id "
			+ "where company_maintainbase.company_id=?1 or maintainbase.company_id=?1  ",
			   nativeQuery=true)
	List<Object[]>findlistbycompanyid(long companyid);
	
	//获取所有的维修基地的MaintainBaseSimple
	List<MaintainBaseSimple> findByMaintainbaseidNotNull();
	
	//根据公司ID获取维修基地信息
	List<MaintainBaseSimple> findByCompanyId(@Param("companyId") GaCompany companyId);
	
	@Query("select t.maintainbaseid as maintainbaseid,t.maintainbasename as maintainbasename, t.companyId.companyId as companyId, t.companyId.name as companyName from MaintainBase t ")
	List<Map<String, Object>> findB();
	
	/**
	 * 
	 * 删除指定维修基地关联表的信息
	 * @param id
	 * @return
	 */
	@Modifying
	@Query(value=" DELETE FROM company_maintainbase WHERE maintainbase_id =?1 ",
			   nativeQuery=true)
	void deleteMaintainbasebycompanyid(long id);
}
