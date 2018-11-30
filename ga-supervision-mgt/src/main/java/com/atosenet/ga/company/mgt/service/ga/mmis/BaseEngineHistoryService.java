package com.atosenet.ga.company.mgt.service.ga.mmis;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.mmis.BaseEngineHistory;
import com.atosenet.ga.company.db.repos.ga.mmis.BaseEngineHistoryRepository;

@Service
@Transactional
public class BaseEngineHistoryService {

	@Autowired
	private BaseEngineHistoryRepository baseEngineHistoryRepository;
	
	/**
	 * 根据发动机ID查询拆装历史记录
	 * 
	 * @param regNumber
	 * @return
	 */
	public List<BaseEngineHistory> findHistoryListByEngineId(String engineId) {
		
		return baseEngineHistoryRepository.findHistoryListByEngineId(engineId);
	}
}
