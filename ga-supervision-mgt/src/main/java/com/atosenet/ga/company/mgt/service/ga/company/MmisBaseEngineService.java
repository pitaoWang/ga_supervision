package com.atosenet.ga.company.mgt.service.ga.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.MmisBaseEngine;
import com.atosenet.ga.company.db.repos.ga.company.MmisBaseEngineRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class MmisBaseEngineService {
	
	@Autowired
	MmisBaseEngineRepository mmisBaseEngineRepos;
	
	/**
	 * 查询发动机引擎信息
	 * @param companyIds
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<MmisBaseEngine> findByCompanyId(String[] companyIds,Pageable pageable){
		return mmisBaseEngineRepos.findByCompanyId(companyIds, pageable);
	}
	
	/**
	 * 根据企业ids和型号分页获取发动机列表
	 * @param companyIds
	 * @param model
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<MmisBaseEngine> findByCompanyIdAndModel(String[] companyIds,String model,Pageable pageable){
		return mmisBaseEngineRepos.findByCompanyIdAndModel(companyIds, model, pageable);
	}
	
	/**
	 * 根据企业ids和航空注册号分页获取发动机列表
	 * @param companyIds
	 * @param aircraftRegNo
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<MmisBaseEngine> findByCompanyIdAndAircraftRegNo(String[] companyIds,String aircraftRegNo,Pageable pageable){
		return mmisBaseEngineRepos.findByCompanyIdAndAircraftRegNo(companyIds, aircraftRegNo, pageable);
	}
	
	/**
	 * 根据企业ids和装机状态分页获取发动机列表
	 * @param companyIds
	 * @param equipmentStatus
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<MmisBaseEngine> findByCompanyIdAndEquipmentStatus(String[] companyIds,String equipmentStatus,Pageable pageable){
		return mmisBaseEngineRepos.findByCompanyIdAndEquipmentStatus(companyIds, equipmentStatus, pageable);
	}
	
	/**
	 * 根据企业ids和型号和航空注册号分页获取发动机列表
	 * @param companyIds
	 * @param model
	 * @param aircraftRegNo
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<MmisBaseEngine> findByModelAndAircraftRegNo(String[] companyIds,String model,String aircraftRegNo,Pageable pageable){
		return mmisBaseEngineRepos.findByModelAndAircraftRegNo(companyIds, model, aircraftRegNo, pageable);
	}
	
	/**
	 * 根据企业ids和型号和装机状态分页获取发动机列表
	 * @param companyIds
	 * @param model
	 * @param equipmentStatus
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<MmisBaseEngine> findByModelAndEquipmentStatus(String[] companyIds,String model,String equipmentStatus,Pageable pageable){
		return mmisBaseEngineRepos.findByModelAndEquipmentStatus(companyIds, model, equipmentStatus, pageable);
	}
	
	/**
	 * 根据企业ids和航空注册号号装机状态分页获取发动机列表
	 * @param companyIds
	 * @param aircraftRegNo
	 * @param equipmentStatus
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<MmisBaseEngine> findByRegNoANdEquipStatus(String[] companyIds,String aircraftRegNo,String equipmentStatus,Pageable pageable){
		return mmisBaseEngineRepos.findByRegNoANdEquipStatus(companyIds, aircraftRegNo, equipmentStatus, pageable);
	}
	
	/**
	 * 根据企业ids型号航空注册号和装机状态分页获取发动机列表
	 * @param companyIds
	 * @param model
	 * @param aircraftRegNo
	 * @param equipmentStatus
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<MmisBaseEngine> findAll(String[] companyIds,String model,String aircraftRegNo,String equipmentStatus,Pageable pageable){
		return mmisBaseEngineRepos.findAll(companyIds, model, aircraftRegNo, equipmentStatus, pageable);
	}
	
}
