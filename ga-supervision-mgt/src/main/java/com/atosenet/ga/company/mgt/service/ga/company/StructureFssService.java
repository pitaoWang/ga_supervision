package com.atosenet.ga.company.mgt.service.ga.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.StructureFss;
import com.atosenet.ga.company.db.repos.ga.company.StructureFssRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class StructureFssService {
	
	@Autowired
	StructureFssRepository structureFssReps;
	
	/**
	 * 根据管理局分页查询飞行服务站列表
	 * @param administrationId
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<StructureFss> findByAdministrationId(Long administrationId,Pageable pageable){
		return structureFssReps.findByAdministrationId(administrationId, pageable);
	}
	
	/**
	 * 根据监管局查询飞行服务站列表
	 * @param supervisionBureauId
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<StructureFss> findBySupervisionBureauId(Long supervisionBureauId,Pageable pageable){
		return structureFssReps.findBySupervisionBureauId(supervisionBureauId, pageable);
	}
	
	/**
	 * 根据监管局和名称分页查询飞行服务站列表(条件查询)
	 * @param administrationId
	 * @param name
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<StructureFss> findByAdministrationIdAndName(Long administrationId,String name,Pageable pageable){
		return structureFssReps.findByAdministrationIdAndName(administrationId, name, pageable);
	}
	
	/**
	 * 根据监管局和名称查询飞行服务站列表
	 * @param supervisionBureauId
	 * @param name
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<StructureFss> findBySupervisionBureauIdAndName(Long supervisionBureauId,String name,Pageable pageable){
		return structureFssReps.findBySupervisionBureauIdAndName(supervisionBureauId, name, pageable);
	}
	
	/**
	 * 根据管理局和飞行服务编号查询飞行服务站列表
	 * @param administrationId
	 * @param code
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<StructureFss> findByAdministrationIdAndCode(Long administrationId,String code,Pageable pageable){
		return structureFssReps.findByAdministrationIdAndCode(administrationId, code, pageable);
	}
	
	/**
	 * 根据监管局和飞行服务编号查询飞行服务站列表
	 * @param supervisionBureauId
	 * @param code
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<StructureFss> findBySupervisionBureauIdAndCode(Long supervisionBureauId,String code,Pageable pageable){
		return structureFssReps.findBySupervisionBureauIdAndCode(supervisionBureauId, code, pageable);
	}
	
	/**
	 * 根据管理局和飞行服务名称和编号查询列表
	 * @param administrationId
	 * @param name
	 * @param code
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<StructureFss> findByAdministrationIdAndNameAndCode(Long administrationId,String name,String code,Pageable pageable){
		return structureFssReps.findByAdministrationIdAndNameAndCode(administrationId, name, code, pageable);
	}
	
	/**
	 * 根据监管局和飞行服务器名称和编号查询列表
	 * @param supervisionBureauId
	 * @param name
	 * @param code
	 * @param pageable
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<StructureFss> findBySupervisionBureauIdAndNameAndCode(Long supervisionBureauId,String name,String code,Pageable pageable){
		return structureFssReps.findBySupervisionBureauIdAndNameAndCode(supervisionBureauId, name, code, pageable);
	}
}
