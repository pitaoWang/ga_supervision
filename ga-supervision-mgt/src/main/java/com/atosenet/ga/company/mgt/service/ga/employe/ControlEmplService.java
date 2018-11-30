/**
 * 
 */
package com.atosenet.ga.company.mgt.service.ga.employe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.employe.ControlEmpl;
import com.atosenet.ga.company.db.repos.ga.employe.ControlEmplRepository;

/**
 * @author lvhao
 *
 */
@Service
@Transactional
public class ControlEmplService {
	
	@Autowired
	private ControlEmplRepository controlEmplRepository;

	/**
	 * @return  
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: save 
	 * @param controlEmpl  
	 * @throws
	 *
	 */
	@Transactional(readOnly=false)
	public ControlEmpl save(ControlEmpl controlEmpl) {
		return this.controlEmplRepository.save(controlEmpl);
		
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: selectById 
	 * @param parseLong
	 * @return  
	 * @throws
	 *
	 */
	public ControlEmpl selectById(long id) {
		// TODO Auto-generated method stub
		return this.controlEmplRepository.findOne(id);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: selectPage 
	 * @param pageable  
	 * @throws
	 *
	 */
	public Page<ControlEmpl> selectPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return this.controlEmplRepository.findAll(pageable);
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: deleteById 
	 * @param id  
	 * @throws
	 *
	 */
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		this.controlEmplRepository.delete(id);
		
	}

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: getByEmployeeId 
	 * @param parseLong
	 * @return  
	 * @throws
	 *
	 */
	public ControlEmpl getByEmployeeId(long employeeId) {
		// TODO Auto-generated method stub
		return this.controlEmplRepository.findByEmployeeId(employeeId);
	}

}
