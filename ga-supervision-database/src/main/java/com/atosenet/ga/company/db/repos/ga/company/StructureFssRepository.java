package com.atosenet.ga.company.db.repos.ga.company;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atosenet.ga.company.db.model.ga.company.GaCompany;
import com.atosenet.ga.company.db.model.ga.company.StructureFss;

public interface StructureFssRepository extends PagingAndSortingRepository<StructureFss,Integer> {
	
	/**
	 * 
	 * @param administrationId
	 * @param pageable
	 * @param name
	 * @return
	 */
	@Query(value = "select * from structure_fss where administrationId=?1 \n-- #pageable\n",
			countQuery = "select count(*) from structure_fss where administrationId=?1",
			nativeQuery = true
			)
	Page<StructureFss> findByAdministrationId(Long administrationId,Pageable pageable);
	
	@Query(value = "select * from structure_fss where supervisionBureauId=?1 \n-- #pageable\n",
			countQuery = "select count(*) from structure_fss where supervisionBureauId=?1",
			nativeQuery = true
			)
	Page<StructureFss> findBySupervisionBureauId(Long supervisionBureauId,Pageable pageable);
	
	/**
	 * 根据监管局和名称查询
	 * @param administrationId
	 * @param name
	 * @param pageable
	 * @return
	 */
	@Query(value = "select * from structure_fss where administrationId=?1 and name like CONCAT('%',?2,'%') \n-- #pageable\n",
			countQuery = "select count(*) from structure_fss where administrationId=?1 and name like CONCAT('%',?2,'%')",
			nativeQuery = true
			)
	Page<StructureFss> findByAdministrationIdAndName(Long administrationId,String name,Pageable pageable);
	
	@Query(value = "select * from structure_fss where supervisionBureauId=?1 and name like CONCAT('%',?2,'%') \n-- #pageable\n",
			countQuery = "select count(*) from structure_fss where supervisionBureauId=?1 and name like CONCAT('%',?2,'%')",
			nativeQuery = true
			)
	Page<StructureFss> findBySupervisionBureauIdAndName(Long supervisionBureauId,String name,Pageable pageable);
	
	@Query(value = "select * from structure_fss where administrationId=?1 and code like CONCAT('%',?2,'%') \n-- #pageable\n",
			countQuery = "select count(*) from structure_fss where administrationId=?1 and code like CONCAT('%',?2,'%')",
			nativeQuery = true
			)
	Page<StructureFss> findByAdministrationIdAndCode(Long administrationId,String code,Pageable pageable);
	
	@Query(value = "select * from structure_fss where supervisionBureauId=?1 and code like CONCAT('%',?2,'%') \n-- #pageable\n",
			countQuery = "select count(*) from structure_fss where supervisionBureauId=?1 and code like CONCAT('%',?2,'%')",
			nativeQuery = true
			)
	Page<StructureFss> findBySupervisionBureauIdAndCode(Long supervisionBureauId,String code,Pageable pageable);
	
	@Query(value = "select * from structure_fss where administrationId=?1 and name like CONCAT('%',?2,'%') and code like CONCAT('%',?3,'%')\n-- #pageable\n",
			countQuery = "select count(*) from structure_fss where administrationId=?1 and name like CONCAT('%',?2,'%') and code like CONCAT('%',?3,'%')",
			nativeQuery = true
			)
	Page<StructureFss> findByAdministrationIdAndNameAndCode(Long administrationId,String name,String code,Pageable pageable);

	
	@Query(value = "select * from structure_fss where supervisionBureauId=?1 and name like CONCAT('%',?2,'%') and code like CONCAT('%',?3,'%')\n-- #pageable\n",
			countQuery = "select count(*) from structure_fss where supervisionBureauId=?1 and name like CONCAT('%',?2,'%') and code like CONCAT('%',?3,'%')",
			nativeQuery = true
			)
	Page<StructureFss> findBySupervisionBureauIdAndNameAndCode(Long supervisionBureauId,String name,String code,Pageable pageable);


	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findBySupervisionBureauId 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	List<StructureFss> findBySupervisionBureauId(Long companyId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findByAdministrationId 
	 * @param companyId
	 * @return  
	 * @throws
	 *
	 */
	List<StructureFss> findByAdministrationId(Long companyId);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findList 
	 * @return  
	 * @throws
	 *
	 */
	@Query("select t from StructureFss t")
	List<StructureFss> findList();

}
