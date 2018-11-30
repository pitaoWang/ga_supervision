package com.atosenet.ga.sys.dao;

import java.util.List;

import com.atosenet.ga.sys.base.XbootBaseDao;
import com.atosenet.ga.sys.entity.Department;

/**
 * 部门数据处理层
 * @author Exrick
 */
public interface DepartmentDao extends XbootBaseDao<Department,Long> {

    /**
     * 通过父id获取 升序
     * @param parentId
     * @return
     */
    List<Department> findByParentIdOrderBySortOrder(Long parentId);

    /**
     * 通过父id和状态获取 升序
     * @param parentId
     * @param status
     * @return
     */
    List<Department> findByParentIdAndStatusOrderBySortOrder(Long parentId, Integer status);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByParentIdAndDepartmentIdOrderBySortOrder 
	 * @param parentId
	 * @param departmentId
	 * @return  
	 * @throws
	 *
	 */
	List<Department> findByParentIdAndIdOrderBySortOrder(Long parentId, Long departmentId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByIdOrderBySortOrder 
	 * @param parentId
	 * @return  
	 * @throws
	 *
	 */
	List<Department> findByIdOrderBySortOrder(Long parentId);

	List<Department> findByType(String type);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByParentIdAndTypeOrderBySortOrder 
	 * @param parentId
	 * @param type
	 * @return  
	 * @throws
	 *
	 */
	List<Department> findByParentIdAndTypeOrderBySortOrder(Long parentId, String type);
}