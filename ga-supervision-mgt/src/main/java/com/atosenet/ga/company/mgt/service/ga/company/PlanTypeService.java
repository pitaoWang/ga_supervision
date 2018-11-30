/**  
 * 
 * @Title: PlanTypeService.java 
 * @Package com.atosenet.ga.company.mgt.service.ga.company  
 * @author dgw   
 * @date 2018年7月31日 下午5:02:52 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.Equipment;
import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.ga.company.PlanType;
import com.atosenet.ga.company.db.repos.ga.company.GaCompanyRepository;
import com.atosenet.ga.company.db.repos.ga.company.PlanTypeRepository;

/** 
 * TODO(机型服务层)
 * 
 * @ClassName: PlanTypeService 
 * @author dgw 
 * @date 2018年7月31日 下午5:02:52 
 *  
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class PlanTypeService {
	
	@Autowired
	PlanTypeRepository planTypeResitory;
	@Autowired
	GaCompanyRepository gaCompanyRepository;
	
	/**
	 * 
	 * TODO(返回系统中所有的机型列表) 
	 *
	 * @Title: findAll 
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly=false)
	public List<PlanType> findAll(){
		List<PlanType> list = planTypeResitory.findAll();
		return list;
	}
	
	/**
	 * 
	 * TODO(根据机型ID获取机型基础信息) 
	 *
	 * @Title: findByPlanTypeId 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly=false)
	public PlanType findByPlanTypeId(long id){
		PlanType planType = planTypeResitory.getOne(id);
		List<GaCompany> companyList = gaCompanyRepository.findList();
		
			for(GaCompany gaCompany : companyList){
				if(planType.getCompanyId()!=null){
					if(gaCompany.getCompanyId()==planType.getCompanyId()){
						planType.setCompanyName(gaCompany.getName());
					}
				}
				
			}	
		
		return planType;
	}
	
	/**
	 * 
	 * TODO(保存机型信息) 
	 *
	 * @Title: save 
	 * @param planType
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public PlanType save(PlanType planType){
		return planTypeResitory.save(planType);
	}
	
	/**
	 * 
	 * TODO(删除机型信息) 
	 *
	 * @Title: delete 
	 * @param id  
	 * @throws
	 *
	 */
	@Transactional(readOnly = false)
	public void delete(long id){
		planTypeResitory.delete(id);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: deletes 
	 * @param list  
	 * @throws
	 *
	 */
	public void deletes(List<PlanType> list) {
		
			planTypeResitory.delete(list);
		
		
		
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findViewListByCompany 
	 * @param companyId
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	public Page<PlanType> findViewListByCompany(String companyId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<PlanType> list = planTypeResitory.findByCompanyId(companyId,pageable);
		List<GaCompany> companyList = gaCompanyRepository.findList();
		for (PlanType planType : list) {
			for(GaCompany gaCompany : companyList){
				if(planType.getCompanyId()!=null){
					if(gaCompany.getCompanyId()==planType.getCompanyId()){
						planType.setCompanyName(gaCompany.getName());
					}
				}
				
			}	
		}
		return list;
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findViewListByCompanys 
	 * @param companyId
	 * @param typeNumber
	 * @param manufacturer
	 * @param aircraftType
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	public Page<PlanType> findViewListByCompanys(String companyId, String typeNumber, String manufacturer,
			String aircraftType, Pageable pageable) {
		Page<PlanType> list = planTypeResitory.findByCompanyIds(companyId,typeNumber,manufacturer,aircraftType,pageable);
		List<GaCompany> companyList = gaCompanyRepository.findList();
		for (PlanType planType : list) {
			for(GaCompany gaCompany : companyList){
				if(planType.getCompanyId()!=null){
					if(gaCompany.getCompanyId()==planType.getCompanyId()){
						planType.setCompanyName(gaCompany.getName());
					}
				}
				
			}	
		}
		return list;
	}
}
