package com.atosenet.ga.sys.serviceimpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.repos.ga.company.GaCompanyRepository;
import com.atosenet.ga.sys.dao.DepartmentDao;
import com.atosenet.ga.sys.entity.Department;
import com.atosenet.ga.sys.service.DepartmentService;

import lombok.extern.slf4j.Slf4j;

/**
 * 部门接口实现
 * @author Exrick
 */
@Slf4j
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	GaCompanyRepository gaCompanyRepository;
    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public DepartmentDao getRepository() {
        return departmentDao;
    }

    @Override
    public List<Department> findByParentIdOrderBySortOrder(Long parentId) {

        return departmentDao.findByParentIdOrderBySortOrder(parentId);
    }

    @Override
    public List<Department> findByParentIdAndStatusOrderBySortOrder(Long parentId, Integer status) {

        return departmentDao.findByParentIdAndStatusOrderBySortOrder(parentId, status);
    }

	/**
	 * (non-Javadoc)
	 * <p>Title: findByParentIdAndDepartmentIdOrderBySortOrder</p> 
	 * <p>Description: </p> 
	 * @param parentId
	 * @param departmentId
	 * @return 
	 * @see com.atosenet.ga.sys.service.DepartmentService#findByParentIdAndDepartmentIdOrderBySortOrder(java.lang.String, java.lang.String)
	 * 
	 */
	@Override
	public List<Department> findByParentIdAndIdOrderBySortOrder(Long parentId, Long departmentId) {
		// TODO Auto-generated method stub
		return departmentDao.findByParentIdAndIdOrderBySortOrder(parentId,departmentId);
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: save</p> 
	 * <p>Description: </p> 
	 * @param gaCom
	 * @param request 
	 * @see com.atosenet.ga.sys.service.DepartmentService#save(com.atosenet.ga.company.db.model.ga.company.GaCompany, javax.servlet.http.HttpServletRequest)
	 * 
	 */
	@Override
	public void save(GaCompany gaCom, HttpServletRequest request) {
		gaCompanyRepository.save(gaCom);
		
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: updateOne</p> 
	 * <p>Description: </p> 
	 * @param title
	 * @param parseLong 
	 * @see com.atosenet.ga.sys.service.DepartmentService#updateOne(java.lang.String, long)
	 * 
	 */
	@Override
	public void updateOne(String title, Long id) {
		// TODO Auto-generated method stub
		int a = gaCompanyRepository.updateOne(title, id);
		System.out.println(a);
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: deletes</p> 
	 * <p>Description: </p> 
	 * @param idsp 
	 * @see com.atosenet.ga.sys.service.DepartmentService#deletes(java.lang.String)
	 * 
	 */
	@Override
	public void deletes(List<Long> idsp) {
		gaCompanyRepository.deleteByOrgIdIn(idsp);
		
	}

	/**
	 * (non-Javadoc)
	 * <p>Title: findByIdOrderBySortOrder</p> 
	 * <p>Description: </p> 
	 * @param parentId
	 * @return 
	 * @see com.atosenet.ga.sys.service.DepartmentService#findByIdOrderBySortOrder(java.lang.Long)
	 * 
	 */
	@Override
	public List<Department> findByIdOrderBySortOrder(Long parentId) {
		// TODO Auto-generated method stub
		return departmentDao.findByIdOrderBySortOrder(parentId);
	}
	
	
	@Override
	public List<Department> findByType(String type) {
		// TODO Auto-generated method stub
		return departmentDao.findByType(type);
}


	@Override
	public Department findById(long id) {
		// TODO Auto-generated method stub
		return departmentDao.findOne(id);
	}


	/**
	 * (non-Javadoc)
	 * <p>Title: findByParentIdAndTypeOrderBySortOrder</p> 
	 * <p>Description: </p> 
	 * @param parentId
	 * @param type
	 * @return 
	 * @see com.atosenet.ga.sys.service.DepartmentService#findByParentIdAndTypeOrderBySortOrder(java.lang.Long, java.lang.String)
	 * 
	 */
	@Override
	public List<Department> findByParentIdAndTypeOrderBySortOrder(Long parentId, String type) {
		// TODO Auto-generated method stub
		return departmentDao.findByParentIdAndTypeOrderBySortOrder(parentId,type);
	}

}