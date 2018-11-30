/**  
 * 
 * @Title: SimulatorRepository.java 
 * @Package com.atosenet.ga.company.db.repos.ga.company  
 * @author dgw   
 * @date 2018年8月2日 上午9:33:26 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.Simulator;


/** 
 * TODO(模拟训练器数据库接口)
 * 
 * @ClassName: SimulatorRepository 
 * @author dgw 
 * @date 2018年8月2日 上午9:33:26 
 *  
 */
@RepositoryRestResource(collectionResourceRel = "simulator", path = "simulator")
public interface SimulatorRepository extends JpaRepository<Simulator,Long> {
	
	/**
	 * 
	 * TODO(根据企业ID获取通航企业模拟训练器列表) 
	 *
	 * @Title: findByCompanyId 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	Page<Simulator> findByCompanyId(@Param("companyId") long companyId,Pageable pageable);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: getSimulatorById 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	Simulator getSimulatorById(@Param("id")long id);
}
