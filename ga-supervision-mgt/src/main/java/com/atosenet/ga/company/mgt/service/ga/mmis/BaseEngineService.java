package com.atosenet.ga.company.mgt.service.ga.mmis;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.mmis.BaseEngine;
import com.atosenet.ga.company.db.repos.ga.mmis.BaseEngineRepository;

@Service
@Transactional
public class BaseEngineService {

	@Autowired
	private BaseEngineRepository baseEngineRepository;
	
	/**
	 * 根据航空器注册号查询发动机信息
	 * 
	 * @param regNumber
	 * @return
	 */
	public List<BaseEngine> findeListByRegNumber(String regNumber) {
		
		return baseEngineRepository.findEngineListByRegNumber(regNumber);
	}
}
