package com.atosenet.ga.company.mgt.service.ga.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.MmisBaseEngineHistory;
import com.atosenet.ga.company.db.repos.ga.company.MmisBaseEngineHistoryRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class MmisBaseEngineHistoryService {
	
	@Autowired
	MmisBaseEngineHistoryRepository mmisBaseEngineHistoryRep;
	
	
	@Transactional(readOnly = true)
	public List<MmisBaseEngineHistory> findByEngineId(String engineId){
		return mmisBaseEngineHistoryRep.findByEngineId(engineId);
	}
}
