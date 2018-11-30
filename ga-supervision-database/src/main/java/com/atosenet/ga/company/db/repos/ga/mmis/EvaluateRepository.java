package com.atosenet.ga.company.db.repos.ga.mmis;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.mmis.Evaluate;

@RepositoryRestResource(collectionResourceRel = "evaluate", path = "evaluate")
public interface EvaluateRepository extends PagingAndSortingRepository<Evaluate, Serializable> {

	@Query(value="select t.* from mmis_tc_as_evaluate t where if(?1 !='',t.no = ?1,1=1) and if(?2 !='',t.as_inform_no = ?2,1=1) and if(?3 !='',t.execute_way = ?3,1=1) and if(?4 !='',t.status = ?4,1=1) and t.company_id in (?5) and is_delete = 0 \n-- #pageable\n",
			countQuery=" select count(*) from mmis_tc_as_evaluate t where if(?1 !='',t.no = ?1,1=1) and if(?2 !='',t.as_inform_no = ?2,1=1) and if(?3 !='',t.execute_way = ?3,1=1) and if(?4 !='',t.status = ?4,1=1) and t.company_id in (?5) and is_delete = 0",
			nativeQuery=true)
	Page<Evaluate> findEvaluateListByPage(String no, String asInformNo, String executeWay, String status, List<Long> companyIds, Pageable pageable);
}
