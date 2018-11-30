/**
 * 
 */
package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.atosenet.ga.company.db.model.ga.company.Aircraft;
import com.atosenet.ga.company.db.model.ga.company.Equipment;
import com.atosenet.ga.company.db.model.ga.company.PlanType;

/**
 * @author lvhao
 *
 */
public interface EquipmentRepository extends PagingAndSortingRepository<Equipment, Long>{

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: selectByOrgId 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	/*@Query("select e from Equipment e where orgId = ?1")
	List<Equipment> selectByOrgId(long id);*/

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findViewListByCompany 
	 * @param companyId
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="SELECT * FROM equipment WHERE company_id =?1 \n-- #pageable\n",
			   countQuery="select count(*) from equipment WHERE company_id =?1",
				nativeQuery=true)
	Page<Equipment> findViewListByCompany(String companyId, Pageable pageable);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByEpNum 
	 * @param epNum
	 * @return  
	 * @throws
	 *
	 */
	Equipment findByEpNumAndCompanyId(@Param("epNum")String epNum,@Param("companyId")Long companyId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByEpNumAndId 
	 * @param epNum
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="select * from equipment t where t.ep_num=?1 and t.company_id =?3 and t.id != ?2",
			nativeQuery=true)
	Equipment findByEpNumAndId(String epNum, Long id,Long companyId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findById 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	List<Equipment> findById(@Param("id")long id);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findViewListByCompanys 
	 * @param companyId
	 * @param epNum
	 * @param epType
	 * @param regNumber
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="SELECT * FROM equipment WHERE company_id =?1 and if(?2 !='',ep_num = ?2,1=1) and if(?3 !='',ep_type = ?3,1=1) and if(?4 !='',reg_number = ?4,1=1) \n-- #pageable\n",
			countQuery=" select count(*) from equipment WHERE company_id =?1 and if(?2 !='',ep_num = ?2,1=1) and if(?3 !='',ep_type = ?3,1=1) and if(?4 !='',reg_number = ?4,1=1)",
			 nativeQuery=true)
	Page<Equipment> findViewListByCompanys(String companyId, String epNum, String epType, String regNumber,
			Pageable pageable);

	
	
}
