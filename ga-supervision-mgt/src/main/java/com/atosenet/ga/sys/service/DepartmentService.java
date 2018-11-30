package com.atosenet.ga.sys.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.sys.base.XbootBaseService;
import com.atosenet.ga.sys.entity.Department;

/**
 * 部门接口
 * @author Exrick
 */
public interface DepartmentService extends XbootBaseService<Department,Long> {

    /**
     * 通过父id获取 升序
     * @param parentId
     * @return
     */
    List<Department> findByParentIdOrderBySortOrder(Long parentId);

    /**
     * 通过父id和状态获取
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
	 * @Title: save 
	 * @param gaCom
	 * @param request  
	 * @throws
	 *
	 */
	void save(GaCompany gaCom, HttpServletRequest request);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: updateOne 
	 * @param title
	 * @param parseLong  
	 * @throws
	 *
	 */
	void updateOne(String title, Long parseLong);

	
	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: deletes 
	 * @param idsp  
	 * @throws
	 *
	 */
	void deletes(List<Long> idsp);

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

	Department findById(long parseLong);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByParentIdAndTypeOrderBySortOrder 
	 * @param parentId
	 * @param string
	 * @return  
	 * @throws
	 *
	 */
	List<Department> findByParentIdAndTypeOrderBySortOrder(Long parentId, String type);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByParentIdAndIdOrderBySortOrder 
	 * @param i
	 * @param parentId
	 * @return  
	 * @throws
	 *
	 */

}