/**  
 * 
 * @Title: SimulatorVORepository.java 
 * @Package com.atosenet.ga.company.db.repos.ga.company  
 * @author xyx   
 * @date 2018年8月30日 下午4:38:35 
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

import com.atosenet.ga.company.db.model.ga.company.Simulator;
import com.atosenet.ga.company.db.model.ga.company.vo.SimulatorVO;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: SimulatorVORepository 
 * @author xyx 
 * @date 2018年8月30日 下午4:38:35 
 *  
 */
public interface SimulatorVORepository extends JpaRepository<SimulatorVO,Integer> {
	@Query(value="select t.*, t1.name as companyName,t1.authority_name as manageName,t1.supervision_name as superName  from simulator t LEFT JOIN ga_company t1 ON t.company_id = t1.company_id LEFT JOIN t_department t2 ON t.super_id = t2.id LEFT JOIN t_department t3 ON t.manager_id = t3.id   where  if(?1 !='',t.equipment_name = ?1,1=1) and if(?2 !='',t.equipment_type = ?2,1=1) and FIND_IN_SET(t.company_id,?3) \n-- #pageable\n",
			countQuery=" select count(*) from simulator t LEFT JOIN ga_company t1 ON t.company_id = t1.company_id LEFT JOIN t_department t2 ON t.super_id = t2.id LEFT JOIN t_department t3 ON t.manager_id = t3.id where  if(?1 !='',t.equipment_name = ?1,1=1) and if(?2 !='',t.equipment_type = ?2,1=1)  and FIND_IN_SET(t.company_id,?3)",
			 nativeQuery=true)
	Page<SimulatorVO> findByEquipmentNameAndEquipmentType (String name,String type, String companyId, Pageable pageable);

	/**
	 * @param productionSn 
	 * @param equipmentModel  
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByEquipmentNameAndEquipmentTypes 
	 * @param name
	 * @param type
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="select t.*, t1.name as companyName,t1.authority_name as manageName,t1.supervision_name as superName  from simulator t LEFT JOIN ga_company t1 ON t.company_id = t1.company_id LEFT JOIN t_department t2 ON t.super_id = t2.id LEFT JOIN t_department t3 ON t.manager_id = t3.id   where  if(?1 !='',t.equipment_name = ?1,1=1) and if(?2 !='',t.equipment_type = ?2,1=1)  and FIND_IN_SET(t.company_id,?3)",
			 nativeQuery=true)
	List<SimulatorVO> findByEquipmentNameAndEquipmentTypes(String name, String type, String companyId);

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
	@Query(value="select t.*, t1.name as companyName,t1.authority_name as manageName,t1.supervision_name as superName from simulator t LEFT JOIN ga_company t1 ON t.company_id = t1.company_id  where  t.company_id = ?1 \n-- #pageable\n",
			countQuery=" select count(*) from simulator t LEFT JOIN ga_company t1 ON t.company_id = t1.company_id  where t.company_id = ?1",
			 nativeQuery=true)
	Page<SimulatorVO> findByCompanyId(long companyId, Pageable pageable);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: getSimulatorById 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="select t.*, t1.name as companyName,t1.authority_name as manageName,t1.supervision_name as superName from simulator t LEFT JOIN ga_company t1 ON t.company_id = t1.company_id  where t.id = ?1",
			 nativeQuery=true)
	SimulatorVO getSimulatorById(long id);
}
