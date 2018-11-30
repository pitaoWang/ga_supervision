package com.atosenet.ga.company.db.repos.ga.company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atosenet.ga.company.db.model.ga.company.MmisBaseEngine;

public interface MmisBaseEngineRepository extends PagingAndSortingRepository<MmisBaseEngine,Integer> {
	
	@Query(value = "select mbe.*,ga.name from mmis_base_engine mbe left join ga_company ga on mbe.company_id = ga.company_id where mbe.company_id in (?1) \n-- #pageable\n",
			countQuery = "select count(*) from mmis_base_engine mbe left join ga_company ga on mbe.company_id = ga.company_id where mbe.company_id in (?1)",
			nativeQuery = true
			)
	Page<MmisBaseEngine> findByCompanyId(String[] companyIds,Pageable pageable);
	
	@Query(value = "select mbe.* ,ga.name from ga_company ga right join mmis_base_engine mbe on ga.company_id = mbe.company_id where mbe.company_id in (?1) and mbe.model=?2 \n-- #pageable\n",
			countQuery = "select count(*) from ga_company ga right join mmis_base_engine mbe on ga.company_id = mbe.company_id where mbe.company_id in (?1) and mbe.model=?2",
			nativeQuery = true
			)
	Page<MmisBaseEngine> findByCompanyIdAndModel(String[] companyIds,String model,Pageable pageable);
	
	@Query(value = "select mbe.*,ga.name from ga_company ga right join mmis_base_engine mbe on ga.company_id = mbe.company_id where mbe.company_id in (?1) and mbe.aircraft_reg_no=?2 \n-- #pageable\n",
			countQuery = "select count(*) from ga_company ga right join mmis_base_engine mbe on ga.company_id = mbe.company_id where mbe.company_id in (?1) and mbe.aircraft_reg_no=?2",
			nativeQuery = true
			)
	Page<MmisBaseEngine> findByCompanyIdAndAircraftRegNo(String[] companyIds,String aircraftRegNo,Pageable pageable);
	
	@Query(value = "select mbe.*,ga.name from ga_company ga right join mmis_base_engine mbe on ga.company_id = mbe.company_id where mbe.company_id in (?1) and mbe.equipment_status=?2 \n-- #pageable\n",
			countQuery = "select count(*) from ga_company ga right join mmis_base_engine mbe on ga.company_id = mbe.company_id where mbe.company_id in (?1) and mbe.equipment_status=?2",
			nativeQuery = true
			)
	Page<MmisBaseEngine> findByCompanyIdAndEquipmentStatus(String[] companyIds,String equipmentStatus,Pageable pageable);
	
	@Query(value = "select mbe.* ,ga.name from ga_company ga right join mmis_base_engine mbe on ga.company_id = mbe.company_id where mbe.company_id in (?1) and mbe.model=?2 and mbe.aircraft_reg_no=?3 \n-- #pageable\n",
			countQuery = "select count(*) from ga_company ga right join mmis_base_engine mbe on ga.company_id = mbe.company_id where mbe.company_id in (?1) and mbe.model=?2 and mbe.aircraft_reg_no=?3",
			nativeQuery = true
			)
	Page<MmisBaseEngine> findByModelAndAircraftRegNo(String[] companyIds,String model,String aircraftRegNo,Pageable pageable);
	
	@Query(value = "select mbe.*,ga.name from ga_company ga right join mmis_base_engine mbe on ga.company_id = mbe.company_id where mbe.company_id in (?1) and mbe.model=?2 and mbe.equipment_status=?3 \n-- #pageable\n",
			countQuery = "select count(*) from ga_company ga right join mmis_base_engine mbe on ga.company_id = mbe.company_id where mbe.company_id in (?1) and mbe.model=?2 and mbe.equipment_status=?3",
			nativeQuery = true
			)
	Page<MmisBaseEngine> findByModelAndEquipmentStatus(String[] companyIds,String model,String equipmentStatus,Pageable pageable);
	
	@Query(value = "select mbe.*,ga.name from ga_company ga right join mmis_base_engine mbe on ga.company_id = mbe.company_id where mbe.company_id in (?1) and mbe.aircraft_reg_no=?2 and mbe.equipment_status=?3 \n-- #pageable\n",
			countQuery = "select count(*) from ga_company ga right join mmis_base_engine mbe on ga.company_id = mbe.company_id where mbe.company_id in (?1) and mbe.aircraft_reg_no=?2 and mbe.equipment_status=?3",
			nativeQuery = true
			)
	Page<MmisBaseEngine> findByRegNoANdEquipStatus(String[] companyIds,String aircraftRegNo,String equipmentStatus,Pageable pageable);
	
	@Query(value = "select mbe.*,ga.name from ga_company ga right join mmis_base_engine mbe on ga.company_id = mbe.company_id where mbe.company_id in (?1) and mbe.model=?2 and mbe.aircraft_reg_no=?3 and mbe.equipment_status=?4 \n-- #pageable\n",
			countQuery = "select count(*) from ga_company ga right join mmis_base_engine mbe on ga.company_id = mbe.company_id where mbe.company_id in (?1) and mbe.model=?2 and mbe.aircraft_reg_no=?3 and mbe.equipment_status=?4",
			nativeQuery = true
			)
	Page<MmisBaseEngine> findAll(String[] companyIds,String model,String aircraftRegNo,String equipmentStatus,Pageable pageable);
}
