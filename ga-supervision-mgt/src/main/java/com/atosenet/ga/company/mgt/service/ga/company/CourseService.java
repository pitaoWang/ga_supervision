/**
 * 
 */
package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.Course;
import com.atosenet.ga.company.db.repos.ga.company.CourseRepository;

/**
 * @author lvhao
 *
 */
@Service
@Transactional
public class CourseService {
	
	@Autowired
	private CourseRepository cRepository;

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: selectPage 
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly=true)
	public Page<Course> selectPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.cRepository.findAll(pageable);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: save 
	 * @param course  
	 * @throws
	 *
	 */
	public Course save(Course course) {
		// TODO Auto-generated method stub
		return this.cRepository.save(course);
		
	}
	
	/**
	 * 
	 * TODO(根据模拟训练器ID获取模拟器的适用课程) 
	 *
	 * @Title: findBysimulatorId 
	 * @param simulatorId
	 * @return  
	 * @throws
	 *
	 */
	public List<Course> findBysimulatorId(Long simulatorId){
		return cRepository.findBySimulatorId(simulatorId);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: delete 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	public Long delete(Long id) {
		cRepository.delete(id);
		return id;
	}

}
