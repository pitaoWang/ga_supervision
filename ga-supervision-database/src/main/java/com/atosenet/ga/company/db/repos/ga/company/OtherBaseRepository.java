/**  
 * 
 * @Title: OtherBaseRepository.java 
 * @Package com.atosenet.ga.company.db.repos.ga.company  
 * @author dgw   
 * @date 2018年8月21日 下午6:04:33 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atosenet.ga.company.db.model.ga.company.OtherBase;
import com.atosenet.ga.company.db.model.ga.company.TakeCourse;

/** 
 * TODO(其他基地)
 * 
 * @ClassName: OtherBaseRepository 
 * @author dgw 
 * @date 2018年8月21日 下午6:04:33 
 *  
 */
public interface OtherBaseRepository extends JpaRepository<OtherBase,Long> {
	
	/**
	 * 
	 * (non-Javadoc)
	 * <p>Title: findAll</p> 
	 * <p>Description:查询其他基地列表 </p> 
	 * @return 
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 *
	 */
	List<OtherBase> findAll();
	
	/**
	 * 
	 * TODO(根据企业id和基地类型查询基地列表) 
	 *
	 * @Title: findByCompanyIdAndBaseType 
	 * @return  
	 * @throws
	 *
	 */
	List<OtherBase> findByCompanyIdAndBaseType(Long companyId,String baseType);
	
	/**
	 * 
	 * TODO(根据企业id查询其他基地信息) 
	 *
	 * @Title: findByCompanyId 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	List<OtherBase> findByCompanyId(Long companyId);
	
	/**
	 * 
	 * TODO(根据名称、类型查询其他基地信息) 
	 *
	 * @Title: findByBaseNameAndBaseType 
	 * @param baseName
	 * @param BaseType
	 * @return  
	 * @throws
	 *
	 */
	OtherBase findByBaseNameAndBaseType(String baseName,String baseType);
	
}
