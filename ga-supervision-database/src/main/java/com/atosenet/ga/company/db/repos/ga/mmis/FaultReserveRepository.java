package com.atosenet.ga.company.db.repos.ga.mmis;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.mmis.FaultReserve;


@RepositoryRestResource(collectionResourceRel = "faultReserve", path = "faultReserve")
public interface FaultReserveRepository extends PagingAndSortingRepository<FaultReserve, Serializable> {
	
	/**
	 * 按条件分页查询故障保留单
	 * 
	 * @param no 
	 * @param aircraftModel
	 * @param reserveDate
	 * @param status
	 * @param regNumber
	 * @param pageable
	 * @return
	 */
	@Query(value="select t.* from mmis_mc_fault_reserve t where if(?1 !='',t.no = ?1,1=1) and if(?2 !='',t.aircraft_model = ?2,1=1) and if(?3 !='',t.reserve_date = ?3,1=1) and if(?4 !='',t.status = ?4,1=1) and t.aircraft_reg_no = ?5 and is_delete = 0 \n-- #pageable\n",
			countQuery=" select count(*) from mmis_mc_fault_reserve t where if(?1 !='',t.no = ?1,1=1) and if(?2 !='',t.aircraft_model = ?2,1=1) and if(?3 !='',t.reserve_date = ?3,1=1) and if(?4 !='',t.status = ?4,1=1) and t.aircraft_reg_no = ?5 and is_delete = 0",
			nativeQuery=true)
	Page<FaultReserve> findFaultListByPage(String no, String aircraftModel, String reserveDate, String status, String regNumber, Pageable pageable);
	
}
