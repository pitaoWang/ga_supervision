/**
 * 
 */
package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atosenet.ga.company.db.model.ga.company.SimulatorRecord;

/**
 * @author lvhao
 *
 */
public interface SimulatorRecordRepository extends PagingAndSortingRepository<SimulatorRecord, Long>{

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: selectBySimulatorId 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	@Query("select s.id from SimulatorRecord s where simulatorId = ?1")
	List<Long> selectBySimulatorId(long id);
	
	/**
	 * 
	 * TODO(根据训练器id查询鉴定记录) 
	 *
	 * @Title: findBySimulatorId 
	 * @param simulatorId
	 * @return  
	 * @throws
	 *
	 */
	List<SimulatorRecord> findBySimulatorId(@Param("simulatorId")Long simulatorId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findBySimulatorIdIn 
	 * @param simulatorId
	 * @return  
	 * @throws
	 *
	 */
	List<SimulatorRecord> findBySimulatorIdIn(@Param("simulatorId")List<Long> simulatorId);
	
}
