/**
 * 
 */
package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.Aircraft;
import com.atosenet.ga.company.db.model.ga.company.Equipment;
import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.ga.company.PlanType;
import com.atosenet.ga.company.db.repos.ga.company.EquipmentRepository;
import com.atosenet.ga.company.db.repos.ga.company.GaCompanyRepository;

/**
 * @author lvhao
 *
 */
@Service
@Transactional
public class EquipmentService {
	
	@Autowired
	private EquipmentRepository eRepository; 
	@Autowired
	GaCompanyRepository gaCompanyRepository;
	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: selectByOrgId 
	 * @param parseLong
	 * @return  
	 * @throws
	 *
	 */
	/*@Transactional(readOnly=true)
	public List<Equipment> selectByOrgId(long id) {
		return this.eRepository.selectByOrgId(id);
	}*/

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: save 
	 * @param equipment  
	 * @throws
	 *
	 */
	public void save(Equipment equipment) {
		// TODO Auto-generated method stub
		this.eRepository.save(equipment);
		
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: delete 
	 * @param parseLong  
	 * @throws
	 *
	 */
	public void delete(long id) {
		this.eRepository.delete(id);
		
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
	public Page<Equipment> findViewListByCompany(String companyId, Pageable pageable) {
		// TODO Auto-generated method stub
		Page<Equipment> list = eRepository.findViewListByCompany(companyId,pageable);
		List<GaCompany> companyList = gaCompanyRepository.findList();
		for (Equipment equipment : list) {
			for(GaCompany gaCompany : companyList){
				if(equipment.getCompanyId()!=null){
					if(gaCompany.getCompanyId()==equipment.getCompanyId().intValue()){
						equipment.setCompanyName(gaCompany.getName());
					}
				}
				
			}	
		}
		return list;
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByEpNum 
	 * @param epNum
	 * @return  
	 * @throws
	 *
	 */
	public Equipment findByEpNumAndCompanyId(String epNum,long companyId) {
		// TODO Auto-generated method stub
		return eRepository.findByEpNumAndCompanyId(epNum,companyId);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByEpNumAndId 
	 * @param epNum
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	public Equipment findByEpNumAndId(String epNum, Long id,Long companyId) {
		// TODO Auto-generated method stub
		return eRepository.findByEpNumAndId(epNum,id,companyId);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findById 
	 * @param parseLong
	 * @return  
	 * @throws
	 *
	 */
	public List<Equipment> findById(long id) {
		List<Equipment> list = eRepository.findById(id);
		List<GaCompany> companyList = gaCompanyRepository.findList();
		for (Equipment equipment : list) {
			for(GaCompany gaCompany : companyList){
				if(equipment.getCompanyId()!=null){
					if(gaCompany.getCompanyId()==equipment.getCompanyId().intValue()){
						equipment.setCompanyName(gaCompany.getName());
					}
				}
				
			}	
		}
		// TODO Auto-generated method stub
		return list;
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: deletes 
	 * @param list  
	 * @throws
	 *
	 */
	public void deletes(List<Equipment> list) {
		eRepository.delete(list);
		
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findViewListByCompanys 
	 * @param companyId
	 * @param epNum
	 * @param epType
	 * @param regNumber
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	public Page<Equipment> findViewListByCompanys(String companyId, String epNum, String epType, String regNumber,
			Pageable pageable) {
		Page<Equipment> list =  eRepository.findViewListByCompanys(companyId,epNum,epType,regNumber,pageable);
		List<GaCompany> companyList = gaCompanyRepository.findList();
		for (Equipment equipment : list) {
			for(GaCompany gaCompany : companyList){
				if(equipment.getCompanyId()!=null){
					if(gaCompany.getCompanyId()==equipment.getCompanyId().intValue()){
						equipment.setCompanyName(gaCompany.getName());
					}
				}
				
			}	
		}
		return list;
		
	}

	

}
