package com.atosenet.ga.company.db.repos.ga.employe.vo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;




import com.atosenet.ga.company.db.model.ga.employe.vo.FlightImplementRecordAndAircraftDTO;


@RepositoryRestResource(collectionResourceRel = "frr", path = "frr")
public interface FlightImplementRecordRepositoryVO extends PagingAndSortingRepository<FlightImplementRecordAndAircraftDTO, Long>{
	 @Query(value="select f.*,a.aircraft_model from (select f.* from flight_implement_record f where f.captain_id=?1 or f.copilot_id=?1) f,aircraft a where  f.aircraft_id=a.aircraft_id ;",
 			nativeQuery=true)
	List<FlightImplementRecordAndAircraftDTO> selectByEmployeeId(Long employee);
	 
	 
	 @Query(value="select f.*,a.aircraft_model from (select f.* from flight_implement_record f where f.captain_id=?1 or f.copilot_id=?1) f,aircraft a where  f.aircraft_id=a.aircraft_id  order by fly_date desc  \n-- #pageable\n",
			 countQuery="select count(*) from (select f.* from flight_implement_record f where f.captain_id=?1 or f.copilot_id=?1) f,aircraft a where  f.aircraft_id=a.aircraft_id",	nativeQuery=true)
	 Page<FlightImplementRecordAndAircraftDTO> selectPageByEmployeeId(Long employee,Pageable pageable);
	 

	 
	 @Query(value="select f.*,a.aircraft_model from flight_implement_record  f,aircraft a  where f.aircraft_id=a.aircraft_id and f.id=?1",
	 			nativeQuery=true)
		FlightImplementRecordAndAircraftDTO selectById(Long id);
	 
	 @Query(value="select f.*,a.aircraft_model from flight_implement_record  f,aircraft a  where f.aircraft_id=a.aircraft_id  and  f.aircraft_id=?1",
 			nativeQuery=true)
	 List<FlightImplementRecordAndAircraftDTO> selectByAircraftId(Long aircraft);
	 
	 
	 @Query(value="select f.*,a.aircraft_model from flight_implement_record  f,aircraft a  where f.aircraft_id=a.aircraft_id  and  f.aircraft_id=?1 order by fly_date desc \n-- #pageable\n",
			 countQuery="select count(*) from flight_implement_record  f,aircraft a  where f.aircraft_id=a.aircraft_id  and  f.aircraft_id=?1",nativeQuery=true)
	 Page<FlightImplementRecordAndAircraftDTO> selectpageByAircraftId(Long aircraft,Pageable pageable);
	
	 @Query(value="select f.*,a.aircraft_model from flight_implement_record  f,aircraft a  where f.aircraft_id=a.aircraft_id and f.company_id=?1",
	 			nativeQuery=true)
		List<FlightImplementRecordAndAircraftDTO> findFlightImplementRecordsByCompanyId(int companydId);
	 @Query(value="select f.*,a.aircraft_model from flight_implement_record  f,aircraft a  where f.aircraft_id=a.aircraft_id and f.company_id=?1 order by fly_date desc \n-- #pageable\n",
			 countQuery="select count(*) from flight_implement_record  f,aircraft a  where f.aircraft_id=a.aircraft_id and f.company_id=?1",	nativeQuery=true)
		Page<FlightImplementRecordAndAircraftDTO> findFlightpageImplementRecordsByCompanyId(int companydId,Pageable pageable);
	 //根据起飞时刻来查询飞行记录
	 @Query(value="select f.*,a.aircraft_model from flight_implement_record  f,aircraft a  where f.aircraft_id=a.aircraft_id and f.company_id=?1 and fly_date>=?2 and fly_date<=?3 order by fly_date desc ",
	 			nativeQuery=true)
		List<FlightImplementRecordAndAircraftDTO> findFlightImplementRecordsByCompanyIdAndTakeoffTime(int companydId,String takeoffTime,String landTime);
}
