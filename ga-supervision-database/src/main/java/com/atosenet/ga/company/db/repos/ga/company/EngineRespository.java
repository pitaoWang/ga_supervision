/**
 * 
 */
package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atosenet.ga.company.db.model.ga.company.Engine;

/**
 * @author lvhao
 *
 */
public interface EngineRespository extends PagingAndSortingRepository<Engine, Long>{

	/** 
	 * TODO(根据模拟训练飞行器 id查询) 
	 *
	 * @Title: selectBySimulatorId 
	 * @param id  
	 * @throws
	 *
	 */
	@Query("select e.id from Engine e where simulatorId = ?1")
	List<Long> selectBySimulatorId(long simulatorId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findBySimulatorId 
	 * @param id  
	 * @throws
	 *
	 */
	List<Engine> findBySimulatorId(@Param("id")Long id);

}
