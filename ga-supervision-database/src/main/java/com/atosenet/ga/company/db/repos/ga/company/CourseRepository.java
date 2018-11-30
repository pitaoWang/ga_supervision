/**
 * 
 */
package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atosenet.ga.company.db.model.ga.company.Course;

/**
 * @author lvhao 
 *
 */
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: selectBySimulatorId 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	@Query("select c.id from Course c where simulatorId = ?1 ")
	List<Long> selectBySimulatorId(long id);
	
	/**
	 * 
	 * TODO(根据模拟训练器ID获取模拟器的适用课程) 
	 *
	 * @Title: findBySimulatorId 
	 * @param simulatorId
	 * @return  
	 * @throws
	 *
	 */
	List<Course> findBySimulatorId(long simulatorId);

}
