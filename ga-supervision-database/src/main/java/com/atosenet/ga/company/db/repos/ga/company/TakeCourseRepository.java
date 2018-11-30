/**  
 * 
 * @Title: TakeCourseRepository.java 
 * @Package com.atosenet.ga.company.db.repos.ga.company  
 * @author dgw   
 * @date 2018年8月15日 下午4:19:46 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.atosenet.ga.company.db.model.ga.company.TakeCourse;

/** 
 * TODO(承担课程)
 * 
 * @ClassName: TakeCourseRepository 
 * @author dgw 
 * @date 2018年8月15日 下午4:19:46 
 *  
 */
public interface TakeCourseRepository extends JpaRepository<TakeCourse,Long> {
	
	List<TakeCourse> findByBaseId(long baseId);
	
	TakeCourse save(TakeCourse takeCourse);
	
	
	void deleteByBaseId(Long baseId);
}
