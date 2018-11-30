package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atosenet.ga.company.db.model.ga.company.MmisBaseEngineHistory;

public interface MmisBaseEngineHistoryRepository extends PagingAndSortingRepository<MmisBaseEngineHistory,Integer> {
	
	@Query(value = "select * from mmis_base_engine_history where engine_id = ?1 and is_delete =0",nativeQuery = true)
	List<MmisBaseEngineHistory> findByEngineId(String engineId);
}
