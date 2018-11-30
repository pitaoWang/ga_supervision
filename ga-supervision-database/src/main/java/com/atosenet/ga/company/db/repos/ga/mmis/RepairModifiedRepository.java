package com.atosenet.ga.company.db.repos.ga.mmis;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.mmis.RepairModified;


@RepositoryRestResource(collectionResourceRel = "repairModified", path = "repairModified")
public interface RepairModifiedRepository extends PagingAndSortingRepository<RepairModified, Serializable> {
	
	/**
	 * 按条件分页查询重要修理及改装管理
	 * 
	 * @param no
	 * @param aircraftModel
	 * @param reserveDate
	 * @param status
	 * @param regNumber
	 * @param pageable
	 * @return
	 */
	@Query(value="select t.* from mmis_mc_repair_modified t where if(?1 !='',t.no = ?1,1=1) and if(?2 !='',t.aircraft_model = ?2,1=1) and if(?3 !='',t.type = ?3,1=1)and if(?4 !='',t.project = ?4,1=1) and t.aircraft_reg_no = ?5 and is_delete = 0 \n-- #pageable\n",
			countQuery=" select count(*) from mmis_mc_repair_modified t where if(?1 !='',t.no = ?1,1=1) and if(?2 !='',t.aircraft_model = ?2,1=1) and if(?3 !='',t.type = ?3,1=1)and if(?4 !='',t.project = ?4,1=1) and t.aircraft_reg_no = ?5 and is_delete = 0",
			nativeQuery=true)
	Page<RepairModified> findRepairModifiedListByPage(String no, String aircraftModel, String type, String project, String regNumber, Pageable pageable);
	
}
