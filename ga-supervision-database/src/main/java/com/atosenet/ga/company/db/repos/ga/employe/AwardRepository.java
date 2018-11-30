package com.atosenet.ga.company.db.repos.ga.employe;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atosenet.ga.company.db.model.ga.employe.AwardRecord;


public interface AwardRepository extends PagingAndSortingRepository<AwardRecord, Long> {
	
	Page<AwardRecord> findByEmployeeId(Long employeeId, Pageable pageable);
	
	@Query("select a from AwardRecord a where employeeId= ?1")
	List<AwardRecord> findByEmployeeId(@Param("employeeId") Long employeeId);

}
