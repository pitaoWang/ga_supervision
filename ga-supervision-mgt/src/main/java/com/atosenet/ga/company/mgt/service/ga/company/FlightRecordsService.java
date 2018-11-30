/**  
 * 
 * @Title: FlightRecordsService.java 
 * @Package com.atosenet.ga.company.mgt.service.ga.company  
 * @author xyx   
 * @date 2018年9月29日 上午11:10:35 
 * @version V1.0
 *
 */
package com.atosenet.ga.company.mgt.service.ga.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atosenet.ga.company.db.model.ga.company.FlightRecord;
import com.atosenet.ga.company.db.model.ga.company.PrepareRelease;
import com.atosenet.ga.company.db.repos.ga.company.FlightRecordsRepository;
import com.atosenet.ga.company.db.repos.ga.mmis.FlightRecordRepository;

/** 
 * TODO(这里用一句话描述这个类的作用)
 * 
 * @ClassName: FlightRecordsService 
 * @author xyx 
 * @date 2018年9月29日 上午11:10:35 
 *  
 */
@Service
public class FlightRecordsService {
	@Autowired
	private FlightRecordsRepository flightRecordsRepository;
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
	public Page<FlightRecord> findFlightRecordByInfo(String organizationId, String aircraftNo, String flightTimeBegin,
			String flightTimeEnd, String flightNature, Pageable pageable) {
		
		return flightRecordsRepository.findFlightRecordByInfo(organizationId,aircraftNo,flightTimeBegin,flightTimeEnd,flightNature,pageable);
	}

}
