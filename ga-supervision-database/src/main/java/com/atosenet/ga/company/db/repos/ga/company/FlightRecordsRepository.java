/**  
 * 
 * @Title: FlightRecordsRepository.java 
 * @Package com.atosenet.ga.company.db.repos.ga.company  
 * @author xyx   
 * @date 2018年9月29日 上午11:12:22 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.db.repos.ga.company;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.atosenet.ga.company.db.model.ga.company.FlightRecord;
import com.atosenet.ga.company.db.model.ga.company.PrepareRelease;



/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: FlightRecordsRepository 
 * @author xyx 
 * @date 2018年9月29日 上午11:12:22 
 *  
 */
public interface FlightRecordsRepository extends PagingAndSortingRepository<FlightRecord, Serializable>{

	/** 
	 * TODO(这里用一句话描述这个方法的作用) 
	 *
	 * @Title: findFlightRecordByInfo 
	 * @param organizationId
	 * @param aircraftNo
	 * @param flightTimeBegin
	 * @param flightTimeEnd
	 * @param flightNature
	 * @param pageable
	 * @return  
	 * @throws
	 *
	 */
	@Query(value="select t.* from flight_record t where if(?1 !='', FIND_IN_SET(t.C_ORGANIZATION_ID,?1), 1=1) and if(?2 !='', t.C_AIRCRAFT_NO = ?2, 1=1) and if(?3 !='', t.C_FLIGHT_TIME_BEGIN >= ?3, 1=1) and if(?4 !='', t.C_FLIGHT_TIME_END <= ?4, 1=1) and if(?5 !='', t.C_FLIGHT_NATURE = ?5, 1=1) and t.C_DELETE_FLAG = 0 \n-- #pageable\n",
			countQuery="select count(*) from flight_record t where if(?1 !='', FIND_IN_SET(t.C_ORGANIZATION_ID,?1), 1=1) and if(?2 !='', t.C_AIRCRAFT_NO = ?2, 1=1) and if(?3 !='', t.C_FLIGHT_TIME_BEGIN >= ?3, 1=1) and if(?4 !='', t.C_FLIGHT_TIME_END <= ?4, 1=1) and if(?5 !='', t.C_FLIGHT_NATURE = ?5, 1=1) and t.C_DELETE_FLAG = 0",
			nativeQuery=true)
	Page<FlightRecord> findFlightRecordByInfo(String organizationId, String aircraftNo, String flightTimeBegin,
			String flightTimeEnd, String flightNature, Pageable pageable);

}
