package com.atosenet.ga.company.db.repos.ga.mmis;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.mmis.WorkReserve;


@RepositoryRestResource(collectionResourceRel = "workReserve", path = "workReserve")
public interface WorkReserveRepository extends PagingAndSortingRepository<WorkReserve, Serializable> {
	
	/**
	 * 按条件分页查询工作保留单
	 * @param regNumber
	 * @param pageable
	 * @return
	 */
	@Query(value="select t.* from mmis_mc_work_reserve t where if(?1 !='',t.no = ?1,1=1) and if(?2 !='',t.aircraft_model = ?2,1=1) and if(?3 !='',t.woc_no = ?3,1=1) and if(?4 !='',t.delay_date = ?4,1=1) and if(?5 !='',t.status = ?5,1=1) and t.aircraft_reg_no = ?6 and is_delete = 0 \n-- #pageable\n",
			countQuery=" select count(*) from mmis_mc_work_reserve t where if(?1 !='',t.no = ?1,1=1) and if(?2 !='',t.aircraft_model = ?2,1=1) and if(?3 !='',t.woc_no = ?3,1=1)and if(?4 !='',t.delay_date = ?4,1=1) and if(?5 !='',t.status = ?5,1=1) and t.aircraft_reg_no = ?6 and is_delete = 0",
			nativeQuery=true)
	Page<WorkReserve> findWorkListByPage(String no, String aircraftModel, String wocNo,String delayDate, String status,String regNumber, Pageable pageable);
	
}
