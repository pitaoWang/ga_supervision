/**  
 * 
 * @Title: OperationBaseService.java 
 * @Package com.atosenet.ga.company.mgt.service.ga.company  
 * @author dgw   
 * @date 2018年8月7日 上午10:52:14 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.base.BaseClassroom;
import com.atosenet.ga.company.db.model.ga.company.OperationBase;
import com.atosenet.ga.company.db.model.ga.company.TakeCourse;

import com.atosenet.ga.company.db.repos.ga.base.BaseClassroomRepository;
import com.atosenet.ga.company.db.repos.ga.company.OperationRepo;
import com.atosenet.ga.company.db.repos.ga.company.TakeCourseRepository;
import com.atosenet.ga.company.mgt.service.ga.company.dto.OperationBaseDTO;

/** 
 * TODO(运行基地服务层)
 * 
 * @ClassName: OperationBaseService 
 * @author dgw 
 * @date 2018年8月7日 上午10:52:14 
 *  
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class OperationBaseService {
	
	@Autowired
	OperationRepo operationRepo;
	
	@Autowired
	TakeCourseRepository takeCourseRepo;
	
	@Autowired
	BaseClassroomRepository classroomRepo;
	
	/**
	 * 
	 * TODO(获取运行基地分页列表) 
	 *
	 * @Title: findPageAll 
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = true)
	public Page<OperationBase> findPageAll(Pageable pageable){
		return operationRepo.findAll(pageable);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findAllOperationBase 
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly=false)
	public List<OperationBase> findAllOperationBase() {
		// TODO Auto-generated method stub
		return operationRepo.findList();
	}
	
	/**
	 * 
	 * TODO(保存运行基地信息) 
	 *
	 * @Title: save 
	 * @param operationBase
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly=false)
	public OperationBase save(OperationBase operationBase){
		return operationRepo.save(operationBase);
	}
	
	/**
	 * 
	 * TODO(根据id删除运行基地信息) 
	 *
	 * @Title: delete 
	 * @param baseId  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public void delete(long baseId){
		operationRepo.delete(baseId);			
		takeCourseRepo.deleteByBaseId(baseId);
	
		classroomRepo.deleteByBaseId(baseId);
		
		
	}
	
	/**
	 * 
	 * TODO(根据运行基地id查询运行基地信息) 
	 *
	 * @Title: findByBaseId 
	 * @param baseId
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public OperationBase findByBaseId(long baseId){
		return operationRepo.findByBaseId(baseId);
	}
	
	/**
	 * 
	 * TODO(根据运行基地id查询承担课程信息) 
	 *
	 * @Title: findTakeCourseByBaseId 
	 * @param baseId
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public List<TakeCourse> findTakeCourseByBaseId(long baseId){
		return takeCourseRepo.findByBaseId(baseId);
	}
	
	/**
	 * 
	 * TODO(根据运行基地id查询基地教室) 
	 *
	 * @Title: findClassroomByBaseId 
	 * @param baseId
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public List<BaseClassroom> findClassroomByBaseId(long baseId){
		return classroomRepo.findByBaseId(baseId);
	}
	
	/**
	 * 
	 * TODO(保存基地教室信息) 
	 *
	 * @Title: saveClassroom 
	 * @param classroom
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public BaseClassroom saveClassroom(BaseClassroom classroom){
		return classroomRepo.save(classroom);
	}
	
	/**
	 * 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: saveOperationAll 
	 * @param operationBaseDTO  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public void saveOperationAll(OperationBaseDTO operationBaseDTO){
		if(operationBaseDTO.getClassrooms().size()==0&&operationBaseDTO.getCourses().size()==0){
			/*operationBaseDTO.getOperationBase().setCreateTime(new Date());*/
			operationRepo.save(operationBaseDTO.getOperationBase());
		}else{			
			    /*operationBaseDTO.getOperationBase().setCreateTime(new Date());*/
				operationRepo.save(operationBaseDTO.getOperationBase());
				List<BaseClassroom> classrooms = operationBaseDTO.getClassrooms();
				for(BaseClassroom bc : classrooms){
					bc.setBaseId(operationBaseDTO.getOperationBase().getBaseId());
					classroomRepo.save(bc);
				}						
				List<TakeCourse> courses = operationBaseDTO.getCourses();
				for(TakeCourse tc : courses){
					tc.setBaseId(operationBaseDTO.getOperationBase().getBaseId());
					takeCourseRepo.save(tc);
				}
					
		}
		
	}
	
	/**
	 * 
	 * TODO(保存承担课程信息) 
	 *
	 * @Title: saveTakeCourse 
	 * @param takeCourse  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public TakeCourse saveTakeCourse(TakeCourse takeCourse){
		return takeCourseRepo.save(takeCourse);
	}
	
	/**
	 * 
	 * TODO(根据基地教室id删除基地教室信息) 
	 *
	 * @Title: deleteBaseClass 
	 * @param classroomId  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public void deleteBaseClass(Long classroomId){
		classroomRepo.delete(classroomId);
	}
	
	/**
	 * 
	 * TODO(根据课程id删除课程信息) 
	 *
	 * @Title: deleteCourse 
	 * @param courseId  
	 * @throws
	 *
	 */
	public void deleteCourse(Long courseId){
		takeCourseRepo.delete(courseId);
	}
	
	/**
	 * 根据企业id分页查询基地信息
	 * @param companyId
	 * @param pageable
	 * @return
	 */
	public Page<OperationBase> findByCompanyId(Long companyId,Pageable pageable){
		return operationRepo.findByCompanyId(companyId, pageable);
	}
}
