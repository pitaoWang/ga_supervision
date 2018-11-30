package com.atosenet.ga.company.mgt.service.ga.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atosenet.ga.company.db.model.ga.company.FlightRunway;
import com.atosenet.ga.company.db.repos.ga.company.FlightRunwayRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly = true)
public class FlightRunwayService {

	@Autowired
	FlightRunwayRepository flightRunwayRepository;
	
	/**
	 * 增加跑道的操作
	 */
	@Transactional(readOnly=false)
    public FlightRunway sava(FlightRunway flightRunway){
		return flightRunwayRepository.save(flightRunway);
    }
	
	/**
	 *删除跑道delete
	 */
	@Transactional(readOnly=false)
    public void delete(Long id){
		flightRunwayRepository.delete(id);
    }
	/**
	 *删除跑道delete
	 */
	@Transactional(readOnly=false)
    public void deleteByAirport(Long id){
		flightRunwayRepository.deleteByAirportId(id);
    }
}
