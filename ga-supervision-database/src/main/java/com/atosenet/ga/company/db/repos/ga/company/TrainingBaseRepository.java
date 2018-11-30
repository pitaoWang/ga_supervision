/**  
 * 
 * @Title: TrainingBaseRepository.java 
 * @Package com.atosenet.ga.company.db.repos.ga.company  
 * @author dgw   
 * @date 2018年8月8日 下午3:20:38 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.repos.ga.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atosenet.ga.company.db.model.ga.company.TrainingBase;

/** 
 * TODO(训练基地数据接口)
 * 
 * @ClassName: TrainingBaseRepository 
 * @author dgw 
 * @date 2018年8月8日 下午3:20:38 
 *  
 */
public interface TrainingBaseRepository extends PagingAndSortingRepository<TrainingBase,Long> {
	
	/**
	 * 
	 * (non-Javadoc)
	 * <p>Title: findAll</p> 
	 * <p>Description: 获取训练基地的分页信息</p> 
	 * @param pageable
	 * @return 
	 * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Pageable)
	 *
	 */
	Page<TrainingBase> findAll(Pageable pageable);
	
	/**
	 * 
	 * (non-Javadoc)
	 * <p>Title: save</p> 
	 * <p>Description:保存训练基地的信息 </p> 
	 * @param trainBase
	 * @return 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 *
	 */
	TrainingBase save(TrainingBase trainBase);
	
	/**
	 * 
	 * TODO(根据训练基地id删除训练基地) 
	 *
	 * @Title: delete 
	 * @param trainbaseId  
	 * @throws
	 *
	 */
	void delete(long trainbaseId);
}
