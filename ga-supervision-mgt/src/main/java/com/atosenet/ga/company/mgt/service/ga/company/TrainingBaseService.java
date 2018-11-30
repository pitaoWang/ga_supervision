/**  
 * 
 * @Title: TrainingBaseService.java 
 * @Package com.atosenet.ga.company.mgt.service.ga.company  
 * @author dgw   
 * @date 2018年8月8日 下午3:23:18 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.service.ga.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.TrainingBase;
import com.atosenet.ga.company.db.repos.ga.company.TrainingBaseRepository;

/** 
 * TODO(训练基地服务层)
 * 
 * @ClassName: TrainingBaseService 
 * @author dgw 
 * @date 2018年8月8日 下午3:23:18 
 *  
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class TrainingBaseService {
	
	@Autowired
	TrainingBaseRepository trainBaseRepository;
	
	/**
	 * 
	 * TODO(获取训练基地分页列表) 
	 *
	 * @Title: findPageAll 
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = true)
	public Page<TrainingBase> findPageAll(Pageable pageable){
		return trainBaseRepository.findAll(pageable);
	}
	
	/**
	 * 
	 * TODO(保存训练基地的信息) 
	 *
	 * @Title: save 
	 * @param trainBase
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public TrainingBase save(TrainingBase trainBase){
		return trainBaseRepository.save(trainBase);
	}
	
	/**
	 * 
	 * TODO(根据id删除训练基地的信息) 
	 *
	 * @Title: delete 
	 * @param trainbaseId  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public void delete(long trainbaseId){
		trainBaseRepository.delete(trainbaseId);
	}
}
