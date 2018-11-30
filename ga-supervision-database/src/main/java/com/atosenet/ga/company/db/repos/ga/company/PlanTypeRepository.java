/**  
 * 
 * @Title: PlanTypeRepository.java 
 * @Package com.atosenet.ga.company.db.repos.ga.company  
 * @author dgw   
 * @date 2018年7月31日 下午4:53:35 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.Equipment;
import com.atosenet.ga.company.db.model.ga.company.PlanType;

/** 
 * TODO(机型jpa数据接口)
 * 
 * @ClassName: PlanTypeRepository 
 * @author dgw 
 * @date 2018年7月31日 下午4:53:35 
 *  
 */
@RepositoryRestResource(collectionResourceRel = "pt", path = "pt")
public interface PlanTypeRepository extends JpaRepository<PlanType,Long> {
	/**
	 * 
	 * (non-Javadoc)
	 * <p>Title: findAll</p> 
	 * <p>Description:返回系统中所有的机型列表 </p> 
	 * @return 
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 *
	 */
	List<PlanType> findAll();
	
	/**
	 * 
	 * TODO(根据机型ID获取机型基础信息) 
	 *
	 * @Title: findByPlanTypeId 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	PlanType getOne(@Param("id")long id);
	
	/**
	 * 
	 * TODO(删除机型信息) 
	 *
	 * @Title: delete 
	 * @param id  
	 * @throws
	 *
	 */
	void delete(@Param("id")long id);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByCompanyId 
	 * @param companyId
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="select * from plan_type where FIND_IN_SET(company_id,:companyId) \n-- #pageable\n",
			   countQuery=" select count(*) from plan_type where FIND_IN_SET(company_id,:companyId) ",
			   nativeQuery=true)
	Page<PlanType> findByCompanyId(@Param("companyId")String companyId, Pageable pageable);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByCompanyIds 
	 * @param companyId
	 * @param typeNumber
	 * @param manufacturer
	 * @param aircraftType
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="select * from plan_type where  if(?2 !='',type_number = ?2,1=1) and if(?3 !='',manufacturer = ?3,1=1) and if(?4 !='',aircraft_type = ?4,1=1) and company_id = ?1 \n-- #pageable\n",
			countQuery=" select count(*) from plan_type where if(?2 !='',type_number = ?2,1=1) and if(?3 !='',manufacturer = ?3,1=1) and if(?4 !='',aircraft_type = ?4,1=1) and company_id = ?1",
			 nativeQuery=true)
	Page<PlanType> findByCompanyIds(String companyId, String typeNumber, String manufacturer, String aircraftType,
			Pageable pageable);
}
