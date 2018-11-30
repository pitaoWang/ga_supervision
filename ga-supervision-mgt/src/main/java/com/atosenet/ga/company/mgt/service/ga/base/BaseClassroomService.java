/**
 * 
 */
package com.atosenet.ga.company.mgt.service.ga.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.base.BaseClassroom;
import com.atosenet.ga.company.db.repos.ga.base.BaseClassroomRepository;

/**
 * @author lvhao
 *
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class BaseClassroomService {
	
	@Autowired
	private BaseClassroomRepository bcRepository;

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: getListByOperationBaseId 
	 * @param baseid
	 * @return  
	 * @throws
	 *
	 */
	@Transactional(readOnly=false)
	public List<BaseClassroom> getListByOperationBaseId(Long baseid) {
		// TODO Auto-generated method stub
		return this.bcRepository.getListByOperationBaseId(baseid);
	}
	
}
