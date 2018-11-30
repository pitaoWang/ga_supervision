/**
 * 
 */
package com.atosenet.ga.company.db.repos.ga.base;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atosenet.ga.company.db.model.ga.base.BaseClassroom;





/**
 * @author lvhao
 *
 */
public interface BaseClassroomRepository extends PagingAndSortingRepository<BaseClassroom, Long>{

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: getListByOperationBaseId 
	 * @param baseid
	 * @return  
	 * @throws
	 *
	 */
	@Query("select b from BaseClassroom b where baseId=?1")
	List<BaseClassroom> getListByOperationBaseId(Long baseid);
	
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
	List<BaseClassroom> findByBaseId(long baseId);
	
	/**
	 * 
	 * (non-Javadoc)
	 * <p>Title: save</p> 
	 * <p>Description:保存基地教室信息 </p> 
	 * @param classroom
	 * @return 
	 * @see org.springframework.data.repository.CrudRepository#save(S)
	 *
	 */
	BaseClassroom save(BaseClassroom classroom);

    void deleteByBaseId(Long baseId);
}
