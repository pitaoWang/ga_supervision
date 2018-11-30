package com.atosenet.ga.company.db.repos.ga.company;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atosenet.ga.company.db.model.ga.company.AircraftFlySumView;



@RepositoryRestResource(collectionResourceRel = "acfr", path = "acfr")
public interface AircraftFlySumRepository extends PagingAndSortingRepository<AircraftFlySumView, Long> {
	@Query(value="select t.aircraft_model,sum(t.sum_time) as fly_sum_time from v_flight_time_as_aircraft as t where FIND_IN_SET(t.company_id,:companyidds) and t.fly_date between :startDate and :endDate group by t.aircraft_model order by fly_sum_time desc",
	nativeQuery=true)
	List<Object> countIdAndTime(@Param("companyidds") String companyidds,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
	
	@Query(value="select * from v_flight_time_as_aircraft t where FIND_IN_SET(t.company_id,:companyidds) and t.fly_date between :startDate and :endDate order by sum_time desc \n-- #pageable\n",
		   countQuery="select count(*) from v_flight_time_as_aircraft t where FIND_IN_SET(t.company_id,:companyidds) and t.fly_date between :startDate and :endDate order by sum_time desc",
			nativeQuery=true)
	Page<AircraftFlySumView> findAircraftFlySumList(@Param("companyidds") String companyidds ,@Param("startDate") Date startDate,@Param("endDate") Date endDate,Pageable pageable);

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: countIdAndTimes 
	 * @param companyIds
	 * @param sdate
	 * @param edate
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="select t.aircraft_model,sum(t.sum_time) as fly_sum_time from v_flight_time_as_aircraft as t where FIND_IN_SET(t.company_id,:companyidds) and t.fly_date between :startDate and :endDate group by t.aircraft_model order by fly_sum_time desc LIMIT 0,10",
			nativeQuery=true)
	List<Object> countIdAndTimes(@Param("companyidds") String companyidds,@Param("startDate") Date startDate,@Param("endDate") Date endDate);
}
