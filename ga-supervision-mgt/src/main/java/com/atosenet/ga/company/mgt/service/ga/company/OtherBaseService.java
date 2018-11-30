/**  
 * 
 * @Title: OtherBaseService.java 
 * @Package com.atosenet.ga.company.mgt.service.ga.company  
 * @author dgw   
 * @date 2018年8月21日 下午6:39:43 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.OtherBase;
import com.atosenet.ga.company.db.repos.ga.company.OtherBaseRepository;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: OtherBaseService 
 * @author dgw 
 * @date 2018年8月21日 下午6:39:43 
 *  
 */
@Service
@Transactional(readOnly = true)
public class OtherBaseService {
	
	@Autowired
	OtherBaseRepository otherBaseRep;
	
	/**
	 * 
	 * TODO(查询所有其他基地列表) 
	 *
	 * @Title: findAll 
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = true)
	public List<OtherBase> findAll(){
		return otherBaseRep.findAll();
	}
	
	/**
	 * 
	 * TODO(保存其他基地信息) 
	 *
	 * @Title: save 
	 * @param otherBase
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public OtherBase save(OtherBase otherBase){	
		return otherBaseRep.save(otherBase);			
	}
	
	/**
	 * 
	 * TODO(删除其他基地信息) 
	 *
	 * @Title: delete 
	 * @param baseId  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public void delete(Long baseId){
		 otherBaseRep.delete(baseId);
	}
	
	/**
	 * 
	 * TODO(根据企业id和基地类型查询基地列表) 
	 *
	 * @Title: findByCompanyIdAndBaseType 
	 * @param companyId
	 * @param baseType
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = true)
	public List<OtherBase> findByCompanyIdAndBaseType(Long companyId,String baseType){
		return otherBaseRep.findByCompanyIdAndBaseType(companyId,baseType);
	}
	
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
	@Transactional(readOnly = true)
	public List<OtherBase> findByCompanyId(Long companyId){
		return otherBaseRep.findByCompanyId(companyId);
	}
	
	/**
	 * 
	 * TODO(根据名称、类型查询其他基地信息) 
	 *
	 * @Title: findByBaseNameAndBaseType 
	 * @param baseName
	 * @param baseType
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = true)
	public OtherBase findByBaseNameAndBaseType(String baseName,String baseType){
		return otherBaseRep.findByBaseNameAndBaseType(baseName, baseType);
	}

}
