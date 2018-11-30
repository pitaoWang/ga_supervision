/**
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

import com.atosenet.ga.company.db.model.ga.company.Engine;
import com.atosenet.ga.company.db.repos.ga.company.EngineRespository;

import springfox.documentation.swagger2.mappers.LicenseMapper.LicenseTranslator;

/**
 * @author lvhao
 *
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class EngineService {
	
	@Autowired
	private EngineRespository engineRespository;
	
	/**
	 * 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: selectBySimulatorId 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	public List<Long> selectBySimulatorId(Long id) {
		// TODO Auto-generated method stub
		return this.engineRespository.selectBySimulatorId(id);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: selectPage 
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly=true)
	public Page<Engine> selectPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.engineRespository.findAll(pageable);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: save 
	 * @param engine  
	 * @throws
	 *
	 */
	public Engine save(Engine engine) {
		// TODO Auto-generated method stub
		return this.engineRespository.save(engine);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: delete 
	 * @param id
	 * @return  
	 * @throws
	 *
	 */
	public Long delete(Long id) {
		engineRespository.delete(id);
		return id;
	}
	
}
