package com.atosenet.ga.company.db.repos.ga.company;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atosenet.ga.company.db.model.ga.company.MmisIllegalFlight;

public interface MmisIllegalFlightRepository extends PagingAndSortingRepository<MmisIllegalFlight,Integer> {
	
	/**
	 * 根据企业ids查询违规飞行记录
	 * @param supervisionId
	 * @return
	 */
	@Query(value = "select mifr.*,ga.name from mmis_illegal_flight_record mifr left join ga_company ga on mifr.company_id = ga.company_id where mifr.company_id in (?1)",nativeQuery = true)
	List<MmisIllegalFlight> findByCompanyId(String[] companyIds);
	
	/**
	 * 根据企业ids和企业id查询违规飞行记录
	 * @param companyIds
	 * @return
	 */
	@Query(value = "select * from (select mifr.*,ga.name from mmis_illegal_flight_record mifr left join ga_company ga on mifr.company_id = ga.company_id where mifr.company_id in (?1))a where a.company_id=?2",nativeQuery=true)
	List<MmisIllegalFlight> findByCompanyIdsAndId(String[] companyIds,Long companyId);
	
	/**
	 * 根据企业ids和航空器注册号查询违规飞行记录
	 * @param companyIds
	 * @param aircraftId
	 * @return
	 */
	@Query(value = "select * from (select mifr.*,ga.name from mmis_illegal_flight_record mifr left join ga_company ga on mifr.company_id = ga.company_id where mifr.company_id in (?1))a where a.aircraft_id=?2",nativeQuery=true)
	List<MmisIllegalFlight> findByCompanyIdsAndAirRegNo(String[] companyIds,String aircraftId);
	
	/**
	 * 根据企业ids和违规时间查询违规飞行记录
	 * @param companyIds
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	@Query(value = "select * from (select mifr.*,ga.name from mmis_illegal_flight_record mifr left join ga_company ga on mifr.company_id = ga.company_id where mifr.company_id in (?1))a where a.starttime>=?2 and a.endtime<=?3",nativeQuery=true)
	List<MmisIllegalFlight> findByCompanyIdsAndDate(String[] companyIds,String starttime,String endtime);
	
	/**
	 * 根据企业ids和企业id和航空器注册号查询违规飞行记录
	 * @param companyIds
	 * @param companyId
	 * @param aircraftId
	 * @return
	 */
	@Query(value = "select * from (select mifr.*,ga.name from mmis_illegal_flight_record mifr left join ga_company ga on mifr.company_id = ga.company_id where mifr.company_id in (?1))a where a.company_id=?2 and a.aircraft_id=?3",nativeQuery=true)
	List<MmisIllegalFlight> findByCompanyIdsAndIdAndAirRegNo(String[] companyIds,Long companyId,String aircraftId);
	
	/**
	 * 根据企业ids和企业id和违规时间查询违规飞行记录
	 * @param companyIds
	 * @param companyId
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	@Query(value = "select * from (select mifr.*,ga.name from mmis_illegal_flight_record mifr left join ga_company ga on mifr.company_id = ga.company_id where mifr.company_id in (?1))a where a.company_id=?2 and (a.starttime>=?3 and a.endtime<=?4)",nativeQuery=true)
	List<MmisIllegalFlight> findByCompanyIdsAndIdAndDate(String[] companyIds,Long companyId,String starttime,String endtime);
	
	/**
	 * 根据企业ids和航空器注册号和违规时间查询违规飞行记录
	 * @param companyIds
	 * @param aircraftId
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	@Query(value = "select * from (select mifr.*,ga.name from mmis_illegal_flight_record mifr left join ga_company ga on mifr.company_id = ga.company_id where mifr.company_id in (?1))a where a.aircraft_id=?2 and (a.starttime>=?3 and a.endtime<=?4)",nativeQuery=true)
	List<MmisIllegalFlight> findByCompanyIdsAndAirRegNoAndDate(String[] companyIds,String aircraftId,String starttime,String endtime);
	
	/**
	 * 根据所有条件查询违规飞行记录
	 * @param companyIds
	 * @param companyId
	 * @param aircraftId
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	@Query(value = "select * from (select mifr.*,ga.name from mmis_illegal_flight_record mifr left join ga_company ga on mifr.company_id = ga.company_id where mifr.company_id in (?1))a where a.company_id=?2 and a.aircraft_id=?3 and (a.starttime>=?4 and a.endtime<=?5)",nativeQuery=true)
	List<MmisIllegalFlight> findByAllCase(String[] companyIds,Long companyId,String aircraftId,String starttime,String endtime);
}