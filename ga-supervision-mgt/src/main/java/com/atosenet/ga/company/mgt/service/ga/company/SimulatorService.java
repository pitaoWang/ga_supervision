/**  
 * 
 * @Title: SimulatorService.java 
 * @Package com.atosenet.ga.company.mgt.service.ga.company  
 * @author dgw   
 * @date 2018年8月2日 上午9:37:11 
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

import com.atosenet.ga.company.db.model.ga.company.Simulator;
import com.atosenet.ga.company.db.model.ga.company.vo.SimulatorVO;
import com.atosenet.ga.company.db.repos.ga.company.SimulatorRepository;
import com.atosenet.ga.company.db.repos.ga.company.SimulatorVORepository;
import com.atosenet.ga.company.mgt.service.ga.company.dto.SimulatorDTO;

/** 
 * TODO(模拟训练器服务层)
 * 
 * @ClassName: SimulatorService 
 * @author dgw 
 * @date 2018年8月2日 上午9:37:11 
 *  
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class SimulatorService {
	
	@Autowired
	SimulatorRepository simulatorRepository;
	
	@Autowired
	SimulatorVORepository simulatorVORepository;
	
	/**
	 * 
	 * TODO(根据企业ID获取通航企业模拟训练器列表) 
	 *
	 * @Title: findByCompanyId 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	public Page<SimulatorVO> findByCompanyId(long companyId,Pageable pageable){
		return simulatorVORepository.findByCompanyId(companyId,pageable);
		//return simulatorRepository.findByCompanyId(companyId,pageable);
	}
	
	/**
	 * 
	* 保存模拟训练器. <br/> 
	* 
	* @author Administrator 
	* @param simulator
	* @return 
	* @since JDK 1.8
	 */
	public Simulator saveSimulator(Simulator simulator) {
		
		return simulatorRepository.save(simulator);
	}
	
	/**
	 * 
	* 根据ID获取模拟训练器. <br/> 
	* 
	* @author Administrator 
	* @param id
	* @return 
	* @since JDK 1.8
	 */
	public Simulator getById(long id) {
		return simulatorRepository.findOne(id);
	}
	
	/**
	 * 
	* delete: 删除模拟训练器. <br/> 
	* 
	* @author Administrator 
	* @param id 
	* @since JDK 1.8
	 */
	public void delete(long id) {
		simulatorRepository.delete(id);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: getSimulatorById 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	public SimulatorVO getSimulatorById(long id) {
		return simulatorVORepository.getSimulatorById(id);
		// TODO Auto-generated method stub
		//return simulatorRepository.getSimulatorById(id);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: save 
	 * @param simulator  
	 * @throws
	 *
	 */
	public void save(Simulator simulator) {
		simulatorRepository.save(simulator);
		
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: delete 
	 * @param list  
	 * @throws
	 *
	 */
	public void delete(List<Simulator> list) {
		// TODO Auto-generated method stub
		simulatorRepository.delete(list);
	}
}
